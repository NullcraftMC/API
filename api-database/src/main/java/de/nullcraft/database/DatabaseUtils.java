package de.nullcraft.database;

import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class DatabaseUtils {

    private static final Pattern REPLACE_USERNAME = Pattern.compile("(\\_)");

    public static String escapeNameForLike(String username) {
        return REPLACE_USERNAME.matcher(username).replaceAll("\\\\$1");
    }

    private DatabaseUtils() { }
}
