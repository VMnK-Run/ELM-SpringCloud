package com.tju.elmcloud.feign;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "credit-server", fallback = CreditFeignClientCallBack.class)
public interface CreditFeignClient {

    @PostMapping("/CreditController/saveCreditByComment/{cmId}")
    public CommonResult<Integer> saveCreditByComment(@PathVariable("cmId") Integer cmId);
}
