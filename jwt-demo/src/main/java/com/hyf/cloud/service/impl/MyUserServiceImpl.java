package com.hyf.cloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyf.cloud.entity.MyUser;
import com.hyf.cloud.entity.RegisterBody;
import com.hyf.cloud.mapper.MyUserMapper;
import com.hyf.cloud.service.IMyUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {
    @Override
    public void register(RegisterBody registerBody) {
        MyUser user = new MyUser();
        BeanUtil.copyProperties(registerBody,user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(("ROLE_USER,TEST"));
        this.save(user);
    }
}
