package rocket.app.view;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;

import eNums.eAction;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	// RocketClient.RocketMainController
	
	// Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtExpenses;
	
	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private ComboBox<?> termLength;
	@FXML
	private ComboBox<?> downPayment;
	
	@FXML
	private Label lblMonthlyPayment;
	
	@FXML
	private Label lblIncome;
	
	@FXML
	private Label lblExpenses;
	
	@FXML
	private Label lblCreditScore;
	
	@FXML
	private Label lblHouseCost;
	
	@FXML
	private Label lblTermLength;
	
	@FXML
	private Label lblDownPayment;
	
	@FXML
	private Button btnCalculateLoanPayment;
	
	@FXML
	private Label lblExceptionThrow;
	
	@FXML
	private Label lblPaymentException;
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	// RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		// RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		try {
			
			lq.setIncome(Double.parseDouble(txtIncome.getText()));
			lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
			lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
			lq.setiTerm(Integer.parseInt(termLength.getValue().toString()));
			lq.setiDownPayment((int)(Integer.parseInt(txtHouseCost.getText())*Double.parseDouble(downPayment.getValue().toString())/100));
			
			double amt = Double.parseDouble(txtHouseCost.getText())-
				
				lq.getiDownPayment();
				lq.setdAmount(amt);
			}
		
			catch (NumberFormatException e) {
				
				lblExceptionThrow.setText("Error: improper amount of significant figures entered.");
				return;
			}
		
		//Set the loan request details...  rate, term, amount, credit score, down payment
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub
		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment) should be calculated.
		// Display dPayment on the form, rounded to two decimal places
			
		double dPayment = Math.round(lRequest.getdPayment() * 100.0)/100.0;
		System.out.println(dPayment);
			
		if (dPayment < lRequest.getIncome() * 0.28) {
				
			if (dPayment < (lRequest.getIncome() * lRequest.getExpenses()) * 0.36) {
					
				lblMonthlyPayment.setText("Monthy Mortgage Payment: $" + dPayment + ". Monthy Mortgage Rate: " + lRequest.getdRate() + "%.");
				lblDownPayment.setText("Down Payment (" + Double.parseDouble(downPayment.getValue().toString()) + "% of Total): $" + lRequest.getiDownPayment());
			}
		}
			
			else {
				
				lblMonthlyPayment.setText("Error: You require additional funds.");
		}
	}
}
