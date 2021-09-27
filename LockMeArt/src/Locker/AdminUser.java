package Locker;


import java.io.*;
import java.nio.*;
import java.util.*;

public class AdminUser {
	static File f = new File("c:\\VK\\User.db.txt");
	static String uname = " ";
	static String utype = "";
	static List<UserDtls> ArrUserDtls = new ArrayList<UserDtls>();

	public String[] ExecuteAdmin() {

		Menus menu = new Menus();
		FileDtls fd = new FileDtls();
		String[] user = { uname, utype };
		String str;
		if (utype.equals("User")) {
			
			user[0] = uname;
			user[1] = utype;
			return user;
		}
		int cnt = 9;
		System.out.println("utype"+utype);
		while (cnt == 9 && utype.equals("Admin")) {
			menu.logAdminMenu(uname);
			System.out.println("");
			Scanner s1 = new Scanner(System.in);

			str = s1.nextLine();

			while ((!fd.isNumeric(str)) || (!(str.equals("1")) && !(str.equals("0"))&& !(str.equals("2"))&& !(str.equals("3"))&& !(str.equals("4"))&& !(str.equals("5")))) {
				System.out.println("Invalid input");
				System.out.println(" ");
				menu.logAdminMenu(uname);
				str = s1.nextLine();

			}
			
			int option = Integer.parseInt(str);
			//System.out.println("option "+option);
			List<UserDtls> ArrUserDtlsnew = new ArrayList<UserDtls>();
			switch (option) {
			case 1:
				/*List all users*/
				ArrUserDtls = fd.readFileSrl(f);
				for (UserDtls rud : ArrUserDtls) {
					System.out.print(rud.getUserId());
					System.out.print('\t');
					System.out.print(rud.getPassword());
					System.out.print('\t');
					System.out.println(rud.getUserType());
				}
				break;
			case 2:
				/*create new user*/
				System.out.println("Enter UserName");
				String usrName = s1.nextLine();
				System.out.println("Enter the new user's password");
				String pass = s1.nextLine();
				ArrUserDtls.add(new UserDtls(usrName, pass, "User"));
				fd.createFile(ArrUserDtls, usrName);
				break;
				case 3:
					/* Change application details */
					try {
						ArrUserDtls = fd.readFileSrl(f);
						System.out.println("Enter the user name you want to change : ");
						String usrname = s1.nextLine();
						System.out.println("Enter the new password for the : ");
						String changepass = s1.nextLine();
						for (UserDtls ud : ArrUserDtls) {
							if (usrname.equals(ud.getUserId())) {
								System.out.print(ud.getUserType());
								System.out.print('\t');
								System.out.print(ud.getUserId());
								System.out.print('\t');
								System.out.println(ud.getPassword());
								ArrUserDtlsnew.add(new UserDtls(usrname,changepass,ud.getUserType()));
							} else {
								ArrUserDtlsnew.add(new UserDtls( ud.getUserId(), ud.getPassword(),ud.getUserType()));
							}
						}
						fd.writeUserFile(ArrUserDtlsnew);
					} catch (Exception e) {

					}
					break;
				case 4:
					/* Delete application details */
					
					try {
						ArrUserDtls = fd.readFileSrl(f);
						System.out.println("Enter the user name you want to Delete : ");
						String usrname = s1.nextLine();
						File usrFile = new File("c:\\VK\\" + usrname + ".txt");
						
						for (UserDtls ud : ArrUserDtls) {
							if (usrname.equals(ud.getUserId())) {
								System.out.print(ud.getUserType());
								System.out.print('\t');
								System.out.print(ud.getUserId());
								System.out.print('\t');
								System.out.println(ud.getPassword());
								
								if(!usrFile.delete()) {
									usrFile.deleteOnExit();
								}

							} else {
								ArrUserDtlsnew.add(new UserDtls( ud.getUserId(), ud.getPassword(),ud.getUserType()));
							}
						}
						fd.writeUserFile(ArrUserDtlsnew);
						
					} catch (Exception e) {

					}
					break;
			case 5:
				/*change user login*/
				AdminUser usr = new AdminUser();
				usr.login();
				if (utype.equals("User")) {
					System.out.println("USer :");
					user[0] = uname;
					user[1] = utype;
					return user;
				}
				break;
			case 0:
				return user;
				
			}

			System.out.println("Press 9 to Continue OR 0 to Exit");
			str = s1.nextLine();

			while (!(fd.isNumeric(str)) ||( !str.equals("9") && !str.equals("0"))) {
				System.out.println("Press 9 to Continue OR 0 to Exit");

				System.out.println(str);
				str = s1.nextLine();

			}
			cnt = Integer.parseInt(str);

		}
		return user;
	}

