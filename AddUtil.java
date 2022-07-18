package com.ly.train.sb.config;

import com.ly.train.sb.common.utils.GetRedisConfig;
import com.ly.train.sb.common.utils.RedisStringUtil;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * RedisConfig
 * 最终目标为：StringRedisTemplate或者RedisTemplate
 * <p>
 * 需要 ： RedisConnectionFactory
 * <p>
 * 其实现为 ： LettuceConnectionFactory
 * <p>
 * 因实现sentinel，所以需要构造参数：
 * <p>
 * 1、RedisClusterConfiguration，其配置sentinel-master和ip:host
 * 2、LettuceClientConfiguration，可其配置连接池以及ssl等相关参数（使用其子接口LettucePoolingClientConfiguration）
 * <p>
 * LettucePoolingClientConfiguration.可通过static方法使用builder模式创建：
 * <p>
 * LettucePoolingClientConfiguration.builder().poolConfig(pool).build();
 * 1
 * pool类型GenericObjectPoolConfig为common-pool2线程池
 *
 * @author John Chen
 * @date 2018/10/30
 */
@Configuration
public class RedisConfig {
    @Value("${tms.project.id}")
    private String appName;

    //region UVPV用Redis构建

    /**
     * UVPVRedis工具类构建
     *
     * @param stringRedisTemplateUvPv StringRedisTemplate实现
     * @return 返回工具类实例
     */
    @Bean
    public RedisStringUtil redisStringUtilUvPv(StringRedisTemplate stringRedisTemplateUvPv) {
        return new RedisStringUtil(stringRedisTemplateUvPv);
    }

    /**
     * 配置StringRedisTemplate
     * 【Redis配置最终一步】
     *
     * @param lettuceConnectionFactoryUvPv redis连接工厂实现
     * @return 返回一个可以使用的StringRedisTemplate实例
     */
    @Bean
    public StringRedisTemplate stringRedisTemplateUvPv(@Qualifier("lettuceConnectionFactoryUvPv") RedisConnectionFactory lettuceConnectionFactoryUvPv) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactoryUvPv);
        return stringRedisTemplate;
    }


    /**
     * 为RedisTemplate配置Redis连接工厂实现
     * LettuceConnectionFactory实现了RedisConnectionFactory接口
     * UVPV用Redis
     *
     * @return 返回LettuceConnectionFactory
     */
    @Bean(destroyMethod = "destroy")
    //这里要注意的是，在构建LettuceConnectionFactory 时，如果不使用内置的destroyMethod，可能会导致Redis连接早于其它Bean被销毁
    public LettuceConnectionFactory lettuceConnectionFactoryUvPv() throws Exception {
        //公司内部通过Redis名称走组件获取到Redis的具体配置
        String redisName = "xxxxxx";
        return new LettuceConnectionFactory(redisClusterConfiguration(redisName), getLettuceClientConfiguration(genericObjectPoolConfig(20, 10, 100)));
    }
    //endregion


    //region 基础配置

    /**
     * 通过Redis名称获取连接配置
     *
     * @param redisName Redis名称。在申请Redis时填入的
     * @return 返回Redis集群的具体配置
     */
    private RedisClusterConfiguration redisClusterConfiguration(String redisName) throws Exception {
        return GetRedisConfig.getRedisClusterConfiguration(redisName);
    }

    /**
     * 通过Redis名称获取连接配置
     *
     * @param redisName Redis名称。在申请Redis时填入的
     * @return 返回Redis集群的具体配置
     */
    private RedisClusterConfiguration redisClusterConfiguration(String redisName, int timeOutMS) throws Exception {
        return GetRedisConfig.getRedisClusterConfiguration(redisName, timeOutMS);
    }

    /**
     * 配置LettuceClientConfiguration 包括线程池配置和安全项配置
     *
     * @param genericObjectPoolConfig common-pool2线程池
     * @return lettuceClientConfiguration
     */
    private LettuceClientConfiguration getLettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
        /*
        【重要！！】
        【重要！！】
        【重要！！】
        ClusterTopologyRefreshOptions配置用于开启自适应刷新和定时刷新。如自适应刷新不开启，Redis集群变更时将会导致连接异常！
         */
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                //开启自适应刷新
                .enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS)
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(10))
                //开启定时刷新
                .enablePeriodicRefresh(Duration.ofSeconds(15))
                .build();
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig)
                .clientOptions(ClusterClientOptions.builder().topologyRefreshOptions(topologyRefreshOptions).build())
                //将appID传入连接，方便Redis监控中查看
                .clientName(appName + "_lettuce")
                .build();
    }

    /**
     * 构建Redis连接池
     *
     * @param maxIdle  最大空闲连接数 推荐20
     * @param mixIdle  最小空闲连接数 推荐10
     * @param maxTotal 设置最大连接数，（根据并发请求合理设置）推荐100。
     * @return 返回一个连接池
     */
    private GenericObjectPoolConfig genericObjectPoolConfig(int maxIdle, int mixIdle, int maxTotal) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(mixIdle);
        poolConfig.setMaxTotal(maxTotal);
        //此处可添加其它配置
        return poolConfig;
    }
    //endregion


}

