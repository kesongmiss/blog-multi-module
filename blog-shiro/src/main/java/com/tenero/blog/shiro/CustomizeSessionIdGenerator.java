package com.tenero.blog.shiro;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author kesong
 * @version V1.0
 * @description 自定义UUID
 * @date 2022/3/25 9:39
 */
public class CustomizeSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "teneroBlog" + UUID.randomUUID().toString().replace("-","");
    }
}
