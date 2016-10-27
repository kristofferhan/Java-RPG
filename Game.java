import java.util.ArrayList;

/**
 * Klassen Game oppretter en spiller og items, legger til dette. 
 * Legger deretter til en liste med monstre og gjennomfører en kamp.
 * 
 * @author Kristoffer Hansen
 * @version 26.10.2016
 */
public class Game {
    public static void main(String[] args) {
        Player player = new Player("Reidar", "Warrior", 10, 50);
        Item knife = new Item("Knife", "Slashes the opponent", "slashes", 567, 1);
        Item potion = new Item("Health potion", "Heals your health", "drinks", 100, 1);
        Item axe = new Item("Axe", "Cuts down trees", "cuts", 250, 3);

        player.buyItem(knife);
        player.buyItem(potion);
        player.buyItem(axe);
       
        System.out.println(player);
                
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        monsters.add(new Monster("Golem", "elementa", 60));
        monsters.add(new Monster("Siren", "hybrid", 40));
        monsters.add(new Monster("Wraith", "specter", 20));
        monsters.add(new Monster("Wolf", "beast", 25));
        
        Battleground battleground = new Battleground(player, monsters);
        battleground.attack();
    }
}
