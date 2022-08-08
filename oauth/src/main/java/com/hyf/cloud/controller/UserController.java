package com.hyf.cloud.controller;

import com.hyf.cloud.vo.ResultVo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }
    @PostMapping("/verificationToken")
    public ResultVo verificationToken(@RequestParam("token") String token){
        return new ResultVo(true);
    }
}
