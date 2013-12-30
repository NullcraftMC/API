package de.nullcraft.api.collection;

import com.google.common.collect.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author maxikg <me@maxikg.de>
 */
public class ImmutableUtilsTest {

    private static final int DATA = 2;

    private final Set<Object> testSet;
    private final Map<Object, Object> testMap;
    private final List<Object> testList;

    public ImmutableUtilsTest() {
        testSet = Sets.newHashSet();
        for (int i = 0; i < DATA; i++)
            testSet.add(new Object());

        testMap = Maps.newHashMap();
        for (int i = 0; i < DATA; i++)
            testMap.put(new Object(), new Object());

        testList = Lists.newArrayList();
        for (int i = 0; i < DATA; i++)
            testList.add(new Object());
    }

    @Test
    public void testEmtpyImmutableCreator() {
        ImmutableUtils.immutableList(Collections.emptyList());
        ImmutableUtils.immutableMap(Collections.emptyMap());
        ImmutableUtils.immutableSet(Collections.emptySet());
    }

    @Test
    public void testImmutableCreator() {
        ImmutableSet<Object> set = ImmutableUtils.immutableSet(testSet);
        for (Object test : testSet)
            Assert.assertTrue(set.contains(test));

        ImmutableMap<Object, Object> map = ImmutableUtils.immutableMap(testMap);
        for (Object test : testMap.keySet())
            Assert.assertEquals(testMap.get(test), map.get(test));

        ImmutableList<Object> list = ImmutableUtils.immutableList(testList);
        for (Object test : testList)
            Assert.assertTrue(list.contains(test));
    }
}
