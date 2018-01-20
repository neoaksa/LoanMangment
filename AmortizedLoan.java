/*****************************************************************
this class is used for amortized loan
inherits from abstract class Loan
@author Jie Tao
@version 2017.12
*****************************************************************/

public class AmortizedLoan extends Loan {

	public AmortizedLoan(int custID,int loanID,  double rate, int years, double amount) {
		super( custID,loanID, rate, years, amount);
		super.type=2;
		// TODO Auto-generated constructor stub
	}
	
	//overide
	public AmortizedLoan(int custID,int loanID,  double rate, int years, double amount,double monthlyPayment,double totalPayment) {
		super( custID,loanID, rate, years, amount, monthlyPayment, totalPayment);
		super.type=2;
		// TODO Auto-generated constructor stub
	}
	
	//implement monthlypayment
	public void calcMonthPayment() {
		double n;
		n = Math.pow((1 + this.monthlyrate) , (this.length * 12));
		this.monthlyPayment = (this.principle * this.monthlyrate * n) / (n - 1);
	}
	
	//Override to String
    public String toString() {
        return "Amortized Loan";
    }
}
