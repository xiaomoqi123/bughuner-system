package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.factory.ResultMessageFactory;
import bughunter.bughunterserver.service.UserService;
import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.ResultMessage;
import bughunter.bughunterserver.vo.UserVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bughunter")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage login(HttpServletRequest request, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String email = jsonObject.getString(Constants.EMAIL);
        String pwd = jsonObject.getString(Constants.PWD);
        ResultMessage resultMessage;

        UserVO userVO = userService.testLogin(email, pwd);
        if (userVO == null)
            resultMessage = new ResultMessage(1, Constants.ERROR);
        else
            resultMessage = new ResultMessage(0, Constants.NO_ERROR, userVO);
        return resultMessage;

    }

    @RequestMapping(value = "/isExist", method = RequestMethod.POST)
    public @ResponseBody
    ResultMessage isExist(HttpServletRequest request, @RequestBody String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        String emailAddr = jsonObject.getString(Constants.EMAIL);
        return ResultMessageFactory.getResultMessage(userService.findByEmail(emailAddr) != null, Constants.ERROR_NO_EXIST);
    }


}
