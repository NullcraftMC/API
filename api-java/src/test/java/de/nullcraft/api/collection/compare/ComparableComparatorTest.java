package de.nullcraft.api.collection.compare;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ComparableComparatorTest {

    @Test
    public void testComparable() {
        ComparableComparator<Integer> comparator = new ComparableComparator<Integer>();
        Integer i1 = new Integer(42);

        Integer lesser = new Integer(i1 / 2);
        Integer same = new Integer(i1);
        Integer greater = new Integer(i1 * 2);

        Assert.assertTrue(comparator.compare(i1, lesser) > 0);
        Assert.assertTrue(comparator.compare(i1, same) == 0);
        Assert.assertTrue(comparator.compare(i1, greater) < 0);
    }
}
