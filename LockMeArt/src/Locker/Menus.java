package Locker;


public class Menus {
	
	
	
	public void mainMenu(){
		System.out.println("Welcome Back !!");
		System.out.println("Please enter the sl.no for action");
		System.out.println("1. Login ");//done
		System.out.println("2. Register ");
	}

	public void logMenu(String username) {
		System.out.println("Welcome "+username);
		System.out.println("Please enter the srl no for action ");
		System.out.println("1. To View All App Details ");//done
		System.out.println("2. Add New APP Details ");//done
		System.out.println("3. Change An APP Detail ");//done
		System.out.println("4. Delete An APP Detail");//done
		System.out.println("5. Change To Another User Detail");//done
		System.out.println("0. To Exit");//done
		
	}
	public void logAdminMenu(String username) {
		System.out.println("Welcome "+username);
		System.out.println("Please enter the srl no for action ");
		System.out.println("1. To View All User Details ");//Done
		System.out.println("2. Add New User Details ");//Done
		System.out.println("3. Edit An User Detail ");
		System.out.println("4. Delete An User Detail");
		System.out.println("5. Change To Another User Detail");//Done
		System.out.println("0. To Exit");//done
		
	}
}