package com.tju.elmcloud.controller;

import com.tju.elmcloud.po.Cart;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/CartController")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/listCart/{userId}")
    public CommonResult<List> listCart(@PathVariable("userId") String userId) throws Exception{
        Cart param = new Cart();
        param.setUserId(userId);
        List<Cart> list = cartService.listCart(param);
        return new CommonResult<>(200,"success",list);
    }

    @GetMapping("/listCart/{userId}/{businessId}")
    public CommonResult<List> listCart(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId) throws Exception{
        Cart param = new Cart();
        param.setUserId(userId);
        param.setBusinessId(businessId);
        List<Cart> list = cartService.listCart(param);
        return new CommonResult<>(200,"success",list);
    }

    @PostMapping("/saveCart/{userId}/{businessId}/{foodId}")
    public CommonResult<Integer> saveCart(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId,
            @PathVariable("foodId") Integer foodId) throws Exception{
        Cart param = new Cart();
        param.setUserId(userId);
        param.setBusinessId(businessId);
        param.setFoodId(foodId);
        int result = cartService.saveCart(param);
        return new CommonResult<>(200,"success",result);
    }

    @PutMapping("/updateCart/{userId}/{businessId}/{foodId}/{quantity}")
    public CommonResult<Integer> updateCart(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId,
            @PathVariable("foodId") Integer foodId,
            @PathVariable("quantity") Integer quantity) throws Exception{
        Cart param = new Cart();
        param.setUserId(userId);
        param.setBusinessId(businessId);
        param.setFoodId(foodId);
        param.setQuantity(quantity);
        int result = cartService.updateCart(param);
        return new CommonResult<>(200,"success", result);
    }

    @DeleteMapping("/removeCart/{userId}/{businessId}/{foodId}")
    public CommonResult<Integer> removeCart(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId,
            @PathVariable("foodId") Integer foodId) throws Exception{
        Cart param = new Cart();
        param.setUserId(userId);
        param.setBusinessId(businessId);
        param.setFoodId(foodId);
        int result = cartService.removeCart(param);
        return new CommonResult<>(200,"success",result);
    }
}
