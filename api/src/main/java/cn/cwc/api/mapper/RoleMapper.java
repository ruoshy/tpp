package cn.cwc.api.mapper;

import cn.cwc.api.entity.Role;
import cn.cwc.api.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("SELECT * FROM role")
    List<Role> findAll();

    Role findById(Integer roleId);

    Role findByUser(User user);

    int insert(Role role);

    int update(Role role);
}
