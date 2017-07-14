/**
 * 
 */
package eecs2030.assignment;

import static org.junit.Assert.*;

import org.junit.Test;

import eecs2030.assignment.GeoLocation;

/**
 * @author Chenxing Zheng
 *
 */
public class GeoLocationTest {

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getInstance(double, double)}.
	 */
	@Test
	public void testGetInstance() {
		GeoLocation location =  GeoLocation.getInstance(123.45356, 56.77777);
		
		System.out.println("testGetInstance "+ location);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getInstance_throws() {

		GeoLocation location =  GeoLocation.getInstance(180.0001, 56.77777);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getInstance_throws2() {

		GeoLocation location =  GeoLocation.getInstance(18.00, -90.1);
		
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset0() {
		int answer = 8;
		GeoLocation Beijing =  GeoLocation.getInstance(116.5, 40.0);
		
		assertEquals(Beijing.getGMTHourOffset(), answer);
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset1() {
		int answer = 0;
		GeoLocation somewhere =  GeoLocation.getInstance(7.4, 40.0);
		
		assertEquals(somewhere.getGMTHourOffset(), answer);
	}
	
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset2() {
		int answer = 1;
		GeoLocation somewhere =  GeoLocation.getInstance(7.6, 40.0);
		
		assertEquals(somewhere.getGMTHourOffset(), answer);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset3() {
		int answer = 0;
		GeoLocation somewhere =  GeoLocation.getInstance(-7.4, 40.0);
		
		assertEquals(somewhere.getGMTHourOffset(), answer);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset4() {
		int answer = -1;
		GeoLocation somewhere =  GeoLocation.getInstance(-7.6, 40.0);

		assertEquals(somewhere.getGMTHourOffset(), answer);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getGMTHourOffset()}.
	 */
	@Test
	public void testGetGMTHourOffset5() {
		int answer = 11;
		GeoLocation somewhere =  GeoLocation.getInstance(179.99, 40.0);

		assertEquals(somewhere.getGMTHourOffset(), answer);
	}
	

	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject0() {
		GeoLocation somewhere0 =  GeoLocation.getInstance(179.99, 40.0);
		
		assertEquals(somewhere0.equals(somewhere0), true);
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject1() {
		GeoLocation somewhere0 =  GeoLocation.getInstance(179.99, 40.0);
		GeoLocation somewhere1 =  GeoLocation.getInstance(179.99, 40.0);
		
		assertEquals(somewhere0.equals(somewhere1), true);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject2() {
		GeoLocation somewhere0 =  GeoLocation.getInstance(179.99, 40.0);
		GeoLocation somewhere1 =  GeoLocation.getInstance(179.99, 40.0001);
		
		assertEquals(somewhere0.equals(somewhere1), false);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject3() {
		GeoLocation somewhere0 =  GeoLocation.getInstance(179.99, 40.0);

		assertEquals(somewhere0.equals(null), false);
	}
	
	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#toString()}.
	 */
	@Test
	public void testToString() {
		
		GeoLocation somewhere =  GeoLocation.getInstance(179.99, -4.0);
		String s = "(+179.9900,-04.0000)";
		assertEquals(somewhere.toString(),s);
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#getCount()}.
	 */
	@Test
	public void testGetCount() {
		GeoLocation somewhere0 =  GeoLocation.getInstance(179.9999, 40.0000);
		GeoLocation somewhere1 =  GeoLocation.getInstance(179.9999, 40.0000);
		GeoLocation somewhere2 =  GeoLocation.getInstance(179.99, 40.1);
		GeoLocation somewhere3 =  GeoLocation.getInstance(179.99, 40.2);
		
		int n = 1+3; // plus the one in testGenerate;
		
		assertEquals(GeoLocation.getCount(), n );
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#compareTo(eecs2030.assignment.GeoLocation)}.
	 */
	@Test
	public void testCompareTo() {
		GeoLocation somewhere1 =  GeoLocation.getInstance(179.99, 40.0);
		GeoLocation somewhere2 =  GeoLocation.getInstance(179.99, 40.1);
		
		int n = somewhere1.compareTo(somewhere2);
		assertEquals(n, -100);
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#distance(eecs2030.assignment.GeoLocation, eecs2030.assignment.GeoLocation)}.
	 */
	@Test
	public void testDistance() {
		double BeijingToNewYork = 11000;
		GeoLocation Beijing =  GeoLocation.getInstance(116.2,39.6);
		GeoLocation NewYork =  GeoLocation.getInstance(-74.00,40.5);
		
		double result = GeoLocation.distance(Beijing, NewYork);
		
		assertEquals(result, BeijingToNewYork, 1000);
	}

	/**
	 * Test method for {@link eecs2030.assignment.GeoLocation#generate(double, double)}.
	 */
	@Test
	public void testGenerate() {
		GeoLocation location1 = GeoLocation.generate(724.56777, -91.11335);
		System.out.println("testGenerate " + location1);
	}



}
