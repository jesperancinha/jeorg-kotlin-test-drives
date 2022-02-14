package com.steelzack.testing.jsonparser;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserJSON {

	private Map<String, int[]> colorMap = null;

	public ParserJSON(String fileName) {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;

			Iterator<?> i = jsonObject.entrySet().iterator();

			Map<String, int[]> colorMap = new HashMap<String, int[]>();
			while (i.hasNext()) {
				Entry<?, ?> colorObject = (Entry<?, ?>) i.next();
				Color color = Color.decode(colorObject.getValue().toString());
				colorMap.put(
						colorObject.getKey().toString(),
						new int[] { color.getRed(), color.getGreen(),
								color.getBlue() });
			}

			this.colorMap = colorMap;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParserJSON obj = new ParserJSON("c:\\colours.json");
		System.out.println(obj.getColour("white", 0.75f));
		System.out.println(obj.toJSON(0.75f));
	}

	public String getColour(String name, float brightness) {
		int[] rgbValues = this.colorMap.get(name);
		float[] hsbVals = Color.RGBtoHSB(rgbValues[0], rgbValues[1],
				rgbValues[2], null);
		Color changedColor = Color.getHSBColor(hsbVals[0], hsbVals[1],
				brightness * hsbVals[2]);
		this.colorMap.put(name,
				new int[] { changedColor.getRed(), changedColor.getGreen(),
						changedColor.getBlue() });
		
		String hexString = Integer.toHexString(changedColor.getRGB() & 0x00ffffff);
		String zeroes = "000000";
		hexString = zeroes.substring(hexString.length()).concat(hexString);
		return "#"
				.concat(hexString);
	}

	@SuppressWarnings("unchecked")
	public String toJSON(float brightness) {
		Iterator<Entry<String, int[]>> i = this.colorMap.entrySet().iterator();
		JSONObject jsonObject = new JSONObject();
		HashMap<String, int[]> newColorMap = new HashMap<String, int[]>();
		
		do {
			Entry<String, int[]> object = (Entry<String, int[]>) i.next();
			newColorMap.put(object.getKey(), object.getValue());
		} while (i.hasNext());

		i = newColorMap.entrySet().iterator();

		do {
			Entry<String, int[]> object = (Entry<String, int[]>) i.next();
			String hexString = getColour(object.getKey(), brightness);
			jsonObject.put(object.getKey(), hexString);
		} while (i.hasNext());

		return jsonObject.toJSONString();
	}
}
