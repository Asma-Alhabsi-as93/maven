import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		 String jsonUrl = "http://universities.hipolabs.com/search?country=United+States";
		        HttpClient client = HttpClient.newHttpClient();
		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(jsonUrl))
		                .build();
		        HttpResponse<String> response = client.send(request,
		                HttpResponse.BodyHandlers.ofString());
		        System.out.println(response.body());
		        Gson gson = new Gson();
		        API api=new API();
//		        api.setId(0);
		        api.setWeb_pages("http://www.marywood.edu");
		        api.setState_province( null);
		        api.setAlpha_two_code("US");
		        api.setName("Marywood University");
		      
		     // Generating json from emp object
		        String apiJson = gson.toJson(api);
		        System.out.println("API json is " + apiJson);
		        
		     // Generating emp object from emp json
		        API apiGenerated = gson.fromJson(
		            gson.toJson(api), API.class);
		        
		     // Print and display the employee been generated
		        System.out.println(
		            "Generated API from json is "
		            + apiGenerated);
		    }
}