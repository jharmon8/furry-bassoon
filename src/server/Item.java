package server;
/**
 * Created by anishdalal on 8/30/17.
 */
public class Item {

    private String type;
    private String itemId;
    private int weight;
    private String condition;
    private int value;
    private String itemName;

    public Item(String itemName, int weight, int value) {
        this.itemName = itemName;
        this.weight = weight;
        this.value = value;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemName(String newName) {
         this.itemName = newName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemWeight(int newWeight) {
        this.weight = newWeight;
    }

    public int getItemWeight() {
        return this.weight;
    }

    public void setItemValue(int newValue) {
        this.value = newValue;
    }

    public int getItemValue() {
        return this.value;
    }


}
