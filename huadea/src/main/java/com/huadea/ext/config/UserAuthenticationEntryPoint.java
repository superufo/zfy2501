package com.huadea.ext.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.huadea.ext.commons.AjaxResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException {
        String errorMessage = "";
        int  code = -1001;
        if (e instanceof UsernameNotFoundException) {
            errorMessage = "用户不存在!";
            code = -1002;
        } else if (e instanceof BadCredentialsException) {
            errorMessage = "身份验证失败!";
            code = -1003;
        } else {
            errorMessage = e.getMessage();
            code = -1004;
        }

        log.info("UserAuthenticationEntryPoint:{} ---------------------------------------------------------------------",errorMessage);
        val objectMapper = new ObjectMapper();
        response.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.defaultCharset()).toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        AjaxResult ajax = AjaxResult.error(code,errorMessage);
        response.getWriter().println(objectMapper.writeValueAsString(ajax));
    }
}
