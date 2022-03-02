package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    User login(User user);

    User findById(Integer userId);

    List<User> findByCinema(Cinema cinema);

    @Insert("INSERT INTO `user`(`name`,sex,phone,role_id)" +
            "VALUES (#{name},#{sex},#{phone},#{roleId})")
    int insert(User user);

    @Update("update `user` set token=#{token} where id=#{id}")
    int updateToken(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);
}
