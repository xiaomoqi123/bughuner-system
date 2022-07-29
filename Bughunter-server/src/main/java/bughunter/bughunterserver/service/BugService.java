package bughunter.bughunterserver.service;

import bughunter.bughunterserver.model.entity.*;
import bughunter.bughunterserver.vo.*;
import org.json.JSONObject;

import java.util.List;

public interface BugService {

    Boolean deleteBug(BugInfoKeys bugInfoKeys);

    BugInfoKeys addBug(BugInfo bugInfo);

    Boolean modifyBug(BugInfoKeys bugInfoKeys, JSONObject jsonObject);

    BugBaseInfoVO findBugBaseInfo(BugInfoKeys bugInfoKeys);

    BugInfoVO findWholeBug(BugInfoKeys bugInfoKeys);

    BugConsoleLogVO findConsoleLogByBugId(BugInfoKeys bugInfoKeys);

    BugDeviceInfoVO findDeviceInfoByBugId(BugInfoKeys bugInfoKeys);

    BugOperateStepVO findOperateStepByBugId(BugInfoKeys bugInfoKeys);

    List<BugBaseInfoVO> findSimilarBugs(BugInfoKeys bugInfoKeys);

    List<BugBaseInfoVO> findAllBugByAppId(String appKey);

    List<BugBaseInfoVO> findAllBugByScreen(String appKey, JSONObject jsonObject);

    List<BugBaseInfoVO> findAllBugs();

    //public List<BugBaseInfoVO> findCurrentBugs(int appKey, String current);

    public List<BugInfoVO> findCurrentBugs(String appKey, String current);

    List<OldBugBaseInfoVO> findOldBug(String appKey, String bugId);

    List<BugBaseInfoVO> findAllBugByAppKeyAndVersion(String appKey, String appVersion);

    BugStatisticInfo getStatisticInfo(String appKey, String appVersion);

    List<BugBaseInfoVO> findAllByUserId(int userId);

    Integer[] getSimpleStatistic(String appKey);
}
