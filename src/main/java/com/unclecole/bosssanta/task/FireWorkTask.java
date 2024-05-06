package com.unclecole.bosssanta.task;

import com.unclecole.bosssanta.BossSanta;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireWorkTask implements Runnable{

    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()) {

            if (player.getInventory().getHelmet() != null) {

                ItemStack helmet = player.getInventory().getHelmet();

                if(helmet.getType().equals(Material.STONE_BRICKS)) {
                    Location loc = player.getLocation();

                    Bukkit.getScheduler().runTask(BossSanta.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();
                            fwm.setPower(1);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.RED,Color.WHITE,Color.BLUE).flicker(true).build());

                            fw.setFireworkMeta(fwm);

                            for(int i = 0; i < 2; i++) {
                                Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                                fw2.setFireworkMeta(fwm);
                            }
                        }
                    });
                }
            }
        }

    }

}
