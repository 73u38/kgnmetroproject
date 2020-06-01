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
        return ChatColor.AQUA+"Metro KGN TM| Confirm Buy Ticket?";
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
        List<String> list = Arrays.asList("Issued by Metro Kgn","A-"+cs,ChatColor.GOLD+"Balance:"+balance+"$");
        Ticket ticket = new Ticket(Material.NAME_TAG, ChatColor.AQUA +"Metro Ticket",list);
        switch(e.getCurrentItem().getType()){
            case GREEN_WOOL:
                e.getWhoClicked().closeInventory();
                EconomyResponse response = eco.withdrawPlayer(p, 5.0);
                if(response.transactionSuccess()){
                    p.sendMessage(ChatColor.GREEN + "Success! You get Metro ticket,Don't Forget to topup!");
                    ticket.add(p);
                    break;
                }else {
                    p.sendMessage(ChatColor.RED + "Failed! You dont have enough money");
                    break;
                }
            case BARRIER:
                new MainMenu(Kgnmetroticket.getMetromenu(p)).open();
                break;

        }
    }

    @Override
    public void setMenuItem() {

        ItemStack yes = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);


        inventory.setItem(2, yes);
        inventory.setItem(6, no);


    }

}
