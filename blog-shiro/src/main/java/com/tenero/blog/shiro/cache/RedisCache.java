package com.tenero.blog.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaowenbang
 * @version V1.0
 * @description Redis缓存
 * @date 2019/8/17 22:53
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String keyPrefix = "mt:session:";

    RedisTemplate<String, Object> redisTemplate;

    /**
     * Returns the Redis session keys prefix.
     *
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key prefix.
     *
     * @param keyPrefix The prefix
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * 通过一个JedisManager实例构造RedisCache
     */
    public RedisCache() {

    }

    /**
     * Constructs a cache instance with the specified Redis manager and using a custom key prefix.
     *
     * @param prefix The Redis key prefix
     */
    public RedisCache(String prefix, RedisTemplate<String, Object> redisTemplate) {
        this.keyPrefix = prefix;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            }
            return (V) redisTemplate.opsForValue().get(getKey(key));
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        try {
            redisTemplate.opsForValue().set(getKey(key), value, 24, TimeUnit.HOURS);
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
            V previous = get(key);
            redisTemplate.delete(getKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("session clear all：");
        try {
//            Set<byte[]> keys = redisTemplate.keys(getKey((K)"*").toString());
//            redisTemplate.delete(keys);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        try {
//            Set<byte[]> keys = redisTemplate.keys(getKey((K)"*").toString());
//            return keys.size();
            return 0;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set<K> keys() {
        try {
            /*Set<byte[]> keys = redisTemplate.keys(getKey((K)"*").toString());
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            }else{
                Set<K> newKeys = new HashSet<K>();
                for(byte[] key:keys){
                    newKeys.add((K)key);
                }
                return newKeys;
            }*/
            return Collections.emptySet();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        return Collections.emptySet();
        /*try {
            Set<byte[]> keys = redisTemplate.keys(getKey((K)"*").toString());
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    V value = fetch((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }*/
    }

    /**
     * 获得byte[]型的key
     *
     * @param key
     * @return
     */
    private String getKey(K key) {
        if (key instanceof String) {
            return this.keyPrefix + key;
        } else {
            return keyPrefix + key;
        }
    }
}