	public boolean login()

	{
		AdminUser user = new AdminUser();
		FileDtls fd = new FileDtls();
		Scanner s = new Scanner(System.in);
		boolean first = user.vKLoad();
		if (!first) {
			Menus main = new Menus();
			main.mainMenu();
			String str = s.nextLine();
			
			while ((!fd.isNumeric(str)) || (!(str.equals("1")) && !(str.equals("2")))) {
				System.out.println("Invalid input");
				System.out.println(" ");
				main.mainMenu();
				str = s.nextLine();

			}
			
			int option = Integer.parseInt(str);
			System.out.println("option "+option);
			
			switch (option) {
			case 1:
				try {

					System.out.println("Login ");
					System.out.println("-------------- ");
					System.out.println("Enter a user name");
					String userName = s.nextLine();
					uname = userName;
					ArrUserDtls = fd.readFileSrl(f);
					for (UserDtls ud :ArrUserDtls ) {
						System.out.println(ud.getUserId());
					}
					if (user.verifyUser(userName)) {
						System.out.println("Enter a Password");
						String password = s.nextLine();
						if (user.verifyPassword(password)) {
							return true;
						} else {
							System.out.println("Invalid password");
							return false;
						}

					} else {
						System.out.println("Invalid user");
						return false;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter UserName");
				String usrName = s.nextLine();
				System.out.println("Enter the new user's password");
				String pass = s.nextLine();
				ArrUserDtls=fd.readFileSrl(f);
				ArrUserDtls.add(new UserDtls(usrName, pass, "User"));
				fd.createFile(ArrUserDtls, usrName);
				uname=usrName;
				utype="User";
				break;
			}

		} else {
			user.ExecuteAdmin();
			return true;
		}
		return true;
	}

	public boolean verifyUser(String userName) {

		for (UserDtls ud : ArrUserDtls) {
			if (userName.equals(ud.getUserId())) {
				return true;
			}

		}
		return false;

	}

	public boolean verifyPassword(String password) {

		for (UserDtls ud : ArrUserDtls) {

			if (uname.equals(ud.getUserId())&&password.equals(ud.getPassword())) {
				utype = ud.getUserType();
				return true;
			}

		}
		return false;

	}

	public boolean vKLoad() {

		if (!f.exists()) {

			System.out.println("*************Welcome To LockedMe.com !!*********************");
			System.out.println("*************    Lockers Pvt. Ltd  !!     *********************");
			
			System.out.println("************* This is your first login  !!*********************");
			System.out.println("");
			System.out.println("An Administrator with user name as : Admin : will also be created");
			System.out.println("This user will have ability to create/modify/delete other users");
			System.out.println("");
			System.out.println("");
			System.out.println("  ");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter an user name");
			String userName = s.nextLine();
			uname = userName;
			System.out.println("Enter a  Password");
			String password = s.nextLine();
			File f = new File("c:\\VK\\");
			try {
				f.mkdir();
				f = new File("c:\\\\VK\\\\User.db.txt");
				f.createNewFile();
				
				UserDtls first = new UserDtls("Admin", password, "Admin");
				try {
					// create file output stream.
					FileOutputStream file = new FileOutputStream(f.getAbsoluteFile());

					// create object stream
					ObjectOutputStream out = new ObjectOutputStream(file);

					// method to serialize object
					out.writeObject(first);
					 first = new UserDtls(userName, password, "User");
					 out.writeObject(first);
					utype = "User";
					uname=userName;
					File usrFile = new File("c:\\VK\\" + userName + ".txt");
					usrFile.createNewFile();
					out.close();
					file.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				System.out.println("Setup complete !!");
				return true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

}