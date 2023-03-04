package com.tju.elmcloud.controller;

import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.Food;
import com.tju.elmcloud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/FoodController")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/listFoodByBusinessId/{businessId}")
    public CommonResult<List<Food>> listFoodByBusinessId(@PathVariable("businessId") Integer businessId) throws Exception {
        List<Food> list = foodService.listFoodByBusinessId(businessId);

        return new CommonResult<>(200, "success", list);
    }
/* TODO 这里的函数还没添加
    @RequestMapping("/getFoodByRandom")
    public Food getFoodByRandom() throws Exception {
        return foodService.getFoodByRandom();
    }

    @RequestMapping("/listFoodByTime")
    public List<Food> listFoodByTime() throws Exception {
        return foodService.listFoodByTime();
    }

 */
}
