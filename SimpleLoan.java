/*****************************************************************
this class is used for simple Loan
inherits from abstract class Loan
@author Jie Tao
@version 2017.12
*****************************************************************/

public class SimpleLoan extends Loan {

	public SimpleLoan(int custID,int loanID,  double rate, int years, double amount) {
		super( custID, loanID,rate, years, amount);
		super.type=1;
		// TODO Auto-generated constructor stub
	}
    
	//overide
	public SimpleLoan(int custID,int loanID,  double rate, int years, double amount,double monthlyPayment,double totalPayment) {
		super( custID,loanID, rate, years, amount, monthlyPayment, totalPayment);
		super.type=1;
		// TODO Auto-generated constructor stub
	}
	
	// overide monthly payment calculation
	public void calcMonthPayment() {
		this.monthlyPayment =(this.principle * (this.monthlyrate * this.length * 12 + 1)) / (this.length * 12);
	}
	
	//Override to String
    public String toString() {
        return "Simple Loan";
    }
}
