package de.nullcraft.api.bukkit.entities;

import javax.persistence.*;

/**
 * Database entity for players.
 *
 * @author maxikg <me@maxikg.de>
 */
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private int id;

    @Column(length = 16, unique = true)
    private String ign;

    /**
     * Set the id.
     *
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the id.
     *
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ingame name (ign).
     *
     * @param ign The ingame name
     */
    public void setIgn(String ign) {
        this.ign = ign;
    }

    /**
     * Returns the ingame name (ign).
     *
     * @return The ingame name
     */
    public String getIgn() {
        return ign;
    }
}
