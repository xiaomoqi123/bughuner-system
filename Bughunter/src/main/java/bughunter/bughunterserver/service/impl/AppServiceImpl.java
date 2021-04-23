package bughunter.bughunterserver.service.impl;

import bughunter.bughunterserver.model.entity.*;
import bughunter.bughunterserver.model.repository.*;
import bughunter.bughunterserver.service.AppService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.AppBaseInfoVO;
import bughunter.bughunterserver.vo.AppMemberVO;
import bughunter.bughunterserver.vo.BugStatisticInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppBaseRepository appBaseRepository;

    @Autowired
    AppMemberRepository appMemberRepository;

    @Autowired
    AppVerRepository appVerRepository;

    @Autowired
    BugRepository bugRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean deleteApp(String appKey) {
        try {
            appBaseRepository.delete(appKey);
            return true;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public String addApp(AppBaseInfo appBaseInfo, int uId) {
        //TODO 生成setAppSecret,同步
        String appKey = UUID.randomUUID().toString().replaceAll("-", "");
        appBaseInfo.setAppKey(appKey);
        appBaseInfo.setAppSecret(UUID.randomUUID().toString().replaceAll("-", ""));
        AppMember appMember = new AppMember();
        appMember.setAppKey(appKey);
        appMember.setUserId(uId);
        appMember.setType(Constants.CREATER);
        appBaseInfo = appBaseRepository.save(appBaseInfo);
        appMemberRepository.save(appMember);
        return appBaseInfo.getAppKey();
    }

    @Override
    public Boolean modifyApp(String appKey, JSONObject jsonObject) {
        try {
            AppBaseInfo appBaseInfo = appBaseRepository.findOne(appKey);
            if (appBaseInfo == null)
                return false;
            appBaseRepository.save(appBaseInfo);
            appBaseInfo.setType(jsonObject.getString(Constants.TYPE));
            appBaseInfo.setName(jsonObject.getString(Constants.NAME));
            appBaseInfo.setSDKVersion(jsonObject.getDouble(Constants.SDK_VERSION));
            appBaseInfo.setmTime(new Date(new java.util.Date().getTime()));
            appBaseRepository.save(appBaseInfo);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public AppBaseInfoVO findApp(String appKey) {
        if (!appBaseRepository.exists(appKey))
            return null;
        AppBaseInfo appBaseInfo = appBaseRepository.findOne(appKey);
        return new AppBaseInfoVO(appBaseInfo);
    }

    @Override
    public List<AppBaseInfoVO> findAllAppsByUserId(int uid) {
        List<AppMember> appMemberList = appMemberRepository.findAllByUserId(uid);
        if (appMemberList.size() == 0)
            return null;

        List<AppBaseInfoVO> appBaseInfoVOList = new ArrayList<AppBaseInfoVO>(appMemberList.size());

        for (AppMember appMember : appMemberList) {
            AppBaseInfo appBaseInfo = appBaseRepository.findOne(appMember.getAppKey());
            System.out.println(appBaseInfo.getName());
            AppBaseInfoVO appBaseInfoVO = new AppBaseInfoVO(appBaseInfo);
            appBaseInfoVOList.add(appBaseInfoVO);
        }

        return appBaseInfoVOList;
    }

    @Override
    public List<AppBaseInfoVO> findAllApps() {
        List<AppBaseInfo> appBaseInfoList = appBaseRepository.findAll();
        if (appBaseInfoList.size() == 0)
            return null;
        List<AppBaseInfoVO> appBaseInfoVOList = new ArrayList<AppBaseInfoVO>(appBaseInfoList.size());
        for (AppBaseInfo appBaseInfo : appBaseInfoList) {
            AppBaseInfoVO appBaseInfoVO = new AppBaseInfoVO(appBaseInfo);
            appBaseInfoVO.setBugSubmitAmount(bugRepository.countAllByAppKey(appBaseInfo.getAppKey()));
            appBaseInfoVO.setVersionAmount(appVerRepository.countAllByAppKey(appBaseInfo.getAppKey()));
            appBaseInfoVOList.add(appBaseInfoVO);
        }
        return appBaseInfoVOList;
    }

    @Override
    public Boolean deleteMember(String appKey, int uId) {
        try {
            appMemberRepository.delete(new AppMemberKeys(appKey, uId));
            return true;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public Boolean addMember(JSONObject jsonObject) {
        AppMember appMember = new AppMember();
        appMember.setAppKey(jsonObject.getString(Constants.APP_KEY));
        appMember.setType(Constants.ORDINARY_MEMBER);
        User user = userRepository.findFirstUserByEmail(jsonObject.getString(Constants.EMAIL));
        if (user == null)
            return false;
        appMember.setUserId(user.getId());
        if (appMemberRepository.exists(new AppMemberKeys(appMember.getAppKey(), appMember.getUserId()))) {
            return false;
        } else {
            appMemberRepository.save(appMember);
        }
        return true;
    }

    @Override
    public List<String> findAppVersionByAppKey(String appKey) {
        List<String> list = new LinkedList<String>();
        for (AppVersion appVersion : appVerRepository.findAllByAppKey(appKey)) {
            list.add(appVersion.getAppVersion());
        }
        return list;
    }

    @Override
    public List<AppMemberVO> findAllMemberByAppKey(String appKey) {
        List<AppMember> appMemberList = appMemberRepository.findAllByAppKey(appKey);
        if (appMemberList.size() == 0)
            return null;
        List<AppMemberVO> appMemberVOList = new ArrayList<AppMemberVO>(appMemberList.size());

        for (AppMember appMember : appMemberList) {
            AppMemberVO appMemberVO = new AppMemberVO(appMember.getType(), userRepository.getOne(appMember.getUserId()));
            appMemberVOList.add(appMemberVO);
        }
        return appMemberVOList;
    }


}
