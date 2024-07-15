package org.example.finaltask.Controller;

import org.example.finaltask.mapper.ContentMapper;
import org.example.finaltask.pojo.vo.UserVO;
import org.example.finaltask.response.JsonResult;
import org.example.finaltask.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("v1/contents")
public class ContentController {

    @Autowired
    private ContentMapper contentMapper;

    @GetMapping("{type}/management")
    public JsonResult management(@PathVariable int type, HttpSession session){
        UserVO user = (UserVO) session.getAttribute("user");
        //user=new UserVO(1L,"Jack","123456","Jack");
        if(user == null){
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }
        return JsonResult.ok(contentMapper.selectContentByTypeAndUserId(type,user.getId()));
    }

}
