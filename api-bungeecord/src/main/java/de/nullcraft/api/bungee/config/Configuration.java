package de.nullcraft.api.bungee.config;

import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public interface Configuration {

    public static final String DELIMITER = ".";
    public static final String ESCAPED_DELIMITER = Pattern.quote(DELIMITER);

    void set(String path, Object value);

    Object get(String path);

    Integer getInt(String path);

    int getInt(String path, int definition);

    Boolean getBoolean(String path);

    boolean getBoolean(String path, boolean definition);

    Configuration getParent();

    void setParent(Configuration parent);

    boolean contains(String path);
}
