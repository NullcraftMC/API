package de.nullcraft.api.bukkit.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.nullcraft.api.collection.ImmutableUtils;

import java.util.List;
import java.util.Map;

/**
 * Casts objects from a map (e.g. from {@link org.bukkit.configuration.serialization.ConfigurationSerializable}) to
 * classes.
 *
 * @author maxikg <me@maxikg.de>
 */
public class ConfigCaster {

    private final ImmutableMap<String, Object> data;

    /**
     * Constructor.
     *
     * @param data The data
     */
    public ConfigCaster(Map<String, Object> data) {
        Preconditions.checkNotNull(data);

        this.data = ImmutableUtils.immutableMap(data);
    }

    /**
     * Retrieve a value from the data.
     *
     * @throws java.lang.ClassCastException thrown if the class cannot be casted to the target class
     * @param key The configuration key
     * @param target The cast target class
     * @param defaultValue The value which will be returned if there is no value for the key
     * @param <T> The cast target type
     * @return The retrieved and casted object
     */
    public <T> T get(String key, Class<T> target, T defaultValue) {
        Object raw = data.get(key);

        if (raw == null)
            return defaultValue;

        return target.cast(raw);
    }

    /**
     * Retrieves a value from the data and throws a exception if it is not set.
     *
     * @throws de.nullcraft.api.bukkit.config.InvalidConfigurationException thrown if the configuration property does
     *                                                                      not exist
     * @throws java.lang.ClassCastException thrown if the class cannot be casted to the target class
     * @param key The configuration key
     * @param target The cast target class
     * @param <T> The cast target type
     * @return The retrieved and casted object
     */
    public <T> T require(String key, Class<T> target) {
        T raw = get(key, target, null);

        if (raw == null)
            throw new InvalidConfigurationException("Missing value: " + key);

        return raw;
    }

    /**
     * Retrieves a casted {@link com.google.common.collect.ImmutableList} or returns the default value if it is not set.
     *
     * @throws java.lang.ClassCastException thrown if the class cannot be casted to the target class
     * @param key The configuration key
     * @param target The cast target class
     * @param defaultValue The value which will be returned if there is no value for the key
     * @param <T> The cast target type
     * @return
     */
    public <T> ImmutableList<T> getList(String key, Class<T> target, List<T> defaultValue) {
        List<T> casted = Lists.newArrayList();

        List<Object> iterable = get(key, List.class, defaultValue);
        for (Object o : iterable)
            casted.add(target.cast(o));

        return ImmutableUtils.immutableList(casted);
    }

    /**
     * Retrieves a casted {@link com.google.common.collect.ImmutableList} and throws a exception if it is not set.
     *
     * @throws de.nullcraft.api.bukkit.config.InvalidConfigurationException thrown if the configuration property does
     *                                                                      not exist
     * @throws java.lang.ClassCastException thrown if the class cannot be casted to the target class
     * @param key The configuration key
     * @param target The cast target class
     * @param <T> The cast target type
     * @return The retrieved and casted object
     */
    public <T> ImmutableList<T> requireList(String key, Class<T> target) {
        List<T> casted = Lists.newArrayList();

        for (Object o : require(key, List.class))
            casted.add(target.cast(o));

        return ImmutableUtils.immutableList(casted);
    }

    public <K, V> ImmutableMap<K, V> getMap(String key, Class<K> keyType, Class<V> valueType, Map<K, V> defaultValue) {
        Map<K, V> casted = Maps.newHashMap();

        for (Object entry : get(key, Map.class, defaultValue).entrySet()) {
            Map.Entry<?, ?> castedEntry = (Map.Entry) entry;
            casted.put(keyType.cast(castedEntry.getKey()), valueType.cast(castedEntry.getValue()));
        }

        return ImmutableUtils.immutableMap(casted);
    }

    public <K, V> ImmutableMap<K, V> requireMap(String key, Class<K> keyType, Class<V> valueType) {
        Map<K, V> casted = Maps.newHashMap();

        for (Object entry : require(key, Map.class).entrySet()) {
            Map.Entry<?, ?> castedEntry = (Map.Entry) entry;
            casted.put(keyType.cast(castedEntry.getKey()), valueType.cast(castedEntry.getValue()));
        }

        return ImmutableUtils.immutableMap(casted);
    }
}
