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

    /**
     * Versucht einen {@link org.bukkit.entity.Player} anhand der angegebenen {@code expession} zu finden.
     *
     * @param expression Die {@code expession}
     * @return Der {@link org.bukkit.entity.Player}, der als erstes auf die {@code expession} passt, oder {@code null},
     *         sollte kein {@link org.bukkit.entity.Player} passen
     */
    public static Player getPlayer(String expression) {
        Preconditions.checkNotNull(expression, "Null is not permitted as playername to search for.");

        Player player = null;

        if (expression.startsWith("@"))
            player = Bukkit.getPlayerExact(expression.substring(1));
        else
            player = Bukkit.getPlayer(expression);

        return player;
    }

    /**
     * Pr&uuml;ft, ob der angegebene Spielername ein f&uuml;r Minecraft als valide geltender Name ist.
     *
     * @param playername Der zu pr&uuml;fende Spielername
     * @return true, sollte der Name valide sein, andernfalls false
     */
    public static boolean isValidPlayername(String playername) {
        Preconditions.checkNotNull(playername, "Null is not permitted as playername to validate.");

        return PLAYERNAME_VALIDATOR.matcher(playername).matches();
    }

    private PlayerUtils() { }
}
