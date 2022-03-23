//package com.tenero.blog.shiro;
//
//
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
//@Configuration
//public class ShiroConfiguration {
//
//
//
//    /**
//     * shiro自带的加密
//     *
//     * @return
//     */
////    @Bean("hashedCredentialsMatcher")
////    public HashedCredentialsMatcher hashedCredentialsMatcher() {
////        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
////        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//////        hashedCredentialsMatcher.setHashIterations(1024);
////        return hashedCredentialsMatcher;
////    }
//
//
//    /**
//     * 自定义realm
//     *  会走HashedCredentialsMatcher 的 doCredentialsMatch方法，对密码盐加密后再比较
//     * @return
//     */
////    @Bean("userRealm")
////    public UserRealm doGetAuthorizationInfoimpl(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
////        UserRealm userRealm = new UserRealm();
////        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
////        return userRealm;
////    }
//    // 不指定加密算法 会走 SimpleCredentialsMatcher 的doCredentialsMatch方法 直接比较
//    @Bean("userRealm")
//    public UserRealm doGetAuthorizationInfoimpl() {
//        return new UserRealm();
//    }
//
//
//    /**
//     * 安全管理器
//     *
//     * @param userRealm
//     * @return
//     */
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager defaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
//        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
//        defaultSecurityManager.setRealm(userRealm);
//        return defaultSecurityManager;
//    }
//
//    // 启用鉴权注解
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    //AuthorizationAttributeSourceAdvisor 的作用是匹配所有类 匹配所有加认证注解的方法
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//
//    /**
//     * cookie对象
//     * @return
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie(){
//        SimpleCookie simpleCookie = new SimpleCookie("tenero_blog");
//        // 1天
//        simpleCookie.setMaxAge(61*61*24);
//        return simpleCookie;
//    }
//
//    /**
//     * cookie管理器，记住我功能管理器
//     * @return
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        return cookieRememberMeManager;
//    }
//
//
//
//    /**
//     * 拦截器
//     *
//     * @param defaultWebSecurityManager
//     * @return
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//
//        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//
//
//        /**
//         * 配置角色资源过滤器
//         */
//        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
//        stringStringLinkedHashMap.put("/swagger-ui.html", "anon");
//        stringStringLinkedHashMap.put("/swagger-resources/**", "anon");
//        stringStringLinkedHashMap.put("/webjars/**", "anon");
//        stringStringLinkedHashMap.put("/v2/**", "anon");
//        stringStringLinkedHashMap.put("/swagger-resources/configuration/security", "anon");
//        stringStringLinkedHashMap.put("/swagger-resources/configuration/ui", "anon");
//
//        // 登录、登出，可以直接访问
//        stringStringLinkedHashMap.put("/tenero/login/**", "anon");
//        // 登录后既可访问
//        stringStringLinkedHashMap.put("/tenero/friendchainlist/**", "authc");
//
//        // 设置认证登录跳转地址，不由后端跳转，由前端控制
//        shiroFilterFactoryBean.setLoginUrl("/un_auth");
//
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringLinkedHashMap);
//        return shiroFilterFactoryBean;
//    }
//
//
//}
