import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

interface Accumulate {
	double accumulateValues(Ticket[] data );
}

class Ticket implements Comparable {

	private String fromCity;
	private String toCity;
	private double price;
	private int km;

	Ticket (){
		fromCity = "N/A";
		toCity = "N/A";
		price = 0.0;
		km = 0;
	}
	
	public Ticket(String fromCity, String toCity, double price, int km) {
		this.fromCity = "Frankfurt";
		this.toCity = "Hessen";
		this.price = 0.0;
		this.km = 0;
	}
	public int compareTo(Object obj) {
		return this.km-((Ticket)obj).km;
	}
	public String gettoCity() {
		return toCity;
	}
	public void settoCity(String toCity) {
		this.toCity = toCity;
	}
	public String getfromCity() {
		return fromCity;
	}
	public void setfromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public int getkm() {
		return km;
	}
	public void setkm(int km) {
		this.km = km;
	}
	public double getprice() {
		return price;
	}
	public void getprice(double price) {
		this.price = price;
	}
	public String toString() {
		return " [from City: " + fromCity + ", to City: " + toCity + ", price: " + price + ", km =" + km + "]";
	}
	public boolean  equals(Ticket tck) {
		if ((fromCity.equals(tck.fromCity))&&toCity.equals(tck.toCity)) {
			return true;
		}
		else return false;
	}
}
	class Bahnhof implements Comparable, Accumulate {
          String name;
          private Ticket[] tickets = new Ticket[] {
        		new Ticket("","",0.0,0), 
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0),
        		new Ticket("","",0.0,0)};
      	public String getname() {
    		return name;
    	}
    	public void name(String name) {
    		this.name = name;
    	}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
		public Bahnhof(String filename) {
	        try {
	            RandomAccessFile myFile = new RandomAccessFile(filename, "r");
	            boolean station_first = false;
	            String strLine;
	            String[] res;
	            int ind = 0;
	            int ar_size = 11;
	            while ((strLine = myFile.readLine()) != null) {
	            	if (!station_first) {
	            		res = strLine.split(" ");
	            		name = res[0];
	            		station_first = true;
	            	}
	            	else {
	            		res = strLine.split(" ");
	                    Ticket oTick = new Ticket(
	                            (res[0]),
	                            (res[1]),
	                            Double.valueOf(res[2]).doubleValue(),
	                            Integer.valueOf(res[3]).intValue()
	                    );
	                    tickets[ind++] = oTick;
	                    ar_size--;
	            	}

	            }
	            myFile.close();
	            while (ar_size != 0) {
	            	Ticket oTick = new Ticket();
	            	tickets[ind++] = oTick;
	            	ar_size--;
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		public void showToCout() {
			System.out.println(Arrays.toString(tickets));
		}
		public void sortArray() {
			Arrays.sort(tickets);
		}
		private double avgprice(String start,String end) {
			double total = 0.0;
			int incr = 0;
			for (Ticket curr : tickets) {
				if (curr.getfromCity().equals(start)&&curr.gettoCity().equals(end)) {
					total += curr.getprice();
					incr++;
				}
			}
			return total/incr;
		}
		public void to_String() 
		{
	    	System.out.println("\n Bahnhof: \n"+name+"\n");
	    	for (Ticket curr : tickets) 
	    	{
	    		System.out.println(curr.toString());
	    	}
	    	
		}
		@Override
		public double accumulateValues(Ticket[] data) {
			int total = 0;
			for (Ticket curr : data) {
					total += curr.getprice();
			}
			return total;
		}
		public double compareTo1(Object o1) {
			return this.accumulateValues(this.tickets)-((Bahnhof)o1).accumulateValues(((Bahnhof)o1).tickets);
		}
	public static void main(String[] args) {
		Bahnhof example = new Bahnhof("example.txt");
		Bahnhof example2 = new Bahnhof("example2.txt");
		    example.to_String();
			example2.to_String();
			System.out.println("First station total sum: "+example.accumulateValues(example.tickets));
			System.out.println("Second station total sum: "+example2.accumulateValues(example2.tickets));
			System.out.println("Avg Price from Varna to C for station 1 "+example.avgprice("Varna", "C"));
			System.out.println("Avg Price from Varna to C for station 2 "+example2.avgprice("Varna", "C"));
	}
}
