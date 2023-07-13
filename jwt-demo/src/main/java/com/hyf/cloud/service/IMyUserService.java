package com.hyf.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyf.cloud.entity.MyUser;
import com.hyf.cloud.entity.RegisterBody;

public interface IMyUserService extends IService<MyUser> {

    void register(RegisterBody registerBody);
}
