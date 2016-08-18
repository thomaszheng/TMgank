package com.thomas.tmgank.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.thomas.tmgank.model.Collection;
import com.thomas.tmgank.model.DailyGank;
import com.thomas.tmgank.model.Gank;
import com.thomas.tmgank.model.ResultDaily;

public class JsonKit {

	/**
	 * 把一个map变成json字符串
	 * @param map
	 * @return
	 */
	public static String parseMapToJson(Map<?, ?> map) {
		try {
			Gson gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把一个json字符串变成对象
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
		}
		return t;
	}

	/**
	 * 把json字符串变成map
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, Object>>() {
		}.getType();
		HashMap<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 把json字符串变成集合
	 * params: new TypeToken<List<yourbean>>(){}.getType(),
	 * 
	 * @param json
	 * @param type  new TypeToken<List<yourbean>>(){}.getType()
	 * @return
	 */
	public static List<?> parseJsonToList(String json, Type type) {
		Gson gson = new Gson();
		List<?> list = gson.fromJson(json, type);
		return list;
	}

	/**
	 * 
	 * 获取json串中某个字段的值，注意，只能获取同一层级的value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getFieldValue(String json, String key) {
		if (TextUtils.isEmpty(json))
			return null;
		if (!json.contains(key))
			return "";
		JSONObject jsonObject = null;
		String value = null;
		try {
			jsonObject = new JSONObject(json);
			value = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static ResultDaily generate(String json){
		Gson gson=new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject root = element.getAsJsonObject();
		JsonArray category = root.getAsJsonArray("category");

		Iterator<JsonElement> iterator = category.iterator();

		JsonObject results = root.getAsJsonObject("results");
		boolean error = root.get("error").getAsBoolean();

		ArrayList<String> types = new ArrayList<>(category.size());
		List<List<Gank>> ghs = new ArrayList<>(category.size());
		while (iterator.hasNext()) {
			JsonElement type = iterator.next();
			String t = type.getAsString();
			types.add(t);
			String e = results.get(t).toString();
			List<Gank> g=gson.fromJson(e,new TypeToken<List<Gank>>(){}.getType() );

			ghs.add(g);
		}

		Collections.sort(types);
		Collections.sort(ghs, new Comparator<List<Gank>>() {
			@Override
			public int compare(List<Gank> lhs, List<Gank> rhs) {
				return lhs.get(0).type.compareTo(rhs.get(0).type);
			}
		});
		return  new ResultDaily(error, new DailyGank(types, ghs));
	}

	public static List<Gank> generateList(String json){
		Gson gson=new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject root = element.getAsJsonObject();
		JsonArray category = root.getAsJsonArray("results");
		List<Gank> g=gson.fromJson(category, new TypeToken<List<Gank>>() {
		}.getType());

		return g;
	}

}
