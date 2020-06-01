package io.github.kgn.kgnmetroticket;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
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

    /*final String username="root"; //Enter in your db username
    final String password="asd"; //Enter your password for the db
    final String url = "jdbc:mysql://localhost:3306/Minecraft"; //Enter URL w/db name

    Connection vars
    static Connection connection;*/

    @Override
    public void onEnable() {
        /*try { //We use a try catch to avoid errors, hopefully we don't get any.
            Class.forName("com.mysql.jdbc.Driver"); //this accesses Driver in jdbc.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("jdbc driver unavailable!");
            return;
        }
        try { //Another try catch to get any SQL errors (for example connections errors)
            connection = (Connection) DriverManager.getConnection(url,username,password);
            //with the method getConnection() from DriverManager, we're trying to set
            //the connection's url, username, password to the variables we made earlier and
            //trying to get a connection at the same time. JDBC allows us to do this.
        } catch (SQLException e) { //catching errors)
            e.printStackTrace(); //prints out SQLException errors to the console (if any)
        }  checking database*/


        System.out.println("the plugins is starting!");
        if (!setupEconomy() ) {
            System.out.println("No Economy plugins found. Disabling!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        //getCommand("metro").setExecutor(new metrocommand());
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
        /*try { //using a try catch to catch connection errors (like wrong sql password...)
            if (connection!=null && !connection.isClosed()){ //checking if connection isn't null to
                //avoid receiving a nullpointer
                connection.close(); //closing the connection field variable.
            }
        } catch(Exception e) {
            e.printStackTrace();
        }*/
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
