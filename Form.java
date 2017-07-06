import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TabableView;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField texeName;
	private JTextField textFamily;
	private JTable table;
	private JLabel lblShow;
	private JButton btnShow;
	private JTable table_1;
	private JTextField textID;
	private JLabel label_error;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	public Form() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 457, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		texeName = new JTextField();
		texeName.setBounds(94, 45, 86, 20);
		texeName.setColumns(10);
		
		textFamily = new JTextField();
		textFamily.setBounds(94, 76, 86, 20);
		textFamily.setColumns(10);
		
		JLabel lblId = new JLabel("name:");
		lblId.setBounds(38, 48, 46, 14);
		
		JLabel lblName = new JLabel("family:");
		lblName.setBounds(38, 79, 46, 14);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(221, 13, 89, 23);
		btnInsert.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent arg0) {
			String id=textID.getText();
			String name=texeName.getText();
			String family=textFamily.getText();
			String sql="INSERT INTO person (id,name,family) VALUES ('%s','%s','%s')";
			sql=String.format(sql, id,name,family);
			MyModel obj=new MyModel();
			obj.notQuery(sql);
			refresh();
			
//				try {
//					Class.forName("com.mysql.jdbc.Driver").newInstance();
//					String url="jdbc:mysql://localhost:3306/homework6?user=root";
//					Connection con=DriverManager.getConnection(url);
//					java.sql.Statement st=con.createStatement();
//					String query="INSERT INTO `homework6`.`person` (`name`, `family`) VALUES ('%s', '%s');";
//					query=String.format(query, texeName.getText(), textFamily.getText());
//				
//
//					st.execute(query);
//					st.close();
//					con.close();
//				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
		}
			
	});
		
		table = new JTable();
		table.setBounds(444, 95, 1, 1);
		contentPane.setLayout(null);
		contentPane.add(lblName);
		contentPane.add(textFamily);
		contentPane.add(table);
		contentPane.add(lblId);
		contentPane.add(texeName);
		contentPane.add(btnInsert);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "name", "family"
			}
		));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id=(String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				String name=(String) table_1.getValueAt(table_1.getSelectedRow(), 1);
				String family=(String) table_1.getValueAt(table_1.getSelectedRow(), 2);
				textID.setText(id);
				texeName.setText(name);
				textFamily.setText(family);
				
				
			}
		});
		
		table_1.setBounds(94, 136, 279, 240);
		contentPane.add(table_1);
		
		lblShow = new JLabel("show:");
		lblShow.setBounds(38, 180, 46, 14);
		contentPane.add(lblShow);
		
		btnShow = new JButton("show");
		btnShow.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				refresh();
				
			}
		});
		btnShow.setBounds(221, 44, 89, 23);
		contentPane.add(btnShow);
		
			
		
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_error.setText("");
				if(table_1.getSelectedRow()==-1){
					label_error.setText("Please Selected Row");
				}
				String id=textID.getText();
				String sql="Delete From person WHERE id='%s'";
				sql=String.format(sql, id);
				
				MyModel obj=new MyModel();
				obj.notQuery(sql);
				refresh();
				
				
			}
		});
		btnDelet.setBounds(221, 75, 89, 23);
		contentPane.add(btnDelet);
		
		textID = new JTextField();
		textID.setBounds(94, 14, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		JLabel lblId_1 = new JLabel("id:");
		lblId_1.setBounds(38, 23, 46, 14);
		contentPane.add(lblId_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_error.setText("");
				if(table_1.getSelectedRow()==-1){
					label_error.setText("Please Selected Row");
				}
				int id =Integer.parseInt(textID.getText());
				String name=texeName.getText();
				String family=textFamily.getText();
				String sql="UPDATE person set name='%s', family='%s' where id=%s ";
				sql=String.format(sql, name,family,id);
				MyModel obj=new MyModel();
				obj.notQuery(sql);
				
				refresh();
				
				
			}
		});
		btnUpdate.setBounds(221, 109, 89, 23);
		contentPane.add(btnUpdate);
		
		label_error = new JLabel("");
		label_error.setBounds(76, 111, 135, 14);
		contentPane.add(label_error);
	}

	protected void refresh() {
		String sql="SELECT * FROM person";
		MyModel obj=new MyModel();
		DefaultTableModel dftm=obj.query(sql);
		table_1.setModel(dftm);
		
	}
}
