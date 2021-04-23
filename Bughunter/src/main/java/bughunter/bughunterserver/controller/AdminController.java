package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.factory.ResultMessageFactory;
import bughunter.bughunterserver.model.entity.AppBaseInfo;
import bughunter.bughunterserver.service.AppService;
import bughunter.bughunterserver.service.UserService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.ResultMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AppService appService;

    @RequestMapping(value = "/getAllApp", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getAllApp(HttpServletRequest request) {
        return ResultMessageFactory.getResultMessage(appService.findAllApps());
    }

    @Autowired
    UserService userService;


    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public @ResponseBody
    ResultMessage getAllUser(HttpServletRequest request) {
        return ResultMessageFactory.getResultMessage(userService.findAllUsers());
    }


}
