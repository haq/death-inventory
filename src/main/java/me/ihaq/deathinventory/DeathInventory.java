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

        drops.forEach(itemStack -> {
            HashMap<Integer, ItemStack> map = inventory.addItem(itemStack);
            System.out.println(map);
        });
        drops.clear();
    }

}