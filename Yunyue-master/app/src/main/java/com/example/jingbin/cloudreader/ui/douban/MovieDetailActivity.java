package com.example.jingbin.cloudreader.ui.douban;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jingbin.cloudreader.BugHunter.FloatingActionButton;
import com.example.jingbin.cloudreader.BugHunter.ScreenShotListenManager;
import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.adapter.MovieDetailAdapter;
import com.example.jingbin.cloudreader.bean.MovieDetailBean;
import com.example.jingbin.cloudreader.bean.moviechild.SubjectsBean;
import com.example.jingbin.cloudreader.databinding.ActivityMovieDetailBinding;
import com.example.jingbin.cloudreader.http.HttpClient;
import com.example.jingbin.cloudreader.utils.CommonUtils;
import com.example.jingbin.cloudreader.utils.DebugUtil;
import com.example.jingbin.cloudreader.utils.StringFormatUtil;
import com.example.jingbin.cloudreader.view.MyNestedScrollView;
import com.example.jingbin.cloudreader.view.statusbar.StatusBarUtil;
import com.example.jingbin.cloudreader.view.test.StatusBarUtils;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.example.jingbin.cloudreader.view.statusbar.StatusBarUtil.getStatusBarHeight;

/**
 * ???????????????{@link OneMovieDetailActivity} ?????????
 * ?????????
 * 1???????????????????????????titlebar,?????????????????????
 * 2???titlebar????????????????????????????????????????????????????????????????????????-titlebar?????????????????????????????????????????????titlebar????????????
 * 3??????????????????scrollview???????????????????????????????????????????????????????????? ??????titlebar???????????????????????????????????????????????????????????????????????????????????????
 * 4????????????????????????????????????
 */
public class MovieDetailActivity extends AppCompatActivity {

