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

public class Forecast {
	public String time_period;
	public String condition; //This is the text summary in the abbv. forecast
	public String text_summary;
	public String low = "";
	public String high = "";
	public String pop = "";
	public String wind;
	public String windchill;
	public String rel_humidity;
	public String percipitation;
	public String snow;
	public String visibility;
	public String uv;
	public String comfort;
	public String frost;

	public void printLog() {
		Log.d("Forecast printLog()" , "time_period " + time_period);
		Log.d("Forecast printLog()" , "condition " + condition);
		Log.d("Forecast printLog()" , "text_summary " + text_summary);
		Log.d("Forecast printLog()" , "low " + low);
		Log.d("Forecast printLog()" , "high " + high);
		Log.d("Forecast printLog()" , "pop " + pop);
		Log.d("Forecast printLog()" , "wind " + wind);
		Log.d("Forecast printLog()" , "windchill " + windchill);
		Log.d("Forecast printLog()" , "rel_humidity " + rel_humidity);
		Log.d("Forecast printLog()" , "percipitation " + percipitation);
		Log.d("Forecast printLog()" , "snow " + snow);
		Log.d("Forecast printLog()" , "visibility " + visibility);
		Log.d("Forecast printLog()" , "uv " + uv);
		Log.d("Forecast printLog()" , "comfort " + comfort);
		Log.d("Forecast printLog()" , "frost " + frost);
	}

}
