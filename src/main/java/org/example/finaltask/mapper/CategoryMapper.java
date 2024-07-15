package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.vo.CategoryVO;

import java.util.List;

@Mapper
public interface CategoryMapper {


    List<CategoryVO> selectCategoryByType(Integer type);


}
