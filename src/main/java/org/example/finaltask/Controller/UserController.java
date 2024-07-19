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
import java.util.Date;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //

    /**
     * 用户注册接口。
     * 通过接收用户注册信息，检查用户名是否已存在，如果不存在，则将用户信息插入数据库。
     *
     * @param userRegDTO 用户注册数据传输对象，包含用户名等注册信息。
     * @return 如果注册成功，返回成功的JSON结果；如果用户名已存在或参数缺失，返回相应的错误信息。
     */
    @PostMapping("/reg")
    public JsonResult reg(@RequestBody UserRegDTO userRegDTO) {
        System.out.println("调用了注册方法");

        // 检查用户名是否已存在
        UserVO userV0 = userMapper.selectUserByUsername(userRegDTO.getUsername());
        if (userV0 != null) {
            return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);
        }

        // 检查用户名是否为空
        if (userRegDTO.getUsername() == null) {
            return new JsonResult(StatusCode.OPERATION_FAILED);
        }

        // 创建新用户对象，并复制注册DTO中的属性到用户对象中
        User user = new User();
        BeanUtils.copyProperties(userRegDTO, user);
        user.setCreateTime(new Date());
        // 将用户信息插入数据库
        userMapper.insertUser(user);

        return JsonResult.ok();
    }


    /**
     * 处理用户登录请求。
     *
     * @param userLoginDTO 包含用户登录信息的数据传输对象，如用户名和密码。
     * @param session      HTTP会话，用于存储登录后的用户信息。
     * @return 如果登录成功，返回包含用户信息的JsonResult；如果登录失败，返回相应的错误信息。
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {
        // 打印日志信息，记录登录方法被调用。
        System.out.println("调用了登录方法");

        // 根据用户名查询用户信息。
        UserVO userVO = userMapper.selectUserByUsername(userLoginDTO.getUsername());

        // 检查用户是否存在，如果不存在，返回用户名错误的JsonResult。
        // 根据用户名查询用户是否存在
        if (userVO == null) {
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }

        // 检查密码是否正确，如果密码不匹配，返回密码错误的JsonResult。
        if (!userVO.getPassword().equals(userLoginDTO.getPassword())) {
            return new JsonResult(StatusCode.PASSWORD_ERROR);
        }

        // 如果用户名和密码验证通过，将用户信息存储在会话中，以保持用户登录状态。
        session.setAttribute("user", userVO);

        // 返回登录成功的JsonResult，包含用户信息。
        return JsonResult.ok(userVO);
    }

    @GetMapping("/logout")
    public JsonResult logout(HttpSession session) {
        System.out.println("调用了登出方法");
        session.removeAttribute("user");
        return JsonResult.ok();
    }

    /**
     * 更新用户信息的接口。
     * 接收来自前端的用户更新请求，更新用户的数据，并处理相关的图片更新逻辑。
     *
     * @param userUpdateDTO 包含用户更新信息的数据传输对象，包括用户ID、图片URL等。
     * @return 返回一个表示操作结果的JsonResult对象，操作成功则返回OK。
     */
    @PostMapping("/update")
    public JsonResult update(@RequestBody UserUpdateDTO userUpdateDTO) {
        System.out.println("调用了更新方法");
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);

        if (user.getImgUrl() != null) {
            System.out.println("照片正在更改");
            String imgUrl = userMapper.selectImgUrlById(user.getId());
            if (imgUrl != null) {
                new File(imgUrl).delete();
            }
        } else {
            System.out.println("照片没有更改");
        }

        userMapper.updateUserById(user);
        return JsonResult.ok();
    }

    @PostMapping("{id}/add-follower")
    public JsonResult addFollowCountById(@PathVariable Long id){
        System.out.println("调用了关注方法");
        userMapper.addFollowCountById(id);
        return JsonResult.ok();
    }

    @PostMapping("{id}/reduce-follower")
    public JsonResult reduceFollowCountById(@PathVariable Long id){
        System.out.println("调用了取消关注方法");
        userMapper.reduceFollowCountById(id);
        return JsonResult.ok();
    }
}






