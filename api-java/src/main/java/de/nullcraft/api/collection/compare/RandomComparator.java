package de.nullcraft.api.collection.compare;

import java.util.Comparator;
import java.util.Random;

/**
 * @author maxikg <me@maxikg.de>
 */
public class RandomComparator<T> implements Comparator<T> {

    private final Random random = new Random();

    public int compare(T o1, T o2) {
        return random.nextInt(3) - 1;
    }
}
