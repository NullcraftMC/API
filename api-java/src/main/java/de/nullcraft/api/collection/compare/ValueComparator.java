package de.nullcraft.api.collection.compare;

import java.util.Comparator;
import java.util.Map;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ValueComparator<V> implements Comparator<Object> {

    private final Map<? extends Object, V> base;
    private final Comparator<V> comparator;

    public ValueComparator(Map<? extends Object, V> base, Comparator<V> comparator) {
        this.base = base;
        this.comparator = comparator;
    }

    public int compare(Object a, Object b) {
        synchronized (base) {
            return comparator.compare(base.get(a), base.get(b));
        }
    }
}
