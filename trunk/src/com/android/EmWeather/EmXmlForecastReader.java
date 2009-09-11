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
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class EmXmlForecastReader extends DefaultHandler {
	private String forecast_url;
	
	private int datetime_count = 0;
	private String record_year = "0";
	
	private boolean in_current = false;
	private boolean in_forecast_group = false;
	private boolean in_forecast = false;
	private boolean in_yesterday = false;
	private boolean in_riseset = false;
	private boolean in_almanac = false;
	private boolean in_location = false;
	private boolean in_warning = false;
	
	private boolean in_temp = false;
	private boolean in_percipitation = false;
	private boolean in_text_summary = false;
	private boolean in_high = false;
	private boolean in_low = false;
	
	//Current
	private boolean in_station = false;
	private boolean in_condition = false;
	private boolean in_temperature = false;
	private boolean in_dewpoint = false;
	private boolean in_windchill = false;
	private boolean in_humidex = false;
	private boolean in_pressure = false;
	private boolean in_visibility = false;
	private boolean in_rel_humidity = false;
	private boolean in_wind = false;
	private boolean in_speed = false;
	private boolean in_gust = false;
	private boolean in_direction = false;
	private boolean in_bearing = false;

	//Forecast
	private boolean in_abb_forecast = false;
	private boolean in_winds = false;
	private boolean in_uv = false;
	private boolean in_snow_level = false;
	private boolean in_comfort = false;
	private boolean in_frost = false;
	private boolean in_time_period = false;
	private boolean in_pop = false;
	
	
	//Riseset
	private boolean in_sunrise = false;
	private boolean in_sunset = false;
	private boolean in_hour = false;
	private boolean in_minute = false;

	//Location
	private boolean in_province = false;
	private boolean in_city = false;
	
	//Almanac
	private boolean in_record_max = false;
	private boolean in_record_min = false;
	private boolean in_normal_max = false;
	private boolean in_normal_min = false;
	private boolean in_normal_mean = false;
	private boolean in_normal_pop = false;
	private boolean in_max_rain = false;
	private boolean in_max_snow = false;
	private boolean in_max_percipitation = false;
	private boolean in_max_snow_on_ground = false;
	private boolean in_datetime = false;
	private boolean in_global_datetime = false;

	public FullForecast full_forecast; 
	private Forecast temp_forecast;
	
	public EmXmlForecastReader(String forecast_url) {
		super();
		this.forecast_url = forecast_url;
		full_forecast = new FullForecast(forecast_url);
	}


	public void startElement(String uri, String name, String qName, Attributes atts) throws SAXException {
//Log.d("XmlForecast", "start " + name);		
		if (in_current) {
//Log.d("XmlForecast", "in current");		
			if (in_wind) {
				if (name.equals("speed"))
					in_speed = true;
				if (name.equals("gust"))
					in_gust = true;
				if (name.equals("direction"))
					in_direction = true;
				if (name.equals("bearing"))
					in_bearing = true;
			}
			
			if (in_datetime) {
//Log.d("XMLForecast in datetime current", "count: " + datetime_count);
				if (2 == datetime_count) {
					if (name.equals("hour"))
						in_hour = true;
					if (name.equals("minute"))
						in_minute = true;
				}
			}
			
			
			if (name.equals("station")) {
//Log.d("XmlForecast", "in station");		
				full_forecast.extra.station_code = atts.getValue("code");
				full_forecast.extra.station_lat = atts.getValue("lat");
				full_forecast.extra.station_lon = atts.getValue("lon");
				in_station = true;				
			}
						
			if (name.equals("condition"))
				in_condition = true;

			if (name.equals("temperature"))
				in_temperature = true;

			if (name.equals("dewpoint"))
				in_dewpoint = true;

			if (name.equals("windChill"))
				in_windchill = true;

			if (name.equals("humidex"))
				in_humidex = true;

			if (name.equals("pressure")) {
				in_pressure = true;
				full_forecast.current.pressure_tendency = atts.getValue("tendency");
				full_forecast.current.pressure_change = atts.getValue("change");
			}
			
			if (name.equals("day")) {
				full_forecast.current.day = atts.getValue("name");
			}

			if (name.equals("visibility"))
				in_visibility = true;

			if (name.equals("relativeHumidity"))
				in_rel_humidity = true;

			if (name.equals("wind"))
				in_wind = true;		
			
			if (name.equals("dateTime")) {
				in_datetime = true;
				datetime_count++;
			}
			
			
		} else if (in_forecast_group) {
			if (in_forecast) {
				if (in_winds) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (in_uv) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (in_snow_level) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (in_windchill) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (in_comfort) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (in_frost) {
					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (name.equals("period"))
					in_time_period = true;

				if (name.equals("textSummary"))
					in_text_summary = true;

				if (in_abb_forecast) {
					if (name.equals("pop"))
						in_pop = true;

					if (name.equals("textSummary"))
						in_text_summary = true;
				}

				if (name.equals("temperature")) {
					if ("high".equals(atts.getValue("class"))) {
						in_high = true;
					}
					if ("low".equals(atts.getValue("class"))) {
						in_low = true;
					}
				}

				if (name.equals("relativeHumidity"))
					in_rel_humidity = true;

				if (name.equals("abbreviatedForecast"))
					in_abb_forecast = true;
				
				if (name.equals("winds"))
					in_winds = true;			

				if (name.equals("uv"))
					in_uv = true;							

				if (name.equals("snowLevel"))
					in_snow_level = true;							

				if (name.equals("comfort"))
					in_comfort = true;							

				if (name.equals("frost"))
					in_frost = true;							

				if (name.equals("windChill"))
					in_windchill = true;							
			}

			if (name.equals("forecast")) {
				in_forecast = true;
				temp_forecast = new Forecast();
			}
			
		} else if (in_yesterday) {
			if (name.equals("temperature")) {
				if ("high".equals(atts.getValue("class"))) {
					in_high = true;
				}
				if ("low".equals(atts.getValue("class"))) {
					in_low = true;
				}
			}
			
			if (name.equals("precip"))
				in_percipitation = true;

		} else if (in_riseset) {
//Log.d("XmlForecast", "IN riseSet");
			if (in_sunrise) {
				if (name.equals("hour"))
					in_hour = true;
				if (name.equals("minute"))
					in_minute = true;
			}
			
			if (in_sunset) {
				if (name.equals("hour"))
					in_hour = true;
				if (name.equals("minute"))
					in_minute = true;				
			}
			
			if (name.equals("dateTime")) {
				in_datetime = true;
				datetime_count++;
//Log.d("XmlForecast", "Datetime count " + datetime_count);
				if (2 == datetime_count) {
					in_sunrise = true;
//Log.d("XmlForecast", "IN SUNRISE");
				}
				if (4 == datetime_count) {
					in_sunset = true;
//Log.d("XmlForecast", "IN SUNSET");
				}
			}
			
		} else if (in_almanac) {
			if (name.equals("temperature")) {
				if ("extremeMax".equals(atts.getValue("class"))) {
					in_record_max = true;
					record_year = atts.getValue("year");					
				}
				if ("extremeMin".equals(atts.getValue("class"))) {
					in_record_min = true;
					record_year = atts.getValue("year");					
				}
				if ("normalMax".equals(atts.getValue("class"))) {
					in_normal_max = true;
				}
				if ("normalMin".equals(atts.getValue("class"))) {
					in_normal_min = true;
				}
				if ("normalMean".equals(atts.getValue("class"))) {
					in_normal_mean = true;
				}
			}

			if (name.equals("precipitation")) {
				if ("extremeRainfall".equals(atts.getValue("class"))) {
					in_max_rain = true;
					record_year = atts.getValue("year");					
				}
				if ("extremeSnowfall".equals(atts.getValue("class"))) {
					in_max_snow = true;
					record_year = atts.getValue("year");					
				}
				if ("extremePrecipitation".equals(atts.getValue("class"))) {
					in_max_percipitation = true;
					record_year = atts.getValue("year");					
				}
				if ("extremeSnowOnGround".equals(atts.getValue("class"))) {
					in_max_snow_on_ground = true;
					record_year = atts.getValue("year");					
				}
			}

			if (name.equals("pop"))
				in_normal_pop = true;
			
		} else if (in_location) {
//Log.d("XmlForecast", "in location start");		

			if (name.equals("province"))
				in_province = true;
			if (name.equals("name")) {
				in_city = true;
				full_forecast.sitecode = atts.getValue("code");
			}

		} else if (in_warning) {
			if (name.equals("textSummary"))
				in_text_summary = true;

		} else if (in_global_datetime) {
//Log.d("XmlForecast", "in global_datetime");		
			if (2 == datetime_count) {
				if (name.equals("textSummary"))
					in_text_summary = true;
			}
		} else { //Not current conditions or forecast.
//Log.d("XmlForecast", "in default");		
			if (name.equals("currentConditions")) {
				in_current = true;
				datetime_count = 0;			
			}
			if (name.equals("forecastGroup"))
				in_forecast_group = true;
			
			if (name.equals("yesterdayConditions"))
				in_yesterday = true;
			if (name.equals("riseSet")) {
				in_riseset = true;
				datetime_count = 0;
			}

			if (name.equals("almanac"))
				in_almanac = true;
			
			if (name.equals("location"))
				in_location = true;

			if (name.equals("warnings"))
				in_warning = true;

			if (name.equals("dateTime")) {
				in_global_datetime = true;
				datetime_count++;
			}
		}		
	}

	
	public void endElement(String uri, String name, String qName) throws SAXException {
//Log.d("XmlForecast", "end " + name);		
		
		if (in_current) {
			if (in_wind) {
				if (name.equals("speed"))
					in_speed = false;
				if (name.equals("gust"))
					in_gust = false;
				if (name.equals("direction"))
					in_direction = false;
				if (name.equals("bearing"))
					in_bearing = false;
			}
			
			if (in_datetime) {
				if (2 == datetime_count) {
					if (name.equals("hour"))
						in_hour = false;
					if (name.equals("minute"))
						in_minute = false;
				}
			}
			
			if (name.equals("dateTime")) {
				in_datetime = false;
			}

			if (name.equals("station"))
				in_station = false;				
			
			if (name.equals("condition"))
				in_condition = false;

			if (name.equals("temperature"))
				in_temperature = false;

			if (name.equals("dewpoint"))
				in_dewpoint = false;

			if (name.equals("windChill"))
				in_windchill = false;

			if (name.equals("humidex"))
				in_humidex = false;

			if (name.equals("pressure"))
				in_pressure = false;
			
			if (name.equals("visibility"))
				in_visibility = false;

			if (name.equals("relativeHumidity"))
				in_rel_humidity = false;

			if (name.equals("wind"))
				in_wind = false;			
			
			if (name.equals("currentConditions")) {
				in_current = false;			
				datetime_count = 0;			
			}
		} else if (in_forecast_group) {
//Log.d("XmlForecast", "in forecast group");		
			if (in_forecast) {
//Log.d("XmlForecast", "in forecast");		
				if (in_winds) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (in_uv) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (in_snow_level) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (in_windchill) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (in_comfort) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (in_frost) {
					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (name.equals("period"))
					in_time_period = false;

				if (name.equals("textSummary"))
					in_text_summary = false;

				if (in_abb_forecast) {
					if (name.equals("pop"))
						in_pop = false;

					if (name.equals("textSummary"))
						in_text_summary = false;
				}

				if (name.equals("temperature")) {
					if (in_high) {
						in_high = false;
					}
					if (in_low) {
						in_low = false;
					}
				}

				if (name.equals("relativeHumidity"))
					in_rel_humidity = false;

				if (name.equals("abbreviatedForecast"))
					in_abb_forecast = false;
				
				if (name.equals("winds"))
					in_winds = false;			

				if (name.equals("uv"))
					in_uv = false;							

				if (name.equals("snowLevel"))
					in_snow_level = false;							

				if (name.equals("comfort"))
					in_comfort = false;							

				if (name.equals("frost"))
					in_frost = false;							

				if (name.equals("windChill"))
					in_windchill = false;							

				if (name.equals("forecast")) {
					in_forecast = false;
					full_forecast.forecasts.add(temp_forecast);
//Log.d("Adding Forecast", "CAN YOU SEE ME?");
				}
			}

			if (name.equals("forecastGroup"))
				in_forecast_group = false;			

		} else if (in_yesterday) {
			if (name.equals("temperature")) {
				if (in_high) {
					in_high = false;
				}
				if (in_low) {
					in_low = false;
				}
			}
			
			if (name.equals("precip"))
				in_percipitation = false;

			if (name.equals("yesterdayConditions"))
				in_yesterday = false;
			
		} else if (in_riseset) {
			if (in_sunrise) {
				if (name.equals("hour"))
					in_hour = false;
				if (name.equals("minute"))
					in_minute = false;
			}
			
			if (in_sunset) {
				if (name.equals("hour"))
					in_hour = false;
				if (name.equals("minute"))
					in_minute = false;				
			} 
			
			if (name.equals("dateTime")) {
				in_datetime = false;
				if (2 == datetime_count)
					in_sunrise = false;
				if (4 == datetime_count)
					in_sunset = false;
			}
			
			if (name.equals("riseSet")) {
				in_riseset = false;
				datetime_count = 0;			
			}
			
		} else if (in_almanac) {
			if (name.equals("temperature")) {
				if (in_record_max)
					in_record_max = false;

				if (in_record_min)
					in_record_min = false;

				if (in_normal_max)
					in_normal_max = false;

				if (in_normal_min)
					in_normal_min = false;

				if (in_normal_mean)
					in_normal_mean = false;
			}

			if (name.equals("precipitation")) {
				if (in_max_rain)
					in_max_rain = false;

				if (in_max_snow)
					in_max_snow = false;

				if (in_max_percipitation)
					in_max_percipitation = false;

				if (in_max_snow_on_ground)
					in_max_snow_on_ground = false;
			}

			if (name.equals("pop"))
				in_normal_pop = false;

			if (name.equals("almanac"))
				in_almanac = false;
			
		} else if (in_location) {
			if (name.equals("province"))
				in_province = false;
			if (name.equals("name"))
				in_city = false;
			if (name.equals("location"))
				in_location = false;
		
		} else if (in_warning) {
			if (name.equals("textSummary"))
				in_text_summary = false;

			if (name.equals("warnings"))
				in_warning = false;

		} else if (in_global_datetime) {
			if (2 == datetime_count)
				if (name.equals("textSummary"))
					in_text_summary = false;

			if (name.equals("dateTime"))
				in_global_datetime = false;
						
		} else { //Not current conditions or forecast.
			//There should never be anything here...
		}			
	}

	public void characters(char ch[], int start, int length) {
		String text = (new String(ch).substring(start, start + length)).trim();
//Log.d("XmlForecast", "text " + text);		

		if (in_current) {
			if (in_wind) { //Wind string: SPEED km/h BEARING degs (DIRECTION), Gusting to GUST
				if (in_speed)
					if (0 < text.length())
						full_forecast.current.wind = text + " km/h";

				if (in_gust)
					if (0 < text.length())
						full_forecast.current.wind_gust = text + " km/h";
				
				if (in_direction)
					if (0 < text.length())
						full_forecast.current.wind += " (" + text + ")";
				
				if (in_bearing)
					if (0 < text.length())
						full_forecast.current.wind += " " + text + " degs";
			}
			
			if (in_datetime) {
				if (2 == datetime_count) {
					if (in_hour)
						full_forecast.current.hour = text;
					if (in_minute)
						full_forecast.current.min = text;
				}
			}

			if (in_station) {
				full_forecast.extra.station = text;			
			}
			
			if (in_condition)
				full_forecast.current.condition = text;

			if (in_temperature)
				full_forecast.current.temp = text;

			if (in_dewpoint)
				full_forecast.current.dewpoint = text;

			if (in_windchill)
				full_forecast.current.windchill = text;

			if (in_humidex)
				full_forecast.current.humidex = text;

			if (in_pressure)
				full_forecast.current.pressure = text;

			if (in_visibility)
				full_forecast.current.visibility = text;

			if (in_rel_humidity)
				full_forecast.current.rel_humidity = text;

			
		} else if (in_forecast_group) {
			if (in_forecast) {
				if (in_winds) {
					if (in_text_summary)
						temp_forecast.wind = text;
				}

				if (in_uv) {
					if (in_text_summary)
						temp_forecast.uv = text;
				}

				if (in_snow_level) {
					if (in_text_summary)
						temp_forecast.snow = text;
				}

				if (in_windchill) {
					if (in_text_summary)
						temp_forecast.windchill = text;
				}

				if (in_comfort) {
					if (in_text_summary)
						temp_forecast.comfort = text;
				}

				if (in_frost) {
					if (in_text_summary)
						temp_forecast.frost = text;
				}

				if (in_time_period)
					temp_forecast.time_period = text;

				if (in_text_summary)
					temp_forecast.text_summary = text;

				if (in_abb_forecast) {
					if (in_pop)
						temp_forecast.pop = text;

					if (in_text_summary)
						temp_forecast.condition = text;
				}

				if (in_high) {
					temp_forecast.high = text;
				}
				if (in_low) {
					temp_forecast.low = text;
				}

				if (in_rel_humidity)
					temp_forecast.rel_humidity = text;
			}
			
		} else if (in_yesterday) {
			if (in_high) {
				full_forecast.extra.yesterday_high = text;
			}
			if (in_low) {
				full_forecast.extra.yesterday_low = text;
			}
			
			if (in_percipitation)
				full_forecast.extra.yesterday_percipitation = text;

		} else if (in_riseset) {
			if (in_sunrise) {
				if (in_hour)
					full_forecast.extra.sunrise = text;
				if (in_minute)
					full_forecast.extra.sunrise += "h" + text;
			} 
			
			if (in_sunset) {
				if (in_hour)
					full_forecast.extra.sunset = text;
				if (in_minute)
					full_forecast.extra.sunset += "h" + text;
			}
			
			
		} else if (in_almanac) {
			if (in_record_max)
				full_forecast.extra.record_max = text;

			if (in_record_min)
				full_forecast.extra.record_min = text;
			
			if (in_normal_max)
				full_forecast.extra.normal_max = text;

			if (in_normal_min)
				full_forecast.extra.normal_min = text;

			if (in_normal_mean)
				full_forecast.extra.normal_mean = text;

			if (in_max_rain)
				full_forecast.extra.max_rain = text;

			if (in_max_snow)
				full_forecast.extra.max_snow = text;

			if (in_max_percipitation)
				full_forecast.extra.max_percipitation = text;

			if (in_max_snow_on_ground)
				full_forecast.extra.max_snow_on_ground = text;

			if (in_normal_pop)
				full_forecast.extra.normal_pop = text;
			
		} else if (in_location) {
			if (in_province) {
				full_forecast.province = text;
			}
				if (in_city)
				full_forecast.city = text;

		} else if (in_warning) {
			if (in_text_summary)
				full_forecast.warning = text;

		} else if (in_global_datetime) {
			if (2 == datetime_count)
				if (in_text_summary)
					full_forecast.extra.forecast_time = text;
						
		} else { //Not current conditions or forecast.
		}			
	}

	
	public void updateForecast() {		

		try {
			URL url = new URL(forecast_url);
			
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
			Log.e("EmWeather XmlForecast updateForecast", e.toString());
		} catch (SAXException e) {
			Log.e("EmWeather XmlForecast updateForecast", e.toString());
		} catch (ParserConfigurationException e) {
			Log.e("EmWeather XmlForecast updateForecast", e.toString());
		}
	}
	
	public void printForecast() {
		full_forecast.printLog();
	}
	
	
}//EmXmlCityCodeReader