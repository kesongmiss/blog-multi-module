package com.tenero.blog.controller.user;

import com.tenero.blog.common.BaseRespDto;
import com.tenero.blog.service.BlogUserService;
import com.tenero.blog.utils.BaseDtoConvert;
import com.tenero.blog.utils.MD5Util;
import com.tenero.blog.vo.user.resp.UserRespVo;
import com.tenero.blog.entity.user.BlogUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/tenero/userlist")
@Api(tags = "用户操作")
public class UserController {

    @Autowired
    public BlogUserService blogUserService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public BaseRespDto register(@RequestBody @Valid UserRespVo userRespVo){
        userRespVo.setPassword(MD5Util.md5LowerCase(userRespVo.getPassword(),userRespVo.getUsername()+"tenero_login"));
        BlogUser blogUser = BaseDtoConvert.tToV(userRespVo, BlogUser.class);

        int i = blogUserService.insertSelective(blogUser);

        if(i>0){
            return BaseRespDto.success();
        }else {
            BaseRespDto baseRespDto = new BaseRespDto();
            baseRespDto.setFail();
            baseRespDto.setErrorDesc("注册失败");
            return baseRespDto;
        }


    }


}
