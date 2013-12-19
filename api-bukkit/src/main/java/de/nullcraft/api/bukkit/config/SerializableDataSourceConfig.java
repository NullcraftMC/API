package de.nullcraft.api.bukkit.config;

import com.avaje.ebean.config.DataSourceConfig;
import com.google.common.collect.Maps;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class SerializableDataSourceConfig extends DataSourceConfig implements ConfigurationSerializable {

    private static final SerializableDataSourceConfig UNIT_CONFIG = new SerializableDataSourceConfig();
    private static final String CONFIG_URL = "url";
    private static final String CONFIG_DRIVER = "driver";
    private static final String CONFIG_USERNAME = "username";
    private static final String CONFIG_PASSWORD = "password";
    private static final String CONFIG_HEARTBEATSQL = "heartbeatsql";
    private static final String CONFIG_MINCONNECTIONS = "minConnections";
    private static final String CONFIG_MAXCONNECTIONS = "maxConnections";
    private static final String CONFIG_MAXINACTIVETIMESECS = "maxInactiveTimeSecs";

    public Map<String, Object> serialize() {
        Map<String, Object> data = Maps.newHashMap();

        data.put(CONFIG_URL, getUrl());
        data.put(CONFIG_DRIVER, getDriver());
        data.put(CONFIG_USERNAME, getUsername());
        data.put(CONFIG_PASSWORD, getPassword());
        data.put(CONFIG_HEARTBEATSQL, getHeartbeatSql());
        data.put(CONFIG_MINCONNECTIONS, getMinConnections());
        data.put(CONFIG_MAXCONNECTIONS, getMaxConnections());
        data.put(CONFIG_MAXINACTIVETIMESECS, getMaxInactiveTimeSecs());

        return data;
    }

    public static SerializableDataSourceConfig deserialize(Map<String, Object> data) {
        SerializableDataSourceConfig config = new SerializableDataSourceConfig();
        ConfigCaster caster = new ConfigCaster(data);

        config.setUrl(caster.require(CONFIG_URL, String.class));
        config.setDriver(caster.require(CONFIG_DRIVER, String.class));
        config.setUsername(caster.require(CONFIG_USERNAME, String.class));
        config.setPassword(caster.require(CONFIG_PASSWORD, String.class));
        config.setHeartbeatSql(caster.get(CONFIG_HEARTBEATSQL, String.class, "SELECT 1"));
        config.setMinConnections(caster.get(CONFIG_MINCONNECTIONS, Integer.class, UNIT_CONFIG.getMinConnections()));
        config.setMaxConnections(caster.get(CONFIG_MAXCONNECTIONS, Integer.class, UNIT_CONFIG.getMaxConnections()));
        config.setMaxInactiveTimeSecs(caster.get(CONFIG_MAXINACTIVETIMESECS, Integer.class, UNIT_CONFIG.getMaxInactiveTimeSecs()));

        return config;
    }
}
