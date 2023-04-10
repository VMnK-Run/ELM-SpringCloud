package com.tju.elmcloud.controller;


import com.tju.elmcloud.feign.CreditFeignClient;
import com.tju.elmcloud.po.Business;
import com.tju.elmcloud.po.Comment;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CommentsController")
public class CommentsController {

    @Autowired
    private CommentService commentService;//动态注入commentService

//    @Autowired
//    private CreditService creditService;

    @Qualifier("com.tju.elmcloud.feign.CreditFeignClient")
    @Autowired
    private CreditFeignClient creditFeignClient;

    //调用service层
    @GetMapping("/listComment/{businessId}")
    public CommonResult<List> listComment(
            @PathVariable("businessId") Integer businessId
    ) {
        Comment comment = new Comment();
        comment.setBusinessId(businessId);
        List<Comment> list = commentService.listComment(comment);
        return new CommonResult<>(200, "success", list);
    }

    @PostMapping("/saveComment/{userId}/{businessId}/{foodId}/{content}/{star}")
    public CommonResult<Integer> saveComment(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId,
            @PathVariable("foodId") Integer foodId,
            @PathVariable("content") String content,
            @PathVariable("star") Integer star
    ){
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setBusinessId(businessId);
        comment.setFoodId(foodId);
        comment.setContent(content);
        comment.setStar(star);
        int cmId = commentService.saveComment(comment);
//        int creditId = creditService.saveCreditByComment(cmId);
        CommonResult<Integer> result = creditFeignClient.saveCreditByComment(cmId);
        System.out.println(result.getMessage());
        return new CommonResult<>(200, "success", cmId);
    }

    @GetMapping("/getCommentById/{cmId}")
    public CommonResult<Comment> getCommentById(@PathVariable("cmId") Integer cmId){
        Comment comment = commentService.getCommentById(cmId);
        return new CommonResult<>(200, "success", comment);
    }

    @DeleteMapping("/removeComment/{cmId}")
    public CommonResult<Integer> removeComment(@PathVariable("cmId") Integer cmId){
        int res = commentService.removeComment(cmId);
        return new CommonResult<>(200, "success", res);
    }

    @PutMapping("/updateComment/{cmId}/{content}/{img}/{star}")
    public CommonResult<Integer> updateComment(
            @PathVariable("cmId") Integer cmId,
            @PathVariable("content") String content,
            @PathVariable("img") String img,
            @PathVariable("star") Integer star
    ){
        Comment comment = new Comment();
        comment.setCmId(cmId);
        comment.setContent(content);
        comment.setImg(img);
        comment.setStar(star);
        int res = commentService.updateComment(comment);
        return new CommonResult<>(200, "success", res);
    }
}
