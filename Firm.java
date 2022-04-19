import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

interface Accumulate {
	Firm[] extractValues(Firm [] data1, Firm[] data2 );
}

class Firm implements Comparable {

    private String name;
    private String id_num;
    private double yearly_outcome;

    Firm (){
        setname("N/A");
        setId_num("N/A");
        setYearly_outcome(0.0);
    }

    public Firm(String name, String id_num, double yearly_outcome) {
        this.name = "IBN";
        this.id_num = "1861967";
        this.yearly_outcome = 0.0;
    }

    public double getYearly_outcome() {
        return yearly_outcome;
    }

    public void setYearly_outcome(double yearly_outcome) {
        this.yearly_outcome = yearly_outcome;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String toString() {
        return "FirmName" + name + " UIC" + id_num + " " + yearly_outcome + "";
    }
    
	public int compareTo(Firm obj) { // Implementaciq po II.b
		return this.id_num.compareTo(obj.id_num);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
class Bank implements Comparable, Accumulate {

	private String bank_name;
	private Bank[] obj_bank = new Bank[4];
	private int yearly_outcome;


	public String getbank_name() {
		return bank_name;
	}
	public Bank[] getObj() {
		return obj_bank;
	}

	public void setObj(Bank[] obj_bank) {
		this.obj_bank = obj_bank;
	}
	public void setbank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Bank(String filename) {
		try {
			Scanner iStream = new Scanner(new File(filename));
			int index = 0;
			while(iStream.hasNext()) {
				obj_bank[index++]=new Bank(iStream.next());
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void toSting() {
    	System.out.println("\nBank: \n"+bank_name+"\n");
    	for (Bank curr : obj_bank) {
    		System.out.println(curr.toString());
    	}
	}
    public String toString() {
        return "BankName" + bank_name + "";
    }	
    public int calcSum() {
		int res=0;
		for(Bank current : obj_bank) {
			res += current.yearly_outcome;
		}
		return res;
	}
	public double calcAvgOutcome() {
		double res=calcSum();
		return obj_bank.length>0?res/obj_bank.length:0.0;
	}

@Override
public Firm[] extractValues(Firm[] data1, Firm[] data2) {
	// TODO Auto-generated method stub
	return null;
}
public static void main(String[] args) 
{
	Bank test = new Bank("example.txt");
	Bank test2 = new Bank("example2.txt");
	test.toString();
	test2.toString();
	System.out.println(test.compareTo(test2));
}
}



