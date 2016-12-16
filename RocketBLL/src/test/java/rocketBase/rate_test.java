package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	//Check to see if a known credit score returns a known interest rate
	
	@Test
	public void testgetRate() throws RateException {
		
		assertEquals(2.5, RateBLL.getRate(1100), 0.001);
		
		assertEquals(3, RateBLL.getRate(950), 0.001);
		
		assertEquals(7.5, RateBLL.getRate(510), 0.001);
		
		assertEquals(6, RateBLL.getRate(580), 0.001);	
	
	}
	
	//Check to see if a RateException is thrown if there are no rates for a given credit score
	
	@Test(expected = RateException.class)
	public void testRateException() throws RateException {
		
		ArrayList<RateDomainModel> rList = RateDAL.getAllRates();
		System.out.println("Found " + rList.size() + " rates in this list.");
		
		assert(rList.size() > 0);
		assertEquals(0, RateBLL.getRate(777), 0.002);
		
	}

}
