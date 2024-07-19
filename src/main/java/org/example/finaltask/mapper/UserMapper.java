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
    int updateUserById(User user);

    //根据id查询用户头像
    String selectImgUrlById(Long id);

    //根据id更改用户点赞数
    int addLikeCountById(Long id);

    //根据id更改用户关注数
    int addFollowCountById(Long id);

    //根据id更改用户内容数
    int addContentNumById(Long id);

    int reduceContentNumById(Long id);

    int reduceFollowCountById(Long id);

    int reduceLikeCountById(Long id);




}
