package core;

import java.util.HashMap;

import model.Parking;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParkingParser extends Parser{
	
	public ParkingParser(){
		this.extractedDatas = new StringBuffer();
	}

	@Override
	public HashMap<Integer, Parking> parse() {
		JSONParser parser = new JSONParser();
		HashMap<Integer, Parking> parkingList = new HashMap<Integer,Parking>();
		
		String datas = this.extractedDatas.toString();

		try {
			Object obj = parser.parse(datas);
			JSONObject obj2=(JSONObject)obj;
			JSONArray parkings = (JSONArray)obj2.get("s");
			
			int i = 0;
			for(Object p : parkings){
				JSONObject tmpJsonParking = (JSONObject)parkings.get(i);
				Parking tmpParking = new Parking(Integer.parseInt((String) tmpJsonParking.get("id")), 
						Integer.parseInt((String)tmpJsonParking.get("df")), 
						Integer.parseInt((String)tmpJsonParking.get("dt")), 
						"default", 
						(String)tmpJsonParking.get("ds"));
				parkingList.put(Integer.parseInt((String)tmpJsonParking.get("id")), tmpParking);
				i++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parkingList;
	}
}
