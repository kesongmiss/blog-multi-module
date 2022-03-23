package com.tenero.blog.service.impl;


import com.tenero.blog.entity.user.BlogUser;
import com.tenero.blog.mapper.user.BlogUserMapper;
import com.tenero.blog.service.BlogUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogUserServiceImpl implements BlogUserService {

    @Resource
    private BlogUserMapper blogUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlogUser record) {
        return blogUserMapper.insert(record);
    }

    @Override
    public int insertSelective(BlogUser record) {
        return blogUserMapper.insertSelective(record);
    }

    @Override
    public BlogUser selectByPrimaryKey(Integer id) {
        return blogUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlogUser record) {
        return blogUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlogUser record) {
        return blogUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public BlogUser queryByUserName(String username) {
        return blogUserMapper.queryByUserName(username);
    }

    @Override
    public BlogUser queryByUserEmail(String username) {

        return blogUserMapper.queryByUserEmail(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer queryRoleByUserName(String username) {
        return blogUserMapper.queryRoleByUserName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> queryPermissionsByRole(Integer roleid) {
        return blogUserMapper.queryPermissionsByRole(roleid);

    }

}
