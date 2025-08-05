package DDT;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class toReadDataFromJson {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader fir=new  FileReader(".\\src\\test\\resources\\commondata1.json");
	    JSONParser parser= new JSONParser();
	    
	    //convert physical file in to java object
	   Object javaobj = parser.parse(fir);
	   //typecasting
	   JSONObject obj = (JSONObject)javaobj;
	   //reading data
	   String Browser = obj.get("Browser").toString();
	   System.out.println(Browser);
	}

}
