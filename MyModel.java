import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 * <h1>MyModel</h1>
 * 
 * <p>
 * this is my first java doc
 * </p>
 * 
 * @author Alireza
 * @param id
 *            myid
 * @since 1396
 */
public class MyModel  {
	private String URL="jdbc:mysql://localhost:3306/homework6?user=root";
	public DefaultTableModel query(String sql){
		try{
			 
			Class.forName("com.mysql.jdbc.Driver").getInterfaces();
			Connection con =DriverManager.getConnection(URL);
			Statement st=con.createStatement();
			st.executeQuery(sql);
			ResultSet rs=st.getResultSet();
			ResultSetMetaData  rsmd =rs.getMetaData();
			int col_num=rsmd.getColumnCount();
			DefaultTableModel dftm=new DefaultTableModel();
			
			for (int i = 0; i < col_num; i++) {
				dftm.addColumn(rsmd.getColumnName(i+1));
				
			}
			while(rs.next()){
				Object[] data=new Object[col_num];
				for (int i = 0; i < col_num; i++) {
					data[i]=rs.getString(i+1);
					
				}
				dftm.addRow(data);
			}
			rs.close();
			st.close();
			con.close();
			return dftm;
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
		
		
	}
	public void notQuery(String sql){
		try{
		Class.forName("com.mysql.jdbc.Driver").getInterfaces();
		Connection con =DriverManager.getConnection(URL);
		Statement st=con.createStatement();
		st.execute(sql);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	

}


