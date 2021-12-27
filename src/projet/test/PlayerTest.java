package projet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import projet.model.Player;
import projet.util.ElementLoader;

import static org.junit.Assert.assertEquals;

/**
 * Player Tester.
 */
public class PlayerTest {

    private Player p1;

    @Before
    public void before() {
        p1 = new Player(10, 20, 1, 3);
    }

    @After
    public void after() {
    }

    /**
     * Method: getPointVie()
     */
    @Test
    public void testGetPointVie() {
        assertEquals(3, p1.getPointVie());
    }

    /**
     * Method: getDirection()
     */
    @Test
    public void testGetDirection() {
        assertEquals(0, p1.getDirection());
    }

    /**
     * Method: getIm()
     */
    @Test
    public void testGetIm() {
        assertEquals(ElementLoader.imageMap.get("player" + p1.getDirection()), p1.getIm());
    }

    /**
     * Method: setPointVie(int pointVie)
     */
    @Test
    public void testSetPointVie() {
        p1.setPointVie(2);
        assertEquals(2, p1.getPointVie());
    }

    /**
     * Method: setDirection(int direction)
     */
    @Test
    public void testSetDirection() {
        p1.setDirection(4);
        assertEquals(4, p1.getDirection());
    }

    /**
     * Method: setNumPlayer(int numPlayer)
     */
    @Test
    public void testSetNumPlayer() {
        p1.setNumPlayer(2);
        assertEquals(2, p1.getNumPlayer());
    }

    /**
     * Method: showPosition()
     */
    @Test
    public void testShowPosition() {
        p1.showPosition();
    }


} 
