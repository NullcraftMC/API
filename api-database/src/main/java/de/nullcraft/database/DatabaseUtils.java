package de.nullcraft.database;

import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class DatabaseUtils {

    private static Pattern CHAR_LIST_PATTERN = Pattern.compile("\\[(.*)\\]");

    /**
     * Escaped einen beliebigen Namen für LIKE-Anfragen.
     *
     * @param str Der String, der escaped werden soll
     * @return Der escapte String
     */
    public static String escapeNameForLike(String str) {
        str = str.replace("_", "\\_");
        str = str.replace("%", "\\%");
        str = CHAR_LIST_PATTERN.matcher(str).replaceAll("\\\\[$1\\\\]");

        return str;
    }

    private DatabaseUtils() { }
}
