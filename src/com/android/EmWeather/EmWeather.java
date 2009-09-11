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

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;


public class EmWeather extends Activity {
	public static final String BASE_URL = "http://dd.weatheroffice.ec.gc.ca/citypage_weather/xml/";
	public static final String PREF_FILE = "preferences";
	public static final int DEFAULT_ICON = 45;

	public TextView long_summary_text;
	public String sitecode, sitecode_url;
	public ForecastIcon forecast_icons[];
	public TableLayout forecast_icon_tables[];
	public static final int NUM_ICONS = 9;
	public EmXmlForecastReader forecaster;

	public Map<String, Integer> current_icon_map;
	public Map<String, Integer> forecast_icon_map;
	public String[] current_condition_night_list;
    public String[] forecast_condition_night_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        forecast_icons = new ForecastIcon[NUM_ICONS];
        forecast_icon_tables = new TableLayout[NUM_ICONS];
        setIconRes(); //Set up forecast item resources (must be hardcoded)
        setIconMaps();
        long_summary_text = (TextView) findViewById (R.id.text_long_summary);
        
        // Restore preferences
        readPreferences();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    }	


    public final int MENU_CHANGE_CITY = 0;
    public final int MENU_FORECAST = 1;
    public final int MENU_QUIT = 2;
    
    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_CHANGE_CITY, Menu.NONE, "Change City").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(Menu.NONE, MENU_FORECAST, Menu.NONE, "Get Forecast").setIcon(android.R.drawable.ic_menu_rotate);
        menu.add(Menu.NONE, MENU_QUIT, Menu.NONE, "Quit").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }

    /* Handles menu item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        	case MENU_CHANGE_CITY:
        		Log.d("Menu", "Menu Change City Pressed");
        		PrefDialog city_dialog = new PrefDialog(this, new onSaveHook());
        		city_dialog.show();             
        		return true;

        	case MENU_FORECAST:
        		Log.d("Menu", "Get Forecast Pressed");
        		
        		forecaster = new EmXmlForecastReader(sitecode_url);
        		forecaster.updateForecast();
        		
        		//Current
        		forecast_icons[0].setValues(getCurrentIconRes(forecaster.full_forecast.current), forecaster.full_forecast.current.temp, "", "", "Now");
//        		getCurrentIconRes(forecaster.full_forecast.current);
        		//All the rest
        		int n = 1;
        		for (Forecast forecast : forecaster.full_forecast.forecasts) {
        			if (NUM_ICONS <= n)
        				break;
//        			getForecastIconRes(forecast);
        			forecast_icons[n].setValues(getForecastIconRes(forecast), forecast.high, forecast.low, forecast.pop, forecast.time_period.substring(0, 3));
            		n++;
        		}

        		long_summary_text.setText("Click icon for full text summary");        		
        		//        		current_weather_text.setText(forecaster.full_forecast.current.getConditions());
        		//        		forecaster.printForecast();
        		
        		return true;

        	case MENU_QUIT:
        		this.finish();
        		return true;
        }

        return false;
    }

    public void readPreferences() {
//        sitecode_text = (TextView) findViewById (R.id.text_saved_city);

        // Restore preferences
        SharedPreferences preferences = getSharedPreferences(PREF_FILE, 0);
        sitecode = preferences.getString("sitecode", "");
        long_summary_text.setText("Sitedcode: " + sitecode);	
        sitecode_url = preferences.getString("sitecode_url", "");
    }

    private void setIconRes() {
    	forecast_icons[0] = new ForecastIcon(R.id.image_icon_1, R.id.text_icon_1_hi, R.id.text_icon_1_low, R.id.text_icon_1_pop, R.id.text_icon_1_day);
    	forecast_icons[1] = new ForecastIcon(R.id.image_icon_2, R.id.text_icon_2_hi, R.id.text_icon_2_low, R.id.text_icon_2_pop, R.id.text_icon_2_day);
    	forecast_icons[2] = new ForecastIcon(R.id.image_icon_3, R.id.text_icon_3_hi, R.id.text_icon_3_low, R.id.text_icon_3_pop, R.id.text_icon_3_day);
    	forecast_icons[3] = new ForecastIcon(R.id.image_icon_4, R.id.text_icon_4_hi, R.id.text_icon_4_low, R.id.text_icon_4_pop, R.id.text_icon_4_day);
    	forecast_icons[4] = new ForecastIcon(R.id.image_icon_5, R.id.text_icon_5_hi, R.id.text_icon_5_low, R.id.text_icon_5_pop, R.id.text_icon_5_day);
    	forecast_icons[5] = new ForecastIcon(R.id.image_icon_6, R.id.text_icon_6_hi, R.id.text_icon_6_low, R.id.text_icon_6_pop, R.id.text_icon_6_day);
    	forecast_icons[6] = new ForecastIcon(R.id.image_icon_7, R.id.text_icon_7_hi, R.id.text_icon_7_low, R.id.text_icon_7_pop, R.id.text_icon_7_day);
    	forecast_icons[7] = new ForecastIcon(R.id.image_icon_8, R.id.text_icon_8_hi, R.id.text_icon_8_low, R.id.text_icon_8_pop, R.id.text_icon_8_day);
    	forecast_icons[8] = new ForecastIcon(R.id.image_icon_9, R.id.text_icon_9_hi, R.id.text_icon_9_low, R.id.text_icon_9_pop, R.id.text_icon_9_day);

    	forecast_icon_tables[0] = (TableLayout) findViewById(R.id.table_icon_1);
    	forecast_icon_tables[1] = (TableLayout) findViewById(R.id.table_icon_2);
    	forecast_icon_tables[2] = (TableLayout) findViewById(R.id.table_icon_3);
    	forecast_icon_tables[3] = (TableLayout) findViewById(R.id.table_icon_4);
    	forecast_icon_tables[4] = (TableLayout) findViewById(R.id.table_icon_5);
    	forecast_icon_tables[5] = (TableLayout) findViewById(R.id.table_icon_6);
    	forecast_icon_tables[6] = (TableLayout) findViewById(R.id.table_icon_7);
    	forecast_icon_tables[7] = (TableLayout) findViewById(R.id.table_icon_8);
    	forecast_icon_tables[8] = (TableLayout) findViewById(R.id.table_icon_9);

    	forecast_icon_tables[0].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[1].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[2].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[3].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[4].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[5].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[6].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[7].setOnClickListener(new onIconClickListener());
    	forecast_icon_tables[8].setOnClickListener(new onIconClickListener());    	
    }
    
    private void setIconMaps() {
    	current_icon_map = new HashMap<String, Integer>();
    	forecast_icon_map = new HashMap<String, Integer>();
    	
    	current_icon_map.put("Blowing Snow", 25);
    	current_icon_map.put("Clear", 30);
    	current_icon_map.put("Night Clear", 30);
    	current_icon_map.put("Cloudy", 10);
    	current_icon_map.put("Night Cloudy", 33);
    	current_icon_map.put("Decreasing Cloud", 05);
    	current_icon_map.put("Night Decreasing Cloud", 34);
    	current_icon_map.put("Distant Precipitation", 28);
    	current_icon_map.put("Drifting Snow", 25);
    	current_icon_map.put("Drizzle", 12);
    	current_icon_map.put("Dust", 23);
    	current_icon_map.put("Dust Devils", 41);
    	current_icon_map.put("Fog", 22);
    	current_icon_map.put("Fog Bank Near Station", 22);
    	current_icon_map.put("Fog Depositing Ice", 27);
    	current_icon_map.put("Fog Patches", 22);
    	current_icon_map.put("Freezing drizzle", 7);
    	current_icon_map.put("Freezing rain", 7);
    	current_icon_map.put("Funnel Cloud", 42);
    	current_icon_map.put("Hail", 27);
    	current_icon_map.put("Haze", 23);
    	current_icon_map.put("Heavy Blowing Snow", 40);
    	current_icon_map.put("Heavy Drifting Snow", 40);
    	current_icon_map.put("Heavy Drizzle", 13);
    	current_icon_map.put("Heavy Hail", 27);
    	current_icon_map.put("Heavy Mixed Rain and Drizzle", 13);
    	current_icon_map.put("Heavy Mixed Rain and Snow Shower", 15);
    	current_icon_map.put("Heavy Rain", 13);
    	current_icon_map.put("Heavy Rain and Snow", 15);
    	current_icon_map.put("Heavy Rainshower", 13);
    	current_icon_map.put("Heavy Snow", 18);
    	current_icon_map.put("Heavy Snow Pellets", 27);
    	current_icon_map.put("Heavy Snowshower", 18);
    	current_icon_map.put("Heavy Thunderstorm with Hail", 7);
    	current_icon_map.put("Heavy Thunderstorm with Rain", 19);
    	current_icon_map.put("Ice Crystals", 27);
    	current_icon_map.put("Ice Pellets", 27);
    	current_icon_map.put("Increasing Cloud", 04);
    	current_icon_map.put("Night Increasing Cloud", 34);
    	current_icon_map.put("Light Drizzle", 11);
    	current_icon_map.put("Light Freezing Drizzle", 14);
    	current_icon_map.put("Light Freezing Rain", 14);
    	current_icon_map.put("Light Rain", 11);
    	current_icon_map.put("Light Rainshower", 11);
    	current_icon_map.put("Light Snow", 16);
    	current_icon_map.put("Light Snow Pellets", 17);
    	current_icon_map.put("Light Snowshower", 17);
    	current_icon_map.put("Lightning Visible", 9);
    	current_icon_map.put("Mainly Clear", 31);
    	current_icon_map.put("Night Mainly Clear", 31);
    	current_icon_map.put("Mainly Sunny", 1);
    	current_icon_map.put("Night Mainly Sunny", 31);
    	current_icon_map.put("Mist", 20);
    	current_icon_map.put("Mixed Rain and Drizzle", 13);
    	current_icon_map.put("Mixed Rain and Snow Shower", 15);
    	current_icon_map.put("Mostly Cloudy", 03);
    	current_icon_map.put("Night Mostly Cloudy", 33);
    	current_icon_map.put("Not Reported", 45);
    	current_icon_map.put("Partly Cloudy", 02);
    	current_icon_map.put("Night Partly Cloudy", 32);
    	current_icon_map.put("Rain", 12);
    	current_icon_map.put("Rain and Snow", 15);
    	current_icon_map.put("Rainshower", 11);
    	current_icon_map.put("Recent Drizzle", 13);
    	current_icon_map.put("Recent Dust or Sand Storm", 23);
    	current_icon_map.put("Recent Fog", 22);
    	current_icon_map.put("Recent Freezing Precipitation", 14);
    	current_icon_map.put("Recent Hail", 27);
    	current_icon_map.put("Recent Rain", 11);
    	current_icon_map.put("Recent Rain and Snow", 15);
    	current_icon_map.put("Recent Rainshower", 11);
    	current_icon_map.put("Recent Snow", 16);
    	current_icon_map.put("Recent Snowshower", 17);
    	current_icon_map.put("Recent Thunderstorm", 19);
    	current_icon_map.put("Recent Thunderstorm with Hail", 19);
    	current_icon_map.put("Recent Thunderstorm with Heavy Hail", 19);
    	current_icon_map.put("Recent Thunderstorm with Heavy Rain", 19);
    	current_icon_map.put("Recent Thunderstorm with Rain", 19);
    	current_icon_map.put("Sand or Dust Storm", 23);
    	current_icon_map.put("Severe Sand or Dust Storm", 23);
    	current_icon_map.put("Shallow Fog", 22);
    	current_icon_map.put("Smoke", 24);
    	current_icon_map.put("Snow", 16);
    	current_icon_map.put("Snow Crystals", 27);
    	current_icon_map.put("Snow Grains", 27);
    	current_icon_map.put("Squalls", 43);
    	current_icon_map.put("Sunny", 0);
    	current_icon_map.put("Night Sunny", 30);
    	current_icon_map.put("Thunderstorm with Hail", 27);
    	current_icon_map.put("Thunderstorm with Rain", 19);
    	current_icon_map.put("Thunderstorm with Sand or Dust Storm", 19);
    	current_icon_map.put("Thunderstorm without Precipitation", 9);
    	current_icon_map.put("Tornado", 42);
    	current_icon_map.put("None",  45);

    	
		forecast_icon_map.put("A few clouds", 1);
		forecast_icon_map.put("Night A few clouds", 31);
		forecast_icon_map.put("A few flurries", 8);
		forecast_icon_map.put("A few flurries mixed with ice pellets", 8);
		forecast_icon_map.put("A few flurries or rain showers", 7);
		forecast_icon_map.put("A few flurries or thundershowers", 7);
		forecast_icon_map.put("A few rain showers or flurries", 7);
		forecast_icon_map.put("A few rain showers or wet flurries", 7);
		forecast_icon_map.put("A few showers", 6);
		forecast_icon_map.put("A few showers or drizzle", 6);
		forecast_icon_map.put("A few showers or thundershowers", 9);
		forecast_icon_map.put("A few showers or thunderstorms", 9);
		forecast_icon_map.put("A few thundershowers", 9);
		forecast_icon_map.put("A few thunderstorms", 9);
		forecast_icon_map.put("A few wet flurries", 7);
		forecast_icon_map.put("A few wet flurries or rain showers", 7);
		forecast_icon_map.put("A mix of sun and cloud", 02);
		forecast_icon_map.put("Blizzard", 40);
		forecast_icon_map.put("Chance of drizzle", 13);
		forecast_icon_map.put("Chance of drizzle mixed with freezing drizzle", 15);
		forecast_icon_map.put("Chance of drizzle mixed with rain", 13);
		forecast_icon_map.put("Chance of drizzle or rain", 13);
		forecast_icon_map.put("Chance of flurries", 16);
		forecast_icon_map.put("Chance of flurries at times heavy", 17);
		forecast_icon_map.put("Chance of flurries mixed with ice pellets", 17);
		forecast_icon_map.put("Chance of flurries or ice pellets", 27);
		forecast_icon_map.put("Chance of flurries or rain showers", 15);
		forecast_icon_map.put("Chance of flurries or thundershowers", 15);
		forecast_icon_map.put("Chance of freezing drizzle", 14);
		forecast_icon_map.put("Chance of freezing rain", 14);
		forecast_icon_map.put("Chance of freezing rain mixed with snow", 14);
		forecast_icon_map.put("Chance of freezing rain or rain", 14);
		forecast_icon_map.put("Chance of freezing rain or snow", 14);
		forecast_icon_map.put("Chance of light snow", 16);
		forecast_icon_map.put("Chance of light snow and blowing snow", 16);
		forecast_icon_map.put("Chance of light snow mixed with freezing drizzle", 14);
		forecast_icon_map.put("Chance of light snow mixed with ice pellets", 16);
		forecast_icon_map.put("Chance of light snow mixed with rain", 15);
		forecast_icon_map.put("Chance of light snow or freezing rain", 14);
		forecast_icon_map.put("Chance of light snow or ice pellets", 16);
		forecast_icon_map.put("Chance of light snow or rain", 15);
		forecast_icon_map.put("Chance of light wet snow", 15);
		forecast_icon_map.put("Chance of rain", 11);
		forecast_icon_map.put("Chance of rain at times heavy", 13);
		forecast_icon_map.put("Chance of rain mixed with snow", 15);
		forecast_icon_map.put("Chance of rain or drizzle", 13);
		forecast_icon_map.put("Chance of rain or freezing rain", 14);
		forecast_icon_map.put("Chance of rain or snow", 15);
		forecast_icon_map.put("Chance of rain showers or flurries", 15);
		forecast_icon_map.put("Chance of rain showers or wet flurries", 15);
		forecast_icon_map.put("Chance of severe thunderstorms", 19);
		forecast_icon_map.put("Chance of showers", 11);
		forecast_icon_map.put("Chance of showers at times heavy", 13);
		forecast_icon_map.put("Chance of showers at times heavy or thundershowers", 13);
		forecast_icon_map.put("Chance of showers at times heavy or thunderstorms", 13);
		forecast_icon_map.put("Chance of showers or drizzle", 13);
		forecast_icon_map.put("Chance of showers or thundershowers", 19);
		forecast_icon_map.put("Chance of showers or thunderstorms", 19);
		forecast_icon_map.put("Chance of snow", 16);
		forecast_icon_map.put("Chance of snow and blizzard", 18);
		forecast_icon_map.put("Chance of snow mixed with freezing drizzle", 14);
		forecast_icon_map.put("Chance of snow mixed with freezing rain", 14);
		forecast_icon_map.put("Chance of snow mixed with rain", 15);
		forecast_icon_map.put("Chance of snow or rain", 15);
		forecast_icon_map.put("Chance of snow squalls", 18);
		forecast_icon_map.put("Chance of thundershowers", 19);
		forecast_icon_map.put("Chance of thunderstorms", 19);
		forecast_icon_map.put("Chance of thunderstorms and possible hail", 19);
		forecast_icon_map.put("Chance of wet flurries", 15);
		forecast_icon_map.put("Chance of wet flurries at times heavy", 15);
		forecast_icon_map.put("Chance of wet flurries or rain showers", 15);
		forecast_icon_map.put("Chance of wet snow", 15);
		forecast_icon_map.put("Chance of wet snow mixed with rain", 15);
		forecast_icon_map.put("Chance of wet snow or rain", 15);
		forecast_icon_map.put("Clear", 30);
		forecast_icon_map.put("Night Clear", 30);
		forecast_icon_map.put("Clearing", 35);
		forecast_icon_map.put("Night Clearing", 35);
		forecast_icon_map.put("Cloudy", 10);
		forecast_icon_map.put("Night Cloudy", 33);
		forecast_icon_map.put("Cloudy periods", 02);
		forecast_icon_map.put("Night Cloudy periods", 32);
		forecast_icon_map.put("Cloudy with sunny periods", 03);
		forecast_icon_map.put("Drizzle", 13);
		forecast_icon_map.put("Drizzle mixed with freezing drizzle", 13);
		forecast_icon_map.put("Drizzle mixed with rain", 13);
		forecast_icon_map.put("Drizzle or freezing drizzle", 14);
		forecast_icon_map.put("Drizzle or rain", 13);
		forecast_icon_map.put("Flurries", 18);
		forecast_icon_map.put("Flurries at times heavy", 18);
		forecast_icon_map.put("Flurries at times heavy or rain showers", 18);
		forecast_icon_map.put("Flurries mixed with ice pellets", 27);
		forecast_icon_map.put("Flurries or ice pellets", 27);
		forecast_icon_map.put("Flurries or rain showers", 15);
		forecast_icon_map.put("Flurries or thundershowers", 15);
		forecast_icon_map.put("Fog", 20);
		forecast_icon_map.put("Fog developing", 21);
		forecast_icon_map.put("Fog dissipating", 22);
		forecast_icon_map.put("Fog patches", 22);
		forecast_icon_map.put("Freezing drizzle", 14);
		forecast_icon_map.put("Freezing rain", 14);
		forecast_icon_map.put("Freezing rain mixed with ice pellets", 14);
		forecast_icon_map.put("Freezing rain mixed with rain", 14);
		forecast_icon_map.put("Freezing rain mixed with snow", 14);
		forecast_icon_map.put("Freezing rain or ice pellets", 14);
		forecast_icon_map.put("Freezing rain or rain", 14);
		forecast_icon_map.put("Freezing rain or snow", 14);
		forecast_icon_map.put("Ice fog", 26);
		forecast_icon_map.put("Ice fog developing", 26);
		forecast_icon_map.put("Ice fog dissipating", 26);
		forecast_icon_map.put("Ice pellet", 27);
		forecast_icon_map.put("Ice pellet mixed with freezing rain", 14);
		forecast_icon_map.put("Ice pellet mixed with snow", 27);
		forecast_icon_map.put("Ice pellet or freezing rain", 14);
		forecast_icon_map.put("Ice pellet or snow", 27);
		forecast_icon_map.put("Increasing cloudiness", 04);
		forecast_icon_map.put("Night Increasing cloudiness", 34);
		forecast_icon_map.put("Increasing clouds", 04);
		forecast_icon_map.put("Night Increasing clouds", 34);
		forecast_icon_map.put("Light snow", 16);
		forecast_icon_map.put("Light snow and blizzard", 25);
		forecast_icon_map.put("Light snow and blizzard and blowing snow", 25);
		forecast_icon_map.put("Light snow and blowing snow", 25);
		forecast_icon_map.put("Light snow mixed with freezing drizzle", 15);
		forecast_icon_map.put("Light snow mixed with freezing rain", 14);
		forecast_icon_map.put("Light snow mixed with ice pellets", 14);
		forecast_icon_map.put("Light snow mixed with rain", 15);
		forecast_icon_map.put("Light snow or freezing drizzle", 15);
		forecast_icon_map.put("Light snow or freezing rain", 15);
		forecast_icon_map.put("Light snow or ice pellets", 15);
		forecast_icon_map.put("Light snow or rain", 15);
		forecast_icon_map.put("Light wet snow", 15);
		forecast_icon_map.put("Light wet snow or rain", 15);
		forecast_icon_map.put("Local snow squalls", 40);
		forecast_icon_map.put("Near blizzard", 40);
		forecast_icon_map.put("Overcast", 10);
		forecast_icon_map.put("Night Overcast", 33);
		forecast_icon_map.put("Periods of drizzle", 13);
		forecast_icon_map.put("Periods of drizzle mixed with freezing drizzle", 14);
		forecast_icon_map.put("Periods of drizzle mixed with rain", 13);
		forecast_icon_map.put("Periods of drizzle or freezing drizzle", 14);
		forecast_icon_map.put("Periods of drizzle or rain", 13);
		forecast_icon_map.put("Periods of freezing drizzle", 14);
		forecast_icon_map.put("Periods of freezing drizzle or drizzle", 14);
		forecast_icon_map.put("Periods of freezing drizzle or rain", 14);
		forecast_icon_map.put("Periods of freezing rain", 14);
		forecast_icon_map.put("Periods of freezing rain mixed with ice pellets", 14);
		forecast_icon_map.put("Periods of freezing rain mixed with rain", 14);
		forecast_icon_map.put("Periods of freezing rain mixed with snow", 14);
		forecast_icon_map.put("Periods of freezing rain or ice pellets", 14);
		forecast_icon_map.put("Periods of freezing rain or rain", 14);
		forecast_icon_map.put("Periods of freezing rain or snow", 14);
		forecast_icon_map.put("Periods of ice pellet", 27);
		forecast_icon_map.put("Periods of ice pellet mixed with freezing rain", 14);
		forecast_icon_map.put("Periods of ice pellet mixed with snow", 27);
		forecast_icon_map.put("Periods of ice pellet or freezing rain", 14);
		forecast_icon_map.put("Periods of ice pellet or snow", 27);
		forecast_icon_map.put("Periods of light snow", 16);
		forecast_icon_map.put("Periods of light snow and blizzard", 25);
		forecast_icon_map.put("Periods of light snow and blizzard and blowing snow", 25);
		forecast_icon_map.put("Periods of light snow and blowing snow", 25);
		forecast_icon_map.put("Periods of light snow mixed with freezing drizzle", 7);
		forecast_icon_map.put("Periods of light snow mixed with freezing rain", 7);
		forecast_icon_map.put("Periods of light snow mixed with ice pellets", 8);
		forecast_icon_map.put("Periods of light snow mixed with rain", 7);
		forecast_icon_map.put("Periods of light snow or freezing drizzle", 7);
		forecast_icon_map.put("Periods of light snow or freezing rain", 7);
		forecast_icon_map.put("Periods of light snow or ice pellets", 8);
		forecast_icon_map.put("Periods of light snow or rain", 7);
		forecast_icon_map.put("Periods of light wet snow", 7);
		forecast_icon_map.put("Periods of light wet snow mixed with rain", 7);
		forecast_icon_map.put("Periods of light wet snow or rain", 7);
		forecast_icon_map.put("Periods of rain", 6);
		forecast_icon_map.put("Periods of rain mixed with freezing rain", 7);
		forecast_icon_map.put("Periods of rain mixed with snow", 7);
		forecast_icon_map.put("Periods of rain or drizzle", 6);
		forecast_icon_map.put("Periods of rain or freezing rain", 7);
		forecast_icon_map.put("Periods of rain or snow", 7);
		forecast_icon_map.put("Periods of rain or thundershowers", 9);
		forecast_icon_map.put("Periods of rain or thunderstorms", 9);
		forecast_icon_map.put("Periods of snow", 8);
		forecast_icon_map.put("Periods of snow and blizzard", 25);
		forecast_icon_map.put("Periods of snow and blizzard and blowing snow", 25);
		forecast_icon_map.put("Periods of snow and blowing snow", 8);
		forecast_icon_map.put("Periods of snow mixed with freezing drizzle", 7);
		forecast_icon_map.put("Periods of snow mixed with freezing rain", 7);
		forecast_icon_map.put("Periods of snow mixed with ice pellets", 8);
		forecast_icon_map.put("Periods of snow mixed with rain", 7);
		forecast_icon_map.put("Periods of snow or freezing drizzle", 7);
		forecast_icon_map.put("Periods of snow or freezing rain", 7);
		forecast_icon_map.put("Periods of snow or ice pellets", 8);
		forecast_icon_map.put("Periods of snow or rain", 7);
		forecast_icon_map.put("Periods of wet snow", 15);
		forecast_icon_map.put("Periods of wet snow mixed with rain", 7);
		forecast_icon_map.put("Periods of wet snow or rain", 7);
		forecast_icon_map.put("Rain", 11);
		forecast_icon_map.put("Rain at times heavy", 13);
		forecast_icon_map.put("Rain at times heavy mixed with freezing rain", 14);
		forecast_icon_map.put("Rain at times heavy mixed with snow", 15);
		forecast_icon_map.put("Rain at times heavy or drizzle", 13);
		forecast_icon_map.put("Rain at times heavy or freezing rain", 14);
		forecast_icon_map.put("Rain at times heavy or snow", 15);
		forecast_icon_map.put("Rain at times heavy or thundershowers", 19);
		forecast_icon_map.put("Rain at times heavy or thunderstorms", 19);
		forecast_icon_map.put("Rain mixed with freezing rain", 14);
		forecast_icon_map.put("Rain mixed with snow", 15);
		forecast_icon_map.put("Rain or drizzle", 13);
		forecast_icon_map.put("Rain or freezing rain", 14);
		forecast_icon_map.put("Rain or snow", 15);
		forecast_icon_map.put("Rain or thundershowers", 19);
		forecast_icon_map.put("Rain or thunderstorms", 19);
		forecast_icon_map.put("Rain showers or flurries", 15);
		forecast_icon_map.put("Rain showers or wet flurries", 15);
		forecast_icon_map.put("Showers", 12);
		forecast_icon_map.put("Showers at times heavy", 13);
		forecast_icon_map.put("Showers at times heavy or thundershowers", 19);
		forecast_icon_map.put("Showers at times heavy or thunderstorms", 19);
		forecast_icon_map.put("Showers or drizzle", 13);
		forecast_icon_map.put("Showers or thundershowers", 19);
		forecast_icon_map.put("Showers or thunderstorms", 19);
		forecast_icon_map.put("Smoke", 44);
		forecast_icon_map.put("Snow", 17);
		forecast_icon_map.put("Snow and blizzard", 40);
		forecast_icon_map.put("Snow and blizzard and blowing snow", 40);
		forecast_icon_map.put("Snow and blowing snow", 40);
		forecast_icon_map.put("Snow at times heavy", 18);
		forecast_icon_map.put("Snow at times heavy and blizzard", 40);
		forecast_icon_map.put("Snow at times heavy and blowing snow", 40);
		forecast_icon_map.put("Snow at times heavy mixed with freezing drizzle", 14);
		forecast_icon_map.put("Snow at times heavy mixed with freezing rain", 14);
		forecast_icon_map.put("Snow at times heavy mixed with ice pellets", 14);
		forecast_icon_map.put("Snow at times heavy mixed with rain", 15);
		forecast_icon_map.put("Snow at times heavy or freezing rain", 14);
		forecast_icon_map.put("Snow at times heavy or ice pellets", 18);
		forecast_icon_map.put("Snow at times heavy or rain", 15);
		forecast_icon_map.put("Snow mixed with freezing drizzle", 14);
		forecast_icon_map.put("Snow mixed with freezing rain", 14);
		forecast_icon_map.put("Snow mixed with ice pellets", 27);
		forecast_icon_map.put("Snow mixed with rain", 15);
		forecast_icon_map.put("Snow or freezing drizzle", 15);
		forecast_icon_map.put("Snow or freezing rain", 15);
		forecast_icon_map.put("Snow or ice pellets", 27);
		forecast_icon_map.put("Snow or rain", 15);
		forecast_icon_map.put("Snow squalls", 40);
		forecast_icon_map.put("Sunny", 0);
		forecast_icon_map.put("Sunny with cloudy periods", 2);
		forecast_icon_map.put("Thunderstorms", 19);
		forecast_icon_map.put("Thunderstorms and possible hail", 19);
		forecast_icon_map.put("Wet flurries", 15);
		forecast_icon_map.put("Wet flurries at times heavy", 15);
		forecast_icon_map.put("Wet flurries at times heavy or rain showers", 15);
		forecast_icon_map.put("Wet flurries or rain showers", 15);
		forecast_icon_map.put("Wet snow", 15);
		forecast_icon_map.put("Wet snow at times heavy", 15);
		forecast_icon_map.put("Wet snow at times heavy mixed with rain", 15);
		forecast_icon_map.put("Wet snow mixed with rain", 15);
		forecast_icon_map.put("Wet snow or rain", 15);
		forecast_icon_map.put("Windy", 43);
		forecast_icon_map.put("None", 45); 	
		
		current_condition_night_list = new String[] { "Clear","Cloudy","Decreasing Cloud","Increasing Cloud","Mainly Clear","Mainly Sunny","Mostly Cloudy","Partly Cloudy","Sunny"};
		forecast_condition_night_list = new String[] {"A few clouds","Clear","Clearing","Cloudy","Cloudy periods","Increasing cloudiness","Increasing clouds","Overcast"};

    }
    
    public int getCurrentIconRes(CurrentConditions current) {
    	int icon_num = -1;
    	String icon_string = "icon";
    	boolean is_night = false;
    	String condition = current.condition;
//    Log.d("Hour of Current", "Hour " + current.hour);	
    	if ((Integer.parseInt(current.hour) < 6) || (Integer.parseInt(current.hour) > 18)) //19h00-05h00
    		is_night = true;
    
    	if ((is_night) && (-1 != arrayIndex(current_condition_night_list, "condition")))
    		condition = "Night " + condition;
    	
    	icon_num = current_icon_map.get(condition);

    	if (-1 < icon_num) {
        	
    		if (10 > icon_num)
    			icon_string += "0";
    		
    		icon_string += icon_num;
    		
    	} else {
        	icon_string += DEFAULT_ICON;		
    	}
Log.d("Current condition String", condition);	
Log.d("Current Icon String", icon_string);	

    	return getResources().getIdentifier(icon_string, "drawable", "com.android.EmWeather");
    }

    public int getForecastIconRes(Forecast forecast) {
    	int icon_num = -1;
    	String icon_string = "icon";
    	boolean is_night = false;
    	String condition = forecast.condition;
    	
    	if (forecast.time_period.contains("night"))
    		is_night = true;
    
    	if ((is_night) && (-1 != arrayIndex(forecast_condition_night_list, "condition")))
    		condition = "Night " + condition;
    	
    	icon_num = forecast_icon_map.get(condition);

    	if (-1 < icon_num) {
        	
    		if (10 > icon_num)
    			icon_string += "0";
    		
    		icon_string += icon_num;
    		
    	} else {
        	icon_string += DEFAULT_ICON;		
    	}
Log.d("Forecast condition String", condition);	
Log.d("Forecast Icon String", icon_string);	
    	return getResources().getIdentifier(icon_string, "drawable", "com.android.EmWeather");
    }
    
    public int arrayIndex(String[] array, String value) {
    	for (int n = 0; n < array.length; n++) {
    		if (value.equals(array[n]))
    			return n;
    	}
    	
    	return -1;    	
    }
    
    
    private class onSaveHook implements PrefDialog.SaveHook {
        @Override
		public void save_preferences(String prov, String city, String sitecode_in, String sitecode_url_in) {
            long_summary_text.setText("Sitedcode: " + sitecode_in);	
            sitecode = sitecode_in;	        	
            sitecode_url = sitecode_url_in;	        	
        }
   } 
    
    private class onIconClickListener implements View.OnClickListener {
    	@Override
    	public void onClick(View v) {
    		int table_num;
    		
    		//We need to find out which icon we are.
    		for (table_num = 0; table_num < NUM_ICONS; table_num++) {
    			if (v == forecast_icon_tables[table_num])
    				break;
    		}	
    	
    		//Now we need to update with the values from the Forecast
    		if (null != forecaster) {
    			if (0 == table_num) {
       				long_summary_text.setText("Current: " + forecaster.full_forecast.current.temp + " " + forecaster.full_forecast.current.condition);
    				
    			} else {
    				Forecast forecast = forecaster.full_forecast.forecasts.get(table_num-1); //-1 to ignore current conditions
    			
    				String forecast_text = forecast.time_period;
    				forecast_text += ": " + forecast.condition + " - " + forecast.text_summary;
    				
    				if (null != forecast.wind)
    					forecast_text += ", " + forecast.wind;
    				if (null != forecast.windchill)
    					forecast_text += ", " + forecast.windchill;
    				if (null != forecast.pop)
    					forecast_text += ", POP: " + forecast.pop;
    				if (null != forecast.rel_humidity)
    					forecast_text += ", Rel Humidity: " + forecast.rel_humidity;
       				if (null != forecast.percipitation)
    					forecast_text += ", Percipitation: " + forecast.percipitation;
       				if (null != forecast.snow)
    					forecast_text += ", Snow: " + forecast.snow;
       				if (null != forecast.visibility)
    					forecast_text += ", Visibility: " + forecast.visibility;
       				if (null != forecast.uv)
    					forecast_text += ", UV: " + forecast.uv;
       				if (null != forecast.comfort)
    					forecast_text += ", Comfort: " + forecast.comfort;
       				if (null != forecast.frost)
    					forecast_text += ", Frost: " + forecast.frost;

       				long_summary_text.setText(forecast_text);    				
    			}
    		}
    		
    			
    		
    	}
  
    }

    private class ForecastIcon {
    	public ImageView image_view;
    	public TextView text_view_hi, text_view_low, text_view_pop, text_view_day;

    	ForecastIcon(int image_res, int text_hi_res, int text_low_res, int text_pop_res, int text_day_res) {
    		setRes(image_res, text_hi_res, text_low_res, text_pop_res, text_day_res);
    	}

    	public void setRes(int image_res, int text_hi_res, int text_low_res, int text_pop_res, int text_day_res) {
    		image_view = (ImageView) findViewById(image_res);
    		text_view_hi = (TextView) findViewById(text_hi_res);
    		text_view_low = (TextView) findViewById(text_low_res);
    		text_view_pop = (TextView) findViewById(text_pop_res);
    		text_view_day = (TextView) findViewById(text_day_res);
    	}

    	public void setValues(int image_res, String text_hi, String text_low, String text_pop, String text_day) {
    		image_view.setImageResource(image_res);	
    		text_view_hi.setText(text_hi);
    		text_view_low.setText(text_low);
    		text_view_pop.setText(text_pop);
    		text_view_day.setText(text_day);
    	}
    }

}




