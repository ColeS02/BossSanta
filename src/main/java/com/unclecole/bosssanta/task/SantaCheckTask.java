package com.unclecole.bosssanta.task;

import com.unclecole.bosssanta.BossSanta;
import org.bukkit.*;
import org.bukkit.entity.Boss;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class SantaCheckTask implements Runnable{

    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()) {

            if (player.getInventory().getHelmet() != null) {

                ItemStack helmet = player.getInventory().getHelmet();

                if (helmet.getItemMeta().hasCustomModelData()) {

                    if ((helmet.getType().equals(Material.FEATHER) && helmet.getItemMeta().getCustomModelData() == 10000) && BossSanta.getInstance().getSantaList().contains(player.getUniqueId())) {
                        continue;
                    }

                    if (!(helmet.getType().equals(Material.FEATHER) && helmet.getItemMeta().getCustomModelData() == 10000) && BossSanta.getInstance().getSantaList().contains(player.getUniqueId())) {
                        BossSanta.getInstance().getSantaList().remove(player.getUniqueId());
                        player.setAllowFlight(false);
                    } else if (!BossSanta.getInstance().getSantaList().contains(player.getUniqueId()) && (helmet.getType().equals(Material.FEATHER) && helmet.getItemMeta().getCustomModelData() == 10000))
                        BossSanta.getInstance().getSantaList().add(player.getUniqueId());

                } else if (BossSanta.getInstance().getSantaList().contains(player.getUniqueId())) {
                    BossSanta.getInstance().getSantaList().remove(player.getUniqueId());
                    player.setAllowFlight(false);
                }
            } else {
                if(BossSanta.getInstance().getSantaList().contains(player.getUniqueId())) {
                    BossSanta.getInstance().getSantaList().remove(player.getUniqueId());
                }
            }
        }

    }

}
