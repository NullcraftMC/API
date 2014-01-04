package de.nullcraft.api.bungee.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class MapConfiguration implements Configuration {

    private final Map<String, Object> store = Maps.newHashMap();
    private Configuration parent;

    public MapConfiguration() { }

    public MapConfiguration(Configuration parent) {
        this.parent = parent;
    }

    public synchronized void set(String path, Object value) {
        Preconditions.checkNotNull(path);

        String[] splitPath = path.split(Configuration.ESCAPED_DELIMITER, 2);

        if (splitPath.length > 1) {
            Object subSection = store.get(splitPath[0]);
            if (subSection == null || !(subSection instanceof Configuration)) {
                subSection = new MapConfiguration(this);
                store.put(splitPath[0], subSection);
            }
            Configuration subSectionConfig = (Configuration) subSection;
            subSectionConfig.set(splitPath[1], value);
        } else {
            if (value == null) {
                store.remove(splitPath[0]);
            } else {
                if (value instanceof Configuration)
                    ((Configuration) value).setParent(this);

                store.put(splitPath[0], value);
            }
        }
    }

    public synchronized Object get(String path) {
        Preconditions.checkNotNull(path);

        String[] splitPath = path.split(Configuration.ESCAPED_DELIMITER, 2);
        if (splitPath.length > 1) {
            Object value = store.get(splitPath[0]);
            if (value == null || !(value instanceof Configuration))
                throw new ClassCastException("Cannot cast " + (value != null ? value.getClass() : "null") + " to " + Configuration.class.getName());

            return ((Configuration) value).get(splitPath[1]);
        } else {
            return store.get(splitPath[0]);
        }
    }

    public Integer getInt(String path) {
        return (Integer) get(path);
    }

    public int getInt(String path, int definition) {
        Integer o = getInt(path);

        return o != null ? o : definition;
    }

    public Boolean getBoolean(String path) {
        return (Boolean) get(path);
    }

    public boolean getBoolean(String path, boolean definition) {
        Boolean o = getBoolean(path);

        return o != null ? o : definition;
    }

    public Configuration getParent() {
        return parent;
    }

    public boolean contains(String path) {
        return get(path) != null;
    }

    public void setParent(Configuration parent) {
        this.parent = parent;
    }
}
