package io.github.kgn.kgnmetroticket.menusystem.menu;

import io.github.kgn.kgnmetroticket.item.Ticket;
import io.github.kgn.kgnmetroticket.menusystem.Menu;
import io.github.kgn.kgnmetroticket.Kgnmetroticket;
import io.github.kgn.kgnmetroticket.menusystem.MetroMenu;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class ConfirmBuyMenu extends Menu implements Listener {


    public ConfirmBuyMenu(MetroMenu mm) {
        super(mm);
    }

    @Override
    public String getMenuName() {
        return "Confirm Buy Ticket?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Economy eco = Kgnmetroticket.getEconomy();
        Player p = (Player) e.getWhoClicked();
        PlayerInventory inventory = p.getInventory();
        int counter = 0;
        int balance = 500;
        String cs = Integer.toString(counter);
        List<String> list = Arrays.asList("A-"+cs,ChatColor.GOLD+"Balance:"+balance+"$");
        Ticket ticket = new Ticket(Material.NAME_TAG, ChatColor.AQUA +"Metro Ticket",list);
        switch(e.getCurrentItem().getType()){
            case EMERALD:
                e.getWhoClicked().closeInventory();
                EconomyResponse response = eco.withdrawPlayer(p, 5.0);
                if(response.transactionSuccess()){
                    p.sendMessage(ChatColor.GREEN + "Success");
                    ticket.add(p);
                    break;
                }else {
                    p.sendMessage(ChatColor.RED + "Failed");
                    break;
                }
            case BARRIER:
                e.getWhoClicked().closeInventory();
                break;

            case DIAMOND:
                e.getWhoClicked().closeInventory();
                break;

        }
    }

    @Override
    public void setMenuItem() {

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);
        ItemStack topup = new ItemStack(Material.DIAMOND, 1);
        ItemMeta topup_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(topup_meta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);
        inventory.setItem(4, topup);

    }

}
