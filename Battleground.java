import java.util.ArrayList;
import java.util.Random;

/**
 * En klasse hvor kampene i spillet foregår.
 * 
 * @author Kristoffer Hansen
 * @version 25.10.2016
 */

public class Battleground {
    private Player player;
    private ArrayList<Monster> monsters;
    private Monster currentMonster;
    private Random random;
    private InputReader reader;

    /**
     * Oppretter en Battleground med en player, en liste med monstre og et tilfeldig monster.
     * @param player Spilleren som skal inn i kamp.
     */
    public Battleground(Player player, ArrayList<Monster> monsters) {
        this.player = player;
        this.monsters = monsters;
        this.random = new Random();
        this.currentMonster = selectRandomMonster();
        this.reader = new InputReader();
    } 

    /**
     * Starter en kamp og kaller metoder under kampen.
     */
    public void attack() {
        printWelcome();
        while(this.player.isAlive() && this.currentMonster.isAlive()) {
            String input = reader.getInput();
            if(input.contains("attack")) {
                newRound();
            } else if (input.contains("run")) {
                run();
                break;
            } else if (input.contains("info")) {
                System.out.println(this.player.toString());
            } else if (input.contains("help")) {
                System.out.println("To play, enter eiter 'attack' or 'run'. Enter 'info' for info about the player. \nPsssst, you can type 'heal' to get some more health, although this is considered cheating.");
            } else if (input.contains("heal")) {
                int addHealth = 10;
                this.player.changeHealth(this.player.getHealth() + addHealth);
                System.out.println("You patched yourself up a little bit. Your health is now " + this.player.getHealth());
            } else {
                System.err.println("You cannot do that. Type 'help' if you don't know what to do");
            }
        }
        endAttack();
    }

    /**
     * Metode som håndterer en runde. Spilleren angriper monsteret og monsteret angriper
     * tilbake hvis det fortsatt er i live.
     */
    public void newRound() {
        int dmgToMonster = this.player.attack(this.currentMonster);
        System.out.println(this.player.getName() + " attacks " + this.currentMonster.getName());
        System.out.println(this.currentMonster.getName() + " looses " + dmgToMonster + " health");
        System.out.println(this.currentMonster.getName() + " now has " + this.currentMonster.getHealth() + " health ");
        System.out.println();
        if (this.currentMonster.isAlive()) {
            int dmgToPlayer = this.currentMonster.attack(this.player);
            System.out.println(this.currentMonster.getName() + " attacks " + this.player.getName());
            System.out.println(this.player.getName() + " looses " + dmgToPlayer + " health ");
            System.out.println(this.player.getName() + " now has " + this.player.getHealth() + " health");
        } 
    }
    
    /**
     * Metode som håndterer slutten av en runde. Hvis spilleren fortsatt lever og monsteret er dødt, vinner spilleren 50 gull. 
     * Spilleren kan deretter velge å slåss mot et nytt monster eller avslutte kampen.
     */
    public void endAttack() {
        if(this.player.isAlive() && this.currentMonster.isDead() && monsters.size() != 0) {
            int goldWin = 50;
            this.player.setGold(this.player.getGold() + goldWin);
            System.out.println("Hooray! You won.");
            System.out.println(this.player.getName() + " took 50 gold from " + this.currentMonster.getName());
            System.out.println("You now have " + this.player.getGold() + " gold.");
            System.out.println("Do you want to fight another monster?");
            String input = reader.getInput();
            if(input.contains("yes"))  {
                this.currentMonster = selectRandomMonster();
                this.attack();
            } else if(input.contains("no")) {
                System.out.println("Alright. Well done!");
            }
        } else if(this.player.isDead()) {
            System.out.println("You died and quit.");
        } else if(this.player.isAlive() && monsters.size() == 0) {
            System.out.println("Congratulations! You have defeated all the monsters");
        }
    }
            
    /**
     * Metode som håndterer en flukt fra kampen. Trekker fra 50 gull fra spilleren. 
     */
    public void run() {
        int goldLoss = 50;
        this.player.setGold(this.player.getGold() - goldLoss);
        System.out.println(this.player.getName() + " runs away from the battle like a sissy and looses 50 gold!");
        System.out.println("You found a safe place behind the treeline.");
        System.out.println(this.player.toString());
    }

    /**
     * Printer ut en velkomst på starten av en kamp.
     */
    public void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the battleground!");
        System.out.println("A wild " + this.currentMonster.getName() + " appears!");
        System.out.println("What do you want to do? Type 'help' if you need help");
    }

    /**
     * Velger et tilfeldig monster fra listen med monstre og fjerner det fra listen.
     * @return et tilfeldig monster.
     */
    public Monster selectRandomMonster() {
        return this.monsters.remove(this.random.nextInt(this.monsters.size()));
    }

    
}
