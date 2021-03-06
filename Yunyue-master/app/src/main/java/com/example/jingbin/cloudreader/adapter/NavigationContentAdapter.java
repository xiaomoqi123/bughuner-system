package com.example.jingbin.cloudreader.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.baseadapter.BaseRecyclerViewAdapter;
import com.example.jingbin.cloudreader.base.baseadapter.BaseRecyclerViewHolder;
import com.example.jingbin.cloudreader.bean.wanandroid.ArticlesBean;
import com.example.jingbin.cloudreader.databinding.ItemNavigationContentBinding;
import com.example.jingbin.cloudreader.databinding.ItemNavigationTitleBinding;
import com.example.jingbin.cloudreader.utils.CommonUtils;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;

/**
 * Created by jingbin on 2019/7/14.
 */

public class NavigationContentAdapter extends BaseRecyclerViewAdapter<ArticlesBean> {

    private static final int TYPE_TITLE = 0x001;
    private static final int TYPE_CONTENT = 0x002;

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return new ViewTitleHolder(parent, R.layout.item_navigation_title);
            case TYPE_CONTENT:
                return new ViewContentHolder(parent, R.layout.item_navigation_content);
            default:
                return new ViewContentHolder(parent, R.layout.item_navigation_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(getData().get(position).getNavigationName())) {
            return TYPE_TITLE;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    /**
                     * ??????GridLayoutManager???getSpanSize???????????????????????????item?????????
                     * ???????????????4????????????GridLayoutManager
                     * new GridLayoutManager(getActivity(),6,GridLayoutManager.VERTICAL,false);
                     * ?????????6???????????????????????????????????????????????????????????????6????????????????????????1??????????????????3 ???2 ???????????????????????????3?????????1/2??????????????????????????????2????????????2?????????1/3??????????????????????????????3???
                     * */
                    switch (type) {
                        case TYPE_TITLE:
                            // title???????????????
                            return gridManager.getSpanCount();
                        case TYPE_CONTENT:
                            // ???????????????2???
                            return 3;
                        default:
                            //????????????2???
                            return 3;
                    }
                }
            });
        }
    }

    private class ViewTitleHolder extends BaseRecyclerViewHolder<ArticlesBean, ItemNavigationTitleBinding> {

        ViewTitleHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final ArticlesBean dataBean, final int position) {
            if (dataBean != null) {
                binding.setBean(dataBean);
                if (position == 0) {
                    binding.viewLine.setVisibility(View.GONE);
                } else {
                    binding.viewLine.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    private class ViewContentHolder extends BaseRecyclerViewHolder<ArticlesBean, ItemNavigationContentBinding> {

        ViewContentHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindViewHolder(final ArticlesBean dataBean, final int position) {
            if (dataBean != null) {
                binding.setBean(dataBean);
                binding.tvNaviTag.setTextColor(CommonUtils.randomColor());
                binding.tvNaviTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WebViewActivity.loadUrl(view.getContext(), dataBean.getLink(), dataBean.getTitle());
                    }
                });
            }
        }
    }
}
