package core;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Parking;

public class LocationParser extends Parser{
	
	public LocationParser(){
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
				JSONObject coordinates = (JSONObject) tmpJsonParking.get("go");
				Parking tmpParking = new Parking();
				tmpParking.setId(Integer.parseInt((String)tmpJsonParking.get("id")));
				tmpParking.mergeDatas((String) tmpJsonParking.get("ln"), (Double)coordinates.get("x"), (Double)coordinates.get("y"));
				parkingList.put(Integer.parseInt((String)tmpJsonParking.get("id")), tmpParking);
				i++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return parkingList;
	}
	
	
}
