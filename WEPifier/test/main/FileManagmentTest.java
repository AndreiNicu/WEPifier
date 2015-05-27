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
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class FileManagmentTest {
    
    public FileManagmentTest() {
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
     * Test of setPath method, of class FileManagment.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String p = "/home/andrei/NetBeansProjects/WEPifier";
        FileManagment instance = new FileManagment();
        instance.setPath(p);
    }

    /**
     * Test of getPath method, of class FileManagment.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        FileManagment instance = new FileManagment();
        instance.setPath("/home/andrei/NetBeansProjects/WEPifier");
        String expResult = "/home/andrei/NetBeansProjects/WEPifier";
        String result = instance.getPath();
        System.out.println(expResult+"  "+result);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of writeToFile method, of class FileManagment.
     */
    @Test
    public void testWriteToFile() {
        System.out.println("writeToFile");
        String log = "This is a test.";
        FileManagment instance = new FileManagment();
        instance.writeToFile(log);
    }

    /**
     * Test of eraseFiles method, of class FileManagment.
     */
    @Test
    public void testEraseFiles() {
        System.out.println("eraseFiles");
        File t = new File("/home/andrei/NetBeansProjects/WEPifier");
        FileManagment instance = new FileManagment();
        instance.eraseFiles(t);
    }

    /**
     * Test of getPacketForMerge method, of class FileManagment.
     */
    @Test
    public void testGetPacketForMerge() {
        System.out.println("getPacketForMerge");
        File t = new File("/home/andrei/NetBeansProjects/WEPifier");
        FileManagment instance = new FileManagment();
        String expResult = "/home/andrei/NetBeansProjects/WEPifier/read-01-00-1E-58-0B-06-3A.xor";
        String result = instance.getPacketForMerge(t);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of eraseKeyFile method, of class FileManagment.
     */
    @Test
    public void testEraseKeyFile() {
        System.out.println("eraseKeyFile");
        File f = new File("/home/andrei/NetBeansProjects/WEPifier");
        FileManagment instance = new FileManagment();
        instance.eraseKeyFile(f);
    }
}
