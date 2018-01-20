/*****************************************************************
this class is used for operating database
all data management layer class should use this class to insert
	or update data
@author Jie Tao
@version 2017.12
*****************************************************************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public  class DataAccess {
	
	//create a connection
	public static Connection getConnection(){
		try
	    {
			//this path can be put into config.xml file
			//System.out.println(FileTest.);
	        //Connection conn=DriverManager.getConnection("jdbc:ucanaccess://D:/DBInfo.accdb");
			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://./DBInfo.accdb");
	        return conn;
	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	        return null;
	    }
	  }
	
	//retrieve the dataset
	public static ResultSet selectData(String tablename, String fieldString, String whereSql) throws Exception {
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;

		try
		{

		    conn = getConnection();
		    stmt = conn.createStatement();
			//return a dataset
		    rs = stmt.executeQuery("select "+fieldString+ " from " + tablename +" where "+whereSql);
		    
		}
		 catch(Exception err)
	    {
	        System.out.println(err);
	    }
		finally
		{		
			try {
                if(null != conn) {
 
                    // cleanup resources, once after processing
                    //rs.close();
                    stmt.close();
 
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
		}
		return rs;
	}
	
	//insert data
	public static void insertData(String tableName,String fieldString,String valueString) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    String cmd=null;
	    
		try
		{
		    conn = getConnection();
		    stmt = conn.createStatement();
	    	cmd=sqlCmd( tableName, fieldString, valueString,  null,1); //insert
	    	stmt.executeUpdate(cmd);
		}
		 catch(Exception err)
	    {
	        System.out.println(err);
	    }
		finally
		{		
			try {
                if(null != conn) {
 
                    // cleanup resources, once after processing
                    stmt.close();
 
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
		}
	}
	
	
	//insert or update data 
	public static void modifyData(String tableName,String fieldString,String valueString, String whereSql) throws Exception{
		Connection conn = null;
	    Statement stmt = null;
	    String cmd=null;
	    
		try
		{
		    conn = getConnection();
		    stmt = conn.createStatement();
		    //udpate first, if return 0, intert into dbase
		    cmd=sqlCmd( tableName, fieldString, valueString,  whereSql,2); //update
		    if(stmt.executeUpdate(cmd)==0) {
		    	cmd=sqlCmd( tableName, fieldString, valueString,  null,1); //insert
		    	stmt.executeUpdate(cmd);
		    }
		}
		 catch(Exception err)
	    {
	        System.out.println(err);
	    }
		finally
		{		
			try {
                if(null != conn) {
 
                    // cleanup resources, once after processing
                    stmt.close();
 
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
		}
	}
	
	//retrieve the dataset
	public static void deleteData(String tablename, String whereSql) throws Exception {
		Connection conn = null;
	    Statement stmt = null;

		try
		{

		    conn = getConnection();
		    stmt = conn.createStatement();
			//delete data 
		    stmt.execute("delete from "+ tablename +" where " + whereSql);
		    
		}
		 catch(Exception err)
	    {
	        System.out.println(err);
	    }
		finally
		{		
			try {
                if(null != conn) {
 
                    // cleanup resources, once after processing
                    stmt.close();
 
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
		}
	}
	
	//combine the sqlString
	//@tableName executed table name
	//@fieldSql= "field1, field2....fieldN"
	//@valueString="value1, value2.....valueN"
	//@whereSql="ceterial1=c1 and ceterial2=c2....ceterial3=c3"
	//@sqlType: 1=insert 2=update
	private static String sqlCmd(String tableName,String fieldString,String valueString, String whereSql, int sqlType) {
		String cmdSql="";
		//insert sql
		if(sqlType==1) {
			cmdSql="insert into " + tableName + "(" + fieldString + ") values (" + valueString + ") ";
		}
		//udpate sql
		else if(sqlType==2) {
			String[] arrayFileds, arrayValues;
			arrayFileds=fieldString.split(",");
			arrayValues=valueString.split(",");
			for(int i=0;i<arrayFileds.length;i++ ) {
				cmdSql=cmdSql+arrayFileds[i]+"="+arrayValues[i]+",";
				}
			cmdSql="update "+tableName + " set "  + cmdSql.substring(0, cmdSql.length()-1) + " where " + whereSql;
		}
			return cmdSql;
	}
}
