package io.github.kgn.kgnmetroticket;

import java.util.logging.Logger;

import io.github.kgn.kgnmetroticket.commands.metrocommand;
import io.github.kgn.kgnmetroticket.events.onClickSign;
import io.github.kgn.kgnmetroticket.listener.MenuListener;
import io.github.kgn.kgnmetroticket.menusystem.menu.ConfirmBuyMenu;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.kgn.kgnmetroticket.menusystem.MetroMenu;
import org.bukkit.plugin.RegisteredServiceProvider;
import java.util.HashMap;

public final class Kgnmetroticket extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    private static final HashMap<Player, MetroMenu> playermetromenuutilitymap = new HashMap<>();


    @Override
    public void onEnable() {
        System.out.println("the plugins is starting!");
        if (!setupEconomy() ) {
            System.out.println("No Economy plugins found. Disabling!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("metro").setExecutor(new metrocommand());
        getServer().getPluginManager().registerEvents(new MenuListener(),this);
        getServer().getPluginManager().registerEvents(new onClickSign(),this);
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }



    @Override
    public void onDisable() {
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static MetroMenu getMetromenu(Player p){
        MetroMenu mm;

        if (playermetromenuutilitymap.containsKey(p)){
            return playermetromenuutilitymap.get(p);
        }else{
            mm = new MetroMenu(p);
            playermetromenuutilitymap.put(p,mm);

            return mm;

        }
    }
}
