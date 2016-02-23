package com.springweather.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
/**
 * BaseActivity作为基类，可以被后面所有要实现透明状态栏的Activity都要extends基类
 * 原因是，要实现透明状态栏的特性，需要import android.support.v7.app.AppCompatActivity包，
 * 而BaseActivity作为android.support.v7.app.AppCompatActivity的实现类，
 * 通过supportRequestWindowFeature(Window.FEATURE_NO_TITLE)实现取消系统标题栏
 * 
 * @author Allenieo
 *
 */
public class BaseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 取消系统的标题栏
		 */
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
