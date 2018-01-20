/*****************************************************************
this class is used for customer
@author Jie Tao
@version 2017.12
*****************************************************************/
import java.sql.ResultSet;

public class Customer {
	//customer basic information
	private int custID;
	private String name;
	private String address;
	private int gender;
	private int degree;
	private int age;
	private String phone;
	private double yearlysalary;
	private  String customerTable="Customer";
	//initial customer 
	public Customer(int custID, String name, String address, int gender, int degree, int age, String phone, double yearlysalary) {
		this.custID=custID;
		this.name=name;
		this.address=address;
		this.gender=gender;
		this.degree=degree;
		this.age=age;
		this.phone=phone;
		this.yearlysalary=yearlysalary;
	}
	
//	public Customer() {
//		
//	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	//add a new customer
	public void addCust() throws Exception {
		DataAccess.insertData(customerTable, 
					"CustID,name,address,gender,degree,age,phone,yearlysalary", 
					""+this.custID+",'"+this.name+"','"+this.address+"',"+this.gender+","+this.degree+","+this.age+",'"+this.phone+"',"+this.yearlysalary);
	}
	
	//delete a customer
	public void delCust(String custID) throws Exception {
		DataAccess.deleteData(customerTable, "CustID="+custID);
	}
	
	//update a customer
	public void upateCust() throws Exception {
		DataAccess.modifyData(customerTable, 
				"CustID,name,address,gender,degree,age,phone,yearlysalary", 
				""+this.custID+",'"+this.name+"','"+this.address+"',"+this.gender+","+this.degree+","+this.age+",'"+this.phone+"',"+this.yearlysalary, 
				"CustID="+this.custID);
	}
	
	// get a customer list
	public ResultSet searchCust(String whereSql) {
		ResultSet rs=null;
		try {
			rs=DataAccess.selectData("Customer", "*",whereSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	// get customer id
	public int getCustID() {
		return this.custID;
	}
}
