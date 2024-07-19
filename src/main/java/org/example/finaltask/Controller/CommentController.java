package org.example.finaltask.Controller;


import org.example.finaltask.mapper.CommentMapper;
import org.example.finaltask.mapper.ContentMapper;
import org.example.finaltask.pojo.dto.CommentDTO;
import org.example.finaltask.pojo.entity.Comment;
import org.example.finaltask.pojo.vo.UserVO;
import org.example.finaltask.response.JsonResult;
import org.example.finaltask.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("v1/comments")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ContentMapper contentMapper;

    @PostMapping("add-new")
    public JsonResult addNew(@RequestBody CommentDTO commentDTO, HttpSession session) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        if(userVO == null){
            return new JsonResult(StatusCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setCreateTime(new Date());
        System.out.println(comment.getContentId());
        contentMapper.updateCommentCountById(commentDTO.getContentId());
        commentMapper.insertComment(comment);
        contentMapper.updateCommentCountById(commentDTO.getContentId());
        return JsonResult.ok();
    }

    @GetMapping("{id}")
    public JsonResult select(@PathVariable Long id){
        return JsonResult.ok(commentMapper.selectCommentByContentId(id));
    }
}
