package de.nullcraft.api.collection.compare;

import java.util.Comparator;
import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ValueComparator<K, V> implements Comparator<K> {

    private final Map<K, V> base;
    private final Comparator<V> comparator;

    public ValueComparator(Map<K, V> base, Comparator<V> comparator) {
        this.base = base;
        this.comparator = comparator;
    }

    public int compare(K a, K b) {
        synchronized (base) {
            return comparator.compare(base.get(a), base.get(b));
        }
    }
}
