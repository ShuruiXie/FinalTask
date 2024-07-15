package org.example.finaltask.Controller;

import org.example.finaltask.mapper.CategoryMapper;
import org.example.finaltask.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据类型查询分类信息
     *
     * @param type 分类的类型，通过路径变量获取
     * @return JsonResult 包含查询结果的对象，查询结果为对应类型的分类列表
     * @RequestMapping 注解用于映射URL，/{type}/sub 表示以type为变量的URL路径，用于接收类型参数并根据该类型查询分类
     */
    @RequestMapping("/{type}/sub")
    public JsonResult sub(@PathVariable int type) {
        // 调用categoryMapper的selectCategoryByType方法查询指定类型的分类信息
        return JsonResult.ok(categoryMapper.selectCategoryByType(type));
    }
}
