package bughunter.bughunterserver.vo;

import bughunter.bughunterserver.model.entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


public class UserVO {

    private int id;

    private String name;

    private String pwd;

    private String email;

    private Date createTime;

    private String teleNumber;

    private int status;

    private int appAmount;

    private int bugSubmitAmount;


    public UserVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.pwd = user.getPwd();
        this.email = user.getEmail();
        this.teleNumber = user.getTeleNumber();
        this.status = user.getStatus();
    }

    public UserVO(int id, int status) {
        this.id = id;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeleNumber() {
        return teleNumber;
    }

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAppAmount() {
        return appAmount;
    }

    public void setAppAmount(int appAmount) {
        this.appAmount = appAmount;
    }

    public int getBugSubmitAmount() {
        return bugSubmitAmount;
    }

    public void setBugSubmitAmount(int bugSubmitAmount) {
        this.bugSubmitAmount = bugSubmitAmount;
    }
}
