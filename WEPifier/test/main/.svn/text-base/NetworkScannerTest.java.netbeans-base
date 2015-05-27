/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.'
 * 
 * Please note that some methods might have been removed due to
 * their dynamic nature. Some may require some arguments which are
 * working only by connecting with other classes.
 * Some might have been removed due to non-static variables which cannot be
 * determined manualy.
 */
package main;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class NetworkScannerTest {
    
    public NetworkScannerTest() {
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
     * Test of resetNetworkDevice method, of class NetworkScanner.
     */
    @Test
    public void testResetNetworkDevice() {
        System.out.println("resetNetworkDevice");
        NetworkScanner instance = new NetworkScanner();
        instance.resetNetworkDevice();
    }

    /**
     * Test of resetMonitorDevice method, of class NetworkScanner.
     */
    @Test
    public void testResetMonitorDevice() {
        System.out.println("resetMonitorDevice");
        NetworkScanner instance = new NetworkScanner();
        instance.resetMonitorDevice();
    }

    /**
     * Test of getNetworkDeviceName method, of class NetworkScanner.
     */
    @Test
    public void testGetNetworkDeviceName() {
        System.out.println("getNetworkDeviceName");
        NetworkScanner instance = new NetworkScanner();
        instance.setNetworkDevices();
        String expResult = "wlan0";
        String result = instance.getNetworkDeviceName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonitorDeviceName method, of class NetworkScanner.
     */
    @Test
    public void testGetMonitorDeviceName() {
        System.out.println("getMonitorDeviceName");
        NetworkScanner instance = new NetworkScanner();
        instance.setNetworkDevices();
        String expResult = "mon0";
        String result = instance.getMonitorDeviceName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfMonitorDevices method, of class NetworkScanner.
     */
    @Test
    public void testGetNumberOfMonitorDevices() {
        System.out.println("getNumberOfMonitorDevices");
        NetworkScanner instance = new NetworkScanner();
        instance.setNetworkDevices();
        int expResult = 1;
        int result = instance.getNumberOfMonitorDevices();
        assertEquals(expResult, result);
    }
    
}
