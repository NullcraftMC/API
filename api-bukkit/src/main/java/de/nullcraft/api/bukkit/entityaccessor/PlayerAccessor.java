package de.nullcraft.api.bukkit.entityaccessor;

import com.avaje.ebean.EbeanServer;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import de.nullcraft.api.bukkit.entities.PlayerEntity;
import org.bukkit.OfflinePlayer;

import java.util.concurrent.TimeUnit;

/**
 * Cached player database accessor.
 *
 * @author maxikg <me@maxikg.de>
 */
public class PlayerAccessor {

    private static volatile PlayerAccessor instance;

    private final EbeanServer server;
    private final Cache<OfflinePlayer, PlayerEntity> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<OfflinePlayer, PlayerEntity>() {
                public PlayerEntity load(OfflinePlayer player) throws Exception {
                    return server.find(PlayerEntity.class)
                            .where()
                            .eq("ign", player.getName())
                            .findUnique();
                }
            });

    /**
     * Constructor.
     *
     * @param server The {@link com.avaje.ebean.EbeanServer} from which the entity will be retrieved
     */
    public PlayerAccessor(EbeanServer server) {
        this.server = server;
    }

    /**
     * Gets the {@link com.avaje.ebean.EbeanServer} from which the entity will be retrieved.
     *
     * @return The {@link com.avaje.ebean.EbeanServer}
     */
    public EbeanServer getServer() {
        return server;
    }

    /**
     * Returns a player from database or cache. If it does not exists it will be created.
     *
     * @param player The player to search for
     * @return The {@link PlayerEntity} or null if an error occurs
     */
    public PlayerEntity getPlayer(OfflinePlayer player) {
        PlayerEntity entity = null;

        try {
            entity = cache.get(player);
        } catch (Exception e) {
            return null;
        }

        if (entity == null) {
            entity = new PlayerEntity();
            entity.setIgn(player.getName());
            server.insert(entity);
        }

        return entity;
    }

    /**
     * Deletes the {@code player} from cache.
     *
     * @param player The player which should delete from cache.
     */
    public void invalidate(OfflinePlayer player) {
        cache.invalidate(player);
    }

    /**
     * Sets the public visible singleton instance.
     *
     * @param instance The instance
     */
    public static void setInstance(PlayerAccessor instance) {
        PlayerAccessor.instance = instance;
    }

    /**
     * Returns the public visible singleton instance.
     *
     * @return The singleton instance
     */
    public static PlayerAccessor getInstance() {
        if (instance == null)
            throw new IllegalStateException("Instance not initialized.");

        return instance;
    }
}
