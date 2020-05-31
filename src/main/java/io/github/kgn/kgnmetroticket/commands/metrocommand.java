package io.github.kgn.kgnmetroticket.commands;

import io.github.kgn.kgnmetroticket.Kgnmetroticket;
import io.github.kgn.kgnmetroticket.menusystem.menu.ConfirmBuyMenu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class metrocommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Economy economy = Kgnmetroticket.getEconomy();

            new ConfirmBuyMenu(Kgnmetroticket.getMetromenu(player)).open();
        }



        return true;
    }
}
