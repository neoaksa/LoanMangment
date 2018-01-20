/*****************************************************************
this class is used for Loan Type
1 - SimpleLoan
2 - Amotized Loan
@author Jie Tao
@version 2017.12
*****************************************************************/
public enum LoanType {

    SimpleLoan(1),
    AmortizedLoan(2);

    private int type;

    private LoanType(int stringVal) {
    	type=stringVal;
    }
    public String toString(){
        return String.valueOf(type);
    }

    public static String getEnumByInt(int code){
        for(LoanType e : LoanType.values()){
            if(code == e.type) return e.name();
        }
        return null;
    }
}