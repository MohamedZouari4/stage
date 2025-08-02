package com.example.gestionstock.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy; // Updated import
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
        config.setInstanceName("hazelcast-instance");

        // Configure eviction policy
        EvictionConfig evictionConfig = new EvictionConfig()
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE) // Updated policy
                .setSize(200) // Max entries (200)
                .setEvictionPolicy(EvictionPolicy.LRU); // Eviction strategy

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("stockCache")
                .setEvictionConfig(evictionConfig) // Set eviction config
                .setTimeToLiveSeconds(3600);

        config.addMapConfig(mapConfig);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return com.hazelcast.core.Hazelcast.newHazelcastInstance(hazelcastConfig());
    }

    @Bean
    public HazelcastCacheManager cacheManager(HazelcastInstance instance) {
        return new HazelcastCacheManager(instance);
    }
}
