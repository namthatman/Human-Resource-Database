package DBProject1;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DepartmentInfo extends JFrame {

	private JPanel contentPane;
	//private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentInfo frame = new DepartmentInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;
	private JTable table_1;
	private JTable table_2;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnSelect_1;
	private JButton btnInsertData;
	private JLabel lblD_ID;
	private JLabel lbl_D_name;
	private JTextField textField_D_ID;
	private JTextField textField_D_name;
	private JButton btnUpdateData;
	private JButton btnDeleteData;
	private JTable table_3;
	private JScrollPane scrollPane_1;
	String data2[][] = new String[100][100];
	String data1[][] = new String[100][100];
	String column[]={"Region_ID","Regioin_name"}; 
	String column1[]={"Department_ID","Department_name","Manager_ID", "Location_ID"};  
	JComboBox comboBox;
	JComboBox comboBox_1 = new JComboBox();
	String ListSize[] = new String[100];
	JScrollPane spane;
	private JTextField textField_Search;
	private JComboBox comboBox_2;
	private JTextField textField_M_ID;
	private JTextField textField_L_ID;
	private JLabel lblManagerid;
	private JLabel lblLocationid;
	
	public void refeshTable(){
		try{					
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");
			
			String query = "select * from departments";
			PreparedStatement pst = con.prepareStatement("select * from departments");				
			ResultSet rs = pst.executeQuery();
		      
		    int i=0,j=0;
		    while(rs.next()){
					System.out.println((rs.getInt(1)+"\t\t"+rs.getString(2)));	
					data2[i][j] = rs.getString(1);
					j++;
					data2[i][j] = rs.getString(2);
					j++;
					data2[i][j] = rs.getString(3);
					j++;
					data2[i][j] = rs.getString(4);
					j=0;
					i++;
		    }	    
		    con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}	    
	}
	
	public void fillComboBox(){
		try{					
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");
			
			String query = "select * from regions";
			PreparedStatement pst = con.prepareStatement(query);				
			ResultSet rs = pst.executeQuery();
		      
		    while(rs.next()){
		    	comboBox.addItem(rs.getString(2));
		    }	    
		    con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}	    
	}
	
	
	public void loadList(){
		try{					
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");
			
			String query = "select * from regions";
			PreparedStatement pst = con.prepareStatement(query);				
			ResultSet rs = pst.executeQuery();
		      
			DefaultListModel DLM = new DefaultListModel();
		    while(rs.next()){
		    	DLM.addElement(rs.getString("region_name"));
		    }
		    
		    pst.close();
		    rs.close();
		    con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}	    
	}
	
	
	public DepartmentInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenu mnNew = new JMenu("New");
		mnNew.setIcon(new ImageIcon("C:\\Users\\DuongHoanVu\\Desktop\\ok2.png"));
		mnNewMenu.add(mnNew);
		
		JMenuItem mntmJavaProject = new JMenuItem("Java Project");
		mnNew.add(mntmJavaProject);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnNewMenu.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnSource = new JMenu("Source");
		menuBar.add(mnSource);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		
		table_1 = new JTable();
		table_1.setBounds(216, 10, 0, 0);
		contentPane.add(table_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(221, 10, 1, 1);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 192, 147);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 192, 147);
		panel_1.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
	// LOAD ALL
		btnSelect_1 = new JButton("Table");
		btnSelect_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");
					
					String query = "select * from departments";
					PreparedStatement pst = con.prepareStatement(query);				
					ResultSet rs = pst.executeQuery();
				      
				    int i=0,j=0;
				    while(rs.next()){
							System.out.println((rs.getInt(1)+"\t\t"+rs.getString(2)));	
							data2[i][j] = rs.getString(1);
							j++;
							data2[i][j] = rs.getString(2);
							j++;
							data2[i][j] = rs.getString(3);
							j++;
							data2[i][j] = rs.getString(4);
							j=0;
							i++;
				    }	    
				    
				    table_3 = new JTable(data2, column1);
					table_3.setShowGrid(false);
					scrollPane_1.setViewportView(table_3);
				    con.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnSelect_1.setBounds(10, 199, 101, 32);
		contentPane.add(btnSelect_1);
		
	// INSERT
		btnInsertData = new JButton("Insert Data");
		btnInsertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");

					String query = "insert into departments(Department_ID, Department_name, Manager_ID, Location_ID) values(?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(query);				
					pst.setString(1, textField_D_ID.getText());
					pst.setString(2, textField_D_name.getText());
					pst.setString(3, textField_M_ID.getText());
					pst.setString(4, textField_L_ID.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data is Saved");
					refeshTable();
					pst.close();
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			
			}
		});
		btnInsertData.setBounds(10, 41, 101, 32);
		contentPane.add(btnInsertData);
		
		lblD_ID = new JLabel("Department_ID");
		lblD_ID.setBounds(147, 21, 97, 28);
		contentPane.add(lblD_ID);
		
		lbl_D_name = new JLabel("Department_name");
		lbl_D_name.setBounds(147, 84, 101, 23);
		contentPane.add(lbl_D_name);
		
		textField_D_ID = new JTextField();
		textField_D_ID.setBounds(147, 53, 97, 20);
		contentPane.add(textField_D_ID);
		textField_D_ID.setColumns(10);
		
		textField_D_name = new JTextField();
		textField_D_name.setBounds(147, 118, 97, 20);
		contentPane.add(textField_D_name);
		textField_D_name.setColumns(10);
		
		
	// UPDATE
		btnUpdateData = new JButton("Update Data");
		btnUpdateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");

					String query = "update departments set Department_ID = '"+textField_D_ID.getText()+"', Department_name = '"+textField_D_name.getText()+"' where Department_ID = '"+textField_D_ID.getText()+"' ";
					PreparedStatement pst = con.prepareStatement(query);				
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data is Updated");
					refeshTable();
					pst.close();
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnUpdateData.setBounds(10, 143, 101, 32);
		contentPane.add(btnUpdateData);
		
	// DELETE
		btnDeleteData = new JButton("Delete Data");
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to Delete");
				if (action == 0){
					try{	
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");
	
						String query = "delete from departments where Department_ID = '"+textField_D_ID.getText()+"' ";
						PreparedStatement pst = con.prepareStatement(query);				
						
						pst.execute();
						JOptionPane.showMessageDialog(null, "Data is Deleted");
						refeshTable();
						pst.close();
						
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteData.setBounds(10, 88, 101, 33);
		contentPane.add(btnDeleteData);
		
		comboBox = new JComboBox();
		comboBox.setBounds(143, 343, 101, 27);
		contentPane.add(comboBox);
		
		table_3 = new JTable(data2, column1);
		table_3.setShowGrid(false);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(304, 85, 278, 191);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(table_3);
		
		
		textField_Search = new JTextField();
		textField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{	
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu");

					String selection = (String)comboBox_2.getSelectedItem();
					
					String query = "select * from departments where "+selection+"=?";
					PreparedStatement pst = con.prepareStatement(query);				
					pst.setString(1, textField_Search.getText());
					ResultSet rs = pst.executeQuery();
					int i=0,j=0;
					while(rs.next()){
						System.out.println((rs.getInt(1)+"\t\t"+rs.getString(2)));	
						data1[i][j] = rs.getString(1);
						j++;
						data1[i][j] = rs.getString(2);
						j++;
						data1[i][j] = rs.getString(3);
						j++;
						data1[i][j] = rs.getString(4);
						j=0;
						i++;
					}	
					
					table_3 = new JTable(data1, column1);
					table_3.setShowGrid(false);
					scrollPane_1.setViewportView(table_3);
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		textField_Search.setBounds(304, 44, 79, 20);
		contentPane.add(textField_Search);
		textField_Search.setColumns(10);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Department_ID", "Department_name"}));
		comboBox_2.setBounds(417, 43, 113, 23);
		contentPane.add(comboBox_2);
		
		textField_M_ID = new JTextField();
		textField_M_ID.setBounds(147, 178, 97, 20);
		contentPane.add(textField_M_ID);
		textField_M_ID.setColumns(10);
		
		textField_L_ID = new JTextField();
		textField_L_ID.setBounds(147, 234, 94, 20);
		contentPane.add(textField_L_ID);
		textField_L_ID.setColumns(10);
		
		lblManagerid = new JLabel("Manager_ID");
		lblManagerid.setBounds(147, 149, 92, 14);
		contentPane.add(lblManagerid);
		
		lblLocationid = new JLabel("Location_ID");
		lblLocationid.setBounds(147, 209, 76, 14);
		contentPane.add(lblLocationid);
		
	/////////////////////////////////////////////////////// 			COMMIT
		JButton btnCommit = new JButton("COMMIT");
		btnCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:duonghoanvu","C##VU","duonghoanvu"); 
				   
				    
				    con.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}	    
			}
		});
		btnCommit.setBounds(10, 253, 101, 32);
		contentPane.add(btnCommit);
		
		JButton btnRollback = new JButton("ROLLBACK");
		btnRollback.setBounds(10, 303, 89, 23);
		contentPane.add(btnRollback);
		
		fillComboBox();
		loadList();
	}
}
