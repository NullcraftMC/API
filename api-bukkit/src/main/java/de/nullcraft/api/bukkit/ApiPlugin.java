package de.nullcraft.api.bukkit;

import de.nullcraft.api.bukkit.config.SerializableDataSourceConfig;
import de.nullcraft.api.bukkit.config.SerializableServerConfig;
import de.nullcraft.api.bukkit.entityaccessor.PlayerAccessor;
import de.nullcraft.api.bukkit.plugin.NullcraftPlugin;
import de.nullcraft.database.ebean.DatabaseRegistry;
import de.nullcraft.database.ebean.EbeanUtils;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ApiPlugin extends NullcraftPlugin {

    private static ApiPlugin instance;

    private ApiConfig configuration;

    @Override
    public void enable() {
        ConfigurationSerialization.registerClass(SerializableServerConfig.class);
        ConfigurationSerialization.registerClass(SerializableDataSourceConfig.class);
        ConfigurationSerialization.registerClass(ApiConfig.class);

        configuration = (ApiConfig) getConfig().get("api-configuration");
        if (configuration == null) {
            getLogger().info("Configuration file not found. Generating a new one...");
            configuration = new ApiConfig();
            getConfig().set("api-configuration", configuration);
            saveConfig();
        }

        getTimer().start("dbsetup");
        if (configuration.isUseDatabase()) {
            DatabaseRegistry registry = new DatabaseRegistry();
            registry.addServers(EbeanUtils.construct(configuration.getDbConfigs(), getClassLoader()));
            DatabaseRegistry.setInstance(registry);

            PlayerAccessor playerAccessor = new PlayerAccessor(registry.getServer(configuration.getServiceConnection()));
            PlayerAccessor.setInstance(playerAccessor);
        }
        getLogger().info("Databases initialized (took " + getTimer().stop("dbsetup") + " ms).");
    }

    public ApiConfig getConfiguration() {
        return configuration;
    }

    public static ApiPlugin getInstance() {
        return instance;
    }

    public static void setInstance(ApiPlugin instance) {
        ApiPlugin.instance = instance;
    }
}
