import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Klassen representerer en spiller i et RPG-spill
 * 
 * @author Kristoffer Hansen
 * @version 25.10.2016
 */
public class Player extends Character {
    private String type;
    private int capacity;
    private HashMap<String, Item> items;

    /**
     * Konstruktør for spilleren. Setter kapasiteten til spilleren til 50kg. 
     * 
     * @param name Navnet på spilleren
     * @param type Spillerens type. Spilleren kan bare være en av følgende [mage, warrior, rogue, ranger]. Hvis annet er spesifisert blir typen satt til unspecified.
     * @param minDmg Minimum skade spilleren kan gi.
     * @param maxDmg Maksimum skade spilleren kan gi.
     */
    public Player(String name, String type, int minDmg, int maxDmg) {
        super(name, minDmg, maxDmg);
        setType(type);
        setCapacity(50);
        this.items = new HashMap<String, Item>();
    }

    /**
     * Metode for å kjøpe en gjenstand. Printer ut informasjon hvis spilleren ikke eier gjenstanden han/hun forsøker å selge.
     * @param item gjenstanden som spilleren vil kjøpe
     */
    public void buyItem(Item item) {
        // Vi sjekker om spilleren kan bære gjenstanden og at han har nok gull til å kjøpe gjenstanden
        if(enoughCapacity(item) & hasEnoughMoney(item)) {
            // Legg så til gjenstanden i listen
            items.put(item.getName(), item);
            // Og trekk fra kostnaden for gjenstanden
            this.gold -= item.getValue();
        }
        else {
            System.out.println("Computer says no.");
        }
    }

    /**
     * Metode for å finne en gjenstand som spilleren har kjøpt
     * @param itemName Navnet på gjenstanden du ønsker å finne
     */
    public Item findItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Metode for å selge en gjenstand
     * @param itemName gjenstanden som ønskes solgt
     * @return true hvis gjenstanden er solgt, false hvis ikke
     */
    public boolean sellItem(String itemName) {
        // Vi finner først gjenstanden vi ønsker å selge
        Item item = findItem(itemName);
        // Så sjekker vi at den eksisterer. Hvis den er null så fant vi den ikke
        if(item != null) {
            // Så fjerner vi gjenstanden fra listen til spilleren
            items.remove(itemName);
            // Og øker antall gull som gjenstanden koster
            this.gold += item.getValue();
            System.out.println(itemName + " sold for " + item.getValue() + " gold!");;
            return true;
        }else {
            System.out.println("Sorry, you don't own this item.");
            return false;
        }
    }

    /**
     * Metode for å bruke en gjenstand
     * @param itemName gjenstanden man vil bruke
     * @return true hvis gjenstanden ble brukt, false hvis ikke
     */
    public boolean useItem(String itemName) {
        Item item = findItem(itemName);
        if (item != null){
            System.out.println(getName() + " " + item.getAction() + " " + item.getName());
            return true;
        }
        System.out.println("You don't own this item.");
        return false;
    }

    /**
     * Metode som returnerer informasjon om spilleren og gjenstandene til spilleren.
     * @return en streng som representerer dette.
     */
    @Override
    public String toString() {
        String string = "Name: " + this.getName() + " Type: " + this.getType() + " Max Weight: " + capacity + " Gold: " + this.getGold();
        String nl = System.lineSeparator();
        if(isAlive()){
            string += nl + "Is alive with health: " + this.getHealth();
        } else {
            string += nl + "Is dead. Game Over";
        }
        string += nl + "Inventory";
        for(Item item : this.items.values()){
            string += nl + " - " + item;
        }
        return string;
    }
    
   
       
    /**
     * Mutasjonsmetode for type.
     * @param type karakterens nye type.
     */
    public void setType(String type) {
        if (type.trim().toLowerCase().equals("mage") || (type.trim().toLowerCase().equals("warrior")
            || (type.trim().toLowerCase().equals("rogue") || (type.trim().toLowerCase().equals("ranger"))))) {
            this.type = type.trim().toLowerCase();
        }else {
            this.type = "Unspecified";
        }
    }
    
    /**
     * Aksessmetode for type.
     * @return type karakterens type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Mutasjonsmetode for kapasitet.
     * @param capacity spillerens nye kapasitet.
     */
    public void setCapacity(int capacity) {
        this.capacity = Utils.cleanNegative(capacity);
    }
    
    /**
     * Aksessmetode for kapasitet.
     * @return capacity spillerens kapasitet.
     */
    public int getCapacity() {
        return this.capacity;
    }
    
    /**
     * Hjelpemetode for å sjekke om spilleren kan bære en gjenstand.
     * @param item gjenstanden vi sjekker mot.
     * @return true hvis spilleren har kapasitet til å bære gjenstanden, false hvis ikke.
     */
    public boolean enoughCapacity(Item item) {
        return (totalWeight() + item.getWeight()) <= this.capacity;
    }

    /**
     * Hjelpemetode for å sjekke om spilleren har nok penger til å kjøpe gjenstanden.
     * @param item gjenstanden spilleren ønsker å kjøpe.
     * @return true hvis spilleren har nok penger, false hvis ikke.
     */
    public boolean hasEnoughMoney(Item item) {
        return this.gold > item.getValue();
    }
    
    /**
     * Metode for å returnere totalvekten på alle gjenstandene spilleren bærer på.
     * @return totalWeight den totale vekten på spillerens gjenstander. 
     */
    public int totalWeight() {
        int totalWeight = 0;
        for (Item item : this.items.values()) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
}

