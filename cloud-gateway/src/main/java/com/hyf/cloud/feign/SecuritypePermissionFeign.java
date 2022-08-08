package com.hyf.cloud.feign;

import com.hyf.cloud.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/1 16:04
 */
@FeignClient(value = "oauth-server")
public interface SecuritypePermissionFeign {

    /**
     * 验证token
     * @param token
     * @return
     */
    @RequestMapping(value = "/verificationToken",method = {RequestMethod.POST})
    ResultVo verificationToken(@RequestParam("token") String token);

}
