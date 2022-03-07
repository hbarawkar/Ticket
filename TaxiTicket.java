package com.ticketing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TaxiTicket {

	public static void main(String[] args) {


		String source = null;
		String destination=null;
		String count=null;

		Map<String,Integer>  map=new HashMap<String,Integer>();

		map.put("PUNE,MUMBAI", 120);
		map.put("PUNE,NASHIK", 200);
		map.put("MUMBAI,GOA", 350);
		map.put("MUMBAI,NASHIK", 180);


		System.out.print("Please Enter Route details.\n");
		System.out.print("==============================\n");

		source = readInputData("Enter Source");
		destination = readInputData("Enter Destination");
		count = readInputData("Enter Traveller Count");

 
		Integer distance =map.get(source+","+destination);

		if(distance!=null) {
			Integer fair = calculateFair(distance,count);
			System.out.println();
			System.out.println();
			System.out.println("Taxi Ticket");
			System.out.println("-----------");
			System.out.println("Source: "+source);
			System.out.println("Destination: "+destination);
			System.out.println("Kms: "+distance);
			System.out.println("No. of travellers = "+count);
			System.out.println("Total = "+fair+" INR");
			
		}else {
			System.out.println("Route Not found!!!");
		}

	}

	private static Integer calculateFair(Integer distance,String count) {
		Integer taxiFair = (distance - 100) * 5 + 750;
		return taxiFair * Integer.parseInt(count);
	}
	
	private static String readInputData(String dataLabel) {
		String dataValue = null;
		boolean isNumber =true;
		do {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(dataLabel + ":");

			try {
				dataValue = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dataValue == null || dataValue.trim().isEmpty()) {
				System.out.println("The value of '" + dataLabel + "' Can't be empty, please re enter it!");
			}
			if(dataLabel.equals("Enter Traveller Count") && !isNumeric(dataValue)) {
				isNumber =false;
				System.out.println("Traveller Count Can't be string, please re enter number!!!");
			}else {
				isNumber =true;
			}
		} while (dataValue == null || dataValue.trim().isEmpty() || !isNumber);

		return dataValue;
	}
	
	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}

}
