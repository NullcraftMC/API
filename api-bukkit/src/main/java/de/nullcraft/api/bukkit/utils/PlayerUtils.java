package de.nullcraft.api.bukkit.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public class PlayerUtils {

    private static final Pattern PLAYERNAME_VALIDATOR = Pattern.compile("[a-zA-Z0-9_]{1,16}");

    public static Player getPlayer(String expression) {
        Preconditions.checkNotNull(expression, "Null is not permitted as playername to search for.");

        Player player = null;

        if (expression.startsWith("@"))
            player = Bukkit.getPlayerExact(expression.substring(1));
        else
            player = Bukkit.getPlayer(expression);

        return player;
    }

    public static boolean isValidPlayername(String playername) {
        Preconditions.checkNotNull(playername, "Null is not permitted as playername to validate.");

        return PLAYERNAME_VALIDATOR.matcher(playername).matches();
    }

    private PlayerUtils() { }
}
