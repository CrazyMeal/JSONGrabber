package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParkingParser {
	private URL urlToParse;
	private StringBuffer extractedDatas;
	
	public ParkingParser(){
		this.extractedDatas = new StringBuffer();
	}

	public void setUrlToParse(String urlToParse) {
		try {
			this.urlToParse = new URL(urlToParse);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void getDatas() {
		try {
			HttpURLConnection connection = (HttpURLConnection) this.urlToParse.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			
				connection.setRequestMethod("GET");
			
			connection.connect();
			InputStream in = connection.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			while((inputLine=reader.readLine()) != null){
				extractedDatas.append(inputLine);
				//System.out.println(inputLine);
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parse() {
		String datas = this.extractedDatas.toString();
		datas = "["+datas+"]";
		JSONParser parser=new JSONParser();
		
		Object obj;
		try {
			obj = parser.parse(datas);
			JSONArray array=(JSONArray)obj;
			System.out.println("======the 2nd element of array======");
			System.out.println(array.get(0));
			
			JSONObject obj2=(JSONObject)array.get(0);
			System.out.println("======field \"1\"==========");
			System.out.println(obj2.get("datatime"));  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
