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

    @RequestMapping("/{type}/sub")
    public JsonResult sub(@PathVariable int type) {
        return JsonResult.ok(categoryMapper.selectCategoryByType(type));
    }
}
