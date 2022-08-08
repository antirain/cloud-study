package com.hyf.cloud.feign;

import com.hyf.cloud.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider")
public interface ProviderFeign {

    @GetMapping("/demo/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp",required = false) String name);


}
