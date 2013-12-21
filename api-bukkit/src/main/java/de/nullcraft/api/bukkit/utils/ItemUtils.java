package de.nullcraft.api.bukkit.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

/**
 * @author maxikg <me@maxikg.de>
 */
public final class ItemUtils {

    public static ItemStack setItemStackLore(ItemStack itemStack, List<String> lore) {
        return setItemStackName(itemStack, null, lore);
    }

    public static ItemStack setItemStackName(ItemStack itemStack, String name) {
        return setItemStackName(itemStack, name, null);
    }

    public static ItemStack setItemStackName(ItemStack itemStack, String name, List<String> lore) {
        Preconditions.checkNotNull(itemStack);

        ItemMeta meta = itemStack.getItemMeta();

        if (name != null)
            meta.setDisplayName(name);

        if (lore != null)
            meta.setLore(lore);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack dyeItemStack(ItemStack itemStack, Color color) {
        Preconditions.checkNotNull(itemStack);
        Preconditions.checkNotNull(color);

        if (itemStack.getType() != Material.LEATHER_BOOTS && itemStack.getType() != Material.LEATHER_CHESTPLATE && itemStack.getType() != Material.LEATHER_HELMET && itemStack.getType() !=  Material.LEATHER_LEGGINGS)
            throw new IllegalArgumentException("Incompatible item: " + itemStack.toString());

        LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
        meta.setColor(color);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack setSkullOwner(ItemStack itemStack, String owner) {
        Preconditions.checkNotNull(itemStack);
        Preconditions.checkNotNull(owner);

        if (itemStack.getType() != Material.SKULL_ITEM)
            throw new IllegalArgumentException("Incompatible item: " + itemStack.toString());

        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(owner);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack getSkull(String playerName) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        setSkullOwner(itemStack, playerName);

        return itemStack;
    }

    private ItemUtils() { }
}
