package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.model.repository.BugDeviceRepository;
import bughunter.bughunterserver.model.repository.BugRepository;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {


    @Autowired
    BugRepository bugRepository;

    @Autowired
    BugDeviceRepository bugDeviceRepository;


    @RequestMapping(value = "/isSuccess", method = RequestMethod.GET)
    public @ResponseBody
    String isSuccess(HttpServletRequest request) {
        return "Success";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getStringList(HttpServletRequest request) {

        List<String> list = new LinkedList<String>();
        list.add("dsfd");
        list.add("sfsddgd");
        list.add("iojmnk");
        return new ResultMessage(list);
    }

    @RequestMapping(value = "/nativeSQL", method = RequestMethod.GET)
    public @ResponseBody
    List nativeSQL(HttpServletRequest request) {
        return bugDeviceRepository.findDeviceByByAppKeyAndAppVersion("bughunter", "1.5", Constants.BUG_PRIORITY_CRASH);
    }
}
