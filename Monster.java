import java.util.ArrayList;
import java.util.Random;

/**
 * Klassen representerer et monster i et RPG-spill.
 * 
 * @author Kristoffer Hansen
 * @version 25.10.2016
 */
public class Monster extends Character {
    private String type;
    
    /**
     * Konstruktør for klassen Monster. Minimum skade er satt til 20.
     * 
     * @param name Navnet på monsteret.
     * @param type Monsterets type. Monsteret kan bare være en av følgende [beast, specter, hybrid, elementa]. Hvis annet er spesifisert blir typen satt til unspecified. 
     * @param maxDmg Maksimal skade.
     * 
     */
    public Monster(String name, String type, int maxDmg) {
        super(name, 20, maxDmg);
        setType(type);
    }

    /**
     * Mutasjonsmetode for type.
     * @param type monsterets nye type.
     */
    public void setType(String type) {
        if (type.trim().toLowerCase().equals("beast") || (type.trim().toLowerCase().equals("specter")
            || (type.trim().toLowerCase().equals("hybrid") || (type.trim().toLowerCase().equals("elementa"))))) {
            this.type = type.trim().toLowerCase();
        }else {
            this.type = "Unspecified";
        }
    }
    
    /**
     * Aksessmetode for type.
     * @return type monsterets type.
     */
    public String getType() {
        return this.type;
    }
}
