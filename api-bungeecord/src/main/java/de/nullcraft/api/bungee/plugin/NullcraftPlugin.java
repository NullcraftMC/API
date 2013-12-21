package de.nullcraft.api.bungee.plugin;

import de.nullcraft.api.Timer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author maxikg <me@maxikg.de>
 */
public abstract class NullcraftPlugin extends Plugin {

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

    public void enable() { }

    public void disable() { }

    @Override
    public final void onEnable() {
        timer = new Timer();
        timer.start("main");
        getLogger().info("== Enable " + getDescription().getName() + "... ==");
        enable();
        getLogger().info("== " + getDescription().getName() + " enabled (took " + timer.stop("main") + "ms) ==");
        timer = null;
    }

    @Override
    public final void onDisable() {
        timer = new Timer();
        timer.start("main");
        getLogger().info("== Disable " + getDescription().getName() + "... ==");
        disable();
        getLogger().info("== " + getDescription().getName() + " disabled (took " + timer.stop("main") + "ms) ==");
        timer = null;
    }
}
