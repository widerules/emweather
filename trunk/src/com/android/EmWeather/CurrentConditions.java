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

import android.util.Log;

public class CurrentConditions {
	public String condition;
	public String temp = "";
	public String dewpoint;
	public String windchill;
	public String humidex;
	public String pressure;
	public String pressure_change;
	public String pressure_tendency;
	public String visibility;
	public String rel_humidity;
	public String wind;
	public String wind_gust;	
	public String day;	
	public String hour;	
	public String min;	

	public void printLog() {
		Log.d("CurrentConditions printLog()" , "condition " + condition);
		Log.d("CurrentConditions printLog()" , "temp " + temp);
		Log.d("CurrentConditions printLog()" , "dewpoint " + dewpoint);
		Log.d("CurrentConditions printLog()" , "windchill " + windchill);
		Log.d("CurrentConditions printLog()" , "humidex " + humidex);
		Log.d("CurrentConditions printLog()" , "pressure " + pressure);
		Log.d("CurrentConditions printLog()" , "pressure_change " + pressure_change);
		Log.d("CurrentConditions printLog()" , "pressure_tendency " + pressure_tendency);
		Log.d("CurrentConditions printLog()" , "visibility " + visibility);
		Log.d("CurrentConditions printLog()" , "rel_humidity " + rel_humidity);
		Log.d("CurrentConditions printLog()" , "wind " + wind);
		Log.d("CurrentConditions printLog()" , "wind_gust " + wind_gust);
	}

	public String getConditions() {
		String conditions = condition + " at " + temp + "C ";
		
		if (null != dewpoint)
			conditions += "dewpoint: " + dewpoint + " ";

		if (null != windchill)
			conditions += "windchill: " + windchill + " ";

		if (null != humidex)
			conditions += "humidex: " + humidex + " ";

		if (null != pressure)
			conditions += "pressure: " + pressure + "kPa change: " + pressure_change + " (" + pressure_tendency + ") ";

		if (null != visibility)
			conditions += "visibility: " + visibility + " ";

		if (null != rel_humidity)
			conditions += "rel_humidity: " + rel_humidity + " ";

		if (null != wind)
			conditions += "wind: " + wind + " ";

		if (null != wind_gust)
			conditions += "gusting to: " + wind_gust + " ";

		return conditions;
	}

}
