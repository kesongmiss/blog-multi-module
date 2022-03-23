package com.tenero.blog.service.impl;


import com.tenero.blog.entity.role.BlogRole;
import com.tenero.blog.mapper.role.BlogRoleMapper;
import com.tenero.blog.service.BlogRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @description
 * @author kesong
 * @date 2022/3/16 16:22
 */
@Service
public class BlogRoleServiceImpl implements BlogRoleService {

    @Resource
    private BlogRoleMapper blogRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogRole record) {
        return blogRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogRole record) {
        return blogRoleMapper.insertSelective(record);
    }

    @Override
    public BlogRole selectByPrimaryKey(Integer id) {
        return blogRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(BlogRole record) {
        return blogRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(BlogRole record) {
        return blogRoleMapper.updateByPrimaryKey(record);
    }

}
