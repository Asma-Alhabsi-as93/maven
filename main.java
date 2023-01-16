import java.beans.Statement;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.jar.Attributes;

//import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class main {
	public static void insertGson() throws IOException, InterruptedException{
	String url = "jdbc:mysql://localhost:3306/gson";
	String username = "root";
	String password = "root";
	 String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(jsonUrl))
	                .build();
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//	        System.out.println(response.body());
	        Gson gson = new Gson();
//	        API api=new API();
////	        api.setId(0);
//	        api.setWeb_pages("http://www.marywood.edu");
//	        api.setState_province( null);
//	        api.setAlpha_two_code("US");
//	        api.setName("Marywood University");
	      
	     // Generating json from emp object
//	        String apiJson = gson.toJson(api);
//	        API[] names = gson.fromJson(response.body(), API[].class);
	        
	        API[] apiResults =gson.fromJson(response.body(), API[].class);
	        for(API x: apiResults) {
	        	System.out.println("**********************************");
	        	 System.out.print("ID: " + x.getId());
	        	System.out.println("Web_Pages is"+x.getWeb_pages()[0]);
	        	System.out.println("state-province is"+x.getState_province());
	        	System.out.println("alpha_two_code is"+x.getAlpha_two_code());
	        	System.out.println("name is"+x.getName());
	        	System.out.println("country is"+x.getCountry());
	        	System.out.println("domain is"+x.getDomains()[0]);
	        	System.out.println("**********************************");
	        }
	        
	        
	        for(API x: apiResults) {
	        String sqlInsert = "insert into gson(web_pages,state_province, alpha_two_code,name, country,domains)"
			        + " values('" + x.getWeb_pages()[0] + "' ,'" + x.getState_province() + "', '" + x.getAlpha_two_code() + "','" + x.getName() + "' ,' "
			        + x.getCountry() + "','" + x.getDomains()[0] + "')";
			        // Connection class object
			        Connection ccon = null;
			        // Try block to check for exceptions
			        try {
			        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			        DriverManager.registerDriver(driver);
			        ccon = DriverManager.getConnection(url, username, password);
			        // Creating a statement
			        java.sql.Statement st = ccon.createStatement();
					
			        // Executing query
			        int s = st.executeUpdate(sqlInsert);
			        if (s >= 1)
			        System.out.println("inserted successfully : " + sqlInsert);
			        else
			        System.out.println("insertion failed");
			        // Closing the connections
			        ccon.close();
			        }
			        // Catch block to handle exceptions
			        catch (Exception ex) {
			        // Display message when exceptions occurs
			        System.err.println(ex);
			        }
	        }
	}
	      
	    
	    
	public static void readFromTable(int reed){
		String url = "jdbc:mysql://localhost:3306/gson";
		String username = "root";
		String password = "root";
		 Scanner sc = new Scanner(System.in);
		 System.out.println(" how many users you have to print");
		 String SQL="SELECT * FROM gson ORDER BY id LIMIT "+reed+"";
			Connection conn = null;
			try {
				Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
				DriverManager.registerDriver(driver);
				conn = DriverManager.getConnection(url,username, password);
				Statement st = (Statement) conn.createStatement();
				ResultSet m = ((java.sql.Statement) st).executeQuery(SQL);
				 while(m.next()){
			            //Display values
					 System.out.println("id"+m.getInt("id"));
					 System.out.println("Web_Pages"+m.getString("Web_Pages"));
					 System.out.println("state-province"+m.getString("state-province"));
					 System.out.println("alpha_two_code"+m.getString("alpha_two_code"));
					 System.out.println("name"+m.getString("name"));
					 System.out.println("country"+m.getString("country"));
					 System.out.println("domain"+m.getString("domain"));
		
				 }
				conn.close();
			}
			catch (Exception ex) {
				System.err.println(ex);
			}
}
	public static void printHotel(int top) {
		String url = "jdbc:mysql://localhost:3306/gson";
		String user = "root";
		String pass = "root";
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);
			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement st = (Statement) con.createStatement();
			Scanner scanner = new Scanner(System.in);
			int count = 1;
			String sql="SELECT * FROM gson ORDER BY id LIMIT "+top;
			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);
			while (rs.next() && count <= top) {
				int id = rs.getInt("id");

				String Web_Pages = rs.getString("Web_Pages");
				String state_province = rs.getString("state-province");
				String alpha_two_code = rs.getString("alpha_two_code");
				String name = rs.getString("name");
				String country = rs.getString("country");
				String domain = rs.getString("domain");
				System.out.println(id + " " + Web_Pages + " " + state_province + " " + alpha_two_code + " " + name
						+ " " + country+" "+domain);
				count++;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	public static void updateById() {
		String url = "jdbc:mysql://localhost:3306/gson";
		String user = "root";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);

		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			// Reference to connection interface
			conn = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement st = (Statement) conn.createStatement();

			System.out.println("Please Enter any id to Update gson data :");
			int userinput = scanner.nextInt();
			System.out.println("Please Enter the new gson Web_Pages:");
			String Web_Pages = scanner.next();
			System.out.println("Please Enter the new gson state_province :");
			String state_province = scanner.next();

			String sql = "UPDATE gson SET Web_Pages='" + Web_Pages + "',state_province='" + state_province
					+ "' WHERE id='" + userinput + "'";
			int result = ((java.sql.Statement) st).executeUpdate(sql);
		} catch (Exception ex) {
			System.err.println(ex);

		}

	}
	
	public static void deleteById() {
		String url = "jdbc:mysql://localhost:3306/gson";
		String user = "root";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);

		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			// Reference to connection interface
			conn = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement st = (Statement) conn.createStatement();
			int inputid=scanner.nextInt();
			String sql = "select * from gson where id='" + inputid + "'";
			
			int rs = ((java.sql.Statement) st).executeUpdate(sql);

		      // execute the preparedstatement
		      
		      conn.close();
		   
	} catch (Exception ex) {
		System.err.println(ex);
	}
}
	public static void main(String[] args) throws IOException, InterruptedException {
		
		  Scanner sc = new Scanner(System.in);
			boolean hasExit = true;
			do {
				System.out.println("hello");
				System.out.println("*****************");
				System.out.println("1:insert gson ");
				System.out.println("2:reed ");
				System.out.println("3:print ");
				System.out.println("4:ubdate");
				System.out.println("5:delete");
				
				int select = sc.nextInt();

				switch (select) {
		
				case 1:
					insertGson();
					break;
					
				case 2:
					
					System.out.println(" how many gson you want reed");
					int num = sc.nextInt();
					readFromTable(num);
					break;
					
				case 3:
					System.out.println(" how many gson you want print");
					int num1 = sc.nextInt();
					printHotel(num1);
					break;
					
				case 4:
					updateById();
					break;
					
				case 5:
					deleteById();
					break;
	

				}			
	} while (hasExit);

	}
}
	