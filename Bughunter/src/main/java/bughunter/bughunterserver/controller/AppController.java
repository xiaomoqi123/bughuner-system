package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.factory.ResultMessageFactory;
import bughunter.bughunterserver.model.entity.AppBaseInfo;
import bughunter.bughunterserver.model.entity.AppMember;
import bughunter.bughunterserver.service.AppService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.AppBaseInfoVO;
import bughunter.bughunterserver.vo.ResultMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService appService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage addApp(HttpServletRequest request, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        AppBaseInfo appBaseInfo = new AppBaseInfo();
        appBaseInfo.setName(jsonObject.getString(Constants.NAME));
        appBaseInfo.setcTime(new Date(new java.util.Date().getTime()));
        appBaseInfo.setmTime(appBaseInfo.getcTime());
        appBaseInfo.setType(jsonObject.getString(Constants.TYPE));
        appBaseInfo.setSDKVersion(jsonObject.getDouble(Constants.SDK_VERSION));
        String appKey = appService.addApp(appBaseInfo, jsonObject.getInt(Constants.USER_ID));
        return ResultMessageFactory.getResultMessage(appKey);
    }

    @RequestMapping(value = "/{appKey}/modify", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage modifyApp(HttpServletRequest request, @PathVariable String appKey, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        return ResultMessageFactory.getResultMessage(appService.modifyApp(appKey, jsonObject), Constants.ERROR);
    }

    @RequestMapping(value = "/{appKey}/delete", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage deleteApp(HttpServletRequest request, @PathVariable String appKey) {
        return ResultMessageFactory.getResultMessage(appService.deleteApp(appKey), Constants.ERROR_NO_EXIST);
    }

    @RequestMapping(value = "/{appKey}/get", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getApp(HttpServletRequest request, @PathVariable String appKey) {
        AppBaseInfoVO appBaseInfo = appService.findApp(appKey);
        return ResultMessageFactory.getResultMessage(appBaseInfo);
    }

    @RequestMapping(value = "/{appKey}/getMembers", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getMembers(HttpServletRequest request, @PathVariable String appKey) {
        return ResultMessageFactory.getResultMessage(appService.findAllMemberByAppKey(appKey));
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage addMember(HttpServletRequest request, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);

        return ResultMessageFactory.getResultMessage(appService.addMember(jsonObject), Constants.ERROR_EXIST);
    }

    @RequestMapping(value = "/{appKey}/{uId}/deleteMember", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage deleteMember(HttpServletRequest request, @PathVariable String appKey, @PathVariable int uId) {
        return ResultMessageFactory.getResultMessage(appService.deleteMember(appKey, uId), Constants.ERROR_NO_EXIST);
    }

    @RequestMapping(value = "/{developerId}/getAll", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getAllApps(HttpServletRequest request, @PathVariable int developerId) {
        List<AppBaseInfoVO> appBaseInfoList = appService.findAllAppsByUserId(developerId);
        return ResultMessageFactory.getResultMessage(appBaseInfoList);
    }

    @RequestMapping(value = "/{appKey}/getVersions", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getVersions(HttpServletRequest request, @PathVariable String appKey) {
        List<String> versionList = appService.findAppVersionByAppKey(appKey);
        return ResultMessageFactory.getResultMessage(versionList);
    }

}
