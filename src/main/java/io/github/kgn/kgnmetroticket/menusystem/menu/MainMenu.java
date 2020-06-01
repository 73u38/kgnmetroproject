package io.github.kgn.kgnmetroticket.menusystem.menu;

import io.github.kgn.kgnmetroticket.Kgnmetroticket;
import io.github.kgn.kgnmetroticket.menusystem.Menu;
import io.github.kgn.kgnmetroticket.menusystem.MetroMenu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class MainMenu extends Menu implements Listener {


    public MainMenu(MetroMenu mm) {
        super(mm);
    }

    @Override
    public String getMenuName() {

        return ChatColor.AQUA + "KGN Metro TM";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        switch (e.getCurrentItem().getType()) {
            case GREEN_WOOL:
                new ConfirmSingleBuyMenu(Kgnmetroticket.getMetromenu(p)).open();
                break;
            case BLUE_WOOL:
                new ConfirmBuyMenu(Kgnmetroticket.getMetromenu(p)).open();
                break;
            case ORANGE_WOOL:
                new ConfirmBuyMenu(Kgnmetroticket.getMetromenu(p)).open();
                break;
            case BARRIER:
                e.getWhoClicked().closeInventory();
                break;
        }
    }
    @Override
    public void setMenuItem() {

        ItemStack singleticket = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta sticket_meta = singleticket.getItemMeta();
        sticket_meta.setDisplayName(ChatColor.GREEN + "Buy Single Ticket??");
        singleticket.setItemMeta(sticket_meta);
        ItemStack ticket = new ItemStack(Material.BLUE_WOOL, 1);
        ItemMeta ticket_meta = ticket.getItemMeta();
        ticket_meta.setDisplayName(ChatColor.BLUE + "Buy Multiple Entry Ticket??");
        ticket.setItemMeta(ticket_meta);
        ItemStack topup = new ItemStack(Material.ORANGE_WOOL, 1);
        ItemMeta topup_meta = topup.getItemMeta();
        topup_meta.setDisplayName(ChatColor.YELLOW + "Top Up");
        topup.setItemMeta(topup_meta);
        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "Cancel");
        no.setItemMeta(no_meta);


        inventory.setItem(1, singleticket);
        inventory.setItem(3, ticket);
        inventory.setItem(5, topup);
        inventory.setItem(8, no);

    }

}
