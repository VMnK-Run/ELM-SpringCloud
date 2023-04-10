package com.tju.elmcloud.controller;

import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.Food;
import com.tju.elmcloud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/FoodController")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping(value = "/listFoodByBusinessId/{businessId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResult<List> listFoodByBusinessId(@PathVariable("businessId") Integer businessId) throws Exception {
        List<Food> list = foodService.listFoodByBusinessId(businessId);
        System.out.println(list);
        return new CommonResult(200, "success(10201)", list);
    }

    @RequestMapping("/getFoodByRandom")
    public Food getFoodByRandom() throws Exception {
        return foodService.getFoodByRandom();
    }

    @RequestMapping("/listFoodByTime")
    public List<Food> listFoodByTime() throws Exception {
        return foodService.listFoodByTime();
    }


}
