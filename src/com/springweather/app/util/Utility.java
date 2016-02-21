package com.springweather.app.util;

import com.springweather.app.db.SpringWeatherDB;
import com.springweather.app.model.City;
import com.springweather.app.model.County;
import com.springweather.app.model.Province;

import android.text.TextUtils;

public class Utility {
	
	/**
	 * 解析、处理服务器返回来的省级数据
	 * 
	 * @param springWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(SpringWeatherDB springWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// 将解析出来的数据存储到Province表
					springWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 解析、处理服务器返回的市级数据
	 * 
	 * @param springWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public static boolean handleCitiesResponse(SpringWeatherDB springWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					// 将解析出来的数据存储到City表中
					springWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 解析处理器返回的县级数据
	 * 
	 * @param springWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public static boolean handleCountiesResponse(SpringWeatherDB springWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// 将解析出来的数据存储到County表
					springWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return true;
	}

}
