package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @com.sky.anotation.AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);


    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品数据
     * @param id
     * @return Dish
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据ids删除菜品
     * @param ids
     */
    void deleteDishByIds(List<Long> ids);

    /**
     * 修改菜品
     * @param dish
     * @return Result
     */
    @com.sky.anotation.AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据菜品分类查菜
     * @param dish
     * @return
     */
    List<Dish> getDishByCategory(Dish dish);

    /**
     * 根据所属套餐查询菜品
     * @param id
     * @return
     */
    @Select("select d.* from dish d left join setmeal_dish s on d.id = s.dish_id where s.setmeal_id = #{id}")
    List<Dish> getBySetmealId(Long id);
}
