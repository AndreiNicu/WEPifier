/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Please note that some methods might have been removed due to
 * their dynamic nature. Some may require some arguments which are
 * working only by connecting with other classes.
 * Some might have been removed due to non-static variables which cannot be
 * determined manualy.
 */
package main;

import java.io.File;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class FilterDataTest {
    
    public FilterDataTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setData method, of class FilterData.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String str = "";
        FilterData instance = new FilterData();
        instance.setData(str);
    }

    /**
     * Test of setBSSID method, of class FilterData.
     */
    @Test
    public void testSetBSSID() {
        System.out.println("setBSSID");
        String[] str = null;
        FilterData instance = new FilterData();
        instance.setBSSID(str);
    }

    /**
     * Test of setName method, of class FilterData.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String[] str = null;
        FilterData instance = new FilterData();
        instance.setName(str);
    }

    /**
     * Test of getLocalFolder method, of class FilterData.
     */
    @Test
    public void testGetLocalFolder_0args() {
        System.out.println("getLocalFolder");
        FilterData instance = new FilterData("dump-01.kismet.csv");
        String expResult = "/home/andrei/NetBeansProjects/WEPifier/";
        String result = instance.getLocalFolder();
        assertEquals(expResult, result);
    }
    /**
     * Test of getLocalFolder method, of class FilterData.
     */
    @Test
    public void testGetLocalFolder_File() {
        System.out.println("getLocalFolder");
        File f = new File("dump-01.kismet.csv");
        FilterData instance = new FilterData();
        String expResult = "/home/andrei/NetBeansProjects/WEPifier/";
        String result = instance.getLocalFolder(f);
        assertEquals(expResult, result);
    }
    /**
     * Test of getData method, of class FilterData.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        FilterData instance = new FilterData();
        String expResult = " ";
        String result = instance.getData();
        assertEquals(expResult, result);
    }
}
