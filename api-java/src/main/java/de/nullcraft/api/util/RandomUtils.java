package de.nullcraft.api.util;

import com.google.common.base.Preconditions;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class RandomUtils {

    private RandomUtils() { }

    public static double nextDouble(Random random, double max) {
        Preconditions.checkArgument(max > 0);

        long bits, value;
        do {
            bits = (random.nextLong() << 1) >>> 1;
            value = (long) (bits % max);
        } while (bits - value + (max - 1) < 0);

        return value;
    }
}
