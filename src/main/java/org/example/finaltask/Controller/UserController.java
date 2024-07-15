package org.example.finaltask.Controller;

import org.example.finaltask.mapper.UserMapper;
import org.example.finaltask.pojo.dto.UserLoginDTO;
import org.example.finaltask.pojo.dto.UserRegDTO;
import org.example.finaltask.pojo.dto.UserUpdateDTO;
import org.example.finaltask.pojo.entity.User;
import org.example.finaltask.response.JsonResult;
import org.example.finaltask.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.finaltask.pojo.vo.UserVO;
import javax.servlet.http.HttpSession;
import java.io.File;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/reg")
    public JsonResult reg(@RequestBody UserRegDTO userRegDTO){
        System.out.println("调用了注册方法");
        UserVO userV0 = userMapper.selectUserByUsername(userRegDTO.getUsername());
        if (userV0 != null){
            return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        if(userRegDTO.getUsername()==null){
            return new JsonResult(StatusCode.OPERATION_FAILED);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegDTO,user);
        userMapper.insertUser(user);
        return JsonResult.ok();
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session){
        System.out.println("调用了登录方法");
        UserVO userVO = userMapper.selectUserByUsername(userLoginDTO.getUsername());
        // 根据用户名查询用户是否存在
        if(userVO == null){
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }
        if(!userVO.getPassword().equals(userLoginDTO.getPassword())){
            return new JsonResult(StatusCode.PASSWORD_ERROR);
        }
        session.setAttribute("user", userVO);
        return JsonResult.ok(userVO);
    }

    @GetMapping("logout")
    public JsonResult logout(HttpSession session) {
        session.removeAttribute("user");
        return JsonResult.ok();
    }

    @PostMapping("/update")
    public JsonResult update(@RequestBody UserUpdateDTO userUpdateDTO)
    {

        if(userUpdateDTO.getImgUrl() != null){
            //得到图片的路径
            String imgUrl = userMapper.selectImgUrlById(userUpdateDTO.getId());
            //删除原来的图片
            new File(imgUrl).delete();
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);
        userMapper.updateUserById(user);
        return JsonResult.ok();
    }

}
