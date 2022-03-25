package com.tenero.blog.mapper.user;



import com.tenero.blog.entity.user.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogUserMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(BlogUser record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(BlogUser record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    BlogUser selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(BlogUser record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(BlogUser record);

    /**
     * 按用户名查找用户
     * @param username
     * @return
     */
    BlogUser queryByUserName(@Param("username") String username);

    BlogUser queryByUserEmail(@Param("username") String username);

    Integer queryRoleByUserName(@Param("username") String username);

    List<String> queryPermissionsByRole(@Param("roleid") Integer roleid);
}