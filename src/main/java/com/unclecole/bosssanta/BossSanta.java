package com.unclecole.bosssanta;

import com.unclecole.bosssanta.Listeners.doubleJumpListener;
import com.unclecole.bosssanta.task.FireWorkTask;
import com.unclecole.bosssanta.task.SantaCheckTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class BossSanta extends JavaPlugin {

    @Getter private ArrayList<UUID> santaList;
    @Getter private HashMap<UUID, Long> santaCooldown;
    @Getter private static BossSanta instance;


    @Override
    public void onEnable() {
        santaList = new ArrayList<>();
        santaCooldown = new HashMap<>();
        instance = this;
        getServer().getPluginManager().registerEvents(new doubleJumpListener(), this);
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new SantaCheckTask(), 20, 20);
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new FireWorkTask(), 20, 80);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
