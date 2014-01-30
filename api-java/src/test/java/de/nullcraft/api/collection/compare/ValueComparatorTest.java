package de.nullcraft.api.collection.compare;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ValueComparatorTest {

    @Test
    public void testValueComparator() {
        Map<Object, Integer> map = Maps.newHashMap();
        List<Integer> randomized = Lists.newArrayList(new Integer[] {
                12,
                24,
                36,
                48,
                60
        });
        Collections.shuffle(randomized);

        for (Integer rnd : randomized)
            map.put(new Object(), rnd);

        ValueComparator<Integer> comparator = new ValueComparator<Integer>(map, new ComparableComparator<Integer>());
        Map<Object, Integer> sortedMap = new TreeMap<Object, Integer>(comparator);
        sortedMap.putAll(comparator.getMap());

        ComparableComparator<Integer> comp = new ComparableComparator<Integer>();
        int lastVal = -1;

        for (Map.Entry<Object, Integer> entry : sortedMap.entrySet()) {
            int val = entry.getValue();
            Assert.assertTrue(val >= lastVal);
            lastVal = val;
        }
    }
}
