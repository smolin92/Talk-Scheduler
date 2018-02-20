//****************************************************
//ScheduleTest.java
//
//by Stefanie Molin
//December 4, 2011
//
//Schedules the most talks using the greedy algorithm
//****************************************************
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScheduleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try{
			File inFile=new File(args[0]);
			Scanner input=new Scanner(inFile);

			String title;
			String startTime;
			String endTime;
			int endHour;
			int startHour;
			int startMinutes;
			int endMinutes;

			Schedule agenda=new Schedule();

			while (input.hasNext()){
				title=input.nextLine();
				startTime=input.nextLine();
				endTime=input.nextLine();
				startHour=Integer.parseInt(startTime.substring(0,2));
				endHour=Integer.parseInt(endTime.substring(0,2));
				startMinutes=Integer.parseInt(startTime.substring(3,5));
				endMinutes=Integer.parseInt(endTime.substring(3,5));

				if(startHour>23 ||startHour<0 ||endHour>23 ||endHour<0){
					throw new NumberFormatException();
				}
				if(startMinutes>59 ||startMinutes<0 ||endMinutes>59 ||endMinutes<0){
					throw new NumberFormatException();
				}
				if(startTime.length()!=5 || endTime.length()!=5){
					throw new StringIndexOutOfBoundsException();
				}//these three if statements make sure that the time slots are correctly formatted.

				if(endHour<17 && startHour>=8){
					agenda.addTalk(title, startTime, endTime);
				}//adds talks if they are within the bounds of 08:00 and 17:00
				if(endTime.equals("17:00")){
					agenda.addTalk(title, startTime, endTime);
				}//adds talks that end at 17:00 exactly

				if(input.hasNext()){
					input.nextLine();
				}//passes the blank line
			}

			agenda.sort();//sorts the talks by end time
			agenda.assignRoom();//schedules the talks using a greedy algorithm

			if(args.length>=2){
				File outFile=new File(args[1]);
				PrintWriter output=new PrintWriter(outFile);
				output.println("The following is a schedule of the talks for today.");
				output.println("\n______________________________\n");
				for(int i=0; i<agenda.getScheduleSize(); i++){
					output.println(agenda.getTalk(i));
					output.println("\n");
				}
				output.close();
			}//if an output file was specified the results print to that file
			else{
				System.out.println(agenda);
			}//prints to the screen if no output file was specified
		}
		catch(IOException e){
			System.out.println("Please try again with the correct input file name.");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Please enter command line arguments as follows:");
			System.out.println("input.txt output.txt");
			System.out.println("Specifying the output file is optional. If no file is specified, results print to screen.");
		}

		catch(NoSuchElementException e){
			System.out.println("The file you specified is incorrectly formatted");
		}//if the file has two few lines or indices in a string, this exception catches it
		catch(NumberFormatException e){
			System.out.println("The file you specified is incorrectly formatted.");
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("The file you specified is incorrectly formatted.");
		}//these two make sure times are entered correctly and that they are numbers
	}

}
