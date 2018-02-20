//****************************************************
//Talk.java
//
//by Stefanie Molin
//December 4, 2011
//
//Blueprint for a talk object used to schedule talks
//****************************************************
public class Talk implements Comparable<Talk>{
	private String name;
	private String start;
	private String end;
	private int startHour;
	private int startMinutes;
	private int endHour;
	private int endMinutes;

	public Talk(String n, String s, String e){
		name=n;
		start=s;
		end=e;
		startHour=Integer.parseInt(start.substring(0,2));
		startMinutes=Integer.parseInt(start.substring(3,5));
		endHour=Integer.parseInt(end.substring(0,2));
		endMinutes=Integer.parseInt(end.substring(3,5));
	}

	public String toString(){
		String description;
		description=name + " \n" + start + "-" + end + "\n";
		return description;
	}//specifies how to represent talks as strings

	//the following return start and end hours and minutes for scheduling
	public int getStartHour(){
		return startHour;
	}

	public int getStartMinutes(){
		return startMinutes;
	}

	public int getEndHour(){
		return endHour;
	}

	public int getEndMinutes(){
		return endMinutes;
	}

	public int compareTo(Talk other) {
		if(this.endHour < other.endHour){
			return -1;
		}
		else if(this.endHour > other.endHour){
			return 1;
		}
		else{
			if(this.endMinutes < other.endMinutes){
				return -1;
			}
			else if(this.endMinutes > other.endMinutes){
				return 1;
			}
			else{
				return 0;
			}
		}

	}//sorts the talks by end time

}
