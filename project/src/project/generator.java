package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class generator {
	
	public static void main(String[] args)
	{	
		System.out.println("Enter directory path where you want to store file:");
		Scanner s = new Scanner(new InputStreamReader(System.in));
		String path = s.nextLine();
		//Enter date in format yyyy-MM-dd
		System.out.println("Enter date to generate logs:");
		String date = s.nextLine();
		String[] date_val = date.split("-");
		String year = date_val[0];
		int y = Integer.parseInt(year);
		String month = date_val[1];
		int m = Integer.parseInt(month)-1;
		String day = date_val[2];
		int d = Integer.parseInt(day);
		//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       // Date date = new Date();
        //to create filename using today's date  
        String fileName = date + ".csv";	
        //String path = "c:\\Users\\admin\\Desktop\\gen";
        String fp = path + "\\" + fileName;
        System.out.println(fp);
        
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(fp).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(fp, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Timestamp");
				csvOutput.write("IP");
				csvOutput.write("CPU_ID");
				csvOutput.write("Usage");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			String timestamp="";
			// write out records
			for(int i=0; i<1000 ; i++)
		    {   
				// Generate random valid IP addresses
				Random r = new Random();
		    	String IP= r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
		    	// iterate for hours of Day
		    	for(int hour = 0; hour < 24; hour++) 
		            {
		    		// iterate for minutes in hour
		    			for(int min=0; min<60; min++)
		    			{
		            	int ts = timestamp(y,m,d,hour,min);
		            	String timeStamp = Integer.toString(ts);
		            	
		            	// Iterate for CPU id
		            	for(int k=0; k<2; k++)
		            	{
		            		String id = Integer.toString(k);
		            		// generate CPU usage using random number generator
		            		Random rand = new Random();
		            		int s4 = rand.nextInt(100);
		            		String usage= Integer.toString(s4);
		            		// Write data to CSV file
		            		csvOutput.write(timeStamp);
		            		csvOutput.write(IP);
		            		csvOutput.write(id);
		            		csvOutput.write(usage);
		            		csvOutput.endRecord();
		            	}
		    			}
		            }
		    }
			csvOutput.close();        
		   
		}
        catch(Exception e)
        {
        	System.out.println(e);
        }       
 }
	
	
	public static Integer timestamp(int y, int m, int d,int hour, int min){
		
		// Generate timestamp by taking the Date String as input from user 
		try{
			Calendar myCal = Calendar.getInstance();
			myCal.set(Calendar.YEAR, y);
			myCal.set(Calendar.MONTH, m);
			myCal.set(Calendar.DAY_OF_MONTH, d);
			myCal.set(Calendar.HOUR, hour);
			myCal.set(Calendar.MINUTE, min);
			Date dt = myCal.getTime();
					
		long epoch = dt.getTime();
	    return (int)(epoch/1000); // convert from millis
		}
		catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
}
}
