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
    List<ContentManagementVO>selectContentUserId(Long id);

    //修改稿件时查询该稿件原本的信息
    ContentUpdateVO selectUpdateInfoById(Long id);

    //根据稿件id修改稿件内容
    int updateContentInfo(Content content);

    //根据稿件id删除稿件
    int deleteContentById(Long id);


    //首页分类查询该分类下的所有稿件
    List<ContentSimpleVO> selectContentByType(Integer type);

    //根据稿件的id查询稿件详细内容
    ContentDetailVO selectContentDetailById(Long id);


    //查询热门的稿件，返回浏览量最高的前4篇稿件
    List<ContentSimpleVO> selectContentHot();

    //根据传入的关键词 模糊查询
    List<ContentSimpleVO> selectContentByWd(String wd);

     //根据传入的稿件的id增加1个评论量
    int updateCommentCountById(Long contentId);

    //根据稿件的id增加更新浏览量
    int updateViewCountById(Long id);

    //根据稿件的id增加点赞量
    int updateLikeCountById(Long id);

    int reduceLikeCountById(Long id);

    List<ContentSimpleVO> selectContentAll();

}
