package de.nullcraft.api.bukkit.utils;

import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author maxikg <me@maxikg.de>
 */
public class TextUtils {

    /**
     * Der {@code char}, der einem Color-Code zur Ersetzung vorangestellt werden muss.
     */
    public static final char WRITABLE_INDICATOR = '&';

    private static final ImmutableMap<String, String> REPLACEMENTS;
    private static final Pattern PARSE_PATTERN;

    static {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

        builder.put("<black>", ChatColor.BLACK.toString());
        builder.put("<navy>", ChatColor.DARK_BLUE.toString());
        builder.put("<darkgreen>", ChatColor.DARK_GREEN.toString());
        builder.put("<darkaqua>", ChatColor.DARK_AQUA.toString());
        builder.put("<darkred>", ChatColor.DARK_RED.toString());
        builder.put("<purple>", ChatColor.DARK_PURPLE.toString());
        builder.put("<gold>", ChatColor.GOLD.toString());
        builder.put("<gray>", ChatColor.GRAY.toString());
        builder.put("<darkgray>", ChatColor.DARK_GRAY.toString());
        builder.put("<blue>", ChatColor.BLUE.toString());
        builder.put("<green>", ChatColor.GREEN.toString());
        builder.put("<cyan>", ChatColor.AQUA.toString());
        builder.put("<red>", ChatColor.RED.toString());
        builder.put("<pink>", ChatColor.LIGHT_PURPLE.toString());
        builder.put("<yellow>", ChatColor.YELLOW.toString());
        builder.put("<white>", ChatColor.WHITE.toString());
        builder.put("<random>", ChatColor.MAGIC.toString());
        builder.put("<bold>", ChatColor.BOLD.toString());
        builder.put("<strikethrough>", ChatColor.STRIKETHROUGH.toString());
        builder.put("<underlined>", ChatColor.UNDERLINE.toString());
        builder.put("<reset>", ChatColor.RESET.toString());

        for (ChatColor chatColor : ChatColor.values())
            builder.put(String.valueOf(WRITABLE_INDICATOR) + chatColor.getChar(), chatColor.toString());

        REPLACEMENTS = builder.build();

        StringBuilder patternBuilder = new StringBuilder();
        for (String key : REPLACEMENTS.keySet()) {
            if (patternBuilder.length() > 0)
                patternBuilder.append("|");

            patternBuilder.append(Pattern.quote(key));
        }
        patternBuilder.insert(0, "(");
        patternBuilder.append(")");
        PARSE_PATTERN = Pattern.compile(patternBuilder.toString());
    }

    /**
     * Formatiert den angegebenen String.
     *
     * @param str Der zu formatiertende String
     * @return Der formatierte String
     */
    public static String format(String str) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = PARSE_PATTERN.matcher(str);

        while (matcher.find())
            matcher.appendReplacement(sb, REPLACEMENTS.get(matcher.group(0)));

        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * Formatiert das angegebene {@code pattern} mit den angegebenen {@code objects}.
     *
     * @param pattern Das zu formatierende Pattern
     * @param objects Die Objekte, die mit Platzhaltern ersetzt werden sollen
     * @return Der formatierte String
     * @see de.nullcraft.api.bukkit.utils.TextUtils#format(String)
     */
    public static String format(String pattern, Object... objects) {
        String str = String.format(pattern, objects);

        return format(str);
    }

    private TextUtils() { }
}
