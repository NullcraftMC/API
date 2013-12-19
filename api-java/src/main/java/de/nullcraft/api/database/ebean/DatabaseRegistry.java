package de.nullcraft.api.database.ebean;

import com.avaje.ebean.EbeanServer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.nullcraft.api.collection.ImmutableUtils;

import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class DatabaseRegistry {

    private static volatile DatabaseRegistry instance;

    private final Map<String, EbeanServer> store = Maps.newHashMap();

    public void addServer(EbeanServer server) {
        synchronized (store) {
            store.put(server.getName(), server);
        }
    }

    public void addServers(Iterable<EbeanServer> servers) {
        synchronized (store) {
            for (EbeanServer server : servers)
                store.put(server.getName(), server);
        }
    }

    public void removeServer(String name) {
        synchronized (store) {
            store.remove(name);
        }
    }

    public EbeanServer getServer(String key) {
        synchronized (store) {
            return store.get(key);
        }
    }

    public ImmutableList<EbeanServer> getServers() {
        synchronized (store) {
            return ImmutableUtils.immutableList(store.values());
        }
    }

    public static DatabaseRegistry getInstance() {
        return instance;
    }

    public static void setInstance(DatabaseRegistry instance) {
        DatabaseRegistry.instance = instance;
    }
}
