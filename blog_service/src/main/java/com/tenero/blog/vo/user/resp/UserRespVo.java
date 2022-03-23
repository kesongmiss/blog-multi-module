package com.tenero.blog.vo.user.resp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class UserRespVo {

    /**
     * 账号
     */
    @NotBlank
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @ApiModelProperty(value = "账号")
    private String password;

    /**
     * 昵称
     */
    @NotBlank
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String headportrait;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String synopsis;


    /**
     * 邮箱
     */
    @Email
    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private Integer phonenumber;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

}
