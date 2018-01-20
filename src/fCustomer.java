/*****************************************************************
this is a GUI class for Cusotmer operation
@author Jie Tao
@version 2017.12
*****************************************************************/
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.ResultSet;

import javax.swing.JOptionPane;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

public class fCustomer {

	protected Shell shlCustomerManagement;
	private Text txtcustID;
	private Text txtname;
	private Text txtage;
	private Text txtphone;
	private Text txtsalary;
	private Text txtaddress;
	private Table atable;
	private Customer aCust;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public  void newfCustomer() {
		try {
			fCustomer window = new fCustomer();
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
		shlCustomerManagement.open();
		shlCustomerManagement.layout();
		while (!shlCustomerManagement.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	//refresh diplay list
	private void RefreshTable() {
		atable.removeAll();
		try {
			//get all customer
			aCust=new Customer();
			ResultSet rs=aCust.searchCust(" 1=1");
			TableItem aTT;
			while(rs.next()) {
				aTT=new TableItem(atable, 0);
				aTT.setText(new String[] {rs.getString("CustID"),rs.getString("name"),rs.getString("age"),rs.getString("yearlysalary")});
			}
			
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlCustomerManagement = new Shell();
		shlCustomerManagement.setSize(589, 576);
		shlCustomerManagement.setText("Customer Management");
		
		//show message
		Label lblmsg = new Label(shlCustomerManagement, SWT.NONE);
		lblmsg.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblmsg.setBounds(39, 10, 289, 20);
		lblmsg.setVisible(false);
		
		//customer id
		Label lblCustId = new Label(shlCustomerManagement, SWT.NONE);
		lblCustId.setBounds(23, 43, 52, 20);
		lblCustId.setText("Cust ID");
		
		txtcustID = new Text(shlCustomerManagement, SWT.BORDER);
		txtcustID.setBounds(82, 40, 85, 26);
		
		//customer name
		Label lblName = new Label(shlCustomerManagement, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(195, 43, 44, 20);
		
		txtname = new Text(shlCustomerManagement, SWT.BORDER);
		txtname.setBounds(243, 40, 85, 26);
		
		//gender
		Label lblGender = new Label(shlCustomerManagement, SWT.NONE);
		lblGender.setText("Gender");
		lblGender.setBounds(363, 43, 52, 20);
		
		Combo cmbgender = new Combo(shlCustomerManagement, SWT.NONE);
		cmbgender.setItems(new String[] {"male", "female"});
		cmbgender.setBounds(419, 40, 85, 28);
		
		//age
		Label lblAge = new Label(shlCustomerManagement, SWT.NONE);
		lblAge.setText("Age");
		lblAge.setBounds(39, 92, 36, 20);
		
		txtage = new Text(shlCustomerManagement, SWT.BORDER);
		txtage.setBounds(82, 89, 85, 26);
		
		//phone
		Label lblPhone = new Label(shlCustomerManagement, SWT.NONE);
		lblPhone.setText("Phone");
		lblPhone.setBounds(195, 92, 44, 20);
		
		txtphone = new Text(shlCustomerManagement, SWT.BORDER);
		txtphone.setBounds(243, 89, 85, 26);
		
		//salary
		Label lblSalary = new Label(shlCustomerManagement, SWT.NONE);
		lblSalary.setText("Salary");
		lblSalary.setBounds(363, 92, 52, 20);
		
		txtsalary = new Text(shlCustomerManagement, SWT.BORDER);
		txtsalary.setBounds(419, 89, 85, 26);
		
		//priority
		Label lblLevel = new Label(shlCustomerManagement, SWT.NONE);
		lblLevel.setText("Level");
		lblLevel.setBounds(23, 141, 52, 20);
		
		Combo cmbdegree = new Combo(shlCustomerManagement, SWT.NONE);
		cmbdegree.setItems(new String[] {"1", "2", "3"});
		cmbdegree.setBounds(82, 133, 85, 28);
		
		Label lblAddress = new Label(shlCustomerManagement, SWT.NONE);
		lblAddress.setText("Address");
		lblAddress.setBounds(180, 141, 59, 20);
		
		txtaddress = new Text(shlCustomerManagement, SWT.None);
		txtaddress.setBounds(243, 135, 261, 26);
		
		Button btnAddNewCustomer = new Button(shlCustomerManagement, SWT.NONE);
		btnAddNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//id and name could not be null
				if(txtcustID.getText()=="" || txtname.getText()=="") {
					//JOptionPane.showMessageDialog(null, "Customer ID and Name is empty!", "Information", 2);
					lblmsg.setText( "Customer ID and Name couldn't be empty!");
					lblmsg.setVisible(true);
				}
				else {
					//add new customer into db
					 aCust=new Customer(Integer.parseInt(txtcustID.getText()), 
							txtname.getText(), 
							txtaddress.getText(), 
							cmbgender.getSelectionIndex(), 
							cmbdegree.getSelectionIndex(), 
							Integer.parseInt(txtage.getText()), 
							txtphone.getText(), 
							Double.parseDouble(txtsalary.getText()));
					try {
						aCust.addCust();
						//JOptionPane.showMessageDialog(null, "Successful!", "Information", 2);
						lblmsg.setText( "Create Successfully!");
						lblmsg.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//refresh table
				RefreshTable();
			}
		});
		btnAddNewCustomer.setBounds(39, 190, 141, 49);
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnSearchCustomer = new Button(shlCustomerManagement, SWT.NONE);
		btnSearchCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//refresh table
				RefreshTable();
			}
			
		});
		btnSearchCustomer.setText("Customer List");
		btnSearchCustomer.setBounds(213, 190, 141, 49);
		
	
		
		Button btnDeleteCustomer = new Button(shlCustomerManagement, SWT.NONE);
		btnDeleteCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//not choose a customer
				if(atable.getSelectionIndex()==-1) {
					lblmsg.setText( "Please select a customer in the customer list!");
					lblmsg.setVisible(true);
					return;
				}
				TableItem att=atable.getItem(atable.getSelectionIndex());
				txtcustID.setText(att.getText(0));
				try {
					aCust=new Customer();
					aCust.delCust(att.getText(0));
					//JOptionPane.showMessageDialog(null, "Customer ID and Name is empty!", "Information", 2);
					lblmsg.setText( "Delete Successfully!");
					lblmsg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//refresh table
				RefreshTable();
			}
		});
		btnDeleteCustomer.setText("Delete Customer");
		btnDeleteCustomer.setBounds(387, 190, 141, 49);
		
		
		atable = new Table(shlCustomerManagement, SWT.BORDER | SWT.FULL_SELECTION);
		atable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		atable.setBounds(23, 272, 505, 233);
		atable.setHeaderVisible(true);
		atable.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("ID");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Name");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(atable, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Age");
		
		TableColumn tblclmnSalary = new TableColumn(atable, SWT.NONE);
		tblclmnSalary.setWidth(100);
		tblclmnSalary.setText("Salary");

	}
}
