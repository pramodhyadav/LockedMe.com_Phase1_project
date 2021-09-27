package Locker;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDtls {
	static File f = new File("c:\\VK\\User.db.txt");
	static String uname = " ";
	static String utype = "";
	static List<UserDtls> ArrUserDtls = new ArrayList<UserDtls>();

	public void createFile(List<UserDtls> uname, String name) {
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream("c:\\\\VK\\\\User.db.txt");

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (UserDtls ud : uname) {
				try {
					// method to serialize object
					out.writeObject(ud);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
			File usrFile = new File("c:\\VK\\" + name + ".txt");
			usrFile.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeUserFile(List<UserDtls> uname) {
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream("c:\\\\VK\\\\User.db.txt");

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (UserDtls ud : uname) {
				try {
					// method to serialize object
					out.writeObject(ud);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeAppSrl(List<AppDtls> appName,File appf) {
		// 1. read a file
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream(appf.getAbsoluteFile());

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (AppDtls ud : appName) {
				try {
					// method to serialize object
					out.writeObject(ud);
					System.out.println(ud.getAppName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<AppDtls> readAppSrl(File filename) throws IOException {
		// 1. read a file
		ObjectInputStream in =null;
		AppDtls appDtls;
		List<AppDtls> appdtls = new ArrayList<AppDtls>();
		try {
			 in = new ObjectInputStream(new FileInputStream(filename));
			System.out.println("App here");
			// 3. method to de-serialized object
			while (true) {
				
				appDtls = (AppDtls) in.readObject();
				appdtls.add( appDtls);	
				System.out.println("appDtls"+appDtls.getAppName());
			}

		} catch (EOFException exc) {
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appdtls;
	}
	public List<UserDtls> readFileSrl(File filename) {
		
		UserDtls usrd;
		ArrUserDtls = new ArrayList<UserDtls>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			
			while (true) {
				
				usrd = (UserDtls) in.readObject();
				ArrUserDtls.add( usrd);
				
			}
			

		} catch (EOFException exc) {
			System.out.println("");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ArrUserDtls;
	}

	public  boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}