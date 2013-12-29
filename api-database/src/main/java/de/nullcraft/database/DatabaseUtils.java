package de.nullcraft.database;

import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class DatabaseUtils {

    private static final Pattern REPLACE_USERNAME = Pattern.compile("(\\_)");

    /**
     * Escaped einen beliebigen Namen für LIKE-Anfragen.
     *
     * @param str Der String, der escaped werden soll
     * @return Der escapte String
     */
    public static String escapeNameForLike(String str) {
        return REPLACE_USERNAME.matcher(str).replaceAll("\\\\$1");
    }

    private DatabaseUtils() { }
}
