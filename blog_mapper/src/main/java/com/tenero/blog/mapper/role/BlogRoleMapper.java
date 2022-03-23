package com.tenero.blog.mapper.role;

import com.tenero.blog.entity.role.BlogRole;
import org.apache.ibatis.annotations.Mapper;


/**
 * @description
 * @author kesong
 * @date 2022/3/16 16:22
 */

@Mapper
public interface BlogRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogRole record);

    int insertSelective(BlogRole record);

    BlogRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogRole record);

    int updateByPrimaryKey(BlogRole record);
}