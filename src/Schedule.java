//****************************************************
//Schedule.java
//
//by Stefanie Molin
//December 4, 2011
//
//Creates a schedule type consisting of talks
//****************************************************

import java.util.ArrayList;
import java.util.Collections;


public class Schedule {
	private ArrayList<Talk> schedule=new ArrayList<Talk>();
	private ArrayList<Talk> master=new ArrayList<Talk>();

	public void addTalk(String title, String start, String end){
		schedule.add(new Talk (title, start, end));
	}//creates addTalk method for adding talks to the schedule array list

	public void sort(){
		Collections.sort(schedule);
	}//will sort the talks according to the compareTo method in the Talk class

	public void assignRoom(){
		//assigns the room first to the talk with the earliest end time (within the bounds of 08:00-17:00)
		//and initializes the current end time to check for conflicts
		master.add(schedule.get(0));
		int currentEndHour=schedule.get(0).getEndHour();
		int currentEndMinutes=schedule.get(0).getEndMinutes();

		for(int i=1; i<schedule.size();i++){
			if(schedule.get(i).getStartHour()>currentEndHour){
				master.add(schedule.get(i));
				currentEndHour=schedule.get(i).getEndHour();
				currentEndMinutes=schedule.get(i).getEndMinutes();
			}//schedules the class if the hour it starts is later than the hour the previous scheduled one ended
			if(schedule.get(i).getStartHour()==currentEndHour && schedule.get(i).getStartMinutes()>=currentEndMinutes){
				master.add(schedule.get(i));
				currentEndHour=schedule.get(i).getEndHour();
				currentEndMinutes=schedule.get(i).getEndMinutes();
			}//schedules the class if it starts when the previous one ends or minutes after but in the same hour
		}
	}

	public String toString(){
		String agenda="The following is a schedule of the talks for today.";
		agenda+="\n______________________________\n";
		for(int i=0;i<master.size();i++){
			agenda+=master.get(i).toString();
			agenda+="\n";
		}
		return agenda;
	}//format for printing the agenda as a string

	public Talk getTalk(int i){
		return master.get(i);
	}//returns the talk at the index (used to print to file)

	public int getScheduleSize(){
		return master.size();
	}//returns the size of the master array list


}
