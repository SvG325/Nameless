package rsi.nameless;

import java.io.Serializable;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Item implements Serializable {
    private final String name;
    private final int strengthBonus;
    private final int defenseBonus;
    private final int skillBonus;
    private final int speedBonus;
    private final int HPBonus;
    private final int level;
    private final int price;
    private final ItemType type;

    public Item (String itemName, int strB, int defB, int sklB, int spdB, int HPB, int level, int price, ItemType type) {
        name = itemName;
        strengthBonus = strB;
        defenseBonus = defB;
        skillBonus = sklB;
        speedBonus = spdB;
        HPBonus = HPB;
        this.level = level;
        this.price = price;
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return the strengthBonus
     */
    public int getStrengthBonus() {
        return strengthBonus;
    }

    /**
     * @return the defenseBonus
     */
    public int getDefenseBonus() {
        return defenseBonus;
    }

    /**
     * @return the skillBonus
     */
    public int getSkillBonus() {
        return skillBonus;
    }

    /**
     * @return the speedBonus
     */
    public int getSpeedBonus() {
        return speedBonus;
    }

    /**
     * @return the HPBonus
     */
    public int getHPBonus() {
        return HPBonus;
    }

    /**
     * @return the type
     */
    public ItemType getType() {
        return type;
    }

    @Override
    public String toString(){
        return name;
    }

    /**
     * Compares this Item with another Item. If they have the same name it returns true
     */
    public boolean equals(Item other){
        if(other == null || this.getClass() != other.getClass())
            return false;
        else
            return (this.name.equals(other.getName()));
    }
}
