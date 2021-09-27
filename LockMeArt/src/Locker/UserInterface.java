package Locker;


import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub1
		
		AdminUser adUsr = new AdminUser();
		String user[];
		
		if (adUsr.login()) {
			user = adUsr.ExecuteAdmin();
			if (user[1].equals("User")) {
				AppUser appUsr = new AppUser();
				appUsr.executeUser(user);
			} 
		}

		System.out.println("Thank you!!!");
	}

}