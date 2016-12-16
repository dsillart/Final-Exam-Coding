package exceptions;

public class RateException extends Exception {

	//	RocketBLL RateException - RateDomainModel should be an attribute of RateException
	
	//	* Add RateRomainModel as an attribute
	
		private rocketDomain.RateDomainModel RateDomainModel;

	
	//	* Create a constructor, passing in RateDomainModel
	
		public RateException(rocketDomain.RateDomainModel RateDomain){
			this.RateDomainModel = RateDomain;
		}
	
	
	//	* Create a getter (no setter, set value only in Constructor)

		public rocketDomain.RateDomainModel getRateDomainModel() {
			return RateDomainModel;
		}
}