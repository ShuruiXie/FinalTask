package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.Content;
import org.example.finaltask.pojo.vo.*;
import java.util.List;

@Mapper
public interface ContentMapper {
    int insertContent(Content content);
    /**
     * 查询当前用户的各类型稿件信息
     * @param type 稿件类型 1 → 食谱 2 → 视频 3 → 咨询
     * @param id   用户id
     * @return 稿件集合
     */
    List<ContentManagementVO>selectContentByTypeAndUserId(Integer type,Long id);
    /**
     * 编辑稿件时,根据稿件的id查询稿件信息
     *
     * @param id 稿件id
     * @return 稿件信息
     */
    ContentUpdateVO selectUpdateInfoById(Long id);
    /**
     * 根据id修改稿件信息
     *
     * @param content 要修改的稿件内容
     * @return 影响的行数
     */
    int updateContentInfo(Content content);

    int deleteContentById(Long id);
    /**
     * 根据一级分类和二级分类查询稿件信息
     *
     * @param type       一级分类
     * @param categoryId 二级分类
     * @return 对应的稿件信息
     */
    List<ContentIndexVO> selectContentByTypeAndCategoryId(Integer type, Long categoryId);
    /**
     * 首页根据一级分类查询该分类下的所有稿件
     * @param type 一级分类
     * @return 稿件信息
     */
    List<ContentIndexVO> selectContentByType(Integer type);
    /**
     * 根据稿件的id查询稿件详细内容
     *
     * @param id 稿件id
     * @return 稿件详细内容
     */
    ContentDetailVO selectContentDetailById(Long id);

    /**
     * 根据稿件的id增加更新浏览量
     * @param id 稿件id
     * @return 受影响行数
     */
    int updateViewCountById(Long id);
    /**
     * 根据作者id查询作者的其他稿件
     *
     * @param userId    作者id
     * @param contentId 不查询的稿件id
     * @return 其他稿件信息
     */
    List<ContentSimpleVO> selectContentOtherInfoByUserId(Long userId, Long contentId);

    /**
     *  查询 热门的稿件
     * @return  返回浏览量最高的前4篇 稿件
     */
    List<ContentSimpleVO> selectContentHot();

    /**
     *   根据传入的关键词 模糊查询
     * @param wd  代表关键词
     * @return
     */
    List<ContentIndexVO> selectContentByWd(String wd);

    /**
     *  根据传入的稿件的id 增加1个评论量
     * @param contentId 稿件ID
     * @return
     */
    int updateCommentCountById(Long contentId);

}
