import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Testklassen PlayerTest.
 *
 * @author Kristoffer Hansen
 * @version 25.10.2016
 */
public class PlayerTest
{
    private Player player;
    private Item item;
    private Item item2;
    private Monster monster;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        player = new Player("Kjell", "warrior", 50, 10);
        item = new Item("Sword", "A fine sword", "slashes",100, 5);
        item2 = new Item("Beer", "Tasty beer", "drinks", 50, 2);
        
        player.buyItem(item);
        player.buyItem(item2);
    }
    
    /**
     * Poisitiv test for sellItem
     */
    @Test
    public void testPositiveSellItem() {
        int gold = player.getGold();
        int itemValue = item.getValue();
        assertEquals(true, player.sellItem("Sword"));
        assertNull(player.findItem("Sword"));
        assertEquals(gold + itemValue, player.getGold());
    }
    
    /**
     * Negativ test for sellItem
     */
    @Test
    public void testNegativeSellItem() {
        player.sellItem("Horse");
        assertEquals(false, player.sellItem("Horse"));
    }
    
    /**
     * Tester changeHealth
     */
    @Test
    public void testChangeHealth() {
        player.changeHealth(-130);
        assertEquals(0, player.getHealth());
        player.changeHealth(1000);
        assertEquals(100, player.getHealth());
    }
    
    /**
     * Negativ test på setName
     */
    @Test
    public void testSetName() {
        player.setName("");
        assertEquals("Unspecified", player.getName());
    }
    
    //negativ test buyitem, forsøke å selge noe som ikke er i  items
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
