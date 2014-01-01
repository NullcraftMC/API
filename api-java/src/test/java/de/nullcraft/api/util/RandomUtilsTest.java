package de.nullcraft.api.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class RandomUtilsTest {

    private static final int TEST_RUNS = 1000;

    @Test
    public void testGenerateLong() {
        Random random = new Random();

        for (int i = 0; i < TEST_RUNS; i++)
            Assert.assertTrue(RandomUtils.nextLong(random, 1) <= 1);
    }
}
