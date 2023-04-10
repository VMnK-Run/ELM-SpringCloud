package com.tju.elmcloud.feign;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class CreditFeignClientCallBack implements CreditFeignClient {
    @Override
    public CommonResult<Integer> saveCreditByComment(Integer cmId) {
        // 返回降级响应
        return new CommonResult<>(403, "feign触发了熔断降级", null);
    }
}
