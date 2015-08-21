# Introduction #

If you love EmWeather and want to help me make it better.  There are a couple of ways to do so.


# Details #

**First**, you can make a bug report on the "Issues" page of this website.  This will make sure that I see the error and that I won't forget about it like I might with an email.  Please describe the error in as much detail as possible, the version you are using, which widgets you are using, and the phone you have.

**Secondly**, if you are more familiar with your phone, I would LOVE some debugging info, particularly for crashes.

If you have the Android SDK installed or android-tools (or a standalone version of adb), after a crash plug your phone in, enable USB debugging and type the following in a terminal window: `adb logcat`.  Scroll up until you find the section that has "EmWeather" information (a sample of this is found below), copy that whole section and paste it into a text file and post it on the issues page or email it to me.  This will realllly help me find the error quickly.

Thanks and enjoy this lovely program!

Sample errors from "adb logcat":

```
E/AndroidRuntime( 1501): Uncaught handler: thread main exiting due to uncaught exception
E/AndroidRuntime( 1501): java.lang.NullPointerException
E/AndroidRuntime( 1501): 	at com.EmWeather.EmWeather.IconMaps.getForecastIconString(IconMaps.java:417)
E/AndroidRuntime( 1501): 	at com.EmWeather.EmWeather.EmWeather.updateGuiWithForecast(EmWeather.java:254)
E/AndroidRuntime( 1501): 	at com.EmWeather.EmWeather.EmWeather.access$0(EmWeather.java:208)
E/AndroidRuntime( 1501): 	at com.EmWeather.EmWeather.EmWeather$1.handleMessage(EmWeather.java:427)
E/AndroidRuntime( 1501): 	at android.os.Handler.dispatchMessage(Handler.java:99)
E/AndroidRuntime( 1501): 	at android.os.Looper.loop(Looper.java:123)
E/AndroidRuntime( 1501): 	at android.app.ActivityThread.main(ActivityThread.java:4363)
E/AndroidRuntime( 1501): 	at java.lang.reflect.Method.invokeNative(Native Method)
E/AndroidRuntime( 1501): 	at java.lang.reflect.Method.invoke(Method.java:521)
E/AndroidRuntime( 1501): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:868)
E/AndroidRuntime( 1501): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:626)
E/AndroidRuntime( 1501): 	at dalvik.system.NativeStart.main(Native Method) 
```