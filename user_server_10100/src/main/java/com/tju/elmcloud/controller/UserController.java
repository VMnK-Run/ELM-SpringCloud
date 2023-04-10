package com.tju.elmcloud.controller;


import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.User;
import com.tju.elmcloud.service.UserService;
import com.tju.elmcloud.viewpo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")  // 跨域处理
@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CreditService creditService;
//
//    @Autowired
//    private WalletService walletService;

    @GetMapping("/getUserByIdByPass/{userId}/{password}")
    public CommonResult<User> getUserByIdByPass(@PathVariable("userId") String userId,
                                          @PathVariable("password") String password) throws Exception {
//        creditService.updateCredit(user.getUserId());
        // 通过这种方式，只修改控制器，不修改service
        User param = new User();
        param.setUserId(userId);
        param.setPassword(password);
        User user = userService.getUserByIdByPass(param);
        return new CommonResult<>(200, "success", user);
    }

    @GetMapping("/getUserById/{userId}")
    public CommonResult<Integer> getUserById(@PathVariable("userId") String userId) throws Exception {
        int result = userService.getUserById(userId);
        return new CommonResult<>(200, "success", result);
    }

    @PostMapping("/saveUser/{userId}/{password}/{userName}/{userSex}")
    public CommonResult<Integer> saveUser(
            @PathVariable("userId") String userId,
            @PathVariable("password") String password,
            @PathVariable("userName") String userName,
            @PathVariable("userSex") Integer userSex
    ) throws Exception {
        User param = new User();
        param.setUserId(userId);
        param.setUserName(userName);
        param.setPassword(password);
        param.setUserSex(userSex);
        int result = userService.saveUser(param);
//        int creditId = creditService.saveCreditForNewUser(user.getUserId());
//        int walletid=walletService.saveWallet(user.getUserId(),0);
        return new CommonResult<>(200, "success", result);
    }

    @PutMapping("/updateUserById/{userId}/{password}/{userName}/{userSex}/{userImg}")
    public CommonResult<Integer> updateUserById(
            @PathVariable("userId") String userId,
            @PathVariable("password") String password,
            @PathVariable("userName") String userName,
            @PathVariable("userSex") Integer userSex,
            @PathVariable("userImg") String userImg
    ) throws Exception {
        User param = new User();
        param.setUserId(userId);
        param.setPassword(password);
        param.setUserName(userName);
        param.setUserSex(userSex);
        param.setUserImg(userImg);
        int result = userService.updateUserById(param);
        return new CommonResult<>(200, "success", result);
    }

    @GetMapping("/getUserInfoById/{userId}")
    public CommonResult<UserInfo> getUserInfoById(@PathVariable("userId") String userId) throws Exception {
        UserInfo result = userService.getUserInfoById(userId);
        return new CommonResult<>(200, "success", result);
    }
}
