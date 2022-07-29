package bughunter.bughunterserver.service;

import bughunter.bughunterserver.model.entity.AppBaseInfo;
import bughunter.bughunterserver.model.entity.AppMember;
import bughunter.bughunterserver.model.entity.User;
import bughunter.bughunterserver.vo.AppBaseInfoVO;
import bughunter.bughunterserver.vo.AppMemberVO;
import bughunter.bughunterserver.vo.BugStatisticInfo;
import org.json.JSONObject;

import java.util.List;

public interface AppService {


    Boolean deleteApp(String appKey);

    String addApp(AppBaseInfo appBaseInfo, int uId);

    Boolean modifyApp(String appKey, JSONObject jsonObject);

    AppBaseInfoVO findApp(String appKey);

    List<AppBaseInfoVO> findAllAppsByUserId(int uappKey);

    List<AppBaseInfoVO> findAllApps();

    Boolean deleteMember(String appkey, int uId);

    Boolean addMember(JSONObject jsonObject);

    List<String> findAppVersionByAppKey(String appKey);

    List<AppMemberVO> findAllMemberByAppKey(String appKey);
}
