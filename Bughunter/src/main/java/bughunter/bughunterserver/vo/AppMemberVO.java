package bughunter.bughunterserver.vo;

import bughunter.bughunterserver.model.entity.AppMember;
import bughunter.bughunterserver.model.entity.User;

public class AppMemberVO {

//    private String appKey;

    private int id;

    private String name;

    private String email;

    private String teleNumber;

    private String type;


    public AppMemberVO(String type, User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.teleNumber = user.getTeleNumber();
        this.type = type;
    }

//    public String getAppKey() {
//        return appKey;
//    }

//    public void setAppKey(String appKey) {
//        this.appKey = appKey;
//    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
