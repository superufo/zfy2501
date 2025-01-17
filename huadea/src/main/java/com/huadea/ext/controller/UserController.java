package com.huadea.ext.controller;

import com.huadea.ext.commons.AjaxResult;
import com.huadea.ext.config.JwtService;
import com.huadea.ext.dto.req.LoginReq;
import com.huadea.ext.dto.req.TestRep;
import com.huadea.ext.dto.resp.JwtResponseResp;
import com.huadea.ext.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author mike
 * @since 2025-01-09
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/test")
    public AjaxResult test(@Validated @RequestBody  TestRep  testRep) {
        AjaxResult ajax = AjaxResult.success();
        log.info("testRep:{}",testRep);

        return ajax;
    }

    @PostMapping("/test1")
    public AjaxResult accountLogin(@Validated @RequestBody LoginReq loginReq) {
        log.info("loginReq:{}",loginReq);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("test", 11111);

        return ajax;
    }

    @PostMapping("/reg")
    public AjaxResult reg(@Validated @RequestBody  LoginReq loginReq) {
        log.info(" loginReq:{} ",loginReq);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("test", 444444);
        return ajax;
    }

    @PostMapping("/login")
    public AjaxResult login(@Validated @RequestBody  LoginReq loginReq) {
        log.info("login  loginReq:{}",loginReq);

        UserDetails ud =  userServiceImpl.loadUserByUsername(loginReq.getPoliceNumber());
        //  存储密码用户 权限到 authentication ，供SecurityConfig  jwtAuthFilter 使用
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getPoliceNumber(), loginReq.getPasswd(), ud.getAuthorities()));
        if (!authentication.isAuthenticated()) {
            return AjaxResult.error("-1001", "authentication认证用户名或密码错误!");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 使用密码编码器比较密码
        if (!passwordEncoder.matches(loginReq.getPasswd(), userDetails.getPassword())) {
            return AjaxResult.error("-1001",  "用户名或密码错误!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String  token = jwtService.generateToken(loginReq.getPoliceNumber());
        return AjaxResult.success(JwtResponseResp.builder()
                .accessToken(token).build());
    }
}
