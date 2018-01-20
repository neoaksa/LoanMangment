/*****************************************************************
this is class for operating Loan in an arraylist
@author Jie Tao
@version 2017.12
*****************************************************************/
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;


public class LoanManager {
	//creat a list for saving Loan for each customer
	private ArrayList<Loan> loans;
	private String loanTable="Loan";
	
	public LoanManager() {
		loans=new ArrayList<Loan>();
	}
	
	//add a Loan into ArrayList for temp store
	public void insertLoans(Loan aLoan) {
		loans.add(aLoan);
	}
	
	//remove a loan
	public void removeLoan(int index) {
		loans.remove(index);
	}
	
	//save a loan to database
	public void addLoan(Loan aLoan) throws Exception {
		DataAccess.insertData(loanTable, 
				"custID,interesRate,principle,length,type,monthlyPayment,totalPayment", 
				aLoan.customerID+","+aLoan.interestRate+","+aLoan.principle+","+aLoan.length+","+aLoan.type+","+aLoan.monthlyPayment+","+aLoan.totalPayment);
	}
	
	//save whole loans  into database
	public void addLoanArray() throws Exception {
		if(loans.size()==0) {
			return;
		}
		else {
			for(Loan aLoan:loans) {
				addLoan(aLoan);
			}
		}
	}
	
//	//updateLoan
//	public void updateLoan(Loan aLoan) throws Exception {
//		DataAccess.modifyData(loanTable, 
//				"custID,interestRate,principle,length", 
//				aLoan.customerID+","+aLoan.interestRate+","+aLoan.principle+","+aLoan.length,
//				"custID="+aLoan.customerID+" and aLoan");
//	}
	
	//deleteLoan
	public void deleteLoanByCust(int custID) throws Exception {
		DataAccess.deleteData(loanTable, 
						"custID="+custID);
	}
	
	//getLoansSummary for each customer, then grant value to arrayList
	public void getAllLoanByCust(int custID) throws Exception {
		ResultSet rs=DataAccess.selectData(loanTable, 
								"*", 
								"custID="+custID);
		//no data retrieved
		if(!rs.next()) {
			return;
		}
		else{
			//Iterate data from resultset
			do{
				int loanID = rs.getInt("loanID");
			    float rate = rs.getFloat("interesRate");
			    int years = rs.getInt("length");
			    float amount = rs.getFloat("principle");
			    int type=rs.getInt("type");
			    float monthlyPayment = rs.getFloat("monthlyPayment");
			    float totalPayment = rs.getFloat("totalPayment");
			    //creat loan by type
			    Loan aLoan;
			    if(type==1) {
			    	 aLoan=new SimpleLoan(custID, loanID, rate, years, amount,monthlyPayment,totalPayment);
			    }
			    else{
			    	 aLoan=new AmortizedLoan(custID, loanID, rate, years, amount,monthlyPayment,totalPayment);
			    }
			    //add loan to loanList
			    loans.add((Loan)aLoan);
			}while(rs.next());
		}
	}
	
	//getLoansSummary for all
	public ResultSet getSummary() throws Exception {
		ResultSet rs=DataAccess.selectData("vGetAllLoans", "*", " 1=1"); 
		return rs;
	}
	
	//getLoansSummary for all(only loan number and total amount
	public ResultSet getSummary2() throws Exception {
		ResultSet rs=DataAccess.selectData("vGetSumLoans", "*", " 1=1"); 
		return rs;
	}
	
	//getLoansSummary  for a customer
	public ResultSet getSummary(int custID) throws Exception {
		ResultSet rs=DataAccess.selectData("vGetAllLoans", "*", " custID="+custID); //get allloansummery from view 
		return rs;
	}
	
	//sort LoanList
	public void sortLoan() {
		loans.sort(Comparator.comparing(Loan::gettotalPayment));
	}
	
	//get Loans
	public ArrayList<Loan> getLoans() {
		return loans;
	}

}
