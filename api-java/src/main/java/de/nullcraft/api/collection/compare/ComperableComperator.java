package de.nullcraft.api.collection.compare;

import java.util.Comparator;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ComperableComperator<T extends Comparable> implements Comparator<T> {

    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
