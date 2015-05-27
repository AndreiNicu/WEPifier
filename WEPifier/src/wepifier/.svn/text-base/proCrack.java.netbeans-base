/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * proCrack.java
 *
 * Created on May 9, 2012, 4:31:27 PM
 */

package wepifier;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import main.WEPConnection;
    
/**
 *
 * @author root
 */
public class proCrack extends javax.swing.JFrame {
    private JRadioButton BSSIDArray[];
    private WEPConnection wc;
    private int flag = -1;
    private int intervalVal = 0;
    /** Creates new form proCrack */
    public proCrack() {
        initComponents();
        lowerTextfield.setBackground(new Color(20,0,10));
        lowerTextfield.setForeground(Color.red);
        lowerTextfield.setBorder(null);
        lowerTextfield.setFont(new Font("Courier",Font.PLAIN,12));
        lowerTextfield.setText("");
        lowerPane.setBorder(null);
        
        upperTextfield.setBackground(new Color(20,0,10));
        upperTextfield.setForeground(Color.red);
        upperTextfield.setBorder(null);
        upperTextfield.setFont(new Font("Courier",Font.PLAIN,12));
        upperTextfield.setText("");
        upperPane.setBorder(null);

        removeVirtualMons.addActionListener(new EmulatorListener());
        wc = new WEPConnection();
        upperTextfield.append(wc.userSystemInfo()+"\n");

        rescanAccesspoints.addActionListener(new ActionList());
        manualBssid.getDocument().addDocumentListener(new DocumentList());
        crack.addActionListener(new ActionList());
        startSniffer.addActionListener(new ActionList());
        arpRequestReplayAttach.addActionListener(new ActionList());
        chopChopAttack.addActionListener(new ActionList());
        fragmentAttack.addActionListener(new ActionList());
        fragWithClients.addActionListener(new ActionList());
        cafeLatteAttack.addActionListener(new ActionList());
        initializer.addActionListener(new ActionList());
        if(wc.isClearedToRun())
            upperTextfield.append("Aircrack-ng suite found!\nPlease initialize the application to contiune.\n"+
            "When the initialization has started, please wait a couple of moments for it to finish.\n");
        else
        {
            if(!wc.isAircrackInstalled())
                upperTextfield.append("Aircrack-ng suite not found!\n");
            initializer.setEnabled(false);
            removeVirtualMons.setEnabled(false);
        }
        setTitle("Expert Crack");
    }
    /** This method generates bssid(s) under Emulator */
    private void addBSSIDToVictimMenu(){
           BSSIDArray = new JRadioButton[wc.getTargets().length];
           victims.removeAll();
          for(int i=0;i<BSSIDArray.length;i++){
              BSSIDArray[i] = new JRadioButton(wc.getTargets()[i]);
              BSSIDArray[i].addActionListener(new EmulatorListener());
              victims.add(BSSIDArray[i]);
          }
           upperTextfield.append(wc.getTargets().length+" targets have been found\n");
    }
    /**
     * checks which BSSID is selected
     */
    private void BSSIDSelectStatus(){
        for(int i=0;i<BSSIDArray.length;i++){
            if(i!=flag)
            BSSIDArray[i].setSelected(false);
        }
    }
    private class DocumentList implements DocumentListener{
       public void changedUpdate(DocumentEvent e)
       {
           
       }
       public void insertUpdate(DocumentEvent e)
       {
           try{
           wc.setManualBSSID(e.getDocument().getText(0, e.getDocument().getLength()));
           } catch (BadLocationException t)
           {
               t.printStackTrace();
           }
       }
       public void removeUpdate(DocumentEvent e)
       {
          
       }
    }
    private void generateFancyArtwork()
    {
        lowerTextfield.setText("");
        for(int i=0;i<(wc.readPassword(flag).length()+18);i++)
        {
            lowerTextfield.append("*");
        }
        lowerTextfield.append("\n");
        lowerTextfield.append("*** PASSWORD: "+wc.readPassword(flag));
        for(int i=0;i<(wc.readPassword(flag).length()-18);i++)
            lowerTextfield.append(" ");
        lowerTextfield.append(" ***\n");
        for(int i=0;i<(wc.readPassword(flag).length()+18);i++)
            lowerTextfield.append("*");
        lowerTextfield.append("\n");
    }
    private class ActionList implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(rescanAccesspoints.equals(e.getSource()))
            {
                if(intv5.isEnabled())
                {
                   upperTextfield.append("Rescanning networks using 5 seconds delay...\n");
                    if(allChannels.isSelected())
                        wc.rescan(5000);
                    else{
                        wc.rescan(5000, channelPick.getValue());
                    }
                }
                else if(intv10.isEnabled()){
                    upperTextfield.append("Rescanning networks using 10 seconds delay...\n");
                    if(allChannels.isSelected())
                        wc.rescan(10000);
                    else
                        wc.rescan(10000, channelPick.getValue());
                }
                else if(intv15.isEnabled()){
                     upperTextfield.append("Rescanning networks using 15 seconds delay...\n");
                    if(allChannels.isSelected())
                        wc.rescan(15000);
                    else
                        wc.rescan(15000, channelPick.getValue());
                }
                addBSSIDToVictimMenu();
            }
            if(arpRequestReplayAttach.equals(e.getSource()))
            {
                if(flag>=0){
                 upperTextfield.append("Initializing Arp Request Replay Attack\n");
                wc.typeOfAttack(3);
                }
                else
                     upperTextfield.append("Could not start attack. No target found!\n");
            }
            if(fragWithClients.equals(e.getSource()))
                upperTextfield.append("Feature has not yet been implemented.");
            if(cafeLatteAttack.equals(e.getSource()))
                upperTextfield.append("Feature has not yet been implemented.");
           
