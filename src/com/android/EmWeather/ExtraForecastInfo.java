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

public class ExtraForecastInfo {
	public String forecast_time; //	 <textSummary>Tuesday September 08, 2009 at 19:01 EDT</textSummary>

	public String station;
	public String station_code;
	public String station_lat;
	public String station_lon;
	
	public String yesterday_high;
	public String yesterday_low;
	public String yesterday_percipitation;
	
	public String sunrise;
	public String sunset;
	public String record_max;
	public String record_min;
	public String normal_max;
	public String normal_min;
	public String normal_mean;
	public String normal_pop;
	public String max_rain;
	public String max_snow;
	public String max_percipitation;
	public String max_snow_on_ground;

	public void printLog() {
		Log.d("ExtraForecastInfo printLog()" , "forecast_time " + forecast_time);
		Log.d("ExtraForecastInfo printLog()" , "station " + station);
		Log.d("ExtraForecastInfo printLog()" , "station_code " + station_code);
		Log.d("ExtraForecastInfo printLog()" , "station_lat " + station_lat);
		Log.d("ExtraForecastInfo printLog()" , "station_lon " + station_lon);
		Log.d("ExtraForecastInfo printLog()" , "yesterday_high " + yesterday_high);
		Log.d("ExtraForecastInfo printLog()" , "yesterday_low " + yesterday_low);
		Log.d("ExtraForecastInfo printLog()" , "yesterday_percipitation " + yesterday_percipitation);
		Log.d("ExtraForecastInfo printLog()" , "sunrise " + sunrise);
		Log.d("ExtraForecastInfo printLog()" , "sunset " + sunset);
		Log.d("ExtraForecastInfo printLog()" , "record_max " + record_max);
		Log.d("ExtraForecastInfo printLog()" , "record_min " + record_min);
		Log.d("ExtraForecastInfo printLog()" , "normal_max " + normal_max);
		Log.d("ExtraForecastInfo printLog()" , "normal_min " + normal_min);
		Log.d("ExtraForecastInfo printLog()" , "normal_mean " + normal_mean);
		Log.d("ExtraForecastInfo printLog()" , "normal_pop " + normal_pop);
		Log.d("ExtraForecastInfo printLog()" , "max_rain " + max_rain);
		Log.d("ExtraForecastInfo printLog()" , "max_snow " + max_snow);
		Log.d("ExtraForecastInfo printLog()" , "max_percipitation " + max_percipitation);
		Log.d("ExtraForecastInfo printLog()" , "max_snow_on_ground " + max_snow_on_ground);
	}
}