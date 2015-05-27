package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class WEPifier extends JPanel {
	private JPanel btnPan;
	private JButton scan;
	private JButton stop;
	private JTextField log;
	private JTextPane dat;
	private Dimension frDim= new Dimension(300,500);
	private Dimension btnDim = new Dimension(70,20);
	public WEPifier()
	{
		btnPan = new JPanel();
		btnPan.setPreferredSize(new Dimension(300,100));
		
		scan = new JButton("Scan");
		scan.setPreferredSize(btnDim);
		scan.addActionListener(new ButonListener());
		
		stop = new JButton("Stop");
		stop.setPreferredSize(btnDim);
		stop.addActionListener(new ButonListener());
		btnPan.add(scan);
		btnPan.add(stop);
		
		log = new JTextField();
		
		setPreferredSize(frDim);
		setLayout(new BorderLayout());
		add(btnPan,BorderLayout.NORTH);
		add(log,BorderLayout.CENTER);
		
	}
	private void setText(String str)
	{
		log.setText(str);
	}
	private class ButonListener implements ActionListener{
		
		private void buildProcess(String[] str)
		{
			ProcessBuilder pb = new ProcessBuilder(str);
			try {
				String string;
				Process p = pb.start();
				BufferedReader bf = new BufferedReader
				(new InputStreamReader(p.getInputStream()));
				BufferedReader bi = new BufferedReader
				(new InputStreamReader(p.getErrorStream()));
				while((string=bf.readLine())!=null)
					log.setText(string);
				bi.close();
				while((string=bi.readLine())!=null)
						log.setText(string);
				bi.close();
				p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(scan))
			{
				String[] str = {"sudo airmon-ng"," start", "wlan0"};
				//log.setText("Been Here");
				buildProcess(str);
				
			}
			if(e.getSource().equals(stop))
			{
				String[] str = {"sudo airmon-ng", "start", "wlan0"};
				buildProcess(str);
			}
			
		}
		
	}
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("WEPifier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new WEPifier());
		frame.setVisible(true);
		frame.pack();
	}
}
