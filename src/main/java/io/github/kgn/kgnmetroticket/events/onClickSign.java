package io.github.kgn.kgnmetroticket.events;

import io.github.kgn.kgnmetroticket.Kgnmetroticket;
import io.github.kgn.kgnmetroticket.menusystem.menu.ConfirmBuyMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onClickSign implements Listener {

    @EventHandler
    public void onClickSign(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (b.getType() == Material.OAK_WALL_SIGN || b.getType() == Material.SPRUCE_WALL_SIGN ||
                b.getType() == Material.BIRCH_WALL_SIGN || b.getType() == Material.JUNGLE_WALL_SIGN ||
                b.getType() == Material.ACACIA_WALL_SIGN || b.getType() == Material.DARK_OAK_WALL_SIGN) {
            Sign sign = (Sign) b.getState();
            if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[Ticket]")) {
                new ConfirmBuyMenu(Kgnmetroticket.getMetromenu(p)).open();
            }
        }
    }
}
