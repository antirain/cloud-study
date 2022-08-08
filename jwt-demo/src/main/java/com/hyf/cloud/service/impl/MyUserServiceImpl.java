package com.hyf.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyf.cloud.entity.MyUser;
import com.hyf.cloud.mapper.MyUserMapper;
import com.hyf.cloud.service.IMyUserService;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {
}
