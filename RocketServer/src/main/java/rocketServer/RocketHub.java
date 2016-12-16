package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			// RocketHub.messageReceived
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			
			try {
				
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
				lq.setdPayment(Math.abs(RateBLL.getPayment(lq.getdRate()/(12*100),lq.getiTerm(), lq.getdAmount(), 0, false)));	
			}
			
			catch (Exception e) {
				
				System.out.println("Error: Invalid User Entry: " + e + ".");
				sendToAll(e);
				
				return;
			}
			
			sendToAll(lq);
		}
	}
}