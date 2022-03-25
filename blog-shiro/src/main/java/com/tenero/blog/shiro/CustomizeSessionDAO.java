package com.tenero.blog.shiro;


import com.alibaba.fastjson.JSON;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kesong
 * @version V1.0
 * @description 实例session，类似于UserDAO
 * @date 2022/3/25 10:14
 */
@Repository
public class CustomizeSessionDAO extends AbstractSessionDAO {
    private static final Logger log = LoggerFactory.getLogger(CustomizeSessionDAO.class);


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private final String prefix = "tenero:blog:session";

    @Value("${spring.redis.shiro.timeout}")
    private int shiroTimeout;


    /**
     * 创建session会话 返回sessionID
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 生成序列化后的SessionId
        Serializable sessionId = generateSessionId(session);
        // 转换打印出来
        String string = JSON.toJSONString(sessionId);
        log.info("获得SessionId {} " + string);
        // 将SessionId 分配到会话中
        assignSessionId(session,sessionId);

        // 先获取redis对value的操作对象,需要先设定key 然后复制key为string
        redisTemplate.boundValueOps(prefix+sessionId).set(session);
//        redisTemplate.opsForValue().set(prefix+session,sessionId,shiroTimeout,TimeUnit.SECONDS);
        return sessionId;
    }

    /**
     * 获取会话id shiro 自定义的方法中会先去线程中获取，线程中没有，在去redis中获取
     * 自定义的直接去redis中获取，上一步已经保存在redis中
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session)redisTemplate.boundValueOps(prefix + sessionId).get();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            throw new UnknownSessionException("session 或者 session为空");
        }
        redisTemplate.boundValueOps(prefix + session.getId()).set(session);

    }

    @Override
    public void delete(Session session) {
        redisTemplate.delete(prefix + session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Object> keys = redisTemplate.keys(prefix);

        if (keys != null) {
            return keys.stream().map(key -> {
                Session s = (Session) redisTemplate.boundValueOps(key).get();
                return s;
            }).collect(Collectors.toList());

        } else {
            return null;
        }

    }
}
