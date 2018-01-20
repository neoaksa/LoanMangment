/*****************************************************************
this is a GUI class for Summary table
@author Jie Tao
@version 2017.12
*****************************************************************/
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class fSummary {

	protected Shell shlSummary;
	private Table atable;
	private static DecimalFormat df2 = new DecimalFormat(".##");
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void NewfSummary() {
		try {
			fSummary window = new fSummary();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSummary.open();
		shlSummary.layout();
		while (!shlSummary.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSummary = new Shell();
		shlSummary.setSize(491, 367);
		shlSummary.setText("Summary");
		
		atable = new Table(shlSummary, SWT.BORDER | SWT.FULL_SELECTION);
		atable.setBounds(10, 10, 441, 283);
		atable.setHeaderVisible(true);
		atable.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(122);
		tblclmnNewColumn_1.setText("Type");
		
		TableColumn tblclmnOfLoans = new TableColumn(atable, SWT.NONE);
		tblclmnOfLoans.setWidth(100);
		tblclmnOfLoans.setText("# of Loans");
		
		TableColumn tblclmnTotalPayment = new TableColumn(atable, SWT.NONE);
		tblclmnTotalPayment.setWidth(115);
		tblclmnTotalPayment.setText("Total Payment");
		
		//load data
		atable.removeAll();
		LoanManager arrayLoan=new LoanManager();
		try {
			//get all loan by customer
			ResultSet rs=arrayLoan.getSummary();
			//Loan is not found
			if(rs==null) {
				JOptionPane.showMessageDialog(null, "No loan found", "Information", 2);
			}
			else {
				//load data to table
				while(rs.next()) {
					TableItem aTT=new TableItem(atable, 0);
					aTT.setText(new String[] {rs.getString("name"),
							LoanType.getEnumByInt(rs.getInt("type")),
							String.valueOf(rs.getInt("numberofLoan")),
							df2.format(rs.getDouble("totalamount"))
					});
				}
				ResultSet rs2=arrayLoan.getSummary2();
				//load total
				if(rs2.next()) {
					TableItem aTT=new TableItem(atable, 0);
					aTT.setText(new String[] {"",
							"Total:",
							String.valueOf(rs2.getInt("loannum")),
							df2.format(rs2.getDouble("amount"))
					});
				}
			}
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
