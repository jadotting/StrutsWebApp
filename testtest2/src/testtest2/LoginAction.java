//TODO: MODEL

package testtest2;

import java.sql.*;

public class LoginAction {
	private String username;
	
	private String password;
	
	public String execute() {
		//exception handling
		if (username.isEmpty() || password.isEmpty()) {
			return "FAILURE";
		}
		else {
			//get username and password from database
			boolean available = readData(username,password);
			
			//if username and password in database then move to successful login page
			if (available) {
				return "SUCCESS";
			}
			//else move back to start page (refresh)
			else {
				return "FAILURE";
			}
		}
	}

	//setter and getters for variables to be assessed elsewhere if needed
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean readData(String username, String password) {
		//query database
		try {
			//credentials setting
			Class.forName("com.mysql.cj.jdbc.Driver");
			//parameters (which host, username of client in MySQL, password of client in MySQL)
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfodb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","myuser","xxxx");
			Statement stmt = con.createStatement();
			
			//sql query
			String sql="Select * from userinfo where username='"+username+"' and password='"+password+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			//get response 
			while (rs.next()) {
				//if response username and password is same as entered -> true 
				if (rs.getString("username").compareTo(username) == 0 && rs.getString("password").compareTo(password) == 0) {
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}

