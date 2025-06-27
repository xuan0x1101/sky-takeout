package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@Slf4j
@Api(tags = "店铺相关接口(user)")
@RequestMapping("/user/shop")
public class ShopController {

    public static final String SHOP_STATUS_KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询店铺的营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("查询店铺的营业状态(user)")
    public Result<Integer> getStatus() {
        log.info("查询店铺的营业状态(user)");
        return Result.success((Integer) redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
    }
}
