package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	// RocketDAL rate_test
	// Check to see if a known credit score returns a known interest rate
	
	// RocketDAL rate_test
	// Check to see if a RateException is thrown if there are no rates for a given credit score
	
	@Test
	public void testReturns() {
		
		ArrayList<RateDomainModel> rList = RateDAL.getAllRates();
		System.out.println("Rate List size: " + rList.size() + " rates found.");
		
			assert(rList.size() > 0);
		
		assertEquals(rList.get(1).getdInterestRate(),4.50, 0.001);
		
		assertEquals(rList.get(2).getdInterestRate(),4.00, 0.001);
		
		assertEquals(rList.get(3).getdInterestRate(),3.75, 0.001);
		
		assertEquals(rList.get(4).getdInterestRate(),3.50, 0.001);
		
		assertEquals(rList.get(5).getdInterestRate(),3.00, 0.001);
		
	}
	
	@Test
	public void testCreditScore(){
		
		ArrayList<RateDomainModel> rList = RateDAL.getAllRates();
		System.out.println("Rate List size: " + rList.size() + " rates found.");
		
			assert(rList.size() > 0);
			
		assertEquals(rList.get(1).getiMinCreditScore(), 650);
		
		assertEquals(rList.get(2).getiMinCreditScore(), 700);
		
		assertEquals(rList.get(3).getiMinCreditScore(), 750);
		
		assertEquals(rList.get(4).getiMinCreditScore(), 800);
		
		assertEquals(rList.get(5).getiMinCreditScore(), 850);
	}
}