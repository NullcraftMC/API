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

    /**
     * Konstruktor.
     */
    public PlayerSet() {
    }

    /**
     * Konstruktor mit einer vorgefertigete {@link java.util.Collection} an {@link org.bukkit.entity.Player}n
     *
     * @param players Die initialien {@link org.bukkit.entity.Player}
     */
    public PlayerSet(Collection<? extends Player> players) {
        synchronized (this.players) {
            this.players.addAll(players);
        }
    }

    /**
     * Sendet eine Nachricht.
     *
     * @param message Die zu sendende Nachricht
     */
    public void sendMessage(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.sendMessage(message);
        }
    }

    /**
     * Sendet mehrere Nachrichten.
     *
     * @param messages Die zu sendende Nachrichten
     */
    public void sendMessage(String[] messages) {
        Preconditions.checkNotNull(messages);

        synchronized (players) {
            for (Player player : players)
                player.sendMessage(messages);
        }
    }

    /**
     * Teleportiert alle {@link org.bukkit.entity.Player} zu einer bestimmten {@link org.bukkit.Location}.
     *
     * @param location Die Ziel-{@link org.bukkit.Location}
     */
    public void teleport(Location location) {
        Preconditions.checkNotNull(location);

        synchronized (players) {
            for (Player player : players)
                player.teleport(location);
        }
    }

    /**
     * Teleportiert alle {@link org.bukkit.entity.Player} zu einem bestimmten {@link org.bukkit.entity.Entity}.
     *
     * @param entity Das Ziel-{@link org.bukkit.entity.Entity}
     */
    public void teleport(Entity entity) {
        Preconditions.checkNotNull(entity);

        synchronized (players) {
            for (Player player : players)
                player.teleport(entity);
        }
    }

    /**
     * Kickt alle {@link org.bukkit.entity.Player} mit einer angegebenen Begr&uuml;ndung.
     *
     * @param message Die Begr&uuml;ndung
     */
    public void kickPlayer(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.kickPlayer(message);
        }
    }

    /**
     * Sendet im Namen aller {@link org.bukkit.entity.Player} eine Nachricht.
     *
     * @param message Die zu sendende Nachricht
     */
    public void chat(String message) {
        Preconditions.checkNotNull(message);

        synchronized (players) {
            for (Player player : players)
                player.chat(message);
        }
    }

    /**
     * Pr&uuml;ft, ob der angegebene {@link org.bukkit.entity.Player} teil dieses {@code PlayerSet}s ist.
     *
     * @param player Der zu pr&uuml;fende {@link org.bukkit.entity.Player}
     * @return true, sollte der Spieler Teil des {@code PlayerSet}s, andernfalls false
     */
    public boolean contains(Player player) {
        synchronized (players) {
            return players.contains(player);
        }
    }

    /**
     * Pr&uuml;ft, ob ein Spieler mit dem angegebenen Namen teil dieses {@code PlayerSet}s ist.
     *
     * @param playername Der zu pr&uuml;fende Name
     * @return true, sollte ein Spieler mit gleichem Namen Teil des {@code PlayerSet}s, andernfalls false
     */
    public boolean contains(String playername) {
        synchronized (players) {
            for (Player player : players)
                if (playername.equals(player.getName()))
                    return true;
        }

        return false;
    }

    /**
     * F&uuml;gt einen neuen Spieler dem {@code PlayerSet} hinzu.
     *
     * @param player Der hinzuzuf&uuml;gende Spieler
     * @return true, sollten sich Datens&auml;tze ver&auml;ndert haben, andernfalls false
     */
    public boolean addPlayer(Player player) {
        synchronized (players) {
            return players.add(player);
        }
    }

    /**
     * Entfernt einen Spieler aus dem {@code PlayerSet}.
     *
     * @param player Der zu entfernende Spieler
     * @return true, sollten sich Datens&auml;tze ver&auml;ndert haben, andernfalls false
     */
    public boolean removePlayer(Player player) {
        synchronized (players) {
            return players.remove(player);
        }
    }
}
