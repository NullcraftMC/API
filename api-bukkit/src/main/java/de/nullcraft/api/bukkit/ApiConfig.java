package de.nullcraft.api.bukkit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.nullcraft.api.bukkit.config.ConfigCaster;
import de.nullcraft.api.bukkit.config.SerializableServerConfig;
import de.nullcraft.api.collection.ImmutableUtils;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.List;
import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ApiConfig implements ConfigurationSerializable {

    private static final String CONFIG_DATABASECONS = "dbConnections";
    private static final String CONFIG_SERVICEDB = "serviceDb";

    private final List<SerializableServerConfig> dbConfigs  = Lists.newArrayList();
    private String serviceConnection;

    public void addDatabaseConfig(SerializableServerConfig config) {
        synchronized (dbConfigs) {
            dbConfigs.add(config);
        }
    }

    public void removeDatabaseConfig(SerializableServerConfig config) {
        synchronized (dbConfigs) {
            dbConfigs.remove(config);
        }
    }

    public ImmutableList<SerializableServerConfig> getDbConfigs() {
        return ImmutableUtils.immutableList(dbConfigs);
    }

    public void setServiceConnection(String serviceConnection) {
        this.serviceConnection = serviceConnection;
    }

    public String getServiceConnection() {
        return serviceConnection;
    }

    public synchronized Map<String, Object> serialize() {
        Map<String, Object> data = Maps.newHashMap();

        data.put(CONFIG_DATABASECONS, dbConfigs);
        data.put(CONFIG_SERVICEDB, serviceConnection);

        return data;
    }

    public static ApiConfig deserialize(Map<String, Object> data) {
        ApiConfig config = new ApiConfig();
        ConfigCaster caster = new ConfigCaster(data);

        config.dbConfigs.addAll(caster.requireList(CONFIG_DATABASECONS, SerializableServerConfig.class));
        config.setServiceConnection(caster.require(CONFIG_SERVICEDB, String.class));

        return config;
    }
}
