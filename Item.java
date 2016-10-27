/**
 * Denne klassen representer en gjenstand spilleren kan kjøpe
 * 
 * @author Kristoffer Hansen
 * @version 18.10.2016
 */
public class Item {
    private String name, description, action;
    private int value, weight;

    /**
     * Konstruktør for Item
     * @param name navnet på gjenstanden
     * @param description beskrivelse av gjenstanden
     * @param action hva skjer når spilleren bruker gjenstanden
     * @param value verdien på gjenstanden i gull
     * @param weight vekten på gjenstanden
     */
    public Item(String name, String description, String action, int value, int weight) {
        this.name = name;
        this.description = description;
        this.action = action;
        setValue(value);
        setWeight(weight);

    }
    
    /**
     * Metode for å printe informasjon om gjenstanden.
     */
    public void print() {
        System.out.println("Name: " + this.name);
        System.out.println("Description: " + this.description);
        System.out.println("Action: " + this.action);
        System.out.println("Value: " + this.value);
        System.out.println("Weight: " + this.weight);
        System.out.println();
    }
    
    @Override
    public String toString(){
        return "[ Name: " + this.name + " | Description: " + this.description + " " + 
        " | Value: " + this.value + " | Weight: " + this.weight + "]";
    }
    
    /**
     * Returnerer navnet på gjenstanden
     * @return Returnerer navnet på gjenstanden
     */
    public String getName() {
        return name;
    }

    /**
     * Setter navnet på gjenstanden
     * @param name Det nye navnet på gjenstanden
     */
    public void setName(String name) {
        this.name = Utils.checkString(name);
    }

    /**
     * Aksessmetode for beskrivelse av gjenstanden.
     * @return Beskrivelse av gjenstanden.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mutasjonsmetode for gjenstanden.
     * @param description Ny beskrivelse av gjenstanden.
     */
    public void setDescription(String description) {
        this.description = Utils.checkString(description);
    }

    /**
     * Aksessmetode for gjenstandens handling.
     * @return gjenstandens handling.
     */
    public String getAction() {
        return action;
    }

    /**
     * Mutasjonsmetode for gjenstandens handling.
     * @param action gjenstandens nye handling.
     */
    public void setAction(String action) {
        this.action = Utils.checkString(action);
    }

    /**
     * Aksessmetode for gjenstandens verdi.
     * @return gjenstandens verdi.
     */
    public int getValue() {
        return value;
    }

    /**
     * Mutasjonsmetode for gjenstandens vardi.
     * @param value gjenstandens nye verdi.
     */
    public void setValue(int value) {
        this.value = Utils.cleanNegative(value);
    }

    /**
     * Aksessmetode for gjenstandens vekt.
     * @return gjenstandens vekt.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Mutasjonsmetode for gjenstandens vekt.
     * @param weight gjenstandens nye vekt. 
     */
    public void setWeight(int weight) {
        this.weight = Utils.cleanNegative(weight);
    }
}
