### Latest SDK Version : 4.3.0
[Download](https://raw.githubusercontent.com/gmobi/go2reach.sample.ads/master/app/libs/go2reach.ads_4.3.0.jar)
###Getting Started
Add sdk jar file to the ***libs*** folder of your Android project. Make sure the following permissions are placed in your AndroidManifest.xml
```xml
<!-- Basic -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<!-- Check Network Connection -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- GPS -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

<!-- Get Device ID : IMEI -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<!-- Get Device ID : WIFI MAC -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```
And add necessary activities and service 
```xml

<activity
    android:name="com.reach.PeerActivity"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" >
    <intent-filter>
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>

<service android:name="com.reach.ActionService" />

<receiver
    android:name="com.reach.ActionMonitor"
    android:enabled="true" >
    <intent-filter>
        <action android:name="android.intent.action.PACKAGE_ADDED" />
        <action android:name="android.intent.action.PACKAGE_REPLACED" />
        <action android:name="android.intent.action.PACKAGE_REMOVED" />

        <data android:scheme="package" />
    </intent-filter>
</receiver>
```
Register your application in Go2Reach backend and add the key as meta-data in  AndroidManifest.xml
```
<meta-data android:name="reach.ads.key" android:value="xxxxxxxxxxxx" />
```

### Banner Ad
* Get the ad service instance
```java
IAdService adService = AdServiceManager.get(context);
```
* Create a banner ad with a preferred unique placement name
```java
IBannerAd adTop = adService.getBannerAd("banner.top");
```
You could also get a specific size banner 
```java
IBannerAd adTop = adService.getBannerAd("banner.top", -1, 60, null); // full width x 60dp
IBannerAd adTop = adService.getBannerAd("banner.top", 320, 60, null); // 320dp x 60dp
```
* Create banner view and put it where you want the banner to display
```java
View banner = adTop.create();
FrameLayout flTop = (FrameLayout)this.findViewById(R.id.topbanner);
flTop.addView(banner);
```
You could make it auto reload with specific interval
```java
adTop.setReloadInterval(60); // make the banner auto reload every 60 seconds
```

### Interstitial Ad
* Get the ad service instance
```java
IAdService adService = AdServiceManager.get(context);
```
* Create an interstitial ad with a preferred unique placement name
```java
IInterstitialAd ad = adService.getInterstitialAd("interstitial.default");
```
You could also customize the size. Display an interstitial ad as 300dp x 500dp in protrait mode and  500dp x 300dp in landscape mode
```java
IInterstitialAd ad = adService.getInterstitialAd("interstitial.custom", 300, 250, 500, 300, null); 
```
Display an interstitial ad in full screen mode
```java
IInterstitialAd ad = adService.getInterstitialAd("interstitial.fullscreen", -1, -1, -1, -1, null); 
```
Make the interstitial ad display only in landscape mode
```java
IInterstitialAd ad = adService.getInterstitialAd("interstitial.landscape", 0, 0, 500, 300, null); 
					
```
* Popup the interstitial ad
```java
ad.popup();
```

### Native Ad
* Get the ad service instance
```java
IAdService adService = AdServiceManager.get(context);
```
* Create a native ad with a preferred unique placement name
```java
INativeAd ad1 = adService.getNativeAd("native.ad1", 50, 50, 1, null);
// create 1 native ad with 50dp x 50dp preferred size image
```
* Bind native ad elements with specific views
```java
final View container = this.findViewById(R.id.container);
IAdItem item = ad1.getAdItem(0);
item.bind(container,
				new String[]{IAdItem.ICON, IAdItem.TITLE, IAdItem.CALL_TO_ACTION}, 
				new int[]{R.id.ivNative, R.id.tvTitle, R.id.btnCta});
```
* Load the ad and make it visible 
```
container.setVisibility(View.GONE);
ad1.setOnLoadLisenter(new ICallback(){

	@Override
	public void call(int resultCode) {
		if (resultCode == IAd.OK){
					container.setVisibility(View.VISIBLE);
		}
				
	}
			
});
ad1.load();
```

### Proguard Config
```
-keep public class com.reach.* {
    public protected *;
}

-keep public class com.reach.widget.* {
    public protected *;
}

-keepclassmembers public class * extends com.reach.IService {
    public <init>(...);
}

-keepclassmembers public class * extends com.reach.IActivity {
    public <init>(...);
}

-keep public class * extends com.reach.IService

```
