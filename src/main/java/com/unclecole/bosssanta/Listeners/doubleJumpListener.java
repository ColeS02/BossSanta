package com.unclecole.bosssanta.Listeners;

import com.unclecole.bosssanta.BossSanta;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;

public class doubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent e) {


        Player p = e.getPlayer();

        if(!BossSanta.getInstance().getSantaList().contains(p.getUniqueId())) return;

        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(2.0D).setY(0.9D));
            p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 15);
            BossSanta.getInstance().getSantaCooldown().put(p.getUniqueId(),System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        if(!BossSanta.getInstance().getSantaList().contains(p.getUniqueId())) return;

        BossSanta.getInstance().getSantaCooldown().putIfAbsent(p.getUniqueId(), System.currentTimeMillis()+2000L);

        if ((e.getPlayer().getGameMode() != GameMode.CREATIVE)
                && (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) &&
                (System.currentTimeMillis() - BossSanta.getInstance().getSantaCooldown().get(p.getUniqueId()))/1000 > 0) {
            p.setAllowFlight(true);
        }
    }
}
