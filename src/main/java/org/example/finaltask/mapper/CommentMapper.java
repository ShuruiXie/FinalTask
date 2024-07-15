package org.example.finaltask.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.Comment;
import org.example.finaltask.pojo.vo.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);
    List<CommentVO> selectCommentByContentId(Long contentId);
}
