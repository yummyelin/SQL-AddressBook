import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class AddressBook{
	int w = 900, h = 600;
	int MyDBRowCount = 0;
	String[][] MyDBdata = new String[2000][15];//data in the database
	String[] MyDatabuffer = new String[15];
	Table_Model MyModel = new Table_Model(20);//(self-defined class)model of table
	JTable MyTable = new JTable(MyModel);
	JTextField[] MyTextField = new JTextField[15];
	
	public AddressBook(){
		JFrame MyFrame = new JFrame();				
		JPanel p1 = new JPanel();//main panel
		JPanel p2 = new JPanel(new GridLayout(2,15));//panel of label and textfield
		JPanel p3 = new JPanel();//panel of control button
		JButton btnConn = new JButton("Connect to SQL");
		JButton btnInsert = new JButton("Insert");
		JButton btnUpdate = new JButton("Update");
		JButton btnDelete = new JButton("Delete");
		JLabel[] MyLabel = new JLabel[15];
		
		
		MyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//__@@@@@@@@@@@@@@
		JScrollPane s_pan = new JScrollPane(MyTable);
        MyFrame.getContentPane().add(s_pan, BorderLayout.NORTH);//____@@@@@@@@@@@@
        
//        MyLabel = new JLabel("用户名：");
//        MyLabel.setBounds(60, 90, 70, 30);
//        MyLabel.setFont(new Font("汉真广标", Font.BOLD, 16));
//        MyTextField = new JTextField();
//        MyTextField.setBounds(140, 90, 120, 30);
//        MyTextField.setFont(new Font("汉真广标", Font.BOLD, 16));// 设置文本框内容的字体样式
//        MyTextField.setHorizontalAlignment(JTextField.CENTER);// 设置文本框内容的水平对齐方式
        

        MyLabel[0] = new JLabel("First Name"); 
//      MyLabel.setBounds(60, 90, 70, 30);//__@@@@@@@@@@@@@
        MyLabel[1] = new JLabel("Last Name");
        MyLabel[2] = new JLabel("Gender");
        MyLabel[3] = new JLabel("Address1");
        MyLabel[4] = new JLabel("Address2");
        MyLabel[5] = new JLabel("Address3");
        MyLabel[6] = new JLabel("City");
        MyLabel[7] = new JLabel("State");
        MyLabel[8] = new JLabel("Zip Code");
        MyLabel[9] = new JLabel("Country");
        MyLabel[10] = new JLabel("Phone");
        MyLabel[11] = new JLabel("Phone(home)");
        MyLabel[12] = new JLabel("Phone(other)");
        MyLabel[13] = new JLabel("Email");
        MyLabel[14] = new JLabel("Label");
        for(int i=0;i<15;i++){
        	p2.add(MyLabel[i]);
        }
        for(int i=0;i<15;i++){
        	MyTextField[i] = new JTextField();
        	MyTextField[i].setHorizontalAlignment(JTextField.CENTER);
        	p2.add(MyTextField[i]);
        }        
        
		p3.add(btnInsert);
		p3.add(btnUpdate);
		p3.add(btnDelete);

		BoxLayout layout = new BoxLayout(p1, BoxLayout.Y_AXIS);
		p1.setLayout(layout);
		p1.add(btnConn);
		p1.add(p2);
		p1.add(p3);
		btnConn.setAlignmentX(Component.LEFT_ALIGNMENT);
		p2.setAlignmentX(Component.LEFT_ALIGNMENT);
		p3.setAlignmentX(Component.LEFT_ALIGNMENT);
		MyFrame.add(p1);
		
        MyFrame.setSize(w, h);
		MyFrame.setLocationRelativeTo(null);
		MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyFrame.setResizable(false);
		MyFrame.setVisible(true);
        
		btnConn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				paintTable();
			}
		});
		btnInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeTable();				
		        for(int i=0;i<15;i++){
		        	MyDatabuffer[i] = MyTextField[i].getText();
		        }
				new InsertDB(MyDatabuffer);
				paintTable();
			}
		});
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeTable();	
				for(int i=0;i<15;i++){
		        	MyDatabuffer[i] = MyTextField[i].getText();
		        }
				new UpdateDB(MyDatabuffer);
				paintTable();
			}
		});
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeTable();
				for(int i=0;i<15;i++){
		        	MyDatabuffer[i] = MyTextField[i].getText();
		        }
				new DeleteDB(MyDatabuffer);
				paintTable();
			}
		});
	}
	
	public void paintTable(){
		QueryDB MyDB = new QueryDB();//database
		MyDBdata = MyDB.getDBdata();
		MyDBRowCount = MyDB.getDBRowCount();
		for(int i=0;i<MyDBRowCount;i++){
			MyModel.addRow(MyDBdata[i][0],MyDBdata[i][1],MyDBdata[i][2],MyDBdata[i][3],MyDBdata[i][4],MyDBdata[i][5],MyDBdata[i][6],MyDBdata[i][7],MyDBdata[i][8],MyDBdata[i][9],MyDBdata[i][10],MyDBdata[i][11],MyDBdata[i][12],MyDBdata[i][13],MyDBdata[i][14]);
		}
        MyTable.repaint();
        MyTable.updateUI();
	}
	
	public void removeTable(){
		for(int i=0;i<MyDBRowCount;i++){
			MyModel.removeRow(0);
		}
		MyTable.repaint();
        MyTable.updateUI();
	}
	
