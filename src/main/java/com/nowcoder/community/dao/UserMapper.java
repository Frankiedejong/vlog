package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 其实就是在UserMapper里的方法和user-mapper里的方法是连接起来的，UserMapper的方法通过在user-mapper里的sql语句直接实现
     * 然后上层，比如Service或者MapperTest创建响应的UserMapper对象来调用对应的方法
     * @param id
     * @return
     */
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
