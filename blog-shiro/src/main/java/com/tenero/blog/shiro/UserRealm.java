package com.tenero.blog.shiro;



import com.tenero.blog.entity.role.BlogRole;
import com.tenero.blog.entity.user.BlogUser;
import com.tenero.blog.service.BlogRoleService;
import com.tenero.blog.service.BlogUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    public BlogUserService blogUserService;

    @Autowired
    public BlogRoleService blogRoleService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        BlogUser username = (BlogUser)principals.getPrimaryPrincipal();
        // 获得角色
        Integer roleid = blogUserService.queryRoleByUserName(username.getUsername());
        // 获得对应角色的权限
        List<String> permissions =  blogUserService.queryPermissionsByRole(roleid);

        BlogRole blogRole = blogRoleService.selectByPrimaryKey(roleid);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        simpleAuthorizationInfo.addRole(blogRole.getRoleName());
        return simpleAuthorizationInfo;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        BlogUser blogUser =  blogUserService.queryByUserName(username);
//        String s = blogUser.getUsername() + "tenero_blog";
        if (blogUser == null) {
            log.info("没有当前用户：{}" + username);
            throw new UnknownAccountException();
        } else {
            // userReam 指定MD5加密后，加盐，加密后再比较
//            return new SimpleAuthenticationInfo(blogUser, blogUser.getPassword(),new SimpleByteSource(s.getBytes()), this.getName());
            // userReam 没有指定加密器，不加盐，不会加密，直接比较
            return new SimpleAuthenticationInfo(blogUser, blogUser.getPassword(), this.getName());
        }

    }

}
