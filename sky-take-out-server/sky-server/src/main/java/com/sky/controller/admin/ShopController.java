package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminShopController")
@Slf4j
@Api(tags = "店铺相关接口")
@RequestMapping("/admin/shop")
public class ShopController {

    public static final String SHOP_STATUS_KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店铺营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(Integer status) {
        log.info("设置店铺的营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(SHOP_STATUS_KEY, status);
        return Result.success();
    }

    /**
     * 查询店铺的营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("查询店铺的营业状态")
    public Result<Integer> getStatus() {
        log.info("查询店铺的营业状态");
        return Result.success((Integer) redisTemplate.opsForValue().get(SHOP_STATUS_KEY);
    }
}
