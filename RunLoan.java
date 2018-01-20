/*****************************************************************
this is main class for Loan app
@author Jie Tao
@version 2017.12
*****************************************************************/

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RunLoan {

	protected Shell shlSimpleLoanApplication;
	private Text txtPleaseChooseOne;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RunLoan window = new RunLoan();
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
		shlSimpleLoanApplication.open();
		shlSimpleLoanApplication.layout();
		while (!shlSimpleLoanApplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSimpleLoanApplication = new Shell();
		shlSimpleLoanApplication.setSize(670, 300);
		shlSimpleLoanApplication.setText("Simple Loan Application");
		
		txtPleaseChooseOne = new Text(shlSimpleLoanApplication, SWT.NONE);
		txtPleaseChooseOne.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		txtPleaseChooseOne.setEditable(false);
		txtPleaseChooseOne.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtPleaseChooseOne.setText("Please choose one of the following operations:");
		txtPleaseChooseOne.setBounds(24, 21, 466, 42);
		
		Button btnCustFrame = new Button(shlSimpleLoanApplication, SWT.NONE);
		btnCustFrame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fCustomer afCust = new fCustomer();
				afCust.newfCustomer();
			}
		});
		btnCustFrame.setBounds(97, 76, 167, 42);
		btnCustFrame.setText("Customer Management");
		
		Button btnLoanFrame = new Button(shlSimpleLoanApplication, SWT.NONE);
		btnLoanFrame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fLoan afLoan=new fLoan();
				afLoan.newfLoan();
			}
		});
		btnLoanFrame.setText("Loan Management");
		btnLoanFrame.setBounds(321, 76, 167, 42);
		
		Button btnSummary = new Button(shlSimpleLoanApplication, SWT.NONE);
		btnSummary.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fSummary aSummery=new fSummary();
				aSummery.NewfSummary();
			}
		});
		btnSummary.setText("Summary");
		btnSummary.setBounds(97, 147, 167, 42);
		
		Button btnExit = new Button(shlSimpleLoanApplication, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setText("Exit");
		btnExit.setBounds(323, 147, 167, 42);

	}

}
