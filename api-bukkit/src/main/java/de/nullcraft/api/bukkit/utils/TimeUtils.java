package de.nullcraft.api.bukkit.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class TimeUtils {

    /**
     * Die Anzahl der Ticks pro Sekunde, mit denen der Server arbeitet.
     */
    public static final long TICKS_PER_SECOND = 20;

    /**
     * Konvertiert eine beliebige Zeit mit angegebener Zeiteinheit zu Ticks um.
     *
     * @param time Die Zeit
     * @param unit Die Zeiteinheit
     * @return Die in Ticks konvertierte Zeit
     */
    public static long toTicks(long time, TimeUnit unit) {
        return unit.toSeconds(time) * TICKS_PER_SECOND;
    }

    private TimeUtils() { }
}
