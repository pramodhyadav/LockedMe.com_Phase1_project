package Locker;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppUser {

	static String uname = " ";
	static String utype = "";
	static List<AppDtls> ArrAppDtls = new ArrayList<AppDtls>();

	public String executeUser(String[] user) {
		uname = user[0];
		utype = user[1];
		File uf = new File("c:\\VK\\" + uname + ".txt");
		FileDtls fd = new FileDtls();
		Menus menu = new Menus();
		int cnt = 9;
		while (cnt == 9 && !utype.equals("Admin")) {
			menu.logMenu(uname);
			System.out.println("");
			Scanner s1 = new Scanner(System.in);
			String str = s1.nextLine();

			while ((!fd.isNumeric(str)) || (!(str.equals("1")) && !(str.equals("2")) && !(str.equals("3"))
					&& !(str.equals("4")) && !(str.equals("5")))) {
				System.out.println("Invalid input");
				System.out.println(" ");
				menu.logAdminMenu(uname);
				str = s1.nextLine();

			}

			int option = Integer.parseInt(str);
			//System.out.println("option " + option);
			List<AppDtls> ArrAppDtlsnew = new ArrayList<AppDtls>();
			switch (option) {
			case 1:
				/* List all application details */
				try {
					ArrAppDtls = fd.readAppSrl(uf);
					for (AppDtls aud : ArrAppDtls) {
						System.out.print(aud.getAppName());
						System.out.print('\t');
						System.out.print(aud.getUserId());
						System.out.print('\t');
						System.out.println(aud.getPassword());
					}
				} catch (Exception e) {

				}
				break;
			case 2:
				/* Add application details */
				System.out.println("Enter App Name");
				String appName = s1.nextLine();
				System.out.println("Enter App User Name");
				String usrName = s1.nextLine();
				System.out.println("Enter App  password");
				String pass = s1.nextLine();

				try {
					ArrAppDtls = fd.readAppSrl(uf);
					ArrAppDtls.add(new AppDtls(appName, usrName, pass));
					fd.writeAppSrl(ArrAppDtls, uf);
				}

				catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 3:
				/* Change application details */
				try {
					ArrAppDtls = fd.readAppSrl(uf);
					System.out.println("Enter the app name you want to change : ");
					String appname = s1.nextLine();
					System.out.println("Enter the new username for the app : ");
					String usrname = s1.nextLine();
					System.out.println("Enter the new password for the app : ");
					String apppass = s1.nextLine();
					for (AppDtls aud : ArrAppDtls) {
						if (appname.equals(aud.getAppName())) {
							System.out.print(aud.getAppName());
							System.out.print('\t');
							System.out.print(aud.getUserId());
							System.out.print('\t');
							System.out.println(aud.getPassword());
							ArrAppDtlsnew.add(new AppDtls(appname, usrname, apppass));
						} else {
							ArrAppDtlsnew.add(new AppDtls(aud.getAppName(), aud.getUserId(), aud.getPassword()));
						}
					}
					fd.writeAppSrl(ArrAppDtlsnew, uf);
				} catch (Exception e) {

				}
				break;
			case 4:
				/* Delete application details */

				try {
					ArrAppDtls = fd.readAppSrl(uf);
					System.out.println("Enter the app name you want to delete : ");
					String appname = s1.nextLine();
					for (AppDtls aud : ArrAppDtls) {
						if (appname.equals(aud.getAppName())) {
							System.out.print(aud.getAppName());
							System.out.print('\t');
							System.out.print(aud.getUserId());
							System.out.print('\t');
							System.out.println(aud.getPassword());
						} else {
							ArrAppDtlsnew.add(new AppDtls(aud.getAppName(), aud.getUserId(), aud.getPassword()));
						}
						fd.writeAppSrl(ArrAppDtlsnew, uf);
					}

				} catch (Exception e) {

				}
				break;
			case 5:
				/* Change user login */
				AdminUser usr = new AdminUser();
				usr.login();
				if (utype.equals("Admin")) {
					System.out.println("Admin :");
					FileDtls fd1 = new FileDtls();
					fd1.readFileSrl(uf);
					return utype;
				}
				break;
			case 0:
				return utype;
			}

			System.out.println("Press 9 to Continue OR 0 to Exit");
			str = s1.nextLine();

			while (!(fd.isNumeric(str)) || (!str.equals("9") && !str.equals("0"))) {
				System.out.println("Press 9 to Continue OR 0 to Exit");

				System.out.println(str);
				str = s1.nextLine();

			}
			cnt = Integer.parseInt(str);
		}
		return utype;
	}
}