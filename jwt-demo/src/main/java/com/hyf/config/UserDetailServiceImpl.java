package com.hyf.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyf.entity.MyUser;
import com.hyf.service.IMyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    IMyUserService userService;

    public UserDetailServiceImpl(IMyUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userService.getOne(new QueryWrapper<MyUser>().eq("username", username));
        return user;
    }
}
