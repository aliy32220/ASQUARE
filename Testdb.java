package asquare;

import java.sql.*;
import java.util.Properties;  
public class Testdb {

	private final String userName="root";
	private final String password="samsung.12345";
	private final String serverName="127.0.0.1";

	private final int portNumber=3306;

	private final String dbName="asquare";

	private final String tableName="sometable";
	private Statement stmt;
	
	public Connection getConnection() throws SQLException {
		
		Connection conn;
		Properties connectionProps=new Properties();
		connectionProps.put("user",this.userName);
		connectionProps.put("password",this.password);
		System.out.println("Trying to get connection!! ");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:"+this.portNumber+"/"+this.dbName,connectionProps);
		return conn;
		
	}
	
	public void executeUpdate(Connection conn,String command) throws SQLException{
		Statement stmt=null;
		try{
			stmt=conn.createStatement();
			stmt.executeUpdate(command);
			System.out.print("Value Updated");
		}
		finally{
			if(stmt!=null){
				stmt.close();
			}
		}
	}
	
	public void Makedb(Connection conn)
	{
		stmt=null;
		String sql="CREATE DATABASE STUDENTS";
		try{
			stmt.executeUpdate(sql);
		 }
		catch(SQLException a){
			a.printStackTrace();
		}
	}
	
	
}
