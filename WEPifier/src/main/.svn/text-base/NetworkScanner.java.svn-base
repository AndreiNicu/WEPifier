package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Scanner;
/**
 * 
 * @author Team8
 *
 */

public class NetworkScanner{
	private String ID;          
        private String nDevice=""; //network card name
        private String mDevice=""; //monitor card name
        private int mCount=0;  // monitor devices number
	private FilterData fd;
	public NetworkScanner()
	{
		fd = new FilterData();
	}
        /**
         * Sets ID for specified target.
         * @param index Location of BSSID in the array
         */
        public void setTarget(int index){ID= fd.getBSSIDAtIndex(index);}
      /**
       * Resets the name of Network devices found.
       */
        public void resetNetworkDevice(){nDevice="";}
       /**
        * Resets the name of Monitor devices found.
        */
        public void resetMonitorDevice(){mDevice="";}
        /**
         * 
         * @return Name of the Network device.
         */
        public String getNetworkDeviceName(){return nDevice;}
        /**
         * 
         * @return Name of the Monitor device.
         */
        public String getMonitorDeviceName(){return mDevice;}
       /**
        * 
        * @param index Location of Protection in the array
        * @return Name of protection at specified index
        */
        public String getProtectionAt(int index){return fd.getProtectionAtIndex(index);}
       /**
        * 
        * @return Number of monitor devices.
        */
        public int getNumberOfMonitorDevices(){return mCount;}
       /**
        * 
        * @return Path of the file 
        */
        public String getFilePath(){return fd.getLocalFolder();}
       /**
        * 
        * @return ID of the target
        */
        public String getTarget(){return ID;}
       /**
        * 
        * @return Number of targets
        */
        public int getNumberOf(){return fd.getSizeOfBssid();} 
       /**
        * 
        * @param i Location of BSSID in the array
        * @return The BSSID of the target
        */
        public String getIdAt(int i){return fd.getBSSIDAtIndex(i);}
       /**
        * 
        * @param i Location of the ESSID in the array
        * @return The ESSID of the target
        */
        public String getNameAt(int i){return fd.getESSIDAtIndex(i);}
        /**
         * 
         * @return A compilation of ESSID + BSSID + Protection for menu items
         */
        public String[] getTargets(){
            String temp[] = new String[fd.getBSSID().length];
            for(int i=0;i<temp.length;i++)
                temp[i]=fd.getBSSIDAtIndex(i) + "  -  "+fd.getESSIDAtIndex(i)+
                        "      "+fd.getProtectionAtIndex(i);
            return temp;
        }
        /**
         * 
         * @param mac BSSID entered by user
         * @return Location of BSSID in the array if found
         */
        public int searchByMAC(String mac){return fd.getTargetByMAC(mac);}
        /*
         * Method will retrieve the name of wireless card and monitor device.
         * 
         */
        private void getNetworkInterface(File f)
        {
            String interfaces="";
            try{
                Scanner scan = new Scanner(f);
                while(scan.hasNext())
                {
                    String str = scan.nextLine();
                    for(int i=0;i<str.length();i++)
                    {
                        char c = str.charAt(i);
                        if((c==32)||(c=='\n')||(c=='\t'))
                            break;
                        interfaces+=c;
                    }
                    if(interfaces.equals("Interface"))
                        interfaces="";
                    interfaces+="\n";
                }
                f.delete();
                String temp = "";
                for(int i=13;i<interfaces.length();i++)
                {
                    char c = interfaces.charAt(i);
                    if((c!=' ')&&(c!='\t')&&(c!='\n'))
                          temp+=c;
                    if(c=='\n'){
                        if(temp.indexOf("mon")>=0){
                            mDevice=temp;
                            mCount++;
                        }
                        if(temp.indexOf("wlan")>=0)
                            nDevice=temp;
                        temp="";
                   }
                }
            } catch(IOException e)
            {
               // System.out.println("No file found.");
            }
        }
        /**
         * Initiate names of wireless card and monitor devices and write them
         * to file for secondary method to read.
         */
        public void setNetworkDevices()
        {
            RunApplication ra = new RunApplication(false, "airmon-ng", "", 0, true);
            ra.run();
            FileWriter fl=null;
            try{
                fl = new FileWriter("temp.txt");
            } catch(IOException e)
            {
               // System.out.println("Could not create such file.");
            }
            if(fl!=null)
            {
                BufferedWriter out = new BufferedWriter(fl);
                try{
                out.write(ra.getLog());
                out.close();
                } catch(IOException e)
                {
                   // System.out.println("Could not write to file.");
                }
                getNetworkInterface(new File("temp.txt"));
            }
        }
        /**
         * Filter network interfaces from generated blocks.
         */
	public void readNetworkInterfaces()
	{
                 fd = new FilterData("dump-01.kismet.csv");
	}
	public void startSniffer()
	{
		 new Sniffer();
	}
               
        public String getMAC()
        {
            
            try {
		NetworkInterface network = NetworkInterface.getByName("wlan0");
		byte[] mac=network.getHardwareAddress();
                String str = "";
                String MAC="";
                for(int i=0;i<mac.length;i++)
                  {
                      str+=Integer.toHexString((int)mac[i])+":";

                  }
                for(int i=0;i<str.length();i++)
                {
                    char c = str.charAt(i);
                    if(c==':'){
                        if((str.charAt(i-1)=='0')&& (((i-2)==-1)||(str.charAt(i-2)==':')))
                            MAC+="00";
                        else
                            MAC+=str.charAt(i-2)+""+str.charAt(i-1);
                        if((i+1)<str.length())
                            MAC+=":";
                    }
                }
                return MAC;
            }  
            catch (SocketException e) {
		return "No such device was found!";
			}
        }
	private class Sniffer
	{
		/*
		 * 1)setStopMode
		 * 2)setProcName
		 * 3)setParam
		 * 4)setRuntime
		 * 5)setPrintMode
		 */
		public Sniffer()
		{
			Thread t = new Thread(){
				public void run()
				{
					int i=0;
					boolean first = true;
					Process p = null;
						if(first){
							try {
								p = Runtime.getRuntime().exec
					("xterm -title Sniffer -bg black -fg red -iconic -e airodump-ng --bssid "+ID+" -w read mon0");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							first=false;
						}
						FilterData fl = new FilterData("read-01.csv");
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                                                  try{p.waitFor();}
                                                  catch(InterruptedException e)
                                                  {
                                                      e.printStackTrace();
                                                  }
					p.destroy();
				}
			};
			t.start();
		}
	}
}
