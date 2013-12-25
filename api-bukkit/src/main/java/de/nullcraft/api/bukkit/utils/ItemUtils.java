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
 * Some utils to deal with {@link org.bukkit.inventory.ItemStack}s.
 *
 * @author maxikg <me@maxikg.de>
 */
public final class ItemUtils {

    /**
     * Sets the lore of an {@link org.bukkit.inventory.ItemStack}.
     *
     * @param itemStack The {@link org.bukkit.inventory.ItemStack} of which the lore will be changed
     * @param lore The new lore as an {@link java.util.List}
     * @return The {@link org.bukkit.inventory.ItemStack} of {@code itemStack}
     */
    public static ItemStack setItemStackLore(ItemStack itemStack, List<String> lore) {
        return setItemStackName(itemStack, null, lore);
    }

    /**
     * Sets the name of an {@link org.bukkit.inventory.ItemStack}.
     *
     * @param itemStack The {@link org.bukkit.inventory.ItemStack} of which the name will be changed
     * @param name The new name
     * @return The {@link org.bukkit.inventory.ItemStack} of {@code itemStack}
     */
    public static ItemStack setItemStackName(ItemStack itemStack, String name) {
        return setItemStackName(itemStack, name, null);
    }

    /**
     * Sets the name and/or the lore. {@code null}-values will not change the property.
     *
     * @throws java.lang.NullPointerException Will be thrown if {@code itemStack} is null
     * @param itemStack The {@link org.bukkit.inventory.ItemStack} of which the metadata will be changed
     * @param name The new name or {@code null} if you do not want to change the name
     * @param lore The new lore or {@code null} if you do not want to change the name
     * @return The {@link org.bukkit.inventory.ItemStack} of {@code itemStack}
     */
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

    /**
     * Will dye an {@link org.bukkit.inventory.ItemStack} if the material does allow this.
     *
     * @throws java.lang.NullPointerException Will be thrown if {@code itemStack} or {@code color} is null
     * @throws java.lang.IllegalArgumentException Will be thrown if {@code itemStack} does not allow to dye it
     * @param itemStack The {@link org.bukkit.inventory.ItemStack} which will you dye
     * @param color The color to which you will dye this item
     * @return The {@link org.bukkit.inventory.ItemStack} of {@code itemStack}
     */
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

    /**
     * Sets the skull owner of an skull.
     *
     * @throws java.lang.NullPointerException Will be thrown if {@code itemStack} or {@code owner} is null
     * @throws java.lang.IllegalArgumentException Will be thrown if the {@link org.bukkit.inventory.ItemStack} does not
     *                                            allow to change the owner
     * @param itemStack The {@link org.bukkit.inventory.ItemStack} of which you will change the skull owner
     * @param owner The name of the new skull owner
     * @return The {@link org.bukkit.inventory.ItemStack} of {@code itemStack}
     */
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

    /**
     * Returns a new {@link org.bukkit.inventory.ItemStack} of an {@link org.bukkit.Material#SKULL_ITEM} with the
     * specified skull owner.
     *
     * @param playerName The name of the skull owner
     * @return The constructed {@link org.bukkit.inventory.ItemStack}
     */
    public static ItemStack getSkull(String playerName) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        setSkullOwner(itemStack, playerName);

        return itemStack;
    }

    private ItemUtils() { }
}
