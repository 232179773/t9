package com.t9.system.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.t9.system.service.SystemConfigService;

/**
* @功能/模块 ：缓存
* @author liaolq
* @version 1.0 2013-7-11
* @类描述  memcached缓存处理类
* @修订历史：
*/
@Component("memcachedUtils")
public class MemcachedUtils {
	
	private MemCachedClient cachedClient = new MemCachedClient("CACHE");

	public void init() {
        // 创建一个Socked连接池实例
		SockIOPool pool = SockIOPool.getInstance("CACHE");
		// 设置缓存服务器列表
        String[] servers = new String[]{"10.0.2.40:11211"};

        // 设置服务器权重
        String[] weightsStr = new String[]{"1"};
        
		Integer[] weights = new Integer[weightsStr.length];
        for (int i = 0; i < weightsStr.length; i++) {
            weights[i] = Integer.parseInt(weightsStr[i]);
        }

        // 向连接池设置服务器和权重
        pool.setServers(servers);
        pool.setWeights(weights);

        
        pool.setInitConn(3);   
        pool.setMinConn(3);   
        pool.setMaxConn(5);   
        pool.setMaxIdle(3);   
        //设置主线程睡眠时间，每30秒苏醒一次，维持连接池大小   
        pool.setMaintSleep(30);   
        //关闭套接字缓存   
        pool.setNagle(false);   
        //连接建立后的超时时间   
        pool.setSocketTO(3000);   
        //连接建立时的超时时间   
        pool.setSocketConnectTO(0);   
        //开启故障切换
        pool.setFailover(true);
        pool.setFailback(true);
        //初始化连接池   
        pool.initialize();
    }
	
	/**obj存储到cached
	 * @param key
	 * @param obj
	 * @param expiry 过期时间
	 */
	public void setCachedObject(String key, Object obj, Date expiry) {
		if(expiry == null) {
			cachedClient.set(key, obj);
		} else {
			cachedClient.set(key, obj, expiry);
		}
	}
	
	public Object getCachedObjectByKey(String key) {
		return cachedClient.get(key);
	}
	
	public void deleteCached(String key) {
		cachedClient.delete(key);
	}
	
}
