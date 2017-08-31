package server;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by anishdalal on 8/30/17.
 */

public class Player {

    private int health;
    private ArrayList<Item> inventory;
    private int capacity;
    private int money;

    public Player(ArrayList<Item> inventory, int capacity) {
        this.inventory = inventory;
        this.capacity = capacity;
    }

    public int addItemToInventory(Item item) {
        int currWeight = calculateInventoryWeight();
        if (currWeight + item.getItemWeight() > this.capacity) {
            return -1;
        }
        inventory.add(item);
        // this.capacity += item.getItemWeight();
        return 0;
    }

    public int removeItemFromInventory(String name) {
        for(int i = 0; i < inventory.size(); i ++) {
            if(this.inventory.get(i).getItemName().equals(name)) {
                this.inventory.remove(i);
                return 0;
            }
        }
        return -1;
    }

    public int removeItemFromInventoryWithId(String id) {
        for(int i = 0; i < inventory.size(); i ++){
            if(this.inventory.get(i).getItemId().equals(id)) {
                this.inventory.remove(i);
                return 0;
            }
        }
        return -1;
    }

    public int calculateInventoryWeight() {
        int weight = 0;
        for (Item item: inventory) {
            weight += item.getItemWeight();
        }
        return weight;
    }

    public void printOutInventory()
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            System.out.println(String.format("%d - %s", i, this.inventory.get(i).getItemName()));
        }
    }
}
