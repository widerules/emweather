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

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PrefDialog extends Dialog {
	
	public interface SaveHook {
		public void save_preferences(String prov, String city, String sitecode, String sitecode_url);
	} 
	
	private SaveHook save_hook;
	
	Spinner city_spinner, prov_spinner;
	TextView sitecode_text, sitecode_url_text;
	String sitecode, sitecode_url, selected_prov, selected_city;
	ArrayAdapter<String> adapter_city;
	EmXmlCityCodeReader city_reader;
	boolean restore_prov, restore_city;
	Context main_context;
	
	public PrefDialog(Context context, SaveHook save_hook) {
		super(context);
		main_context = context;
		this.save_hook = save_hook;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        prov_spinner = (Spinner) findViewById (R.id.spin_prov);
        city_spinner = (Spinner) findViewById (R.id.spin_city);
        sitecode_text = (TextView) findViewById (R.id.text_sitecode);
        sitecode_url_text = (TextView) findViewById (R.id.text_sitecode_url);
       
        city_reader = new EmXmlCityCodeReader();
        city_reader.updateCityList();

        List<EmCity> city_list = city_reader.getCityList(true); 
        List<String> prov_list = city_reader.GetProvList(true);

        adapter_city = new ArrayAdapter<String>(main_context, android.R.layout.simple_spinner_item);
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                    
        city_spinner.setAdapter(adapter_city);           

        ArrayAdapter<String> adapter_prov = new ArrayAdapter<String>(main_context, android.R.layout.simple_spinner_item, prov_list.toArray(new String[0]));
        adapter_prov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                    
        prov_spinner.setAdapter(adapter_prov);           

        prov_spinner.setOnItemSelectedListener(new ProvSpinnerClicker());
        city_spinner.setOnItemSelectedListener(new CitySpinnerClicker()); 

        // Restore preferences
        SharedPreferences preferences = main_context.getSharedPreferences(EmWeather.PREF_FILE, 0);
        selected_prov = preferences.getString("selected_prov", "");
        selected_city = preferences.getString("selected_city", "");
        sitecode = preferences.getString("sitecode", "");
        sitecode_url = preferences.getString("sitecode_url", "");

        List<String> city_name_list = city_reader.GetCityNameListByProv(selected_prov, true);
        adapter_city = new ArrayAdapter<String>(adapter_city.getContext(), android.R.layout.simple_spinner_item, city_name_list.toArray(new String[0]));
        adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                    
        city_spinner.setAdapter(adapter_city);

        //Semaphores so that selecting a province or city does not trigger the on-click events
        restore_prov = true;
        restore_city = true;
        
        prov_spinner.setSelection(prov_list.indexOf(selected_prov));
        city_spinner.setSelection(city_name_list.indexOf(selected_city));
        
        sitecode_text.setText(sitecode);
        sitecode_url_text.setText(sitecode_url);

        Button button_save = (Button) findViewById(R.id.button_save);
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        button_save.setOnClickListener(new SaveListener()); 
        button_cancel.setOnClickListener(new CancelListener()); 
    
    
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    }

    
    class ProvSpinnerClicker implements Spinner.OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			if (restore_prov) {
				restore_prov = false;
			} else {
				selected_prov = (String) prov_spinner.getSelectedItem(); 

				List<String> city_name_list = city_reader.GetCityNameListByProv(selected_prov, true);
				adapter_city = new ArrayAdapter<String>(adapter_city.getContext(), android.R.layout.simple_spinner_item, city_name_list.toArray(new String[0]));
				adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                    
				city_spinner.setAdapter(adapter_city);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			adapter_city.clear();
		}

    }

    class CitySpinnerClicker implements Spinner.OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			if (restore_city) {
				restore_city = false;
			} else {
				selected_prov = (String) prov_spinner.getSelectedItem(); 
				selected_city = (String) city_spinner.getSelectedItem();             
				sitecode = new String(city_reader.GetSiteCodeByName(selected_city));
				sitecode_url = new String(EmWeather.BASE_URL + selected_prov + "/" + sitecode + "_e.xml");

				sitecode_text.setText(sitecode);
				sitecode_url_text.setText(sitecode_url);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
            sitecode = new String("");
            sitecode_url = new String("");
            sitecode_text.setText("");
            sitecode_url_text.setText("");
		}

    }

    private class SaveListener implements android.view.View.OnClickListener {
        @Override
        public void onClick(View v) {
        	//Save preferences
            SharedPreferences preferences = main_context.getSharedPreferences(EmWeather.PREF_FILE, 0);
        	SharedPreferences.Editor editor = preferences.edit();
        	editor.putString("selected_prov", selected_prov);
        	editor.putString("selected_city", selected_city);
        	editor.putString("sitecode", sitecode);
        	editor.putString("sitecode_url", sitecode_url);
        	
        	editor.commit();
        	
        	save_hook.save_preferences(selected_prov, selected_city, sitecode, sitecode_url);
        	PrefDialog.this.dismiss();
        }
   } 

    private class CancelListener implements android.view.View.OnClickListener {
        @Override
        public void onClick(View v) {
        	PrefDialog.this.cancel();
        }
   } 

}
