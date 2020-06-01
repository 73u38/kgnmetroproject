package io.github.kgn.kgnmetroticket.events;

import io.github.kgn.kgnmetroticket.Kgnmetroticket;
import io.github.kgn.kgnmetroticket.menusystem.menu.ConfirmBuyMenu;
import io.github.kgn.kgnmetroticket.menusystem.menu.MainMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

public class onClickSign implements Listener{

    @EventHandler
    public void onClickSign(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        ItemStack stack = e.getItem();
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (b.getType() == Material.OAK_WALL_SIGN || b.getType() == Material.SPRUCE_WALL_SIGN ||
                b.getType() == Material.BIRCH_WALL_SIGN || b.getType() == Material.JUNGLE_WALL_SIGN ||
                b.getType() == Material.ACACIA_WALL_SIGN || b.getType() == Material.DARK_OAK_WALL_SIGN) {
            Sign sign = (Sign) b.getState();
            if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[Ticket]")) {
                new MainMenu(Kgnmetroticket.getMetromenu(p)).open();
            }
            if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[Test]")) {
                if(stack.getType() == Material.NAME_TAG){
                    if(stack.hasItemMeta()) {
                        ItemMeta meta = stack.getItemMeta();

                        if (meta.hasLore() && meta.hasDisplayName()) {

                            if(ChatColor.stripColor(meta.getDisplayName()).equals(("Metro Ticket"))) {
                                List<String> lore = meta.getLore();

                                if (!lore.isEmpty()) {
                                    String check = ChatColor.stripColor(lore.get(0));

                                    if (check.equals("Single Ticket")){
                                        p.sendMessage(ChatColor.AQUA+"Single Ticket");
                                    }
                                    else if (check.equals("Multiple Ticket")){
                                        String balance = ChatColor.stripColor(lore.get(3));
                                        p.sendMessage(ChatColor.GOLD+balance);
                                        String test = balance.substring(8, balance.length() -1);
                                        int test2 = Integer.parseInt(test);
                                        test2 = test2-100;
                                        System.out.println(test2);


                                    }
                                }
                            }
                            else
                                {

                                p.sendMessage(ChatColor.DARK_RED+"ERROR1"+meta.getDisplayName());
                                }
                        }
                    }
                }else{
                    p.sendMessage(ChatColor.DARK_RED+"ERROR2");
                }
            }
        }
    }
}
