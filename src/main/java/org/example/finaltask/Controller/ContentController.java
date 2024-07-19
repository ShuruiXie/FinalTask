package org.example.finaltask.Controller;

import org.example.finaltask.mapper.ContentMapper;
import org.example.finaltask.mapper.UserMapper;
import org.example.finaltask.pojo.dto.ContentDTO;
import org.example.finaltask.pojo.entity.Content;
import org.example.finaltask.pojo.vo.ContentUpdateVO;
import org.example.finaltask.pojo.vo.UserVO;
import org.example.finaltask.response.JsonResult;
import org.example.finaltask.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("v1/contents")
public class ContentController {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("add-new")
    public JsonResult addNew(@RequestBody ContentDTO contentDTO, HttpSession session){
        System.out.println("调用了添加");
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO,content);
        if(contentDTO.getId()!=null){
            System.out.println("调用了更新");
            UserVO userVO = (UserVO) session.getAttribute("user");
            content.setUpdateBy(userVO.getId());
            content.setUpdateTime(new Date());
            contentMapper.updateContentInfo(content);
            return JsonResult.ok();
        }
        content.setCreateTime(new Date());
        System.out.println(1);
        UserVO userVO = (UserVO) session.getAttribute("user");
        System.out.println(2);
        System.out.println(userVO.getId());
        userMapper.addContentNumById(userVO.getId());
        System.out.println(3);
        contentMapper.insertContent(content);
        return JsonResult.ok();
    }

    @GetMapping("{id}/update")
    public JsonResult selectForUpdate(@PathVariable Long id){
        System.out.println("调用了更新");
        return JsonResult.ok(contentMapper.selectUpdateInfoById(id));
    }
    @GetMapping("/management")
    public JsonResult management(HttpSession session){
        System.out.println("调用了管理");
        UserVO user = (UserVO) session.getAttribute("user");
        System.out.println(user.getId());
        if(user == null){
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }
        return JsonResult.ok(contentMapper.selectContentUserId(user.getId()));
    }

    @GetMapping("hot")
    public JsonResult selectHot(){
        System.out.println("调用了热门文章");
        return JsonResult.ok(contentMapper.selectContentHot());
    }

    @GetMapping("all")
    public JsonResult selectAll(){
        return JsonResult.ok(contentMapper.selectContentAll());
    }

    @PostMapping("{id}/delete")
    public JsonResult delete(@PathVariable Long id){
        ContentUpdateVO contentUpdateVO = contentMapper.selectUpdateInfoById(id);
        new File("D:/Desktop/Idea project/FinalTask/file"+contentUpdateVO.getImgUrl()).delete();
        if(contentUpdateVO.getVideoUrl()!=null){
            new File("D:/Desktop/Idea project/FinalTask/file"+contentUpdateVO.getVideoUrl()).delete();
        }
        contentMapper.deleteContentById(id);
        userMapper.reduceContentNumById(contentUpdateVO.getCreateBy());
        return JsonResult.ok();
    }

    @GetMapping("/{wd}/search")
    public JsonResult selectSearch(@PathVariable String wd){
        System.out.println("调用了搜索");
        return JsonResult.ok(contentMapper.selectContentByWd(wd));
    }

    @GetMapping("{type}/list")
    public JsonResult selectList(@PathVariable int type){
        System.out.println("调用了列表");
        return JsonResult.ok(contentMapper.selectContentByType(type));
    }

    @GetMapping("/{id}/detail")
    public JsonResult selectDetail(@PathVariable Long id){
        System.out.println("调用了详情");
        contentMapper.updateViewCountById(id);
        return JsonResult.ok(contentMapper.selectContentDetailById(id));

    }

    @PostMapping("/{id}/add-like")
    public JsonResult updateLikeCountById(@PathVariable Long id){
        System.out.println("调用了点赞");
        contentMapper.updateLikeCountById(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id}/reduce-like")
    public JsonResult reduceLikeCountById(@PathVariable Long id){
        System.out.println("调用了取消点赞");
        contentMapper.reduceLikeCountById(id);
        return JsonResult.ok();
    }

}
