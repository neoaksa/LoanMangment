/*****************************************************************
this is a abstract class for Loan
Different type loans heritage from this class
@author Jie Tao
@version 2017.12
*****************************************************************/

public abstract class Loan {
	 	
		 protected int loanID;
	     protected double monthlyPayment; 
	     protected double totalPayment;
	     protected int customerID;
	     protected double interestRate;
	     protected int length;
	     protected double principle;
	     protected double monthlyrate;
	     protected int type;
	    	
	    //inital abstract class
	    public Loan(int custID,int loanID, double rate, int years, double amount) {
	    	this.loanID=loanID;
	        this.customerID = custID;
	        this.interestRate = rate;
	        this.length = years;
	        this.principle = amount;
	        this.monthlyrate = rate / 12;
	    }
	    
	    //override
	    public Loan(int custID,int loanID, double rate, int years, double amount,double monthlyPayment,double totalPayment) {
	    	this.loanID=loanID;
	        this.customerID = custID;
	        this.interestRate = rate;
	        this.length = years;
	        this.principle = amount;
	        this.monthlyrate = rate / 12;
	        this.monthlyPayment=monthlyPayment;
	        this.totalPayment=totalPayment;
	    }
	    // caculate total payment and return
	    public double process() {
	        this.calcMonthPayment();
	        this.makeSummary();
	        return this.totalPayment;
	    }
	    
	    // caculate monthly payment
	     abstract public void calcMonthPayment();
	        

	    //cacualte total payment return value
	    public void makeSummary() {
	    		this.totalPayment = this.monthlyPayment * this.length * 12;
	    }
	    
	    public String toString() {
	        return "Loan";
	    }
	    
	    public int getLoanID(){
	    	return this.loanID;
	    }
	    
	    //open fields as properties
	    public double getmonthlyPayment(){
	    	return this.monthlyPayment;
	    }
	    public double gettotalPayment(){
	    	return this.totalPayment;
	    }
	    public int getcustomerID(){
	    	return this.customerID;
	    }
	    public double getinterestRate(){
	    	return this.interestRate;
	    }
	    public int getlength(){
	    	return this.length;
	    }
	    public double getprinciple(){
	    	return this.principle;
	    }
	    public double getmonthlyrate(){
	    	return this.monthlyrate;
	    }
	    public int gettype() {
	    	return this.type;
	    }
	    
	    
	    
//	    // find the same name
//	    public isSameName(self, name):
//	        if self._name == name:
//	            return True
//	        else:
//	            return False

}