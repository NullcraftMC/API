package de.nullcraft.api;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class Timer {

    private final Map<String, Long> data = Maps.newHashMap();

    public void start(String key) {
        long current = System.currentTimeMillis();

        synchronized (data) {
            data.put(key, current);
        }
    }

    public Long current(String key) {
        long current = System.currentTimeMillis();

        synchronized (data) {
            Long start = data.get(key);

            if (start != null)
                return current - start;
        }

        return null;
    }

    public Long stop(String key) {
        Long l = current(key);

        if (l != null) {
            synchronized (data) {
                data.remove(key);
            }
        }

        return l;
    }
}
