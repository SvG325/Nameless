package rsi.nameless;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stéphanie on 18-5-2016.
 * Edited by Rick on 25-5-2016
 */
public class Map {
    public enum eventType { BATTLE, SHOP, EMPTY }
    private eventType[] events; //Array of events at point 0, ..., 8
    private Character player;
    private int currentPoint, bgImgID; //Current location of player
    private int level; //Level of the map
    private ItemLibrary items;
    private ArrayList<Character> mapEnemies;
    private Character boss;
    private boolean battle; // is the player currently in a battle event
    private boolean shop; // is the player currently in a shop event
    private Conversation conv; //The starting conversation of the map


    /**
     * Basic constructor
     */
    public Map(int level, EnemyLibrary enemies, ItemLibrary items, ConversationLibrary convLib){
        this.level = level;
        mapEnemies = enemies.getEnemiesWithLevel(level);
        boss = enemies.getBoss(level-1);
        conv = convLib.getConversation(level-1);
        this.items = items;
        this.events = new eventType[9];
        battle = false;
        shop = false;
        generateEventTypes();
        currentPoint = 8;

        switch(level){
            case 1:
                bgImgID = R.drawable.achtergrond1;
                break;
            case 2:
                bgImgID = R.drawable.desert_background;
                break;
            case 3:
                bgImgID = R.drawable.bglvl3;
                break;
        }
    }

    /**
     * Basic setter for player
     * @param player the new instance of player
     */
    public void setPlayer(Character player) {
        this.player = player;
    }


    private Event generateEvent(int index) {
        if (events[index] == eventType.SHOP) {
            return new Shop(level, items);
        } else if (events[index] == eventType.BATTLE && index!=0) {
            Random rand = new Random();
            int limit = mapEnemies.size();
            int randomInt = rand.nextInt(limit);
            return new Battle(player, mapEnemies.get(randomInt));
        } else if (index == 0){
            return new Battle(player, boss);
        } else {
            return new EmptyEvent();
        }
    }

    /**
     * Fills the world with eventTypes (Shop, Battles, etc)
     */
    private void generateEventTypes(){
        Random rand = new Random();
        int rdNR = rand.nextInt(3);
        events[rdNR+3] = eventType.SHOP;
        for(int i=0; i<8; i++){
            if(events[i] == null){
                events[i] = eventType.BATTLE;
            }
        }
        events[8] = eventType.EMPTY;
    }

    /**
     * Sets battle and shop to the values of the current point
     * @param point the point to inspect for a battle or shop
     */
    public void setCurrentPoint(int point) {
        currentPoint = point;
        if(events[currentPoint] == eventType.BATTLE) { //Hier stond currentPoint-1, waarom?
            shop = false;
            battle = true;
        }
        else if(events[currentPoint] == eventType.SHOP) { //Hier stond currentPoint-1, waarom?
            battle = false;
            shop = true;
        }
        else {
            shop = false;
            battle = false;
        }
    }


    /**
     * Getter for battle
     * @return true if the player is in battle
     */
    public boolean inBattle() {
        return battle;
    }

    /**
     * Getter for shop
     * @return true if the player is in a shop
     */
    public boolean inShop(){
        return shop;
    }

    /**
     * Getter for currentPoint
     * @return the current location of the player
     */
    public int getCurrentPoint(){
        return currentPoint;
    }

    public Character getPlayer(){
        return player;
    }

    public int getLevel(){
        return level;
    }

    /**
     * Getter for an event at an index
     * @param index the index of the event
     * @return the event at the specified index
     */
    public Event getEvent(int index) {
        return generateEvent(index);
    }

    public void setEvent(int index, eventType type){
        this.events[index] = type;
    }

    public Conversation getConversation(){
        return conv;
    }

    public int getBgImgID(){
        return bgImgID;
    }
    
}
