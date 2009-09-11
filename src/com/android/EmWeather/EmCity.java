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

import java.util.Comparator;

public class EmCity implements Comparator<EmCity> {
	public String name_en = new String("");
	public String name_fr = new String("");
	public String code = new String("");
	public String province_code = new String("");
	
	public boolean iscomplete() {
		if ((!this.name_en.equals("")) && (!this.name_fr.equals("")) && (!this.province_code.equals("")) && (!this.code.equals("")))
			return true;
		else
			return false;
	}

	public int compare(EmCity city1, EmCity city2){
		return city1.name_en.compareTo(city2.name_en);
	}
	
}