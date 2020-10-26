package Travel;

public class TravelList {
	
	private String privatenum;
	private String countryNum;
	private String country;
	private String city;
	private String attraction;
	private String recommend1;
	private String recommend2;
	private String recommend3;
	private String review;
	public String getPrivatenum() {
		return privatenum;
	}
	public void setPrivatenum(String privatenum) {
		this.privatenum = privatenum;
	}
	public String getCountryNum() {
		return countryNum;
	}
	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAttraction() {
		return attraction;
	}
	public void setAttraction(String attraction) {
		this.attraction = attraction;
	}
	public String getRecommend1() {
		return recommend1;
	}
	public void setRecommend1(String recommend1) {
		this.recommend1 = recommend1;
	}
	public String getRecommend2() {
		return recommend2;
	}
	public void setRecommend2(String recommend2) {
		this.recommend2 = recommend2;
	}
	public String getRecommend3() {
		return recommend3;
	}
	public void setRecommend3(String recommend3) {
		this.recommend3 = recommend3;
	}
	public TravelList() {
		super();
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public TravelList(String privatenum, String countryNum, String country, String city, String attraction, String recommend1,
			String recommend2, String recommend3, String review) {
		super();
		this.privatenum = privatenum;
		this.countryNum = countryNum;
		this.country = country;
		this.city = city;
		this.attraction = attraction;
		this.recommend1 = recommend1;
		this.recommend2 = recommend2;
		this.recommend3 = recommend3;
		this.review = review;
	}
	
	@Override
	public String toString() {
		return "TravelList [privatenum=" + privatenum + ", countryNum=" + countryNum + ", country=" + country
				+ ", city=" + city + ", attraction=" + attraction + ", recommend1=" + recommend1 + ", recommend2="
				+ recommend2 + ", recommend3=" + recommend3 + ", review=" + review + "]";
	}
	
	
	

}