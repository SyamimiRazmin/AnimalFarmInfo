
package animalfarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @SyamimiRazmin
 */
public class AnimalFarm {
    
    ArrayList<CattlesInfo> CattlesInfolist = new ArrayList<CattlesInfo>();
    String header[] = new String[]{"Cattle ID", "Weight", "Breed Type", "Date Registered"};
    DefaultTableModel dtm = new DefaultTableModel(header, 1);

    AnimalFarm(){
    
        JFrame frame = new JFrame("Cattles Farm");
        frame.setSize(400, 400);
        
        JLabel cattleID = new JLabel("Cattle ID:");
        cattleID.setBounds(10, 10, 80, 20);
        frame.add(cattleID);
        
        JLabel weight = new JLabel("Weight:");
        weight.setBounds(10, 30, 80, 20);
        frame.add(weight);
        
        JLabel breedType = new JLabel("Breed Type:");
        breedType.setBounds(10, 50, 100, 20);
        frame.add(breedType);
        
        JLabel dateRegistered = new JLabel("Date Registered:");
        dateRegistered.setBounds(10, 70, 100, 20);
        frame.add(dateRegistered);
        
        JTextField jtfcattleID = new JTextField();
        jtfcattleID.setBounds(90, 10, 250, 20);
        frame.add(jtfcattleID);
        
        JTextField jtfweight= new JTextField();
        jtfweight.setBounds(90, 30, 250, 20);
        frame.add(jtfweight);
        
        JTextField jtfbreedType = new JTextField();
        jtfbreedType.setBounds(110, 50, 230, 20);
        frame.add(jtfbreedType);
        
        JTextField jtfdateRegistered = new JTextField();
        jtfdateRegistered.setBounds(110, 70, 230, 20);
        frame.add(jtfdateRegistered);
        
        
        
        JTable jtable = new JTable();
        jtable.setBounds(20, 140, 350, 180);
        frame.add(jtable);
        jtable.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(jtable);
        scrollPane.setBounds(20, 140, 350, 200);
        frame.add(scrollPane);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(75);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(75);
        jtable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtable.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        JButton jbuttoninsert = new JButton("INSERT");
        jbuttoninsert.setBounds(80,100, 100, 20);
        frame.add(jbuttoninsert);
        
        jbuttoninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cattleID = jtfcattleID.getText().toUpperCase();
                String weight = jtfweight.getText();
                String breedType = jtfbreedType.getText();
                String dateRegistered = jtfdateRegistered.getText();
                
                if (cattleID.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "ID should contain more than 3 component ");
                    return;
                }
              
                CattlesInfo cattle = new CattlesInfo(cattleID, weight, breedType, dateRegistered);
                CattlesInfolist.add(cattle);//create object list array
                writeData();
            }
        });
        
        JButton jbuttondelete = new JButton("DELETE");
        jbuttondelete.setBounds(200, 100, 100, 20);
        frame.add(jbuttondelete);
        
        jbuttondelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cattleID = JOptionPane.showInputDialog("Cattle ID number to delete?");
                if (cattleID != null) {
                    System.out.println("not null");
                    for (int i = 0; i < CattlesInfolist.size(); i++) {
                        if (CattlesInfolist.get(i).getcattleID().equalsIgnoreCase(cattleID)) {
                            CattlesInfolist.remove(i);
                        }
                    }
                   writeData(); 
                }
            }
        });
        
        
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int row = jtable.getSelectedRow();
                jtfcattleID.setText(dtm.getValueAt(row, 0).toString());
                jtfweight.setText(dtm.getValueAt(row, 1).toString());
                jtfbreedType.setText(dtm.getValueAt(row, 2).toString());
                jtfdateRegistered.setText(dtm.getValueAt(row, 3).toString());
            }
        });
        
        
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        AnimalFarm animal = new AnimalFarm();
    }    
    
    private void writeData() { //write data to file "data.txt"
        try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < CattlesInfolist.size(); i++) {
                sb.append(CattlesInfolist.get(i).getcattleID() + "," + CattlesInfolist.get(i).getweight() + "," + CattlesInfolist.get(i).getbreedType() 
                        + CattlesInfolist.get(i).getdateRegister() + "-");
            }
            f.write(sb.toString());
            f.close();
        } catch (IOException e) {
            return;
        }
        dtm.setRowCount(0); //update table content
        for (int i = 0; i < CattlesInfolist.size(); i++) {//populate table using object list
            Object[] objs = {CattlesInfolist.get(i).getcattleID(), CattlesInfolist.get(i).getweight(), CattlesInfolist.get(i).getbreedType(),
            CattlesInfolist.get(i).getdateRegister()};
            dtm.addRow(objs);
        }
    }
   
    
    
}

