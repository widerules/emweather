/*  This file is part of EmWeather.

    EmWeather is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    EmWeather is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with EmWeather.  If not, see <http://www.gnu.org/licenses/>.

	Copyright 2009 Emilie Roberts
*/

package com.android.EmWeather;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;



public class EmXmlCityCodeReader extends DefaultHandler {

	private final String SITELIST_URL = "http://dd.weatheroffice.ec.gc.ca/citypage_weather/xml/siteList.xml";
	
	private boolean in_site_list = false;
	private boolean in_site = false;
	private boolean in_name_en = false;
	private boolean in_name_fr = false;
	private boolean in_province_code = false;

	private List<EmCity> city_list = new LinkedList<EmCity>();
	private EmCity temp_city = new EmCity();
	
	public EmXmlCityCodeReader() {
		super();
	}


	public void startElement(String uri, String name, String qName, Attributes atts) throws SAXException {
//		Log.d("EmWeather start", name);
		
		if (name.equals("siteList"))
			in_site_list = true;
		
		if (name.equals("site")) {
//			Log.d("EmWeather start city", atts.getValue("code"));
			in_site = true;
			temp_city = new EmCity();
			temp_city.code = atts.getValue("code");
		}
		
		if (name.equals("nameEn"))
			in_name_en = true;
		if (name.equals("nameFr"))
			in_name_fr = true;
		if (name.equals("provinceCode"))
			in_province_code = true;
		
	}

	public void endElement(String uri, String name, String qName) throws SAXException {
//		Log.d("EmWeather end", name);
		
		if (name.equals("siteList"))
			in_site_list = false;

		if (name.equals("site")) {
//			Log.d("EmWeather end citcy", name);
			in_site = false;
			if (temp_city.iscomplete()) {
				city_list.add(temp_city);
			}
		}
		
		if (name.equals("nameEn"))
			in_name_en = false;
		if (name.equals("nameFr"))
			in_name_fr = false;
		if (name.equals("provinceCode"))
			in_province_code = false;		
	}

	public void characters(char ch[], int start, int length) {
		String text = (new String(ch).substring(start, start + length));
//		Log.d("EmWeather Text " + start + " " + length + " ", text.trim());

		if (in_site_list) { //if we're not in a site list, ignore it.

			if(in_site) {
			}	
			
			if(in_name_en) {
				temp_city.name_en = text.trim();
			}	

			if(in_name_fr) {
				temp_city.name_fr = text.trim();
			}	

			if(in_province_code) {
				temp_city.province_code = text.trim();
			}	

		}
	}

	
	public void updateCityList() {		

		try {
			URL url = new URL(SITELIST_URL);
			
			SAXParserFactory spf = SAXParserFactory.newInstance();		
			spf.setNamespaceAware(true);

			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(this);

			InputSource is = new InputSource();
			is.setEncoding("ISO-8859-1");
			is.setByteStream(url.openStream());
			xr.parse(is);
		
		} catch (IOException e) {
			Log.e("EmWeather updateCityList", e.toString());
		} catch (SAXException e) {
			Log.e("EmWeather updateCityList", e.toString());
		} catch (ParserConfigurationException e) {
			Log.e("EmWeather updateCityList", e.toString());
		}
	}
	
	public List<EmCity> getCityList(boolean sort) {
		if (sort) {
			Collections.sort(city_list, new EmCity());
		}
		
		return city_list;
	}

	public List<String> GetCityNameList(boolean sort) {
		if (sort)
			Collections.sort(city_list, new EmCity());

		List<String> city_string_list = new LinkedList<String>();
		
		int list_size = city_list.size();
		for (int n = 0; n < list_size; n++) {
			city_string_list.add(city_list.get(n).name_en);
		}
		
		return city_string_list;
	}
	
	public List<String> GetProvList(boolean sort) {
		List<String> prov_list =  new LinkedList<String>();
		int list_size = city_list.size();
		EmCity city = null;
		
		for (int n = 0; n < list_size; n++) {	
			city = city_list.get(n);
//	    	if (n < 40)
//		    	Log.d("ProvList ", list_size + " " + n + " thinking " + city.province_code + " " + city.name_en);
			if (!prov_list.contains(city.province_code)) {
		    	prov_list.add(city.province_code);
//		    	if (n < 40)
	//		    	Log.d("ProvList ", list_size + " " + n + " YES adding " + city.province_code);
		    } else {
//		    	Log.d("ProvList ", "not adding " + city.province_code);
		    }
		}
		
		if (sort)
			Collections.sort(prov_list);
	
//		Log.d("SORTED Prov List now size...", "size: " + prov_list.size());
		return prov_list;
	}
	
	
	public List<String> GetCityNameListByProv(String prov, boolean sort) {

		List<String> city_string_list = new LinkedList<String>();
		
		int list_size = city_list.size();
		for (int n = 0; n < list_size; n++) {
			if (city_list.get(n).province_code.equals(prov))
				city_string_list.add(city_list.get(n).name_en);
		}

		if (sort)
			Collections.sort(city_string_list);
		
		return city_string_list;		
		
	}
	
	public EmCity GetCityByName(String city_name) {
		int list_size = city_list.size();
		for (int n = 0; n < list_size; n++) {
			if (city_list.get(n).name_en.equals(city_name))
				return city_list.get(n);
		}
		return null;
	}
	
	public String GetSiteCodeByName(String city_name) {
		EmCity city = GetCityByName(city_name);
		if (city != null)
			return city.code;
		else
			return null;
	}
	
}//EmXmlCityCodeReader