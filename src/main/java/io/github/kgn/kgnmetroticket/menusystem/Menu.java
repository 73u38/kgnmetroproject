package io.github.kgn.kgnmetroticket.menusystem;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected MetroMenu mm;

    public Menu(MetroMenu mm){
        this.mm = mm;
    }
    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItem();

    public void open(){

        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItem();
        mm.getOwner().openInventory(inventory);

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
