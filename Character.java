import java.util.Random;
import java.util.*;

/**
 * Klassen representerer en karakter i et RPG-spill
 * 
 * @author Kristoffer Hansen
 * @version 25.10.2016
 */
abstract public class Character {
    public String name;
    public int health;
    public int gold;
    private int maxDmg;
    public int minDmg;
    private Random random;

    /**
     * Konstruktør for karakteren. 
     * Denne konstruktøren setter følgende standardverdier: 
     * Helse: 100, gull 1000
     * 
     * @param name Navnet på karakteren
     * @param charMinDmg Minimal skade karakteren kan gi.
     * @param charMaxDmg Maksimal skade karakteren kan gi
     */
    public Character(String name, int charMinDmg, int charMaxDmg) {
        setName(name);
        setMaxDmg(charMaxDmg);
        setMinDmg(charMinDmg);
        this.health = 100;
        setGold(1000);
        this.random = new Random();
    }

    /**
     * Angriper en karakter med en tilfeldig verdi mellom minimum og maksimum skade.
     * 
     * @param Character karakteren som skal angripes.
     * @return damage skaden som ble utført.
     */
    protected int attack(Character character) {
        int damage = this.random.nextInt(this.maxDmg + 1 - this.minDmg) + this.minDmg;
        character.changeHealth(-damage);
        return damage;
    }
    
    /**
     * Mutasjonsmetode for navn.
     * 
     * @param name spillerens nye navn.
     */
    protected void setName (String name) {
        this.name = Utils.checkString(name);
    }
    
    /**
     * Aksessmetode for navn.
     * 
     * @return name spillerens navn.
     */ 
    protected String getName() {
        return this.name;
    }
    
    /**
     * Mutasjonsmetode for gull.
     * 
     * @param gold spillerens nye gullbeholdning.
     */
    protected void setGold(int gold) {
        this.gold = Utils.cleanNegative(gold);
    }
    
    /**
     * Aksessmetode for gull.
     * 
     * @return gold spillerens gullbeholdning.
     */
    protected int getGold() {
        return this.gold;
    }

    /**
     * Metode for å sjekke om spilleren er død.
     * @return true hvis helsen til spillern er <= 0, false hvis ikke
     */
    protected boolean isDead() {
        return this.health <= 0;
    }
 
    /**
     * Sjekker om karakteren er i live.
     * 
     * @return true hvis karakteren lever.
     */
    protected boolean isAlive() {
        return 0 < this.health;
    }
    
    /**
     * Metode for å endre helsen til spilleren. Helsen kan bli endret, både med positive og negative heltall
     * @param newHealth den nye helsen til spilleren.
     */
    protected void changeHealth(int newHealth) {
        this.health += newHealth;
        if (this.health > 100) {
            this.health = 100;
        } else if(this.health < 0) {
            this.health = 0;
        }
    }
    
    /**
     * Aksessmetode for karakterens helse.
     * 
     * @return health karakterens helsetilstand.
     */
    protected int getHealth() {
        return this.health;
    }

    /**
     * Mutasjonsmetode for maks skade.
     * 
     * @param maxDmg karakterens maks skade.
     */
    protected void setMaxDmg(int maxDmg) {
        this.maxDmg = Utils.cleanNegative(maxDmg);
    }
    
    /**
     * Aksessmetode for maks skade.
     * 
     * @return maxDmg karakterens maks skade.
     */
    protected int getMaxDmg() {
        return this.maxDmg;
    }
    
    /**
     * Mutasjonsmetode for minimum skade.
     * 
     * @param minDmg karakterens minimum skade.
     */
    protected void setMinDmg(int minDmg) {
        this.minDmg = Utils.cleanNegative(minDmg);
    }
    
    /**
     * Aksessmetode for minimum skade.
     * 
     * @return minDmg karakterens minimum skade.
     */
    protected int getMinDmg() {
        return this.minDmg;
    }
    
    /**
     * Metode som generer tilfeldig max/min skade.
     * 
     * @param random
     */
    protected Random getRandom(){
        return this.random;
    }
    
}

