package com.hyf.cloud.controller;

import com.hyf.cloud.constant.ResultCode;
import com.hyf.cloud.entity.RegisterBody;
import com.hyf.cloud.service.IMyUserService;
import com.hyf.cloud.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IMyUserService myUserService;
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResultVo register(@RequestBody RegisterBody registerBody){
        myUserService.register(registerBody);
        return new ResultVo(ResultCode.SUCCESS);
    }

}
