package com;

public class Rounding_off {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double inp = 121.79710;
		String inp1="1234IDMS";
		
			String inp2= inp1.indexOf("IDMS")>0?inp1.substring(0,inp1.indexOf("IDMS")):inp1;
			System.out.println(inp2.trim());
		
		
		//System.out.println(String.format("%.4g%n", inp));

	}

}
