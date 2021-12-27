package projet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import projet.model.Box;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Box Tester.
 */
public class BoxTest {

    private Box b1, b2, b3;
    private ImageIcon im;

    @Before
    public void before() {
        im = new ImageIcon();
        b1 = new Box(1, 2, true);
        b2 = new Box(3, 4, im);
        b3 = new Box(5, 6, false, true, im);
    }

    @After
    public void after() {
    }

    /**
     * Method: isEliminable()
     */
    @Test
    public void testIsEliminable() {
        assertTrue(b3.isEliminable());
    }

    /**
     * Method: setEliminable(boolean eliminable)
     */
    @Test
    public void testSetEliminable() {
        b3.setEliminable(false);
        assertFalse(b3.isEliminable());
    }

    /**
     * Method: getIm()
     */
    @Test
    public void testGetIm() {
        assertEquals(im, b2.getIm());
    }

    /**
     * Method: setIm(ImageIcon im)
     */
    @Test
    public void testSetIm() {
        ImageIcon im2 = new ImageIcon();
        b2.setIm(im2);
        assertEquals(im2, b2.getIm());
        assertNotEquals(im, b2.getIm());
    }

    /**
     * Method: isPassable()
     */
    @Test
    public void testIsPassable() {
        assertTrue(b1.isPassable());
    }

    /**
     * Method: setPassable(boolean passable)
     */
    @Test
    public void testSetPassable() {
        b1.setPassable(false);
        assertFalse(b1.isPassable());
    }


} 
