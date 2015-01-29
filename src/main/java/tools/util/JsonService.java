package tools.util;


import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map.Entry;

public class JsonService {

	public static JSONObject getJsonFile(HashMap<String, String> fieldValues){
		JSONObject jsonObj = new JSONObject();
		for (Entry<String, String> fieldValue : fieldValues.entrySet()) {
			jsonObj.put(fieldValue.getKey(), fieldValue.getValue());
		}
        return jsonObj;
	}
	
//	public static void getGsonFile(HashMap<String, String> fieldValues){
//		new GsonBuilder().disableHtmlEscaping().;
//		
////		JSONObject jsonObj = new JSONObject();
////		for (Entry<String, String> fieldValue : fieldValues.entrySet()) {
////			jsonObj.put(fieldValue.getKey(), fieldValue.getValue());
////		}
////        return jsonObj;
//	}
}
