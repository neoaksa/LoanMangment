/*****************************************************************
this is a GUI class for Loan operation
@author Jie Tao
@version 2017.12
*****************************************************************/
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class fLoan {

	protected Shell shlLoanManagment;
	private Text txtcustID;
	private Table atable;
	private Text txtPrincple;
	private Text txtyear;
	private LoanManager arrayLoan=new LoanManager();//for add and edit loans
	private Customer aCust=new Customer();//for search customer
//	private Loan tempLoan; //for edit purpose
	private static DecimalFormat df2 = new DecimalFormat(".##");
	private static DecimalFormat df3 = new DecimalFormat(" #,##0 %");
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void newfLoan() {
		try {
			fLoan window = new fLoan();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//refresh table
	private void refreshTable() {
		//refresh all table
		atable.removeAll();
		TableItem aTT;
		for(Loan aLoan1:arrayLoan.getLoans()) {
			aTT=new TableItem(atable, 0);
			
			aTT.setText(new String[] {String.valueOf(aLoan1.getLoanID()),
									String.valueOf(df3.format(aLoan1.getinterestRate())),
									String.valueOf(aLoan1.getprinciple()),
									String.valueOf(aLoan1.getlength()),
									//String.valueOf(aLoan.gettype()),
									LoanType.getEnumByInt(aLoan1.gettype()),
									String.valueOf(df2.format(aLoan1.getmonthlyPayment())),
									String.valueOf(df2.format(aLoan1.gettotalPayment())),
			});
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLoanManagment.open();
		shlLoanManagment.layout();
		while (!shlLoanManagment.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoanManagment = new Shell();
		shlLoanManagment.setSize(603, 520);
		shlLoanManagment.setText("Loan Managment");
		
		Label lblNewLabel = new Label(shlLoanManagment, SWT.NONE);
		lblNewLabel.setBounds(10, 25, 47, 20);
		lblNewLabel.setText("Cust ID");
		
		txtcustID = new Text(shlLoanManagment, SWT.BORDER);
		txtcustID.setBounds(79, 25, 73, 26);
		
		Label lblName = new Label(shlLoanManagment, SWT.NONE);
		lblName.setBounds(158, 25, 63, 26);
		
		
		//message box
		Label lblmsg = new Label(shlLoanManagment, SWT.NONE);
		lblmsg.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblmsg.setBounds(419, 20, 165, 45);
		lblmsg.setVisible(false);

		
		Button btnSearch = new Button(shlLoanManagment, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//check the customer ID
				if(txtcustID.getText()=="") {
					lblmsg.setText("Please enter customer ID!");
					lblmsg.setVisible(true);
					return;
				}
				atable.removeAll();
				lblName.setText("");
				arrayLoan=new LoanManager();
				try {
					//get customer name
					ResultSet rsc=aCust.searchCust(" custID="+txtcustID.getText());
					if(rsc.next()) {
						lblName.setText(rsc.getString("name"));
						lblmsg.setVisible(false);
					}
					else {
						//customer is not found
						//JOptionPane.showMessageDialog(null, "Customer is not found!", "Information", 2);
						lblmsg.setText("Customer is not found!");
						lblmsg.setVisible(true);
						return;
					}
					//get all loan by customer
					arrayLoan.getAllLoanByCust(Integer.parseInt(txtcustID.getText()));
					//Loan is not found
					if(arrayLoan.getLoans()==null) {
						//JOptionPane.showMessageDialog(null, "there is no loan found!", "Information", 2);
						lblmsg.setText("There is no loan found!");
						lblmsg.setVisible(true);
					}
					else {
						lblmsg.setVisible(false);
						refreshTable();
					}
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		btnSearch.setBounds(227, 20, 90, 30);
		btnSearch.setText("Search");
		
		atable = new Table(shlLoanManagment, SWT.BORDER | SWT.FULL_SELECTION);
		atable.setBounds(10, 71, 565, 199);
		atable.setHeaderVisible(true);
		atable.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn.setWidth(0);
		tblclmnNewColumn.setText("ID");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(52);
		tblclmnNewColumn_1.setText("Rate");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_2.setWidth(80);
		tblclmnNewColumn_2.setText("Princple");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_3.setWidth(65);
		tblclmnNewColumn_3.setText("Year");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_4.setWidth(99);
		tblclmnNewColumn_4.setText("Type");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_5.setWidth(135);
		tblclmnNewColumn_5.setText("Monthly Payment");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_6.setWidth(129);
		tblclmnNewColumn_6.setText("Total Payment");
		
		Label lblType = new Label(shlLoanManagment, SWT.NONE);
		lblType.setText("Type:");
		lblType.setBounds(10, 312, 47, 20);
		
		Button rdoSL = new Button(shlLoanManagment, SWT.RADIO);
		rdoSL.setSelection(true);
		rdoSL.setBounds(57, 308, 120, 28);
		rdoSL.setText("Simple Loan");
		
		Button rdoAL = new Button(shlLoanManagment, SWT.RADIO);
		rdoAL.setBounds(183, 308, 143, 28);
		rdoAL.setText("Amoritized Loan");
		
		Button btnDelete = new Button(shlLoanManagment, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//check the selection
				if(atable.getSelectionIndex()==-1) {
					lblmsg.setText("Please select a loan \n in the list!");
					lblmsg.setVisible(true);					
					return;
				}
				//remove from arraylist
				arrayLoan.removeLoan(atable.getSelectionIndex());
				refreshTable();
			}
		});
		btnDelete.setText("Delete");
		btnDelete.setBounds(323, 20, 90, 30);
		
		Label lblPrincple = new Label(shlLoanManagment, SWT.NONE);
		lblPrincple.setText("Princple");
		lblPrincple.setBounds(10, 349, 53, 20);
		
		txtPrincple = new Text(shlLoanManagment, SWT.BORDER);
		txtPrincple.setBounds(80, 342, 73, 26);
		
		Label lblYear = new Label(shlLoanManagment, SWT.NONE);
		lblYear.setText("Year");
		lblYear.setBounds(174, 342, 33, 20);
		
		txtyear = new Text(shlLoanManagment, SWT.BORDER);
		txtyear.setBounds(214, 342, 73, 26);
		
		Label lblRate = new Label(shlLoanManagment, SWT.NONE);
		lblRate.setText("Rate");
		lblRate.setBounds(318, 342, 33, 20);
		
		Combo cmbRate = new Combo(shlLoanManagment, SWT.NONE);
		cmbRate.setItems(new String[] {"30%", "40%", "50%", "60%", "70%"});
		cmbRate.setBounds(355, 341, 92, 28);
		
		Label lblMonthlypayment = new Label(shlLoanManagment, SWT.NONE);
		lblMonthlypayment.setBounds(10, 391, 120, 20);
		lblMonthlypayment.setText("MonthlyPayment");
		
		Label lblMP = new Label(shlLoanManagment, SWT.NONE);
		lblMP.setBounds(135, 391, 70, 20);
		
		Label lblTotalpayment = new Label(shlLoanManagment, SWT.NONE);
		lblTotalpayment.setText("TotalPayment");
		lblTotalpayment.setBounds(238, 391, 92, 20);
		
		Label lblTP = new Label(shlLoanManagment, SWT.NONE);
		lblTP.setBounds(344, 391, 132, 20);
		
		Button btnCalc = new Button(shlLoanManagment, SWT.NONE);
		btnCalc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//need to choose a loan type
				if(txtcustID.getText()=="") {
					//JOptionPane.showMessageDialog(null, "Please search customer loan first!", "Information", 2);
					lblmsg.setText("Please search customer loan first!");
					lblmsg.setVisible(true);					
					return;
				}
				//check customer existing
				ResultSet rsc=aCust.searchCust(" custID="+txtcustID.getText());
				try {
					if(rsc.next()) {
						lblName.setText(rsc.getString("name"));
						lblmsg.setVisible(false);
					}
					else {
						//customer is not found
						//JOptionPane.showMessageDialog(null, "Customer is not found!", "Information", 2);
						lblmsg.setText("Customer is not found!");
						lblmsg.setVisible(true);
						return;
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//if no loan exist, creat a arryloan 
//				if(arrayLoan==null)
//					arrayLoan=new LoanManager();
				//create a Loan to save a loan temporally
				Loan aLoan;
				double rate=Double.parseDouble(cmbRate.getText().substring(0, cmbRate.getText().length()-1))/100;
				//simple Loan
				if(rdoSL.getSelection())
					aLoan=new SimpleLoan(Integer.parseInt(txtcustID.getText()), 
							0, 
							rate, 
							Integer.parseInt(txtyear.getText()),
							Double.parseDouble(txtPrincple.getText()));
				//amortized loan
				else
					aLoan=new AmortizedLoan(Integer.parseInt(txtcustID.getText()), 
							0, 
							rate, 
							Integer.parseInt(txtyear.getText()),
							Double.parseDouble(txtPrincple.getText()));
				aLoan.process();//caculation
				lblMP.setText(String.valueOf(df2.format(aLoan.getmonthlyPayment()))); //set monthlypayment
				lblTP.setText(String.valueOf(df2.format(aLoan.gettotalPayment()))); //set total payment
				//refresh table
				arrayLoan.insertLoans(aLoan);
				//refresh all table
				refreshTable();
			}
		});
		btnCalc.setText("Add New Loan");
		btnCalc.setBounds(26, 431, 143, 30);
		
		Button btnSave = new Button(shlLoanManagment, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//save whole arraylist to database
				try {
					arrayLoan.deleteLoanByCust(Integer.parseInt(txtcustID.getText()));
					arrayLoan.addLoanArray();
					//JOptionPane.showMessageDialog(null, "Successful!", "Information", 2);
					lblmsg.setText("Save Successfully!");
					lblmsg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(323, 431, 105, 30);
		
		Label lblAddupdateALoan = new Label(shlLoanManagment, SWT.NONE);
		lblAddupdateALoan.setBounds(10, 286, 157, 20);
		lblAddupdateALoan.setText("Add/Update a loan:");
		
		Label label = new Label(shlLoanManagment, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 304, 437, 2);
		
		Button btnEdit = new Button(shlLoanManagment, SWT.NONE);
		btnEdit.setText("Confirm Edit");
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Verify the customer list choosen
				if(atable.getSelectionIndex()==-1) {
					lblmsg.setText("please select a customer \n in the list.");
					lblmsg.setVisible(true);
					return;
				}
				//delete original one
				arrayLoan.removeLoan(atable.getSelectionIndex());
				//create a Loan to save a loan temporally
				Loan aLoan;
				double rate=Double.parseDouble(cmbRate.getText().substring(0, cmbRate.getText().length()-1))/100;
				if(rdoSL.getSelection())
					aLoan=new SimpleLoan(Integer.parseInt(txtcustID.getText()), 
							0, 
							rate, 
							Integer.parseInt(txtyear.getText()),
							Double.parseDouble(txtPrincple.getText()));
				else
					aLoan=new AmortizedLoan(Integer.parseInt(txtcustID.getText()), 
							0, 
							rate, 
							Integer.parseInt(txtyear.getText()),
							Double.parseDouble(txtPrincple.getText()));
				aLoan.process();//caculation
				lblMP.setText(String.valueOf(df2.format(aLoan.getmonthlyPayment()))); //set monthlypayment
				lblTP.setText(String.valueOf(df2.format(aLoan.gettotalPayment()))); //set total payment
				//refresh table
				arrayLoan.insertLoans(aLoan);
				//refresh all table
				refreshTable();
				//set everything default
				lblmsg.setText("Edit Successfully!");
				lblmsg.setVisible(true);
			}
		});
		btnEdit.setBounds(174, 431, 143, 30);

		
		//select a item within Loan list
		atable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 Loan tempLoan=arrayLoan.getLoans().get(atable.getSelectionIndex());
				 //assign value to edit box
				 if(tempLoan.gettype()==1) {
					 rdoSL.setSelection(true);
					 rdoAL.setSelection(false);
				 }
				 else if(tempLoan.gettype()==2) {
					 rdoAL.setSelection(true);
					 rdoSL.setSelection(false);
				 }
				 //assign value to textboxs
				 txtPrincple.setText(String.valueOf(tempLoan.getprinciple()));
				 txtyear.setText(String.valueOf(tempLoan.getlength()));
				 cmbRate.setText(String.valueOf(df3.format(tempLoan.getinterestRate())));
				 lblMP.setText(String.valueOf(df2.format(tempLoan.getmonthlyPayment())));
				 lblTP.setText(String.valueOf(df2.format(tempLoan.gettotalPayment())));
			}
		});

	}
}