//	static class MyTask extends java.util.TimerTask{ 
//        public void run() { 
//            System.out.println("________");
//            MyTable.repaint();
//            MyTable.updateUI();
//        }
//    }
	public static void main(String[] args){
		new AddressBook();
	}	
}

class Table_Model extends AbstractTableModel {
	private static final long serialVersionUID = -3094977414157589758L;
	private Vector content = null;
    private String[] title_name = { "First Name", "Last Name", "Gender","Address Line 1","Address Line 2","Address Line 3","City","State","Zip Code","Country","Phone","Phone(home)","Phone(other)","Email","Label" };

    public Table_Model() {
        content = new Vector();
    }

    public Table_Model(int count) {
        content = new Vector(count);
    }
	
    public void addRow(int row) {//加入一空行 
        Vector v = new Vector(15);
        v.add(0, null);
        v.add(1, null);
        v.add(2, null);
        v.add(3, null);
        v.add(4, null);
        v.add(5, null);
        v.add(6, null);
        v.add(7, null);
        v.add(8, null);
        v.add(9, null);
        v.add(10, null);
        v.add(11, null);
        v.add(12, null);
        v.add(13, null);
        v.add(14, null);
        content.add(row, v);
    }
    
    public void addRow(String first_name, String last_name, String gender, String address_line1,String address_line2,String address_line3, String city, String state, String zip_code, String country, String phone,String phone_home,String phone_other,String email,String label){
        Vector v = new Vector(15);
        v.add(0, first_name);
        v.add(1, last_name);
        v.add(2, gender);
        v.add(3, address_line1);
        v.add(4, address_line2);
        v.add(5, address_line3);
        v.add(6, city);
        v.add(7, state);
        v.add(8, zip_code);
        v.add(9, country);
        v.add(10, phone);
        v.add(11, phone_home);
        v.add(12, phone_other);
        v.add(13, email);
        v.add(14, label);
        content.add(v);
    }
    
    public void removeRow(int row) {
        content.remove(row);
    }
    
    public String getColumnName(int col) {
        return title_name[col];
    }
    
    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }
    
    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }
    
    public int getColumnCount() {
        return title_name.length;
    }

    public int getRowCount() {
        return content.size();
    }
    
//    public Class getColumnClass(int col) {
//        return getValueAt(0, col).getClass();
//    }
}
