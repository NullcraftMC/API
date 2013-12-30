package de.nullcraft.api;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author maxikg <me@maxikg.de>
 */
public class TimerTest {

    @Test
    public void testTimer() {
        Timer timer = new Timer();
        timer.start("test1");
        timer.start("test2");
        sleep(100);
        Assert.assertEquals(100, timer.stop("test1"), 1);
        sleep(100);
        Assert.assertEquals(200, timer.stop("test2"), 1);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Assert.fail();
        }
    }
}
