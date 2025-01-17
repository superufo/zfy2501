package com.huadea.ext.config;

import com.huadea.ext.domain.Module;
import com.huadea.ext.domain.vo.ModuelsWithRoleVo;
import com.huadea.ext.service.IModuleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
public class HuadeaAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Autowired
    IModuleService moduleService;

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        HttpServletRequest request = requestAuthorizationContext.getRequest();

        boolean granted = false;
        //1. 根据当前请求分析出来当前请求属于 menu 中的哪一种 http://localhost:8080/personnel/ec/hello（menu）
        //1.1 获取当前请求 url 地址
        String requestURI = request.getRequestURI();
        if (    "/auth/add".equals(requestURI)
                || "/error".equals(requestURI)
                || "/auth/test".equals(requestURI)
                || "/user/reg".equals(requestURI)
                || "/user/login".equals(requestURI)
                || "/user/test".equals(requestURI)) {
            return new AuthorizationDecision(true);
        }

        log.info("requestURI:{} --------------",  requestURI);
        Authentication auth = authentication.get();
        if(auth==null){
            return new AuthorizationDecision(false);
        }

        //1.2 和 modules 表中的记录进行比较
        List<ModuelsWithRoleVo> modelusWithRoles = moduleService.getAllModuelsWithRole();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (ModuelsWithRoleVo modelusWithRole : modelusWithRoles) {
            // 1 获取当前登录用户所具备的角色
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                // 2 判断当前用户 某个roleid 等于modelusWithRole中roleid
                if (authority.getAuthority().equals(modelusWithRole.getRoleId().toString())) {

                    for (Module module : modelusWithRole.getChildren()) {
                        if ( antPathMatcher.match(module.getAlias(), requestURI)) {
                            //说明当前用户具备所需要的角色
                            granted = true;
                        }
                    }
                }
            }
        }

        //如果 granted 为 true，表示请求通过；granted 为 false 表示请求不通过（即用户权限不足）
        return new AuthorizationDecision(granted);
    }
}
