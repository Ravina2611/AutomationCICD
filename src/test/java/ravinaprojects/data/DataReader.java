package ravinaprojects.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		
		//read json to string 
		//System.out.println("File path: " + System.getProperty("user.dir") + "//src//test//java//ravinaprojects//data//PurchaseOrder.json");
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//ravinaprojects//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//convert String to hashmap  Jackson Databind (Jackson databind dependency heps you to convert string content into hashmap

		
		ObjectMapper mapper = new ObjectMapper();
		
		//readValue method can help to read the String value and convert it into hashmaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		{
	     
		});
		  return data;
		
		//{map, map}
		
		
		
		
		
	}
	
	
}
