package com.tenero.blog.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser implements Serializable {
    private Integer id;

    /**
    * 账号
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 性别
    */
    private Integer sex;

    /**
    * 头像地址
    */
    private String headportrait;

    /**
    * 简介
    */
    private String synopsis;

    private Date createtime;

    /**
    * 修改时间
    */
    private Date updatetime;

    /**
    * 1、可用，2、禁用，0、删除
    */
    private Integer status;

    /**
    * 邮箱
    */
    private String mailbox;

    /**
    * 电话号码
    */
    private Integer phonenumber;

    /**
    * 地址
    */
    private String address;

    private static final long serialVersionUID = 1L;

    private String token;
}