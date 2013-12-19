package de.nullcraft.api.bukkit.plugin;

import de.nullcraft.api.Timer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Modified {@link org.bukkit.plugin.java.JavaPlugin} for Nullcraft plugins.
 *
 * @author maxikg <me@maxikg.de>
 */
public abstract class NullcraftPlugin extends JavaPlugin {

    private Timer timer;

    /**
     * Returns the plugins {@link de.nullcraft.api.Timer}.
     *
     * @return The plugins {@link de.nullcraft.api.Timer}
     */
    protected Timer getTimer() {
        if (timer == null)
            throw new IllegalStateException("Timer is only accessible during start or stop.");

        return timer;
    }

    /**
     * Will be called at startup.
     *
     * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
     */
    public void enable() { }

    /**
     * Will be called at shutdown.
     *
     * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
     */
    public void disable() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onEnable() {
        timer = new Timer();
        timer.start("main");
        getLogger().info(StringUtils.join("== Enable ", getName(), "... =="));
        enable();
        getLogger().info(StringUtils.join("== ", getName(), " enabled (took ", timer.stop("main"), "ms) =="));
        timer = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onDisable() {
        timer = new Timer();
        timer.start("main");
        getLogger().info(StringUtils.join("== Disable ", getName(), "... =="));
        disable();
        getLogger().info(StringUtils.join("== ", getName(), " disabled (took ", timer.stop("main"), "ms) =="));
        timer = null;
    }
}
