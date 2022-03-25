package com.tenero.blog.service;



import com.tenero.blog.entity.user.BlogUser;

import java.util.List;

public interface BlogUserService {


    int deleteByPrimaryKey(Integer id);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    BlogUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    BlogUser queryByUserName(String username);

    BlogUser queryByUserEmail(String username);

    Integer queryRoleByUserName(String username);

    List<String> queryPermissionsByRole(Integer roleid);
}
