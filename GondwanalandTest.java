/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lluuldea
 */
public class GondwanalandTest {
    
    public GondwanalandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Gondwanaland.
     */
    //@Test
    public void testMain() {
        //System.out.println("main");
        //String[] args = null;
        //Gondwanaland.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of solve method, of class Gondwanaland.
     */
    @Test
    public void testSolve() {
        System.out.println("A 183-5724 17 58 18 04");
        char[] step = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A' };
        String[] phone = { "183-5724", "183-5724", "183-5724", "183-5724", "183-5724" ,"183-5724", "183-5724", "183-5724", "183-5724", "183-5724" };
        int[] startMinute = { 1078, 540, 1140, 1500, 420, 540, 540, 1140, 1140, 540, 1350, 1380, 1140 } ;
        int[] endMinute = { 1084, 1140, 1380, 540, 540, 1380, 420, 420, 1110, 510, 1170, 1350, 540 };
        String[] expResult = {"  183-5724     2     4     0  A    0,44"};
        String[] pasta = { "0,44", "57,60", "12,00", "16,60", "7,20", "69,60", "79,20", "21,60", "84,60", "83,40", "76,80", "85,80", "28,80" };
        for ( int i = 0; i < 10; i++ ) {
            String result = Gondwanaland.solve(step[i], phone[i], startMinute[i], endMinute[i]);
            String[] components;
            components = result.split( "\\p{Space}+" );
            System.out.printf( "%d - %d\n", startMinute[i], endMinute[i]);
            System.out.println( result );
            //assertEquals(expResult[i], result);
            assertEquals(pasta[i], components[6]);
        }
    }
}