            if(crack.equals(e.getSource())){
                upperTextfield.append("Cracking is in process...\n");
                if(flag>=0){
                    wc.startCrack(flag);
                generateFancyArtwork();
                lowerTextfield.append("Generating report file to "+wc.getWritePath()+"...\n");
                lowerTextfield.append(wc.exportCSVData(flag)); 
                }
                else
                    upperTextfield.append("No target has been selected!\n");
            }
            if(startSniffer.equals(e.getSource()))
            {
                if(flag>=0){
                    upperTextfield.append("Sniffer has been started on ");
                    if(wc.isManualMacUsable()){
                        upperTextfield.append("manual target \n");
                        wc.secondPhase();
                    }
                    else{
                        upperTextfield.append(wc.getTarget(flag));
                        wc.secondPhase(flag);
                    }
                }
                else
                    upperTextfield.append("Could not start Sniffer. No target found!\n");
            }
            if(chopChopAttack.equals(e.getSource()))
            {
                if(flag>=0){
                upperTextfield.append("Initializing Chop Chop Attack\n");
                wc.typeOfAttack(4);
                }
                else
                    upperTextfield.append("Could not start attack. No target found!\n");
            }
            if(fragmentAttack.equals(e.getSource())){
                if(flag>=0){
                upperTextfield.append("Initializing Fragmentation Attack \n");
                wc.typeOfAttack(5);
                }
                else
                     upperTextfield.append("Could not start attack. No target found!\n");
            }
            if(initializer.equals(e.getSource()))
            {
                wc.firstPhase();
                upperTextfield.append("Monitor device "+wc.monitorDeviceName()+" has been enabled and is used"+
                "\n");
                addBSSIDToVictimMenu();
                upperTextfield.append("Waiting for further instructions.\n");
            }
        }
    }
    private class EmulatorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for (int i=0;i<BSSIDArray.length;i++){
                if(BSSIDArray[i].equals(e.getSource()))
                    flag = i;
            }
             upperTextfield.append("Target "+wc.getTarget(flag)+" has been selected \n");
                BSSIDSelectStatus();
                if(removeVirtualMons.equals(e.getSource())){
                    upperTextfield.append("All monitor devices have been removed \n");
                    wc.stopMonitor();
                }
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LowerPane = new javax.swing.JPanel();
        lowerPane = new javax.swing.JScrollPane();
        lowerTextfield = new javax.swing.JTextArea();
        UpperPane = new javax.swing.JPanel();
        upperPane = new javax.swing.JScrollPane();
        upperTextfield = new javax.swing.JTextArea();
        channelPick = new javax.swing.JSlider();
        manualBssid = new javax.swing.JTextField();
        channelLabel = new javax.swing.JLabel();
        manualBssidLabel = new javax.swing.JLabel();
        allChannels = new javax.swing.JCheckBox();
        removeVirtualMons = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu WEPifierMenu = new javax.swing.JMenu();
        initializer = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        interfaceHandler = new javax.swing.JMenu();
        wlanInterface = new javax.swing.JMenu();
        virtualInterface = new javax.swing.JMenu();
        mon0 = new javax.swing.JMenu();
        scanInterval = new javax.swing.JMenu();
        intv5 = new javax.swing.JRadioButtonMenuItem();
        intv10 = new javax.swing.JRadioButtonMenuItem();
        intv15 = new javax.swing.JRadioButtonMenuItem();
        rescanAccesspoints = new javax.swing.JMenuItem();
        sniffer = new javax.swing.JMenu();
        victims = new javax.swing.JMenu();
        startSniffer = new javax.swing.JMenuItem();
        attack = new javax.swing.JMenu();
        noClients = new javax.swing.JMenu();
        chopChopAttack = new javax.swing.JMenuItem();
        fragmentAttack = new javax.swing.JMenuItem();
        withClients = new javax.swing.JMenu();
        arpRequestReplayAttach = new javax.swing.JMenuItem();
        fragWithClients = new javax.swing.JMenuItem();
        withClientsAP = new javax.swing.JMenu();
        cafeLatteAttack = new javax.swing.JMenuItem();
        WEPCrack = new javax.swing.JMenu();
        crack = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wepifier.WEPifierApp.class).getContext().getResourceMap(proCrack.class);
        setTitle(resourceMap.getString("proCrack.title")); // NOI18N
        setBackground(resourceMap.getColor("proCrack.background")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(resourceMap.getColor("proCrack.foreground")); // NOI18N
        setName("proCrack"); // NOI18N
        setResizable(false);

        LowerPane.setBackground(resourceMap.getColor("LowerPane.background")); // NOI18N
        LowerPane.setForeground(resourceMap.getColor("LowerPane.foreground")); // NOI18N
        LowerPane.setName("LowerPane"); // NOI18N

        lowerPane.setName("lowerPane"); // NOI18N

        lowerTextfield.setColumns(20);
        lowerTextfield.setRows(5);
        lowerTextfield.setName("lowerTextfield"); // NOI18N
        lowerPane.setViewportView(lowerTextfield);

        javax.swing.GroupLayout LowerPaneLayout = new javax.swing.GroupLayout(LowerPane);
        LowerPane.setLayout(LowerPaneLayout);
        LowerPaneLayout.setHorizontalGroup(
            LowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
            .addGroup(LowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LowerPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lowerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        LowerPaneLayout.setVerticalGroup(
            LowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(LowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LowerPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lowerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        UpperPane.setBackground(resourceMap.getColor("UpperPane.background")); // NOI18N
        UpperPane.setForeground(resourceMap.getColor("UpperPane.foreground")); // NOI18N
        UpperPane.setName("UpperPane"); // NOI18N

        upperPane.setName("upperPane"); // NOI18N

        upperTextfield.setColumns(20);
        upperTextfield.setRows(5);
        upperTextfield.setName("upperTextfield"); // NOI18N
        upperPane.setViewportView(upperTextfield);

        javax.swing.GroupLayout UpperPaneLayout = new javax.swing.GroupLayout(UpperPane);
        UpperPane.setLayout(UpperPaneLayout);
        UpperPaneLayout.setHorizontalGroup(
            UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperPane, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                .addContainerGap())
        );
        UpperPaneLayout.setVerticalGroup(
            UpperPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upperPane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        channelPick.setMaximum(14);
        channelPick.setMinimum(1);
        channelPick.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        channelPick.setName("channelPick"); // NOI18N

        manualBssid.setText(resourceMap.getString("manualBssid.text")); // NOI18N
        manualBssid.setName("manualBssid"); // NOI18N

        channelLabel.setText(resourceMap.getString("channelLabel.text")); // NOI18N
        channelLabel.setName("channelLabel"); // NOI18N

        manualBssidLabel.setText(resourceMap.getString("manualBssidLabel.text")); // NOI18N
        manualBssidLabel.setName("manualBssidLabel"); // NOI18N

        allChannels.setText(resourceMap.getString("allChannels.text")); // NOI18N
        allChannels.setName("allChannels"); // NOI18N

        removeVirtualMons.setText(resourceMap.getString("removeVirtualMons.text")); // NOI18N
        removeVirtualMons.setName("removeVirtualMons"); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        WEPifierMenu.setText(resourceMap.getString("WEPifierMenu.text")); // NOI18N
        WEPifierMenu.setName("WEPifierMenu"); // NOI18N

        initializer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        initializer.setText(resourceMap.getString("initializer.text")); // NOI18N
        initializer.setName("initializer"); // NOI18N
        WEPifierMenu.add(initializer);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wepifier.WEPifierApp.class).getContext().getActionMap(proCrack.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        WEPifierMenu.add(exitMenuItem);

        menuBar.add(WEPifierMenu);

        interfaceHandler.setText(resourceMap.getString("interfaceHandler.text")); // NOI18N
        interfaceHandler.setName("interfaceHandler"); // NOI18N

        wlanInterface.setText(resourceMap.getString("wlanInterface.text")); // NOI18N
        wlanInterface.setEnabled(false);
        wlanInterface.setName("wlanInterface"); // NOI18N
        interfaceHandler.add(wlanInterface);

        virtualInterface.setText(resourceMap.getString("virtualInterface.text")); // NOI18N
        virtualInterface.setName("virtualInterface"); // NOI18N

        mon0.setText(resourceMap.getString("mon0.text")); // NOI18N
        mon0.setName("mon0"); // NOI18N

        scanInterval.setText(resourceMap.getString("scanInterval.text")); // NOI18N
        scanInterval.setName("scanInterval"); // NOI18N

        intv5.setSelected(true);
        intv5.setText(resourceMap.getString("intv5.text")); // NOI18N
        intv5.setName("intv5"); // NOI18N
        intv5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intv5ActionPerformed(evt);
            }
        });
        scanInterval.add(intv5);

        intv10.setSelected(true);
        intv10.setText(resourceMap.getString("intv10.text")); // NOI18N
        intv10.setName("intv10"); // NOI18N
        intv10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intv10ActionPerformed(evt);
            }
        });
        scanInterval.add(intv10);

        intv15.setSelected(true);
        intv15.setText(resourceMap.getString("intv15.text")); // NOI18N
        intv15.setName("intv15"); // NOI18N
        intv15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intv15ActionPerformed(evt);
            }
        });
        scanInterval.add(intv15);

        mon0.add(scanInterval);

        rescanAccesspoints.setText(resourceMap.getString("rescanAccesspoints.text")); // NOI18N
        rescanAccesspoints.setName("rescanAccesspoints"); // NOI18N
        mon0.add(rescanAccesspoints);

        virtualInterface.add(mon0);

        interfaceHandler.add(virtualInterface);

        menuBar.add(interfaceHandler);

        sniffer.setText(resourceMap.getString("sniffer.text")); // NOI18N
        sniffer.setName("sniffer"); // NOI18N

        victims.setText(resourceMap.getString("victims.text")); // NOI18N
        victims.setName("victims"); // NOI18N
        sniffer.add(victims);

        startSniffer.setText(resourceMap.getString("startSniffer.text")); // NOI18N
        startSniffer.setName("startSniffer"); // NOI18N
        sniffer.add(startSniffer);

        menuBar.add(sniffer);

        attack.setText(resourceMap.getString("attack.text")); // NOI18N
        attack.setName("attack"); // NOI18N

        noClients.setText(resourceMap.getString("noClients.text")); // NOI18N
        noClients.setName("noClients"); // NOI18N

        chopChopAttack.setText(resourceMap.getString("chopChopAttack.text")); // NOI18N
        chopChopAttack.setName("chopChopAttack"); // NOI18N
        noClients.add(chopChopAttack);

        fragmentAttack.setText(resourceMap.getString("fragmentAttack.text")); // NOI18N
        fragmentAttack.setName("fragmentAttack"); // NOI18N
        noClients.add(fragmentAttack);

        attack.add(noClients);

        withClients.setText(resourceMap.getString("withClients.text")); // NOI18N
        withClients.setName("withClients"); // NOI18N

        arpRequestReplayAttach.setText(resourceMap.getString("arpRequestReplayAttach.text")); // NOI18N
        arpRequestReplayAttach.setName("arpRequestReplayAttach"); // NOI18N
        withClients.add(arpRequestReplayAttach);

        fragWithClients.setText(resourceMap.getString("fragWithClients.text")); // NOI18N
        fragWithClients.setName("fragWithClients"); // NOI18N
        withClients.add(fragWithClients);

        attack.add(withClients);

        withClientsAP.setText(resourceMap.getString("withClientsAP.text")); // NOI18N
        withClientsAP.setName("withClientsAP"); // NOI18N

        cafeLatteAttack.setText(resourceMap.getString("cafeLatteAttack.text")); // NOI18N
        cafeLatteAttack.setName("cafeLatteAttack"); // NOI18N
        withClientsAP.add(cafeLatteAttack);

        attack.add(withClientsAP);

        menuBar.add(attack);

        WEPCrack.setText(resourceMap.getString("WEPCrack.text")); // NOI18N
        WEPCrack.setName("WEPCrack"); // NOI18N

        crack.setText(resourceMap.getString("crack.text")); // NOI18N
        crack.setName("crack"); // NOI18N
        WEPCrack.add(crack);

        menuBar.add(WEPCrack);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(channelPick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(channelLabel)))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(manualBssid, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(removeVirtualMons))
                    .addComponent(manualBssidLabel))
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(allChannels)
                .addContainerGap(684, Short.MAX_VALUE))
            .addComponent(UpperPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LowerPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(UpperPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LowerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(channelPick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(channelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(manualBssid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeVirtualMons))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manualBssidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allChannels)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void intv5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intv5ActionPerformed
            intervalVal = 5;
            intv5.setSelected(true);
            intv10.setSelected(false);
            intv15.setSelected(false);
    }//GEN-LAST:event_intv5ActionPerformed

    private void intv10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intv10ActionPerformed
            intervalVal = 10;
            intv5.setSelected(false);
            intv10.setSelected(true);
            intv15.setSelected(false);
    }//GEN-LAST:event_intv10ActionPerformed

    private void intv15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intv15ActionPerformed
            intervalVal = 15;
            intv5.setSelected(false);
            intv10.setSelected(false);
            intv15.setSelected(true);
    }//GEN-LAST:event_intv15ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proCrack().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LowerPane;
    private javax.swing.JPanel UpperPane;
    private javax.swing.JMenu WEPCrack;
    private javax.swing.JCheckBox allChannels;
    private javax.swing.JMenuItem arpRequestReplayAttach;
    private javax.swing.JMenu attack;
    private javax.swing.JMenuItem cafeLatteAttack;
    private javax.swing.JLabel channelLabel;
    private javax.swing.JSlider channelPick;
    private javax.swing.JMenuItem chopChopAttack;
    private javax.swing.JMenuItem crack;
    private javax.swing.JMenuItem fragWithClients;
    private javax.swing.JMenuItem fragmentAttack;
    private javax.swing.JMenuItem initializer;
    private javax.swing.JMenu interfaceHandler;
    private javax.swing.JRadioButtonMenuItem intv10;
    private javax.swing.JRadioButtonMenuItem intv15;
    private javax.swing.JRadioButtonMenuItem intv5;
    private javax.swing.JScrollPane lowerPane;
    private javax.swing.JTextArea lowerTextfield;
    private javax.swing.JTextField manualBssid;
    private javax.swing.JLabel manualBssidLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mon0;
    private javax.swing.JMenu noClients;
    private javax.swing.JButton removeVirtualMons;
    private javax.swing.JMenuItem rescanAccesspoints;
    private javax.swing.JMenu scanInterval;
    private javax.swing.JMenu sniffer;
    private javax.swing.JMenuItem startSniffer;
    private javax.swing.JScrollPane upperPane;
    private javax.swing.JTextArea upperTextfield;
    private javax.swing.JMenu victims;
    private javax.swing.JMenu virtualInterface;
    private javax.swing.JMenu withClients;
    private javax.swing.JMenu withClientsAP;
    private javax.swing.JMenu wlanInterface;
    // End of variables declaration//GEN-END:variables

}
