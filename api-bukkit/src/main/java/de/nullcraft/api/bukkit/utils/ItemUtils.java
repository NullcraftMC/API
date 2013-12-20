package de.nullcraft.api.bukkit.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class ItemUtils {

    public static void setItemStackLore(ItemStack itemStack, List<String> lore) {
        setItemStackName(itemStack, null, lore);
    }

    public static void setItemStackName(ItemStack itemStack, String name) {
        setItemStackName(itemStack, name, null);
    }

    public static void setItemStackName(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();

        if (name != null)
            meta.setDisplayName(name);

        if (lore != null)
            meta.setLore(lore);

        itemStack.setItemMeta(meta);
    }

    private ItemUtils() { }
}
