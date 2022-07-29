package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.factory.ResultMessageFactory;
import bughunter.bughunterserver.model.entity.BugInfo;
import bughunter.bughunterserver.model.entity.BugInfoKeys;
import bughunter.bughunterserver.service.BugService;
import bughunter.bughunterserver.service.EdgeService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.until.PicName;
import bughunter.bughunterserver.vo.*;
import bughunter.bughunterserver.wrapper.EdgeVOWrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/app/bug")
public class BugController {

    @Autowired
    BugService bugService;

    @Autowired
    EdgeService edgeService;

    private final ResourceLoader resourceLoader;

    @Autowired
    EdgeVOWrapper edgeVOWrapper;

    @Autowired
    public BugController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/{appKey}/{current}/getCurrentActivityBug", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getCurrentActivityBug(HttpServletRequest request, @PathVariable String appKey, @PathVariable String current) {
        List<BugInfoVO> bugBaseInfoList = bugService.findCurrentBugs(appKey, current);
        return ResultMessageFactory.getResultMessage(bugBaseInfoList);
    }


    @RequestMapping(value = "/{appKey}/getAll", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getAllBugByAppKey(HttpServletRequest request, @PathVariable String appKey) {
        List<BugBaseInfoVO> bugBaseInfoList = bugService.findAllBugByAppId(appKey);
        return ResultMessageFactory.getResultMessage(bugBaseInfoList);
    }

    @RequestMapping(value = "/{userId}/getAllSubmit", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getAllBugByUserId(HttpServletRequest request, @PathVariable int userId) {
        return ResultMessageFactory.getResultMessage(bugService.findAllByUserId(userId));
    }

    @RequestMapping(value = "/{appKey}/{appVersion}/getAllByAppVersion", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getAllByAppVersion(HttpServletRequest request, @PathVariable String appKey, @PathVariable String appVersion) {
        List<BugBaseInfoVO> bugBaseInfoList = bugService.findAllBugByAppKeyAndVersion(appKey, appVersion);
        return ResultMessageFactory.getResultMessage(bugBaseInfoList);
    }


    //有截图会出错
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultMessage submitBug(@RequestParam(name = "screenshot", required = false) MultipartFile file, @RequestParam(name = "bug") String jsonStr) {

        String screenshotName = Constants.SCREENSHOT_NO_EXIST;
        //获取图片的名字，同时把图片名赋值给Constant里的bug_id，供提交bugs时使用，确保bug_id和文件名的一致性
        String picName = bughunter.bughunterserver.until.PicName.getPicName();
        if (file != null && !file.isEmpty()) {
            try {
                File logoSaveFile = new File(Constants.SCREENSHOT_BASE_URL);
                if (!logoSaveFile.exists()) {
                    logoSaveFile.mkdirs();
                }
                screenshotName = picName + ".png";
                String screenshotFileName = Constants.SCREENSHOT_BASE_URL + File.separator + screenshotName;
                File screenshotFile = new File(screenshotFileName);
                file.transferTo(screenshotFile);
                System.out.println(screenshotFile.getAbsolutePath() + "----------------------------------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ResultMessage(1, e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultMessage(1, e.getMessage());
            }
        }
        System.out.println(jsonStr);
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            BugInfo bugInfo = new BugInfo(jsonObject, screenshotName);
            BugInfoKeys bugInfoKey = bugService.addBug(bugInfo);
            return ResultMessageFactory.getResultMessage(bugInfoKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResultMessage(1, Constants.ERROR);
    }


    @RequestMapping(value = "/{appKey}/{bugId}/get", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getBugById(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        BugInfoVO bugInfo = bugService.findWholeBug(getBugInfoKeys(appKey, bugId));
        return ResultMessageFactory.getResultMessage(bugInfo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/screenshot/{filename:.+}")
    public
    @ResponseBody
    Resource getScreenshot(@PathVariable String filename) {
        try {
            return resourceLoader.getResource("file:" + Paths.get(Constants.SCREENSHOT_BASE_URL, filename).toString());
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/{appKey}/getAfterScreen", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultMessage getAllBugByScreen(HttpServletRequest request, @PathVariable String appKey, @RequestBody String jsonStr) {
        List<BugBaseInfoVO> bugBaseInfoList = bugService.findAllBugByScreen(appKey, new JSONObject(jsonStr));
        return ResultMessageFactory.getResultMessage(bugBaseInfoList);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/oldGet", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getOldBugById(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        List<OldBugBaseInfoVO> oldBugBaseInfoVOList = bugService.findOldBug(appKey, bugId);
        return ResultMessageFactory.getResultMessage(oldBugBaseInfoVOList);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/modify", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultMessage modifyBug(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        return ResultMessageFactory.getResultMessage(bugService.modifyBug(getBugInfoKeys(appKey, bugId), jsonObject), Constants.ERROR);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResultMessage deleteBug(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId, @RequestBody String jsonStr) {
        return ResultMessageFactory.getResultMessage(bugService.deleteBug(getBugInfoKeys(appKey, bugId)), Constants.ERROR_NO_EXIST);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/base", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getBugBaseInfo(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        BugBaseInfoVO bugBaseInfo = bugService.findBugBaseInfo(getBugInfoKeys(appKey, bugId));
        return ResultMessageFactory.getResultMessage(bugBaseInfo);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/device", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getBugDeviceInfo(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        BugDeviceInfoVO bugDeviceInfo = bugService.findDeviceInfoByBugId(getBugInfoKeys(appKey, bugId));
        return ResultMessageFactory.getResultMessage(bugDeviceInfo);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/console", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getBugConsoleLog(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        BugConsoleLogVO bugConsoleLog = bugService.findConsoleLogByBugId(getBugInfoKeys(appKey, bugId));
        return ResultMessageFactory.getResultMessage(bugConsoleLog);
    }

    @RequestMapping(value = "/{appKey}/{bugId}/step", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getBugOperateStep(HttpServletRequest request, @PathVariable String appKey, @PathVariable String bugId) {
        BugOperateStepVO bugOperateStep = bugService.findOperateStepByBugId(getBugInfoKeys(appKey, bugId));
        return ResultMessageFactory.getResultMessage(bugOperateStep);
    }

    @RequestMapping(value = "/{appKey}/{appVersion}/getStatisticInfo", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getStatisticInfo(HttpServletRequest request, @PathVariable String appKey, @PathVariable String appVersion) {
        return ResultMessageFactory.getResultMessage(bugService.getStatisticInfo(appKey, appVersion));
    }

    @RequestMapping(value = "/{appKey}/getSimpleStatistic", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getSimpleStatistic(HttpServletRequest request, @PathVariable String appKey) {
        return ResultMessageFactory.getResultMessage(bugService.getSimpleStatistic(appKey));
    }


    private static BugInfoKeys getBugInfoKeys(String appKey, String bugId) {
        return new BugInfoKeys(appKey, bugId);
    }


    @RequestMapping(value = "/{appKey}/{currentWindow}/bugList/{isCovered}/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getRecommendedBugs(HttpServletRequest request, @PathVariable String appKey,
                                     @PathVariable String currentWindow, @PathVariable Integer isCovered,
                                     @PathVariable Integer userId) {
        String[] infos = currentWindow.split("\\.");
        currentWindow = infos[infos.length - 1];
        List<EdgeVO> messages = edgeService.getRecommBugs_2(appKey, currentWindow, isCovered, userId);
        return ResultMessageFactory.getResultMessage(messages);
    }


}
