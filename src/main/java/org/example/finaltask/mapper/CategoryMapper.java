package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.vo.CategoryVO;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 根据类型（第一级）查询分类
    List<CategoryVO> selectCategoryByType(Integer type);
}
