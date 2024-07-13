package org.example.finaltask.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.finaltask.pojo.entity.User;
import org.example.finaltask.pojo.vo.UserVO;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    UserVO selectUserByUsername(String username);
    int updateUserById(User user);
    String selectImgUrlById(Long id);

//    /**
//     * 查找user表中所有的用户记录,并将一个个的用户记录信息封装到一个个的UserAdminVO对象中
//     * 最终返回一个集合
//     * @return
//     */
//    List<UserAdminVO> selectAllUser();
//
//    /**练习：
//     *  根据传入的id 删除用户的接口方法和sql
//     *  需要自行实现xml中的删除sql 根据id删除
//     *  然后自行测试,没问题扣666
//     */
//    int deleteUserById(Long id);


}
