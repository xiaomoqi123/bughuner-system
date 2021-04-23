package bughunter.bughunterserver.vo;

/**
 * Created by alpaca on 17-6-16.
 */
public class ResultMessage {

    public ResultMessage(int errno, String message, Object data) {
        this.error = errno;
        this.message = message;
        this.data = data;
    }

    public ResultMessage(int errno) {
        this.error = errno;
    }

    public ResultMessage(int errno, String message) {
        this.error = errno;
        this.message = message;
    }


    public ResultMessage(Object data) {
        this(0, "");
        this.data = data;
    }


    public int error;
    public String message;
    public Object data = "";
}
