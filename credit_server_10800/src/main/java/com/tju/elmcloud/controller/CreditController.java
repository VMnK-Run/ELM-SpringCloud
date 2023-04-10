package com.tju.elmcloud.controller;

import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.CreditVO;
import com.tju.elmcloud.po.ValidCredit;
import com.tju.elmcloud.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CreditController")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PutMapping("/spendCredit/{userId}/{channelId}/{eventId}/{credit}")
    public CommonResult<Integer> spendCredit(
            @PathVariable("userId") String userId,
            @PathVariable("channelId") String channelId,
            @PathVariable("eventId") Integer eventId,
            @PathVariable("credit") Integer credit
    ) throws Exception {
        int res = creditService.spendCredit(userId, channelId, eventId, credit);
        return new CommonResult<>(200, "success", res);
    }

    @GetMapping("/getTotalCredit/{userId}")
    public CommonResult<Integer> getTotalCredit(@PathVariable("userId") String userId) throws Exception {
        int res = creditService.getTotalCredit(userId);
        return new CommonResult<>(200, "success", res);
    }

    @GetMapping("/getValidCredit/{userId}")
    public CommonResult<List> getValidCredits(@PathVariable("userId") String userId) throws Exception {
        List<ValidCredit> list =  creditService.getValidCredits(userId);
        return new CommonResult<>(200, "success", list);
    }

    @GetMapping("/getCreditTotalDetails/{userId}")
    public CommonResult<List> getCreditTotalDetails(@PathVariable("userId") String userId) throws Exception {
        List<CreditVO> list = creditService.getCreditTotalDetails(userId);
        return new CommonResult<>(200, "success", list);
    }

    @GetMapping("/getCreditByParam/{userId}/{param}")
    public CommonResult<List> getCreditTotalDetailsByParam(
            @PathVariable("userId") String userId,
            @PathVariable("param") Integer param) throws Exception {
        List<CreditVO> list = creditService.getCreditByParam(userId, param);
        return new CommonResult<>(200, "success", list);
    }

    @PostMapping("/saveCreditByOrder/{orderId}")
    public CommonResult<Integer> saveCreditByOrder(@PathVariable("orderId") Integer orderId) {
        int res = creditService.saveCreditByOrder(orderId);
        return new CommonResult<>(200, "success", res);
    }

    @PostMapping("/saveCreditByComment/{cmId}")
    public CommonResult<Integer> saveCreditByComment(@PathVariable("cmId") Integer cmId) {
        int res = creditService.saveCreditByComment(cmId);
        return new CommonResult<>(200, "success", res);
    }

    // TODO: 新用户注册获取积分，更新积分
}
