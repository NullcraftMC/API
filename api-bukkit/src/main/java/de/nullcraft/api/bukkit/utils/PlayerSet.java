package de.nullcraft.api.bukkit.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

/**
 * A set of {@link org.bukkit.entity.Player}s on which you can apply some actions.
 *
 * @author maxikg <me@maxikg.de>
 */
public class PlayerSet {

    private final Set<Player> players = Sets.newHashSet();

    public PlayerSet() {
    }

    public PlayerSet(Collection<? extends Player> players) {
        synchronized (this.players) {
            this.players.addAll(players);
        }
    }

    public void sendMessage(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.sendMessage(message);
        }
    }

    public void sendMessage(String[] messages) {
        Preconditions.checkNotNull(messages);

        synchronized (players) {
            for (Player player : players)
                player.sendMessage(messages);
        }
    }

    public void teleport(Location location) {
        Preconditions.checkNotNull(location);

        synchronized (players) {
            for (Player player : players)
                player.teleport(location);
        }
    }

    public void teleport(Entity entity) {
        Preconditions.checkNotNull(entity);

        synchronized (players) {
            for (Player player : players)
                player.teleport(entity);
        }
    }

    public void kickPlayer(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.kickPlayer(message);
        }
    }

    public void chat(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.chat(message);
        }
    }

    public boolean contains(Player player) {
        synchronized (players) {
            return players.contains(player);
        }
    }

    public boolean contains(String playername) {
        synchronized (players) {
            for (Player player : players)
                if (playername.equals(player.getName()))
                    return true;
        }

        return false;
    }

    public boolean addPlayer(Player player) {
        synchronized (players) {
            return players.add(player);
        }
    }

    public boolean removePlayer(Player player) {
        synchronized (players) {
            return players.remove(player);
        }
    }
}
