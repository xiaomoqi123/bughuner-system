package bughunter.bughunterserver.factory;

import bughunter.bughunterserver.until.Constants;
import bughunter.bughunterserver.vo.ResultMessage;

public class ResultMessageFactory {

    public static ResultMessage getResultMessage(Object data) {
        ResultMessage resultMessage;
        if (data == null)
            resultMessage = new ResultMessage(1, Constants.ERROR);
        else
            resultMessage = new ResultMessage(0, Constants.OKSTR, data);
        return resultMessage;
    }


    public static ResultMessage getResultMessage(boolean flag, String msg) {
        ResultMessage resultMessage;
        if (flag)
            resultMessage = new ResultMessage(0, Constants.NO_ERROR);
        else
            resultMessage = new ResultMessage(1, msg);
        return resultMessage;
    }

    public static ResultMessage getResultMessage(int id) {
        ResultMessage resultMessage;
        if (id > 0)
            resultMessage = new ResultMessage(0, Constants.NO_ERROR, id);
        else
            resultMessage = new ResultMessage(1, Constants.ERROR);
        return resultMessage;
    }

}
