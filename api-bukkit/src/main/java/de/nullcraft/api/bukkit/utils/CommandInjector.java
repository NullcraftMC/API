package de.nullcraft.api.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

/**
 * Access the {@link org.bukkit.command.CommandMap} of Bukkit to inject commands.
 *
 * @author maxikg <me@maxikg.de>
 */
public class CommandInjector {

    private static final String FIELDNAME_COMMANDMAP = "commandMap";
    private static CommandInjector instance;

    public final Field commandField;
    private final Server server;

    static {
        instance = new CommandInjector(Bukkit.getServer());
    }

    /**
     * Constructor.
     *
     * @param server The {@link org.bukkit.Server} instance of which the {@link org.bukkit.command.CommandMap} will be
     *               injected.
     */
    public CommandInjector(Server server) {
        this.server = server;

        try {
            commandField = server.getClass().getField(FIELDNAME_COMMANDMAP);
            commandField.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Error while construct CommandInjector.", e);
        }
    }

    /**
     * Gets the {@link org.bukkit.command.CommandMap} of the server.
     *
     * @return The {@link org.bukkit.command.CommandMap}
     */
    public CommandMap getCommandMap() {
        try {
            return (CommandMap) commandField.get(server);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Cannot retrieve command map.", e);
        }
    }

    /**
     * Gets the default {@code CommandInjector} instance.
     *
     * @return The default {@code CommandInjector} instance.
     */
    public static CommandInjector getInstance() {
        return instance;
    }

    /**
     * Sets the default {@code CommandInjector} instance.
     *
     * @param instance The new default {@code CommandInjector} instance.
     */
    public static void setInstance(CommandInjector instance) {
        CommandInjector.instance = instance;
    }
}
