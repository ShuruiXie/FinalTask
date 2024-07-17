package org.example.finaltask.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.User;
import org.example.finaltask.pojo.vo.UserVO;

@Mapper
public interface UserMapper {
    //插入用户
    int insertUser(User user);

    //根据用户名查询用户
    UserVO selectUserByUsername(String username);

    //根据id更改用户信息
    int updateUserById(Long id);

    //根据id查询用户头像
    String selectImgUrlById(Long id);

    //根据id更改用户点赞数
    int updateLikeCountById(Long id);
}
