import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.security.MessageDigest;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

public class GenPass {
	public String id;
	public String password;
	public String date;

	public GenPass(String n, String p, String d) {
		id = n;
		password = p;
		date = d;
	}
	public void hashPassword(String pass) {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		try{
			PrintWriter writer = new PrintWriter("password.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			  e.printStackTrace();
			  System.err.println("Error while writing to output file");
			}
			catch (UnsupportedEncodingException e2) {
	           e2.printStackTrace();
	           System.err.println("Error while writing to output file");
	       }
	    writer.println();
	}


	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter user name: ");
		String name = reader.readLine();
		System.out.println("Please enter password: ");
		String pw = reader.readLine();
		Date date = new Date(); 
		String string_date = date.toString();
		GenPass first_user = new GenPass(name, pw, string_date);
		first_user.hashPassword(pw);
		
	}
}
