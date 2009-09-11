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

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

public class FullForecast {
	
	public ExtraForecastInfo extra;
	public CurrentConditions current;
	public List<Forecast> forecasts;
	public String forecast_url;
	
	//Forecast information
	public String province;
	public String city;
	public String sitecode;
	public String warning;
	
	FullForecast(String url) {
		forecasts = new LinkedList<Forecast>();		
		extra = new ExtraForecastInfo();
		current = new CurrentConditions();
		forecast_url = url;
	}
	
	public void printLog() {
		Log.d("FullForecast printLog()" , "prov " + province);
		Log.d("FullForecast printLog()" , "city " + city);
		Log.d("FullForecast printLog()" , "sitecode " + sitecode);
		Log.d("FullForecast printLog()" , "warning " + warning);	

		Log.d("FullForecast printLog()" , " ");	
		Log.d("FullForecast printLog()" , "EXTRA Information ");	
		extra.printLog();

		Log.d("FullForecast printLog()" , " ");	
		Log.d("FullForecast printLog()" , "Current Forecast ");	
		current.printLog();

		for (int n = 0; n < forecasts.size(); n++) {
			Log.d("FullForecast printLog()" , " ");	
			Log.d("FullForecast printLog()" , "Forecast #" + n);	
			forecasts.get(n).printLog();
		}
	}
}
