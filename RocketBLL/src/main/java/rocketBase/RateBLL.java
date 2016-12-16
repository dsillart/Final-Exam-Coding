package rocketBase;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	//RocketBLL RateBLL.getPayment 
	
	private static RateDAL RateDAL = new RateDAL();
	
	public double getRate(int GivenCreditScore) {
		
		double Rate = 0;
		
		for (RateDomainModel r : RateDAL.getAllRates() ) {
			
			if(GivenCreditScore >= r.getiMinCreditScore()) {
				
				Rate = r.getdInterestRate();
				System.out.println("Rate " + Rate);	
			}
		}
		
			if (Rate == 0) {
			
				throw new RateException(r);
			}
			
		return Rate;		
	}
	
	public double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}