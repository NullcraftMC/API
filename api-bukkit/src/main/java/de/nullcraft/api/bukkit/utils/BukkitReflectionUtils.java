package de.nullcraft.api.bukkit.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class BukkitReflectionUtils {

    private static final String MINECRAFTSERVER_BASE = "net.minecraft.server." + getPackageVersionString() + ".";

    /**
     * Returns the dynamic version string of native NMS packages (e.g. net.minecraft.server.v1_6_R2.Something) for
     * reflective access.
     *
     * @return The dynamic version string
     */
    public static String getPackageVersionString() {
        String packetName = Bukkit.getServer().getClass().getPackage().getName();

        return packetName.substring(packetName.lastIndexOf('.') + 1);
    }

    /**
     * Try to get a class from the package {@code net.minecraft.server.[currentVersion]}.
     *
     * @param className The name of the class
     * @return The loaded class or null if it cannot be loaded
     */
    public static Class<?> getMinecraftServerClass(String className) {
        String classPath = MINECRAFTSERVER_BASE + className;

        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Invokes the {@code getHandle}-Method and returns the given object. You can use this helper on objects
     * like {@link org.bukkit.entity.Entity} or {@link org.bukkit.Server}.
     *
     * @param object A object which has a {@code getHandle} method
     * @return The returned object or null if something goes wrong
     */
    public static Object getHandle(Object object) {
        try {
            Method method = object.getClass().getMethod("getHandle");
            return method.invoke(object);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    private BukkitReflectionUtils() { }
}
