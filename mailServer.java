import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

interface setLoader{
	Set<emailProfile> loadData();
}

class emailProfile implements Comparable<Object>{
	
	protected String username;
	protected String password;
	
	public emailProfile(String usr,String pw) {
		username = usr;
		password = pw;
	}
	
	public void set_usr(String usr) {
		username = usr;
	}
	public void set_pw(String pw) {
		password = pw;
	}
	
	public String get_username() {
		return username;
	}
	public String get_password() {
		return password;
	}
	
	public String toString() {
		return ("Username: "+username+"\nPassword: "+password+"\n");
	}
	
	public boolean profiles_equal(emailProfile prof) {
		return  (username.equals(prof.get_username()) && password.equals(prof.get_password()));
	}
	
	@Override
	public int compareTo(Object arg0) {
		if (username.equals(((emailProfile)arg0).get_username())){
			if (password.equals(((emailProfile)arg0).get_password())){
				return 0;
			}
			else return password.compareTo(((emailProfile)arg0).get_password());
		}
		else return username.compareTo(((emailProfile)arg0).get_username());
	}
	
}

class profile_creation implements setLoader{
	
	protected String filename;
	
	public profile_creation(String file) {
		filename = file;
	}

	@Override
	public Set<emailProfile> loadData() {
		Set<emailProfile> temp_set = new HashSet<emailProfile>();
    	try {
			Scanner iStream = new Scanner(new File(filename));
			while(iStream.hasNext()) {
				emailProfile single_row = new emailProfile(iStream.next(),iStream.next());
				temp_set.add(single_row);
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp_set;
	}
	
}


public class mailServer {
	
	protected String server_ID;
	protected Set<emailProfile> server_data = new HashSet<emailProfile>();
	
	public mailServer(String ID,Set<emailProfile> data) {
		server_ID = ID;
		server_data.addAll(data);
	}
	
	public String get_serverID() {
		return server_ID;
	}
	
	public void printData() {
		System.out.println("Server ID: "+server_ID+"\n"+server_data.toString()); 
	}
	
	public emailProfile find_user(String name, String pass) {
		emailProfile temp_prof = new emailProfile(name,pass);
		for(Iterator<emailProfile> It = server_data.iterator(); It.hasNext();){
			emailProfile current = (emailProfile)It.next();
			if (current.profiles_equal(temp_prof)) {
				return current;
			}
		}
		return null;
	}

	public List<String> find_user_password(String name) {
		/*
		List<String> result = new ArrayList<String>();
		for (emailProfile current : server_data) {
			if (current.get_username().equals(name)) {
				result.add(current.get_password());
			}
		}return result;
		*/
		
		List<String> result = new ArrayList<String>();
		for(Iterator<emailProfile> It = server_data.iterator(); It.hasNext();){
			emailProfile current = (emailProfile)It.next();
			if (current.get_username().equals(name)) {
				result.add(current.get_password());
			}
		}
		return result;
	}

	public static void main(String[] args) {
		profile_creation profiles = new profile_creation("users.txt");
		mailServer server = new mailServer("123.123.12.1.", profiles.loadData());
		System.out.println(server.find_user("U2345678", "pass1").toString());
		System.out.println(server.find_user_password("U2345678").toString());
	}

}
