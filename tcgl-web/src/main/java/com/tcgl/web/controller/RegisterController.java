package com.tcgl.web.controller;

import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;
import com.tcgl.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册控制类
 *
 * @author Shuguang_Liux
 * @date 2021/06/20 18:36
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "userRegister",method = RequestMethod.POST)
    public ResultVo<?> register(@RequestBody UserEntity userEntity){
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userService.register(userEntity);
    }
}
