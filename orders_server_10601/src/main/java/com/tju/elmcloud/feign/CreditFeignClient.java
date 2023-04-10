package com.tju.elmcloud.feign;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "credit-server", fallback = CreditFeignClientCallBack.class)
public interface CreditFeignClient {

    @PostMapping("/CreditController/saveCreditByOrder/{orderId}")
    public CommonResult<Integer> saveCreditByOrder(@PathVariable("orderId") Integer orderId);
}
