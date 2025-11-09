package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param ids
     * @return
     */
    List<Long> getByDishId(List<Long> ids);

    /**
     * 批量插入套餐和菜品的关联关系
     * @param
     */
//    @AutoFill(value= OperationType.INSERT)
//    void insertBatch(List<SetmealDish> setMealDishes);

    void deleteBatch(List<Long> ids);

    /**
     * 插入套餐和菜品的关联关系
     * @param setmealDish
     */
    @Insert("insert into setmeal_dish(setmeal_id, dish_id, name, price, copies)" +
            " values(#{setmealId},#{dishId},#{name},#{price},#{copies})")
    void insert(SetmealDish setmealDish);

    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getBySetmealId(Long id);

    @Delete("delete from setmeal_dish where setmeal_id=#{setmealID}")
    void deleteBySetmealId(Long setmealId);
}
