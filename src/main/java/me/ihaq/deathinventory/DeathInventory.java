package me.ihaq.deathinventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class DeathInventory extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        PlayerInventory inventory = killer.getInventory();
        List<ItemStack> drops = e.getDrops();

        for (int i = 0; i < 36 - inventory.getContents().length; i++) {
            ItemStack item = drops.remove(i);
            inventory.addItem(item);
        }

        if (drops.size() > 0) {
            killer.sendMessage("Your inventory is now full, the rest of the items have been dropped on the ground.");
        }

    }
}