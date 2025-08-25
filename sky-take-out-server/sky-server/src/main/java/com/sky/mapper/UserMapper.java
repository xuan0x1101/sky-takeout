package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    /**
     * find user by openid
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * INSERT user
     * @param user
     */
    void insert(User user);

    @Select("select * from sky_take_out.user where id = #{userId}")
    User getById(Long userId);

    /**
     * 统计区间用户数据
     * @param beginTime
     * @param endTime
     * @return
     */
    Integer getUserCount(LocalDateTime beginTime, LocalDateTime endTime);
}
