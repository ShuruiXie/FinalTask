package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.Comment;
import org.example.finaltask.pojo.vo.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 插入评论
    void insertComment(Comment comment);

    // 根据内容id查询评论
    List<CommentVO> selectCommentByContentId(Long contentId);
}