    private int slidingDistance;
    private SubjectsBean subjectsBean;
    private ActivityMovieDetailBinding binding;
    private String TAG = "---MovieDetailActivity:";
    // ?????????????????????????????????
    private int imageBgHeight;
    // ????????????url
    private String mMoreUrl;
    // ??????name
    private String mMovieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (SubjectsBean) getIntent().getSerializableExtra("bean");
        }

        initSlideShapeTheme();

        // ????????????
        setTitleBar();
        setHeaderData(subjectsBean);

        loadMovieDetail();
        final FloatingActionButton button=new FloatingActionButton(this);
        ScreenShotListenManager manager = ScreenShotListenManager.newInstance(this);
        manager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    public void onShot(String imagePath) {
                        button.camera(FloatingActionButton.getCurrentActivity());
                    }
                }
        );
        manager.startListen();


    }

    private void loadMovieDetail() {
        // ?????????...
//        binding.include.tvOneCity.setText("????????????/?????????");
//        binding.include.tvOneDay.setText("???????????????");
//        binding.tvOneTitle.setText("");
        HttpClient.Builder.getDouBanService().getMovieDetail(subjectsBean.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailBean>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(final MovieDetailBean movieDetailBean) {
                        // ????????????
                        binding.include.tvOneDay.setText("???????????????" + movieDetailBean.getYear());
                        // ????????????
                        binding.include.tvOneCity.setText("????????????/?????????" + StringFormatUtil.formatGenres(movieDetailBean.getCountries()));
                        binding.include.setMovieDetailBean(movieDetailBean);
                        binding.setMovieDetailBean(movieDetailBean);

                        mMoreUrl = movieDetailBean.getAlt();
                        mMovieName = movieDetailBean.getTitle();

                        transformData(movieDetailBean);
                    }
                });

    }

    /**
     * ????????????????????????
     */
    private void transformData(final MovieDetailBean movieDetailBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < movieDetailBean.getDirectors().size(); i++) {
                    movieDetailBean.getDirectors().get(i).setType("??????");
                }
                for (int i = 0; i < movieDetailBean.getCasts().size(); i++) {
                    movieDetailBean.getCasts().get(i).setType("??????");
                }

                MovieDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(movieDetailBean);
                    }
                });
            }
        }).start();
    }

    /**
     * ????????????&??????adapter
     */
    private void setAdapter(MovieDetailBean movieDetailBean) {
        binding.xrvCast.setVisibility(View.VISIBLE);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MovieDetailActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.xrvCast.setLayoutManager(mLayoutManager);
        binding.xrvCast.setPullRefreshEnabled(false);
        binding.xrvCast.setLoadingMoreEnabled(false);
        // ??????????????????????????????
        binding.xrvCast.setNestedScrollingEnabled(false);
        binding.xrvCast.setHasFixedSize(false);

        MovieDetailAdapter mAdapter = new MovieDetailAdapter();
        mAdapter.addAll(movieDetailBean.getDirectors());
        mAdapter.addAll(movieDetailBean.getCasts());
        binding.xrvCast.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail, menu);
        return true;
    }

    private void setHeaderData(SubjectsBean positionData) {
        binding.include.setSubjectsBean(positionData);
        // ????????????UI???????????????
        binding.include.executePendingBindings();
    }

    /**
     * toolbar??????
     */
    private void setTitleBar() {
        setSupportActionBar(binding.titleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //????????????Title??????
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        // ????????????????????????
        binding.titleToolBar.setTitleTextAppearance(this, R.style.ToolBar_Title);
        binding.titleToolBar.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);

        binding.titleToolBar.setTitle(subjectsBean.getTitle());
        binding.titleToolBar.setSubtitle(String.format("?????????%s", StringFormatUtil.formatName(subjectsBean.getCasts())));

        binding.titleToolBar.inflateMenu(R.menu.movie_detail);
        binding.titleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        binding.titleToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.titleToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionbar_more:// ????????????
                        WebViewActivity.loadUrl(MovieDetailActivity.this, mMoreUrl, mMovieName);
                        break;
                }
                return false;
            }
        });
    }


    /**
     * ?????????????????????
     */
    private void initSlideShapeTheme() {
        setImgHeaderBg();

        // toolbar ??????
        int toolbarHeight = binding.titleToolBar.getLayoutParams().height;
        Log.i(TAG, "toolbar height:" + toolbarHeight);
        final int headerBgHeight = toolbarHeight + getStatusBarHeight(this);
        Log.i(TAG, "headerBgHeight:" + headerBgHeight);

        // ?????????????????????????????????????????????????????????titlebar+statusbar????????????
        ViewGroup.LayoutParams params = binding.ivTitleHeadBg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) binding.ivTitleHeadBg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

        binding.ivTitleHeadBg.setImageAlpha(0);
        StatusBarUtils.setTranslucentImageHeader(this, 0, binding.titleToolBar);

        // ?????????????????????????????????????????????(???????????????????????????????????????)
        if (binding.include.imgItemBg != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.include.imgItemBg.getLayoutParams();
            layoutParams.setMargins(0, -StatusBarUtil.getStatusBarHeight(this), 0, 0);
        }

        ViewGroup.LayoutParams imgItemBgparams = binding.include.imgItemBg.getLayoutParams();
        // ??????????????????????????????
        imageBgHeight = imgItemBgparams.height;

        // ??????
        initScrollViewListener();

        initNewSlidingParams();
    }


    /**
     * ??????titlebar??????
     */
    private void setImgHeaderBg() {
        if (subjectsBean != null) {

            // ?????????????????? ?????? ?????????12,5  23,4
            Glide.with(this).load(subjectsBean.getImages().getLarge())
                    .error(R.drawable.stackblur_default)
                    .transform(new BlurTransformation(25, 5))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            binding.titleToolBar.setBackgroundColor(Color.TRANSPARENT);
                            binding.ivTitleHeadBg.setImageAlpha(0);
                            binding.ivTitleHeadBg.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(binding.ivTitleHeadBg);
        }
    }


    private void initScrollViewListener() {
        // ????????????23??????
        binding.nsvScrollview.setOnScrollChangeListener(new MyNestedScrollView.ScrollInterface() {
            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
    }

    private void initNewSlidingParams() {
        int titleBarAndStatusHeight = (int) (CommonUtils.getDimens(R.dimen.nav_bar_height) + getStatusBarHeight(this));
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (CommonUtils.getDimens(R.dimen.nav_bar_height_more));
    }

    /**
     * ??????????????????????????????Header??????
     */
    private void scrollChangeHeader(int scrolledY) {

        DebugUtil.error("---scrolledY:  " + scrolledY);
        DebugUtil.error("-----slidingDistance: " + slidingDistance);

        if (scrolledY < 0) {
            scrolledY = 0;
        }
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);

        DebugUtil.error("----alpha:  " + alpha);
        Drawable drawable = binding.ivTitleHeadBg.getDrawable();

        if (scrolledY <= slidingDistance) {
            // title???????????????
            drawable.mutate().setAlpha((int) (alpha * 255));
            binding.ivTitleHeadBg.setImageDrawable(drawable);
        } else {
            drawable.mutate().setAlpha(255);
            binding.ivTitleHeadBg.setImageDrawable(drawable);
        }
    }

    /**
     * @param context      activity
     * @param positionData bean
     * @param imageView    imageView
     */
    public static void start(Activity context, SubjectsBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("bean", positionData);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//???xml????????????
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
