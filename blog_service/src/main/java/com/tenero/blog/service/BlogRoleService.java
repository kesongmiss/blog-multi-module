package com.tenero.blog.service;


import com.tenero.blog.entity.role.BlogRole;


/**
 * @description
 * @author kesong
 * @date 2022/3/16 16:22
 */
public interface BlogRoleService {


    int deleteByPrimaryKey(Integer id);

    int insert(BlogRole record);

    int insertSelective(BlogRole record);

    BlogRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogRole record);

    int updateByPrimaryKey(BlogRole record);

}
