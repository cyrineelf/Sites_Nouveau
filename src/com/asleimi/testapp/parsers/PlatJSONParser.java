package com.asleimi.testapp.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.asleimi.testapp.model.Plat;

public class PlatJSONParser {
	
public static List<Plat> parseFeed(String content) {
		
		try {
			JSONArray ar = new JSONArray(content);
			List<Plat> platList = new ArrayList<>();
			
			for (int i = 0; i < ar.length(); i++) {
				
				JSONObject obj = ar.getJSONObject(i);
				Plat plat = new Plat(i, null, null, null, null);
				
				plat.setIdPlat(obj.getInt("idPlat"));
				plat.setNomPlat(obj.getString("nomPlat"));
				plat.setPhotoPlat(obj.getString("photoPlat"));
				plat.setIngredientPlat(obj.getString("ingredientPlat"));
				plat.setEtapePlat(obj.getString("etapePlat"));
				
				
				
			platList.add(plat);
			}
			
			return platList;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
