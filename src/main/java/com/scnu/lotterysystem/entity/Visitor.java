package com.scnu.lotterysystem.entity;


import java.util.Date;

/* *
* 每日使用该抽奖系统的使用者
* 权限最低
* */
public class Visitor {
    private Integer id;

    private String name;

    private Date createTime;  //该用户建立时间

    private Date lastTime;   //上一次登录的时间

    private Date updateTime;  //现在登录的时间

    /* *
    * 可抽奖的次数，
    * 每天更新为3次
    * */
    private Integer usingTimes;
}
