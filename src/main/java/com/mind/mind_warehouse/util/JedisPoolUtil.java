package com.mind.mind_warehouse.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisPoolUtil {
    private static volatile JedisPoolUtil instance ;

    private static JedisPool jedisPool;
    private static int maxtotal;
    private static int maxwaitmillis;
    private static String host;
    private static int port;

    private JedisPoolUtil(){}

    public static JedisPoolUtil getInstance(){
        if (instance == null){
            synchronized (JedisPoolUtil.class){
                if (instance == null){
                    instance = new JedisPoolUtil();
                }
            }
        }

        return instance ;
    }

    /*读取jedis.properties配置文件*/
    static{
        ResourceBundle rb = ResourceBundle.getBundle("redis");
        maxtotal = Integer.parseInt(rb.getString("maxtotal"));
        maxwaitmillis = Integer.parseInt(rb.getString("maxwaitmillis"));
        host = rb.getString("host");
        port = Integer.parseInt(rb.getString("port"));

        //给连接池 赋值数据
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxtotal);
        config.setMaxWaitMillis(maxwaitmillis);
        jedisPool = new JedisPool(config,host,port);
    }

    /*获取jedis*/
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    /*关闭Jedis*/
    public void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

}
