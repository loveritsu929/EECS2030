package eecs2030.assignment;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * EECS2030 ASSIGNMENT
 * June 10, 2017
 * @author Chenxing Zheng
 * @YUID #214634901
 *
 */
public final class GeoLocation implements Comparable<GeoLocation> {
	
	private static int numLocation = 0;
	private static final double RADIUS_OF_EARTH = 6371;
	private static final double LONGITUDE_OF_GREENWICH = 0.0000;
	private final double longitude;
	private final double latitude;
	private final static DecimalFormat df = new java.text.DecimalFormat("#.0000"); 
	private static final Map<String, GeoLocation> instances = new TreeMap<String, GeoLocation>();
	
	private GeoLocation(double longitude, double latitude){
		
		if(longitude < -180 || longitude > 180 || latitude < -90 || latitude > 90 ){
			
			throw new IllegalArgumentException("Coordinates out of range");
		}
		
		double lo = Double.valueOf(df.format(longitude));
		double la = Double.valueOf(df.format(latitude));
		
	
		if(longitude == -180){
			lo = 180.0000;
		}
		this.longitude = lo;
		this.latitude  = la;
		numLocation++;
		
	}
	
	/**
	 * You should use this method to get an instance. It will find the instance in the Map,
	 * otherwise it will create the instance with given coordinates and put it in the Map
	 * 
	 * @param longitude the longitude
	 * @param latitude the latitude
	 * @return an instance of GeoLocation with given coordinates
	 * 
	 * @pre longitude in the range (-180.0, 180.0) && latitude in the range ( -90.0, 90.0)
	 */
	public static GeoLocation getInstance(double longitude, double latitude){
		
		double lo = Double.valueOf(df.format(longitude));
		double la = Double.valueOf(df.format(latitude));
		if(longitude == -180){
			lo = 180.0000;
		}
		
		String key = "" + lo + ", " + la;
		GeoLocation n = instances.get(key);
		if(n == null){
			n = new GeoLocation(longitude, latitude);
			instances.put(key, n);
		}
		
		return n;
	}
	
	/**
	 * This method will retrieve the hour offset from GMT
	 * 
	 * @return the hour offset from GMT ranging from -12 to 11
	 * 
	 */
	public int getGMTHourOffset(){
	
		 int timeOffsetInt = (int) (((this.longitude - LONGITUDE_OF_GREENWICH) - 7.5 )/ 15.0);
		 
		 if((this.longitude - LONGITUDE_OF_GREENWICH) - 7.5  > 0) timeOffsetInt++;
		 if(timeOffsetInt == 12) timeOffsetInt = 11;
		 
		 
		 return timeOffsetInt;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(this.getClass() != obj.getClass()){
			return false;
		}
		
		GeoLocation other = (GeoLocation) obj;
		
		if(Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)){
			return false;
		}
		if(Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)){
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public int hashCode(){
		
		return Objects.hash(this.longitude,this.latitude);
	}
	
	@Override
	public String toString(){
		
		DecimalFormat df2 = new java.text.DecimalFormat("00.0000"); 
		String strlongitude = "";
		String strlatitude = "";
		if(this.longitude >= 0){
			
			 strlongitude = "+" + df2.format(this.longitude);
		}
		else{
			
			 strlongitude =  df2.format(this.longitude);
		}
		
		if(this.latitude >= 0){
			
			 strlatitude = "+" + df2.format(this.latitude);
		}
		else{
			
			 strlatitude =   df2.format(this.latitude);
		}
		
		String result = "(" + strlongitude + "," + strlatitude + ")";
		
		return result;
		
	}
	
	/**
	 * @return the number of instances which have been created
	 */
	public static int getCount(){
		
		int n = numLocation;
		return n;
	}
	
	/**
	 * This method compares two instance of this class, and return an int based on the coordinates of the instances
	 * 
	 * @param other the other GeoLocation instance to be compared with
	 * @return a positive number if the time offset is greater than the other one's;
	 * 		   a negative number if the time offset is less than the other one's
	 * 		   100 if the latitude is greater than the other one's;
	 * 		   -100 if the latitude is less than the other one's ;
	 * 		   0 if their coordinates are equal	
	 */
	@Override
	public int compareTo(GeoLocation other){
		
		if(this.getGMTHourOffset() != other.getGMTHourOffset()){
			
			return this.getGMTHourOffset() - other.getGMTHourOffset();
		}
		
		if(this.latitude > other.latitude){
			
			return 100;
		}
		if(this.latitude < other.latitude){
			
			return -100;
		}
		
		return 0;
	}
	
	/**
	 * This method compute the shortest distance between given locations
	 * @param location1 a GeoLocation instance
	 * @param location2 another GeoLocation instance
	 * @return The distance in km between the two location
	 */
	public static double distance(GeoLocation location1, GeoLocation location2){
		
		double jA = Math.toRadians(location1.longitude);
		double wA = Math.toRadians(location1.latitude);
		double jB = Math.toRadians(location2.longitude);
		double wB = Math.toRadians(location2.latitude);
		
		double diffLon = jA - jB;
		double diffLat = wA - wB;
		
		double distance = RADIUS_OF_EARTH*2*Math.asin(Math.sqrt(Math.pow(Math.sin(diffLat/2),2) + Math.cos(wA)*Math.cos(wB)*Math.pow(Math.sin(diffLon/2),2)));
		
		
		return distance;
		
	}
	
	/**
	 * This is the static factory method which will work for any numbers passed. For numbers out of range, 
	 * they should be converted to valid range
	 * 
	 * @param longitude the longitude
	 * @param latitude the latitude
	 * @return an instance of GeoLocation
	 */
	public static GeoLocation generate(double longitude, double latitude){
		
		double lon = longitude;
		double lat = latitude;
		int n = 0;
		
		if(lon > 180.0 || lon < -180.0){
			n = ((int) lon)/180;
			lon = lon-n*180;
		}
		
		if(lat > 90.0 || lat < -90.0){
			n = ((int) lat)/90;
			lat = lat-n*90;
		}
		
		if(lon == -180){
			lon = 180.0000;
		}
		
		 lon = Double.valueOf(df.format(lon));
		 lat = Double.valueOf(df.format(lat));
		
		return getInstance(lon, lat);
	
	}
	
	/**
	 * The longitude accessor
	 * 
	 * @return the longitude of the instance
	 */
	public double getLongitude(){
		
		double n = this.longitude;
		return n;
	}
	
	/**
	 * The latitude accessor
	 * 
	 * @return the latitude of the instance
	 */
	public double getLatitude(){
		
		double n = this.latitude;
		return n;
	}
	
	public static void main(String[] args) {
		

		System.out.println(getCount());
		
		GeoLocation l1 = generate(116.3,39.8);
		GeoLocation l2 = generate(-74.0,40.6);
		GeoLocation l3 = getInstance(-74.0, 40.5999999);
		GeoLocation l4 = generate(123, 234);
		
		System.out.println(getCount());
		System.out.println(l2.equals(l3));
		
		//System.out.println(l2.hashCode());
		//System.out.println(l3.hashCode());
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	

}
