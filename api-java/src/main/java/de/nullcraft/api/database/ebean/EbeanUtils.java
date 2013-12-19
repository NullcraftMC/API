package de.nullcraft.api.database.ebean;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.google.common.collect.ImmutableSet;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class EbeanUtils {

    public static EbeanServer construct(ServerConfig config, ClassLoader classLoader) {
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        EbeanServer server = EbeanServerFactory.create(config);
        Thread.currentThread().setContextClassLoader(previous);

        return server;
    }

    public static ImmutableSet<EbeanServer> construct(Iterable<? extends ServerConfig> configs, ClassLoader classLoader) {
        ImmutableSet.Builder<EbeanServer> servers = ImmutableSet.builder();

        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);

        for (ServerConfig config : configs)
            servers.add(EbeanServerFactory.create(config));

        Thread.currentThread().setContextClassLoader(previous);

        return servers.build();
    }

    private EbeanUtils() { }
}
