package de.nullcraft.api.bungee.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author maxikg <me@maxikg.de>
 */
public class MapConfigurationTest {

    @Test
    public void testConfiguration() {
        Configuration config = new MapConfiguration();
        config.set("this.is.a.test", "TEST");

        Assert.assertTrue(config.contains("this.is.a.test"));
        Assert.assertEquals("TEST", config.get("this.is.a.test"));
        Assert.assertEquals("TEST", ((Configuration) config.get("this")).get("is.a.test"));
    }
}
