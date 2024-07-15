package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.Content;
import org.example.finaltask.pojo.vo.*;
import java.util.List;

@Mapper
public interface ContentMapper {
    //插入稿件
    int insertContent(Content content);

    //查询当前用户特定种类稿件信息，返回一个稿件list
    List<ContentManagementVO>selectContentByTypeAndUserId(Integer type,Long id);

    //修改稿件时查询该稿件原本的信息
    ContentUpdateVO selectUpdateInfoById(Long id);

    //根据稿件id修改稿件内容
    int updateContentInfo(Content content);

    //根据稿件id删除稿件
    int deleteContentById(Long id);

    //根据一级分类和二级分类查询稿件信息
    List<ContentIndexVO> selectContentByTypeAndCategoryId(Integer type, Long categoryId);

    //首页根据一级分类查询该分类下的所有稿件
    List<ContentIndexVO> selectContentByType(Integer type);

    //根据稿件的id查询稿件详细内容
    ContentDetailVO selectContentDetailById(Long id);

    //根据作者id查询作者的其他稿件
    List<ContentSimpleVO> selectContentOtherInfoByUserId(Long userId, Long contentId);

    //查询热门的稿件，返回浏览量最高的前4篇稿件
    List<ContentSimpleVO> selectContentHot();

    //根据传入的关键词 模糊查询
    List<ContentIndexVO> selectContentByWd(String wd);

     //根据传入的稿件的id增加1个评论量
    int updateCommentCountById(Long contentId);

    //根据稿件的id增加更新浏览量
    int updateViewCountById(Long id);

}
