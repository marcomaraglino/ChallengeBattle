package it.marcomaraglino.challengebattle.configfile;

import org.bukkit.Material;

public class GameConfigStructure<T> {
    private String name;
    private Material icon;
    private T item;

    public GameConfigStructure(String name, Material icon, T item) {
        this.name = name;
        this.icon = icon;
        this.item = item;
    }
    public GameConfigStructure() {

    }

    public Material getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public T getItem() {
        return item;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public void setName(String name) {
        this.name = name;
    }
}
