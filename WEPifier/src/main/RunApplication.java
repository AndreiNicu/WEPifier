package main;
/**
 * @author Team 8
 * WEPifier v1.0
 * RunApplication.class
 * This class is meant to run external processes of the aircrack-ng suite
 * Please note that for now the code is not yet 100% stable
 * more adjustment to come in later versions
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Timer;

public class RunApplication implements Runnable {
	private Process p;            //the process which must run
	private String proc;          //process name           
	private String param;        // parameters of invocation
	private String log;         // log generated by the invoked application
	private boolean shouldStop; // true if the invoked application must stop after a number of seconds
	private long maxRuntime;  //the number of milliseconds of runtime
	private long currentRuntime; //the current runtime
	private Timer waitTime;
	private boolean shouldPrint;  //true if target should not print yet
	/**
         * 
         * @param stop Should the application stop
         * @param procName Name of the process for executing
         * @param param Parameters for execution
         * @param time Time to execute external process
         * @param print Should print to screen or not
         */
	public RunApplication(boolean stop, String procName, String param, long time, boolean print)
	{
		setStopMode(stop);
		proc = procName;
		this.param = param;
		maxRuntime = time;
		setPrintMode(print);
		currentRuntime = 0;
	}
	public void setProc(String n){proc =n;}
	public void setParam(String n){param = n;}
	public void setLog(String n){log=n;}
	public void setStopMode(boolean flag){shouldStop=flag;}
	public void setStopTime(long mls){maxRuntime=mls;}
	public void setPrintMode(boolean flag){shouldPrint=flag;}
	public String getProcessID(){return p.toString();}
	
	public String getProcName(){return proc;}
	public String getParam(){return param;}
	public String getLog(){return log;}
	public boolean getStopState(){return shouldStop;}
	public long getStopTime(){return maxRuntime;}
	public boolean getPrintMode(){return shouldPrint;}

	/**
	 * Run method inherited from the Runnable interface.
	 * Please note that all running an external application is located here
	 * WARNING! Code may become unstable ---- more testing is needed as well as 
	 * a better implementation model 
	 * @throws InterruptedException 
	 */
	public void run() {
		//synchronized(this){
			log="";
			String read="";
			try {
				p = Runtime.getRuntime().exec(proc+" "+param);  //Execution of the external app
				if(getPrintMode()){
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					BufferedReader bre = new BufferedReader
							(new InputStreamReader(p.getErrorStream()));
			
					while((read=br.readLine())!=null)
					{
						log+=read+"\n";
					}
					br.close();
					while((read=bre.readLine())!=null)
					{
						log+=read+"\n";
					}
					bre.close();
					setStopMode(true);
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * Please note that the threading problem is almost fixed.
			 * A more stable version will soon follow
			 */
			if(!getPrintMode()&&maxRuntime!=-1)
			{
				waitTime = new Timer(1, new TimeListener());
				waitTime.start();
			}
			if(!getPrintMode()&&maxRuntime==-2)
			{
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//}
	}
	private class TimeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(currentRuntime>=maxRuntime)
			{
				waitTime.stop();
				p.destroy();
				currentRuntime=0;
			}
			currentRuntime++;	
		}
	}
}