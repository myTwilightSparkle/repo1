package com.iotek.hrmgr.config;



import com.iotek.hrmgr.realm.CustomRealm;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



import java.util.LinkedHashMap;

import java.util.Map;



@Configuration

public class ShiroConfig {

    @Bean("shiroFilter")

    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManage

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射

        shiroFilterFactoryBean.setLoginUrl("/session");

        // 设置无权限时跳转的 url;

        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");



        // 设置拦截器

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //游客，开发权限

        filterChainDefinitionMap.put("/visitor/**", "roles[visitor]");

        //用户，需要角色权限 “user”

        filterChainDefinitionMap.put("/employee/**", "roles[employee]");

        //管理员，需要角色权限 “admin”

        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        //开放登陆接口

        filterChainDefinitionMap.put("/session/**", "anon");

        filterChainDefinitionMap.put("/signUp/**", "anon");

        filterChainDefinitionMap.put("/signUpPage/**", "anon");

        filterChainDefinitionMap.put("/", "anon");



        //其余接口一律拦截

        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截

        filterChainDefinitionMap.put("/**", "authc");



        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功");

        return shiroFilterFactoryBean;

    }



    /**

     * 注入 securityManager

     */

    @Bean

    public DefaultWebSecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm.

        securityManager.setRealm(customRealm());

        return securityManager;

    }



    /**

     * 自定义身份认证 com.iotek.hrmgr.realm;

     * <p>

     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm.java，

     * 否则会影响 CustomRealm类 中其他类的依赖注入

     */

    @Bean

    public CustomRealm customRealm() {

        return new CustomRealm();

    }

}