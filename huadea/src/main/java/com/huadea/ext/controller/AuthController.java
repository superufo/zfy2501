package com.huadea.ext.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huadea.ext.commons.AjaxResult;
import com.huadea.ext.domain.Auth;
import com.huadea.ext.mapper.AuthMapper;
import com.huadea.ext.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author mike                @Validated @RequestBody  ExamStudentLoginDTO dto, HttpServletRequest request
 * @since 2025-01-09
 */
@RestController
@RequestMapping("/auth")  //CommonsConstants.ApiPrefix.EXAM +
@Slf4j
@Validated
@RequiredArgsConstructor
@DS("slave")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @Autowired
    private  Auth auth;

    @Autowired
    private AuthMapper authMapper;

    @PostMapping("/test")
    public AjaxResult  accountLogin(@Validated @RequestBody  HashMap param) {

        //Auth auth = new Auth();
        auth.setRoleId(1);
        IPage<Auth> authList =  authService.getByAuthId(1,10,auth);

        log.info("aaaa",param);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("test", 11111);
        ajax.put("auth_list", authList);

        System.out.println(param);
        return ajax;
    }

    @PostMapping("/add")
    public AjaxResult  add(@Validated @RequestBody  Auth auth) {
        // 开启 IDENTITY_INSERT
        authMapper.enableIdentityInsert();
        log.info("auth:",auth);

        boolean res = authService.save(auth);
        // 关闭 IDENTITY_INSERT
        authMapper.disableIdentityInsert();

        if (res){
            return AjaxResult.success();
        }else{
            return  AjaxResult.error("新增角色失败");
        }
    }

}
