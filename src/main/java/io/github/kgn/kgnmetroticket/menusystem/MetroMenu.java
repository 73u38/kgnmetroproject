package io.github.kgn.kgnmetroticket.menusystem;

import org.bukkit.entity.Player;

public class MetroMenu {

    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public MetroMenu(Player owner) {
        this.owner = owner;


    }
}

