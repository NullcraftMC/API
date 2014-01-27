package de.nullcraft.database;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author maxikg <me@maxikg.de>
 */
public class DatabaseUtilsTest {

    @Test
    public void testEscapeUsername() {
        String testUser = "this_is_a_test[asd]";
        String expected  = "this\\_is\\_a\\_test\\[asd\\]";

        Assert.assertEquals(expected, DatabaseUtils.escapeNameForLike(testUser));
    }
}
