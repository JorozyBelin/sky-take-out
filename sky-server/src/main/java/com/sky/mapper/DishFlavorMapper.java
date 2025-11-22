package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味数据
     * @param flavors
     */

    void insertBatch(List<DishFlavor> flavors);


    /**
     * 根据菜品id查询对应的口味数据
     * @param dishId
     * @return
     */
    @Delete("delete from dish_flavor where dish_id=#{id}")
    void deleteByDishId(Long id);

    /**
     * 根据菜品id批量删除
     * @param ids
     */
    void deleteByDishIds(List<Long> ids);

    /**
     * 根据菜品id查询对应的口味数据
     */
    @Select("select name,value from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getByDishId(Long id);
}
