package com.tenero.blog.controller.user;

import com.tenero.blog.common.BaseRespDto;
import com.tenero.blog.shiro.CacheUser;
import com.tenero.blog.utils.BaseDtoConvert;
import com.tenero.blog.utils.MD5Util;
import com.tenero.blog.vo.user.req.UserloginReqVo;
import com.tenero.blog.entity.user.BlogUser;
import com.tenero.blog.service.BlogUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/tenero/login")
@Api(tags = "登录")
public class UserLoginController {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public BlogUserService blogUserService;



    @PostMapping(value = "/userlogin")
    @ApiOperation(value = "登录")
    public BaseRespDto login(@RequestBody @Valid UserloginReqVo user){
        BaseRespDto baseRespDto = new BaseRespDto();


        if(StringUtils.isNotBlank(user.getMailbox())){
            // 邮箱登录
            BlogUser blogUser = blogUserService.queryByUserEmail(user.getMailbox());
            if(blogUser == null){
                baseRespDto.setFail();
                baseRespDto.setErrorDesc("邮箱或密码不正确！");
                return baseRespDto;
            }
            user.setUsername(blogUser.getUsername());
        }
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), MD5Util.md5LowerCase(user.getPassword(),user.getUsername()+"tenero_kesong"));

        //获得当前用户到登录对象，现在状态为未认证
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(token);

            BlogUser blogUser = (BlogUser) subject.getPrincipals().getPrimaryPrincipal();
            CacheUser cacheUser = CacheUser.builder().token(subject.getSession().getId().toString()).build();
            BaseDtoConvert.tToV(blogUser,cacheUser);

            log.info("CacheUser is {}",cacheUser.toString());
            return BaseRespDto.success();
        } catch (IncorrectCredentialsException ice){
            baseRespDto.setFail();
            baseRespDto.setErrorDesc("用户名或密码不正确！");
            return baseRespDto;
        }catch(UnknownAccountException uae){
            baseRespDto.setFail();
            baseRespDto.setErrorDesc("未知账户！");
            return baseRespDto;
        }catch (Exception e) {
            baseRespDto.setFail();
            baseRespDto.setErrorDesc("验证未通过！");
            return baseRespDto;
        }

    }


    @PostMapping(value = "/userlogout")
    @ApiOperation(value = "登出")
    public BaseRespDto logout(){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return BaseRespDto.success();
    }

}
