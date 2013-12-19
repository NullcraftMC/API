package de.nullcraft.api.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.Map;

/**
 * Tools to deal with Google Common immutable collections.
 *
 * @author maxikg <me@maxikg.de>
 */
public final class ImmutableUtils {

    /**
     * Generate a {@link com.google.common.collect.ImmutableList} without throw any exception.
     *
     * @param data The data from which the list will be generated. It can also be null or empty.
     * @param <T> The content type of the list.
     * @return A {@link com.google.common.collect.ImmutableList}.
     */
    public static <T> ImmutableList<T> immutableList(Collection<T> data) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();

        if (data != null && data.size() > 0)
            builder.addAll(data);

        return builder.build();
    }

    public static <K, V> ImmutableMap<K, V> immutableMap(Map<K, V> data) {
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();

        if (data != null && data.size() > 0)
            builder.putAll(data);

        return builder.build();
    }

    /**
     * This is a helper class so it should not be constructed.
     */
    private ImmutableUtils() { }
}
