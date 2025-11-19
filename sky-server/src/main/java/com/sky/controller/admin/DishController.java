package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Api("菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        //清理缓存数据
        String key="dish_"+dishDTO.getCategoryId();
        cleanCache(key);
        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult> page (DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询");
        PageResult pageResult=dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @ApiOperation("删除菜品接口")
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品:{}",ids);
        dishService.deleteBatch(ids);
        //清理缓存数据
        cleanCache("key_*");
        return Result.success();
    }

    @ApiOperation("查询菜品接口")
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("查询菜品:{}",id);
        DishVO dishVO=dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品:{}",dishDTO);
        dishService.update(dishDTO);
        //清理缓存数据
        cleanCache("dish_*");
        return Result.success();
    }
    @PostMapping("status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("修改菜品id:{}",id);
        dishService.startOrStop(status,id);
        //清理缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId){
        log.info("查询菜品分类:{}",categoryId);
        List<Dish> list=dishService.list(categoryId);
        return Result.success(list);
    }
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
