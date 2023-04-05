package com.tju.elmcloud.controller;

import com.tju.elmcloud.feign.FoodFeignClient;
import com.tju.elmcloud.po.Business;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.Food;
import com.tju.elmcloud.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/BusinessController")
public class BusinessController {

    //自动注入一个对象
    @Autowired
    private BusinessService businessService;

    @Qualifier("com.tju.elmcloud.feign.FoodFeignClient")
    @Autowired
    private FoodFeignClient foodFeignClient;

    //TODO 这里提供的代码是访问了食品微服务，不知道有啥意义
    @GetMapping ("/listBusinessByOrderTypeId/{orderTypeId}")
    public CommonResult<List<Business>> listBusinessByOrderTypeId(@PathVariable("orderTypeId") Integer orderTypeId) throws Exception {
        List<Business> list = businessService.listBusinessByOrderTypeId(orderTypeId);
        return new CommonResult<>(200, "success(10301)", list);

    }

    @GetMapping("/getBusinessById/{businessId}")
    public CommonResult<Business> getBusinessById(@PathVariable("businessId") Integer businessId) throws Exception {
        Business business = businessService.getBusinessById(businessId);
        CommonResult<List> result = foodFeignClient.listFoodByBusinessId(businessId);
        System.out.println(result.getMessage());
        if(result.getCode() == 200) {
            business.setFoodList(result.getResult());
        } else {
            business.setFoodList(new ArrayList<>());
        }
        return new CommonResult<>(200, "success", business);
    }

    /**
     * 按默认顺序对商家进行排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @GetMapping("/listBusinessDefault/{longitude}/{latitude}")
    public CommonResult<List<Business>> listBusinessDefault(
            @PathVariable("longitude") String longitude,
            @PathVariable("latitude") String latitude
    ) throws Exception {
        List<Business> list = businessService.listBusinessDefault(longitude, latitude);
        return new CommonResult<>(200, "success", list);
    }

    /**
     * 按距离升序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @GetMapping("/listBusinessByDistance/{longitude}/{latitude}")
    public CommonResult<List<Business>> listBusinessByDistance(
            @PathVariable("longitude") String longitude,
            @PathVariable("latitude") String latitude
    ) throws Exception {
        List<Business> list = businessService.listBusinessDefault(longitude, latitude);
        return new CommonResult<>(200, "success", list);
    }

    /**
     * 按销量对商家降序排序
     * @param longitude
     * 用户所在经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @GetMapping("/listBusinessBySales/{longitude}/{latitude}")
    public CommonResult<List<Business>> listBusinessBySales(
            @PathVariable("longitude") String longitude,
            @PathVariable("latitude") String latitude
    ) throws Exception {
        List<Business> list = businessService.listBusinessDefault(longitude, latitude);
        return new CommonResult<>(200, "success", list);
    }

    /**
     * 筛选符合条件的商家
     * @param orderTypeId
     * 商家分类
     * @param starPrice
     * 起送费上限
     * @param deliveryPrice
     * 配送费上限
     * @param distance
     * 距离上限
     * @param deliveryTime
     * 配送时间上限
     * @param longitude
     * 用户当前经度
     * @param latitude
     * 用户当前纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByConditions/{orderTypeId}/{starPrice}/{deliveryPrice}/{distance}/{deliveryTime}/{longitude}/{latitude}")
    public CommonResult<List<Business>> listBusinessByConditions(
            @PathVariable("orderTypeId") Integer orderTypeId,
            @PathVariable("starPrice") Double starPrice,
            @PathVariable("deliveryPrice") Double deliveryPrice,
            @PathVariable("distance") Double distance,
            @PathVariable("deliveryTime") Double deliveryTime,
            @PathVariable("longitude") String longitude,
            @PathVariable("latitude") String latitude
    ) throws Exception {
        List<Business> list = businessService.listBusinessByConditions(orderTypeId,starPrice,deliveryPrice,distance,deliveryTime,longitude,latitude);
        return new CommonResult<>(200, "success", list);
    }

    /**
     * 按关键字搜素商家，优先匹配商家名称，然后匹配食品名称并返回拥有该食品的商家 按匹配相似度排序返回
     * @param keywords
     * 关键字
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @GetMapping("/listBusinessByKeyWords/{keywords}")
    public CommonResult<List<Business>> listBusinessByKeyWords(@PathVariable("keywords") String keywords) throws Exception {
        keywords = keywords.replace(" ", "");
        List<Business> list = businessService.listBusinessByKeyWords(keywords);
        return new CommonResult<>(200, "success", list);
    }

    /**
     * 按评分降序排序
     * @param longitude
     * 用户当前经度
     * @param latitude
     * 用户所在纬度
     * @return businessService
     * 调用service层
     * @throws Exception
     */
    @RequestMapping("/listBusinessByScore/{longitude}/{latitude}")
    public CommonResult<List<Business>> listBusinessByScore(
            @PathVariable("longitude") String longitude,
            @PathVariable("latitude") String latitude
    ) throws Exception {
        List<Business> list = businessService.listBusinessByScore(longitude, latitude);
        return new CommonResult<>(200, "success", list);
    }
} 
