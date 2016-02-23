# SpringWeather

####一个简单的Android天气应用，实现了TranslucnetSystemBar特效，兼容了Android6.0、5.0、4.4等版本
----------------
###下面主要介绍·下TranslucnetSystemBar·的实现过程：
 - New一个·BaseActivity·作为基类实现·android.support.v7.app.AppCompatActivity·兼容包，因为透明栏需要v7包兼容，所有·Activity·都需要·extends·这个基类，才能实现透明栏效果，另外·onCreate()·方法中·supportRequestWindowFeature(Window.FEATURE_NO_TITLE)·实现取消系统标题栏，这里不需要系统标题栏，我们自己在布局文件中设置就好，代码如下：

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

- 设置所有·Activity·的布局文件，在根布局的加入·android:background="#484e61"·和·android:fitsSystemWindows="true"·标签，·background·属性值需要和标题布局保持一致，即为·#484e61·，·fifsSyatemWindows·属性设为·true·,保证整个·View·不会上移与系统状态栏重合。
- 为了适配不同版本，在·res·目录下分别创建·values-v19·和·values-v21·这两个文件夹，分别创建·styles.xml·文件，设置一下下面这四个属性：

 <item name="android:windowTranslucentStatus">false</item>
 <item name="android:windowTranslucentNavigation">true</item>
 <item name="android:windowDrawsSystemBarBackgrounds">true</item>
 <item name="android:statusBarColor">布局文件中指定的状态栏以及标题栏的颜色</item>


·values/styles.xml·代码如下：
    <resources>
    
        <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
            <item name="colorPrimary">#3f51b5</item>
            <item name="colorPrimaryDark">#303f9f</item>
            <item name="colorAccent">#ff4081</item>
        </style>
        
        <style name="ColorTranslucentTheme" parent="AppBaseTheme">
        </style>
    
    </resources>

·values-v19/styles.xml·代码如下：


 <resources>
 
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">#3f51b5</item>
        <item name="colorPrimaryDark">#303f9f</item>
        <item name="colorAccent">#ff4081</item>
    </style>
    
    <style name="ColorTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="android:windowTranslucentStatus">false</item>
        <item name="android:windowTranslucentNavigation">true</item>
        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
        <item name="android:statusBarColor">#484e61</item>
    </style>

</resources>

·values-v21/styles.xml·代码如下：
        <resources>
    
        <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
            <item name="colorPrimary">#3f51b5</item>
            <item name="colorPrimaryDark">#303f9f</item>
            <item name="colorAccent">#ff4081</item>
        </style>
        
        <style name="ColorTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
            <item name="android:windowTranslucentStatus">false</item>
            <item name="android:windowTranslucentNavigation">true</item>
            <item name="android:windowDrawsSystemBarBackgrounds">true</item>
            <item name="android:statusBarColor">#484e61</item>
        </style>
        
        <!-- 
        	<item name="android:windowTranslucentStatus">false</item>
    		<item name="android:windowTranslucentNavigation">true</item>
    		<item name="android:windowDrawsSystemBarBackgrounds">true</item>
    		<item name="android:statusBarColor">布局文件中指定的状态栏以及标题栏的颜色</item>
         -->
    
    </resources>


有必要对上面的·styles.xml·文件做一下说明，我们可以看到在·values·,·values-v19·,·values-v21·三个文件夹下的·styles.xml·文件都设置了·ColorTranslucentTheme·这样一个·theme·属性，·values·文件夹下的·theme·属性不做任何设置，·values-v19·下的·theme·属性和·values-v21·下的一样，都设置了·windowTranslucentStatus·,·windowTranslucentNavigation·,·windowDrawsSystemBarBackgrounds·,·statusBarColor·这个四个值，分别表示状态栏透明为·false·，导航栏透明为·true·，绘制系统栏背景为·true·，状态栏颜色为·#484e61·。

- 到这里你就完成了前步，最后需要到清单文件中为所有引用·ColorTranslucentTheme·的·theme·属性的·Activity·都添加一个·theme·标签，并且为·Application·标签的设置·android:supportsRtl="true"·属性。代码如下如所示：




    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.springweather.app.activity.ChooseAreaActivity"
            android:theme="@style/ColorTranslucentTheme"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity 
            android:name="com.springweather.app.activity.WeatherActivity"
            android:theme="@style/ColorTranslucentTheme" >
        </activity>
        <service android:name="com.springweather.app.service.AutoUpdateService" ></service>
        <receiver android:name="com.springweather.app.receiver.AutoUpdateReceiver" ></receiver>
    </application>




 

