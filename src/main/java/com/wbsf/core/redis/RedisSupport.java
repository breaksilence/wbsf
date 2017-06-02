package com.wbsf.core.redis;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

/**
 * ParentDao  操作字符串redis缓存方法
 *            list中的操作全是按照right方式
 */
public class RedisSupport<V> extends RedisTemplate<String, V>{

    /**
     * 缓存value操作
     * @param key 缓存key
     * @param v 缓存的值
     * @param time 过期时间
     */
    protected void cacheValue(String key, V v, long time) {
        ValueOperations<String, V> valueOps =  opsForValue();
        valueOps.set(key, v);
        if (time > 0) 
        	expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 缓存value操作
     * @param key
     * @param v 缓存的值
     */
    protected void cacheValue(String key, V v) {
    	cacheValue(key, v, -1);
    }


    /**
     * 判断是否包含key
     * @param key 缓存key
     * @return 是否包含key的boolean
     */
    protected boolean containsKey(String key) {
    	return hasKey(key);
    }

    /**
     * 获取缓存
     * @param key
     * @return 缓存的值
     */
    protected V getValue(String key) {
	    ValueOperations<String, V> valueOps =  opsForValue();
	    return valueOps.get(key);
    }

    /**
     * 移除缓存
     * @param key 待移除的缓存key
     * @return 返回处理状态
     */
    protected void remove(String key) {
    	delete(key);
    }

    /**
     * 缓存set
     * @param key 缓存key
     * @param v 缓存值
     */
    protected void cacheSet(String key, V v) {
        cacheSet(key, v, -1);
    }

    /**
     * 缓存set
     * @param key 缓存的key 
     * @param v 缓存的值
     * @param time 过期时间
     */
    @SuppressWarnings("unchecked")
	protected void cacheSet(String key, V v, long time) {
        SetOperations<String, V> setOps =  opsForSet();
        setOps.add(key, v);
        if (time > 0) 
        	expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 缓存set
     * @param key 缓存的key
     * @param vSet 缓存的set
     * @param time 过期时间
     */
	@SuppressWarnings("unchecked")
	protected void cacheSet(String key, Set<V> vSet, long time) {
    	Iterator<V> it = vSet.iterator();
        while (it.hasNext()) {
        	opsForSet().add(key, it.next());
        }
        if (time > 0) 
        	expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 缓存set
     * @param key 缓存的key
     * @param vSet 缓存的set
     */
    protected void cacheSet(String key, Set<V> vSet) {
        cacheSet(key, vSet, -1);
    }

    /**
     * 获取缓存set数据
     * @param key
     * @return 缓存的set
     */
    protected Set<V> getSet(String key) {
        SetOperations<String, V> setOps = opsForSet();
        return setOps.members(key);
    }
    
    /**
     * 获取总条数, 可用于分页
     * @param key
     * @return 缓存的大小
     */
    protected long getSetSize(String key) {
        SetOperations<String, V> setOps =  opsForSet();
        return setOps.size(key);
    }
    
    /**
     * list缓存
     * @param key 缓存的key
     * @param v 缓存的值
     * @param time 过期时间
     */
    protected void cacheList(String key, V v, long time) {
        ListOperations<String, V> listOps =  opsForList();
        listOps.rightPush(key, v);
        if (time > 0) 
        	expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 缓存list
     * @param key 缓存的key
     * @param v 缓存的值
     */
    protected void cacheList(String key, V v) {
        cacheList(key, v, -1);
    }

    /**
     * 缓存list
     * @param key 缓存的key
     * @param v 缓存的值
     * @param time 过期时间
     */
    protected void cacheList(String key, List<V> vList, long time) {
        ListOperations<String, V> listOps =  opsForList();
        listOps.rightPushAll(key, vList);
        if (time > 0) expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 缓存list
     * @param key 缓存的key
     * @param v 缓存的值
     */
    protected void cacheList(String k, List<V> vList) {
       cacheList(k, vList, -1);
    }

    /**
     * 获取list缓存
     * @param key 缓存的key
     * @param start 开始
     * @param end 结束
     * @return 获取缓存list
     */
    protected List<V> getList(String key, long start, long end) {
        ListOperations<String, V> listOps =  opsForList();
        return listOps.range(key, start, end);
    }

    /**
     * 获取总条数, 可用于分页
     * @param key 缓存的key
     * @return list缓存的长度
     */
    protected long getListSize(String key) {
        ListOperations<String, V> listOps =  opsForList();
        return listOps.size(key);
    }

    /**
     * 移除list缓存
     * @param key
     */
    protected void removeOneOfList(String key) {
        ListOperations<String, V> listOps =  opsForList();
        listOps.rightPop(key);
    }
    
    /**
     * 获取缓存
     * @param key 缓存key
     * @param cacheMap 
     */
    protected void cacheMap(String key, Map<?,V> cacheMap){
    	cacheMap(key, cacheMap, -1);
    }
    
    /**
     * 缓存Map
     * @param key
     * @param HK
     * @param hv
     */
    protected void cacheMap(String key, Object HK ,V hv){
    	cacheMap(key, HK, hv, -1);
    }
    
    /**
     * 缓存map
     * @param key 缓存key
     * @param HK 缓存Map的key
     * @param hv 缓存Map的value
     * @param time 过期时间
     */
    protected void cacheMap(String key, Object HK ,V hv, long time){
    	opsForHash().put(key, HK, hv);
    	if (time > 0) 
    		expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 
     * @param key 缓存key
     * @param cacheMap 缓存Map
     * @param time 过期时间
     */
    protected void cacheMap(String key, Map<?,V> cacheMap, long time){
    	opsForHash().putAll(key, cacheMap);
    	if (time > 0) 
    		expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 缓存Map
     * @param key
     * @return
     */
    protected Map<?,V> getMap(String key){
    	HashOperations<String,?,V> hashOperations = opsForHash();
    	return hashOperations.entries(key);
    }
    
    /**
     * 获取缓存的size
     * @param key
     * @return 缓存的Map的实际size
     */
    protected Long getMapSize(String key){
    	return opsForHash().size(key);
    }
}
