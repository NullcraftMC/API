package de.nullcraft.api.bukkit.config;

import com.avaje.ebean.config.ServerConfig;
import com.google.common.collect.Maps;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class SerializableServerConfig extends ServerConfig implements ConfigurationSerializable {

    private static final ServerConfig UNIT_CONFIG = new ServerConfig();
    private static final String CONFIG_NAME  = "name";
    private static final String CONFIG_DEBUGSQL = "debugSql";
    private static final String CONFIG_DATASOURCE = "datasource";

    public SerializableServerConfig() {
        normalize();
        setDataSourceConfig(new SerializableDataSourceConfig());
    }

    public void normalize() {
        setRegister(false);
        setDefaultServer(false);
    }

    public Map<String, Object> serialize() {
        Map<String, Object> data = Maps.newHashMap();

        data.put(CONFIG_NAME, getName());
        data.put(CONFIG_DEBUGSQL, isDebugSql());
        data.put(CONFIG_DATASOURCE, getDataSourceConfig());

        return data;
    }

    public static SerializableServerConfig deserialize(Map<String, Object> data) {
        SerializableServerConfig config = new SerializableServerConfig();
        ConfigCaster caster = new ConfigCaster(data);

        config.setName(caster.require(CONFIG_NAME, String.class));
        config.setDebugSql(caster.get(CONFIG_DEBUGSQL, Boolean.class, UNIT_CONFIG.isDebugSql()));
        config.setDataSourceConfig(caster.require(CONFIG_DATASOURCE, SerializableDataSourceConfig.class));

        return config;
    }
}
