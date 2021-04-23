package bughunter.bughunterserver.service.impl;

import bughunter.bughunterserver.model.entity.*;
import bughunter.bughunterserver.model.repository.*;
import bughunter.bughunterserver.service.BugService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.*;
import org.hibernate.annotations.Synchronize;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    BugRepository bugRepository;

    @Autowired
    BugDeviceRepository bugDeviceRepository;

    @Autowired
    BugConsoleLogRepository bugConsoleLogRepository;

    @Autowired
    BugOperateStepRepository bugOperateStepRepository;

    @Autowired
    OldBugRepository oldBugRepository;

    @Autowired
    AppVerRepository appVerRepository;


    @Override
    public Boolean deleteBug(BugInfoKeys bugInfoKeys) {
        try {
            //TODO 使用级联删除
            bugRepository.delete(bugInfoKeys);
            bugOperateStepRepository.delete(bugInfoKeys);
            bugConsoleLogRepository.delete(bugInfoKeys);
            bugDeviceRepository.delete(bugInfoKeys);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BugInfoKeys addBug(BugInfo bugInfo) {
        //TODO 同步问题
        AppVersion appVersion = new AppVersion();
        appVersion.setAppKey(bugInfo.getAppKey());
        appVersion.setAppVersion(bugInfo.getBugBaseInfo().getAppVersion());
        if (!appVerRepository.exists(new AppVersionKeys(appVersion.getAppKey(), appVersion.getAppVersion()))) {
            appVerRepository.save(appVersion);
        }
        //这里通过获取
        String bugId = Constants.bug_id;
        bugInfo.setBugId(bugId);
        if (bugInfo.getBugBaseInfo() != null)
            bugRepository.save(bugInfo.getBugBaseInfo());
        if (bugInfo.getBugDeviceInfo() != null)
            bugDeviceRepository.save(bugInfo.getBugDeviceInfo());
        if (bugInfo.getBugConsoleLog() != null)
            bugConsoleLogRepository.save(bugInfo.getBugConsoleLog());
        if (bugInfo.getBugOperateStep() != null)
            bugOperateStepRepository.save(bugInfo.getBugOperateStep());
        return bugInfo.getBugInfoKeys();
    }

    public static int Guid = 100;

//    public static String getBugId() {
//        Guid += 1;
//        long now = System.currentTimeMillis();
//        //获取4位年份数字
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//        //获取时间戳
//        String time = dateFormat.format(now);
//        String info = now + "";
//        //获取三位随机数
//        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
//        if (Guid > 999) {
//            Guid = 100;
//        }
//        //调用这个功能的bug，将把其bugid交出，让需要保存的图片做文件名，使根据bugID可以找到相对应的图片
//        Constants.bug_id = time + info.substring(2, info.length()) + Guid;
//        return time + info.substring(2, info.length()) + Guid;
//    }

    @Override
    public Boolean modifyBug(BugInfoKeys bugInfoKeys, JSONObject jsonObject) {
        try {
            BugBaseInfo bugBaseInfo = bugRepository.findOne(bugInfoKeys);
            if (bugBaseInfo == null)
                return false;
            oldBugRepository.save(getOldBugBaseInfo(bugBaseInfo));
            if (jsonObject.has(Constants.TYPE))
                bugBaseInfo.setType(jsonObject.getString(Constants.TYPE));
            if (jsonObject.has(Constants.STATUS))
                bugBaseInfo.setStatus(jsonObject.getString(Constants.STATUS));
            if (jsonObject.has(Constants.DESCRIBE))
                bugBaseInfo.setBugDescribe(jsonObject.getString(Constants.DESCRIBE));
            if (jsonObject.has(Constants.PRIORITY))
                bugBaseInfo.setPriority(jsonObject.getString(Constants.PRIORITY));
            bugBaseInfo.setmTime(new Timestamp(System.currentTimeMillis()));
            bugBaseInfo.setUserId(jsonObject.getInt(Constants.USER_ID));
            bugRepository.save(bugBaseInfo);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    private OldBugBaseInfo getOldBugBaseInfo(BugBaseInfo bugBaseInfo) {
        OldBugBaseInfo oldBugBaseInfo = new OldBugBaseInfo();
        oldBugBaseInfo.setAppKey(bugBaseInfo.getAppKey());
        oldBugBaseInfo.setBugDescribe(bugBaseInfo.getBugDescribe());
        oldBugBaseInfo.setBugId(bugBaseInfo.getBugId());
        oldBugBaseInfo.setcTime(bugBaseInfo.getcTime());
        oldBugBaseInfo.setCurrent(bugBaseInfo.getCurrent());
        oldBugBaseInfo.setmTime(bugBaseInfo.getmTime());
        oldBugBaseInfo.setPriority(bugBaseInfo.getPriority());
        oldBugBaseInfo.setStatus(bugBaseInfo.getStatus());
        oldBugBaseInfo.setType(bugBaseInfo.getType());
        oldBugBaseInfo.setUserId(bugBaseInfo.getUserId());
        return oldBugBaseInfo;
    }

    @Override
    public BugBaseInfoVO findBugBaseInfo(BugInfoKeys id) {
        if (!bugRepository.exists(id))
            return null;
        BugBaseInfo bugBaseInfo = bugRepository.findOne(id);
        return new BugBaseInfoVO(bugBaseInfo, bugBaseInfo.getUser());
    }

    @Override
    public BugInfoVO findWholeBug(BugInfoKeys bugInfoKeys) {
        if (!bugRepository.exists(bugInfoKeys))
            return null;
        BugInfoVO bugInfo = new BugInfoVO();
        bugInfo.setBugBaseInfo(bugRepository.findOne(bugInfoKeys));
        if (bugConsoleLogRepository.exists(bugInfoKeys))
            bugInfo.setBugConsoleLog(bugConsoleLogRepository.findOne(bugInfoKeys));
        if (bugDeviceRepository.exists(bugInfoKeys))
            bugInfo.setBugDeviceInfo(bugDeviceRepository.findOne(bugInfoKeys));
        if (bugOperateStepRepository.exists(bugInfoKeys))
            bugInfo.setBugOperateStep(bugOperateStepRepository.findOne(bugInfoKeys));
        return bugInfo;
    }

    @Override
    public BugConsoleLogVO findConsoleLogByBugId(BugInfoKeys bugInfoKeys) {
        if (!bugRepository.exists(bugInfoKeys))
            return null;
        BugConsoleLog bugConsoleLog = bugConsoleLogRepository.findOne(bugInfoKeys);
        return new BugConsoleLogVO(bugConsoleLog);
    }

    @Override
    public BugDeviceInfoVO findDeviceInfoByBugId(BugInfoKeys bugInfoKeys) {
        if (!bugRepository.exists(bugInfoKeys))
            return null;
        BugDeviceInfo bugDeviceInfo = bugDeviceRepository.findOne(bugInfoKeys);
        return new BugDeviceInfoVO(bugDeviceInfo);
    }

    @Override
    public BugOperateStepVO findOperateStepByBugId(BugInfoKeys bugInfoKeys) {
        if (!bugRepository.exists(bugInfoKeys))
            return null;
        BugOperateStep bugOperateStep = bugOperateStepRepository.findOne(bugInfoKeys);
        return new BugOperateStepVO(bugOperateStep);
    }

    @Override
    public List<BugBaseInfoVO> findAllBugByAppId(String appKey) {
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findAllByAppKey(appKey);
        if (bugBaseInfoList.size() == 0)
            return null;
        List<BugBaseInfoVO> bugBaseInfoVOList = new ArrayList<BugBaseInfoVO>(bugBaseInfoList.size());
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugBaseInfoVO bugBaseInfoVO = new BugBaseInfoVO(bugBaseInfo, bugBaseInfo.getUser());
            bugBaseInfoVOList.add(bugBaseInfoVO);
        }
        return bugBaseInfoVOList;
    }

    @Override
    public List<BugBaseInfoVO> findAllBugByScreen(String appKey, JSONObject jsonObject) {
        String[] statuss = jsonObject.getString(Constants.STATUS).split(",");
        String[] types = jsonObject.getString(Constants.TYPE).split(",");
        String[] prioritys = jsonObject.getString(Constants.PRIORITY).split(",");
        Collection<String> statusList = new LinkedList<String>();
        for (int i = 0; i < statuss.length; i++) {
            statusList.add(statuss[i]);
        }
        Collection<String> typeList = new LinkedList<String>();
        for (int i = 0; i < types.length; i++) {
            typeList.add(types[i]);
        }
        Collection<String> priorityList = new LinkedList<String>();
        for (int i = 0; i < prioritys.length; i++) {
            priorityList.add(prioritys[i]);
        }

        List<BugBaseInfo> bugBaseInfoList = bugRepository.findByAppKeyAndStatusInAndTypeInAndPriorityIn(appKey, statusList, typeList, priorityList);
        if (bugBaseInfoList.size() == 0)
            return null;
        List<BugBaseInfoVO> bugBaseInfoVOList = new ArrayList<BugBaseInfoVO>(bugBaseInfoList.size());
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugBaseInfoVO bugBaseInfoVO = new BugBaseInfoVO(bugBaseInfo, bugBaseInfo.getUser());
            bugBaseInfoVOList.add(bugBaseInfoVO);
        }
        return bugBaseInfoVOList;
    }

    @Override
    public List<BugBaseInfoVO> findAllBugs() {
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findAll();
        if (bugBaseInfoList.size() == 0)
            return null;
        List<BugBaseInfoVO> bugBaseInfoVOList = new ArrayList<BugBaseInfoVO>(bugBaseInfoList.size());
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugBaseInfoVO bugBaseInfoVO = new BugBaseInfoVO(bugBaseInfo, bugBaseInfo.getUser());
            bugBaseInfoVOList.add(bugBaseInfoVO);
        }
        return bugBaseInfoVOList;
    }

    @Override
    public List<BugBaseInfoVO> findSimilarBugs(BugInfoKeys bugInfoKeys) {
        //TODO实现返回相似bug
        if (!bugRepository.exists(bugInfoKeys))
            return null;
        BugBaseInfo bugBaseInfo = bugRepository.findOne(bugInfoKeys);
        return null;
    }

    @Override
    public List<BugInfoVO> findCurrentBugs(String appKey, String current) {
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findAllByAppKeyAndCurrent(appKey, current);
        List<BugInfoVO> bugInfoVOList = new LinkedList<BugInfoVO>();
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugInfoKeys bugInfoKeys = new BugInfoKeys(bugBaseInfo.getAppKey(), bugBaseInfo.getBugId());
            BugInfoVO bugInfo = new BugInfoVO();
            bugInfo.setBugBaseInfo(bugRepository.findOne(bugInfoKeys));
            bugInfo.setBugConsoleLog(bugConsoleLogRepository.findOne(bugInfoKeys));
            bugInfo.setBugDeviceInfo(bugDeviceRepository.findOne(bugInfoKeys));
            bugInfo.setBugOperateStep(bugOperateStepRepository.findOne(bugInfoKeys));
            bugInfoVOList.add(bugInfo);
        }
        return bugInfoVOList;
    }

    @Override
    public List<OldBugBaseInfoVO> findOldBug(String appKey, String bugId) {
        List<OldBugBaseInfo> oldBugBaseInfoList = oldBugRepository.findAllByAppKeyAndBugId(appKey, bugId);
        if (oldBugBaseInfoList.size() == 0)
            return null;
        List<OldBugBaseInfoVO> oldBugBaseInfoVOList = new LinkedList<OldBugBaseInfoVO>();
        for (OldBugBaseInfo oldBugBaseInfo : oldBugBaseInfoList) {
            OldBugBaseInfoVO oldBugBaseInfoVO = new OldBugBaseInfoVO(oldBugBaseInfo);
            oldBugBaseInfoVOList.add(oldBugBaseInfoVO);
        }
        return oldBugBaseInfoVOList;
    }

    @Override
    public List<BugBaseInfoVO> findAllBugByAppKeyAndVersion(String appKey, String appVersion) {
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findByAppKeyAndAppVersion(appKey, appVersion);
        if (bugBaseInfoList.size() == 0)
            return null;
        List<BugBaseInfoVO> bugBaseInfoVOList = new ArrayList<BugBaseInfoVO>(bugBaseInfoList.size());
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugBaseInfoVO bugBaseInfoVO = new BugBaseInfoVO(bugBaseInfo, bugBaseInfo.getUser());
            bugBaseInfoVOList.add(bugBaseInfoVO);
        }
        return bugBaseInfoVOList;
    }

    @Override
    public BugStatisticInfo getStatisticInfo(String appKey, String appVersion) {
        BugStatisticInfo bugStatisticInfo = new BugStatisticInfo();
        bugStatisticInfo.setBugAmount(bugRepository.countAllByAppKeyAndAppVersion(appKey, appVersion));
        bugStatisticInfo.setUserAmount(bugRepository.findUserByByAppKeyAndAppVersion(appKey, appVersion).size());
        bugStatisticInfo.setBugSolve(bugRepository.countAllByAppKeyAndAppVersionAndStatus(appKey, appVersion, Constants.BUG_STATUS_SOLVE)
                / (float) bugStatisticInfo.getBugAmount());
        bugStatisticInfo.setCrashAmount(bugRepository.countAllByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_CRASH));
        bugStatisticInfo.setCrashUserAmount(bugRepository.findUserByByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_CRASH).size());
        bugStatisticInfo.setCrashDeviceAmount(bugDeviceRepository.findDeviceByByAppKeyAndAppVersion(appKey, appVersion, Constants.BUG_PRIORITY_CRASH).size());
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findByAppKeyAndAppVersion(appKey, appVersion);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //TODO
        timestamp = new Timestamp(timestamp.getYear(), timestamp.getMonth(), timestamp.getDate(), 0, 0, 0, 0);
        int[] crashTwoWeeks = new int[14];
        for (int i = 0; i < crashTwoWeeks.length; i++) {
            crashTwoWeeks[i] = 0;
        }
        for (int i = 0; i < bugBaseInfoList.size(); i++) {
            long timeDifference = (timestamp.getTime() - bugBaseInfoList.get(i).getcTime().getTime());
            if (timeDifference < 0)
                timeDifference = -86400000;
            int index = 12 - (int) (timeDifference / 86400000);
            if (index >= 0)
                crashTwoWeeks[index]++;
        }
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < crashTwoWeeks.length; i++) {
            long temp = 86400000 * (crashTwoWeeks.length - i - 1);
            map.put(new Date(timestamp.getTime() - temp).toString(), crashTwoWeeks[i]);
        }
        bugStatisticInfo.setBugRecentAmount(map);
        int[] bugStatus = {bugRepository.countAllByAppKeyAndAppVersionAndStatus(appKey, appVersion, Constants.BUG_STATUS_NEW),
                bugRepository.countAllByAppKeyAndAppVersionAndStatus(appKey, appVersion, Constants.BUG_STATUS_PROCESS),
                bugRepository.countAllByAppKeyAndAppVersionAndStatus(appKey, appVersion, Constants.BUG_STATUS_SOLVE),
                bugRepository.countAllByAppKeyAndAppVersionAndStatus(appKey, appVersion, Constants.BUG_STATUS_CLOSE)};
        bugStatisticInfo.setBugStatus(bugStatus);
        int[] bugType = {bugRepository.countAllByAppKeyAndAppVersionAndType(appKey, appVersion, Constants.BUG_TYPE_FUNCTION),
                bugRepository.countAllByAppKeyAndAppVersionAndType(appKey, appVersion, Constants.BUG_TYPE_INTERFACE),
                bugRepository.countAllByAppKeyAndAppVersionAndType(appKey, appVersion, Constants.BUG_TYPE_SECURITY),
                bugRepository.countAllByAppKeyAndAppVersionAndType(appKey, appVersion, Constants.BUG_TYPE_PERFORMANCE)};
        bugStatisticInfo.setBugType(bugType);
        int[] bugPriority = {bugRepository.countAllByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_SERIOUS),
                bugRepository.countAllByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_COMMON),
                bugRepository.countAllByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_CRASH),
                bugRepository.countAllByAppKeyAndAppVersionAndPriority(appKey, appVersion, Constants.BUG_PRIORITY_INFERIOR)};
        bugStatisticInfo.setBugPriority(bugPriority);
        return bugStatisticInfo;
    }

    @Override
    public List<BugBaseInfoVO> findAllByUserId(int userId) {
        List<BugBaseInfo> bugBaseInfoList = bugRepository.findAllByUserId(userId);
        if (bugBaseInfoList.size() == 0)
            return null;
        List<BugBaseInfoVO> bugBaseInfoVOList = new ArrayList<BugBaseInfoVO>(bugBaseInfoList.size());
        for (BugBaseInfo bugBaseInfo : bugBaseInfoList) {
            BugBaseInfoVO bugBaseInfoVO = new BugBaseInfoVO(bugBaseInfo, null);
            bugBaseInfoVOList.add(bugBaseInfoVO);
        }
        return bugBaseInfoVOList;
    }

    @Override
    public Integer[] getSimpleStatistic(String appKey) {
        System.out.println(appKey + bugRepository.countAllByAppKeyAndStatus(appKey, "新建"));
        Integer[] simpleInfo = {bugRepository.countAllByAppKeyAndStatus(appKey, Constants.BUG_STATUS_NEW),
                bugRepository.countAllByAppKeyAndStatus(appKey, Constants.BUG_STATUS_PROCESS),
                bugRepository.countAllByAppKeyAndStatus(appKey, Constants.BUG_STATUS_SOLVE),
                bugRepository.countAllByAppKeyAndStatus(appKey, Constants.BUG_STATUS_CLOSE),
                bugRepository.countAllByAppKeyAndType(appKey, Constants.BUG_TYPE_FUNCTION),
                bugRepository.countAllByAppKeyAndType(appKey, Constants.BUG_TYPE_INTERFACE),
                bugRepository.countAllByAppKeyAndType(appKey, Constants.BUG_TYPE_SECURITY),
                bugRepository.countAllByAppKeyAndType(appKey, Constants.BUG_TYPE_PERFORMANCE),
                bugRepository.countAllByAppKeyAndPriority(appKey, Constants.BUG_PRIORITY_SERIOUS),
                bugRepository.countAllByAppKeyAndPriority(appKey, Constants.BUG_PRIORITY_COMMON),
                bugRepository.countAllByAppKeyAndPriority(appKey, Constants.BUG_PRIORITY_CRASH),
                bugRepository.countAllByAppKeyAndPriority(appKey, Constants.BUG_PRIORITY_INFERIOR)
        };
        return simpleInfo;
    }


}
