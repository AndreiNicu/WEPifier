
package main;

/**
 *
 * @author root
 */
public class WEPAttack {   
    private String log;
    private int mark;
    private String crackFileName;
    private RunApplication ra;
    private NetworkScanner ns;
    public WEPAttack(NetworkScanner ns)
    {
        crackFileName="";
        log="";
        mark=0;
        this.ns=ns;
    }
    
    public void setLog(String str){log=str;}
    public void setMark(int x){mark=x;}
    public void setRunApplication(RunApplication t){ra=t;}
    
    public String getLog(){return log;}
    public int getMark(){return mark;}
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
            log=ra.getLog();
        }

    }
    
    public void attackNoClient(String targetName, String userMac, String fileName)
    {
        
    }
    public void startCrack()
    {
        String param=" -b "+ns.getTarget()+" -l "+ns.getNameAt(mark) +"-passwd.txt "+ns.getFilePath()+"read-01.cap";
        System.out.println("We are trying to invoke aircrack."+" "+param);
              
        System.out.println(log);
    }
    
}
