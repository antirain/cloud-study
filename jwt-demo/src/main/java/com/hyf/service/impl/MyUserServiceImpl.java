package com.hyf.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hyf.entity.MyUser;
import com.hyf.mapper.MyUserMapper;
import com.hyf.service.IMyUserService;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {
}
