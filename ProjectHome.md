**BIG News**

The newest version of EmWeather, 0.9.7.2, is the last free version I will be releasing.  I apologize to my long-term users but I am no longer able to continue developing this application for free.  However, I will be offering an inexpensive version on the Google Play Market that will offer many new features, more frequent releases, and hopefully an overall better user experience.  I hope to very shortly add radar maps, better themeing, and more.  Please consider supporting EmWeather financially by purchasing the new paid version.

The paid version will be available at a special price of $1.99 for the rest of 2012 in order to give a little bonus to my old users.  Starting in 2013, the price will be slightly higher.

All the money I earn from developing EmWeather will go directly to paying off my student loans :)

Thank you for your continued support,
Emilie




If you would like EmWeather to be in your own language, and can help with simple translation, see the item in the Issues section entitled "Adding additional locales"

Please note: I am now only supporting Android 2.1 (Eclair, API 7) and up.

Also, please feel free to email me feedback/support.


**About EmWeather**

EmWeather is a free, Canadian weather application for Android that uses environment canada weather feeds for it's data.android:icon="@drawable/icon"

It displays the current conditions as well as 8 forecasted conditions with the ability to quickly read the full details for each forecast.  Very detailed extra forecast information is available through the menu.

Environment Canada provides extremely detailed forecasts that are often very accurate.  Having access to the full forecast information is often lacking in weather applications for mobile devices.  EmWeather seeks to address this.

The current version is 0.9.6.7.2 Beta.  This means that it is very functional and looks swell.  There may be some bugs with it however.  Please feel free to open an Issue if there are any bugs preventing you from enjoying this app.


**Latest Changelog**

EmWeather 0.9.7.2
  * Last free version!!!
  * early morning lows display before highs in main application
  * more cities added by default to locations - now default list covers half the population of canada (you may need to reset your databases under "troubleshoot" to see them)
  * added more options to the widget update rates (10 and 15 minutes) by request
  * little bug fixes

EmWeather 0.9.7
  * Major widget overhaul!
    * New backgrounds
    * Added city name to 2x2 widgets
    * Added clickable settings and refresh icons
    * Better support for Android 4.0 and up
    * Fluid layout
    * No more double/triple clicking
  * Main App additions:
    * Added action-bar support for devices without a menu button
    * Added city name
    * Added done/close buttons to a number of dialogs to unify interface
    * If no city is selected, the selection dialog appears automatically
    * What's New text should now appear only once

EmWeather 0.9.6.3
  * fixed the width in the "extras" menu, which was squashed, especially on Ice Cream Sandwich devices (updated to fix word wrapping error).
  * fixed the major null-pointer error in the widgets.  This also fixes a regression in 0.9.6.2 which prevented new widgets from auto-updating.
  * added both highs and lows to the main app and widgets now allow you to choose to default to highs or lows.

EmWeather 0.9.6.1
  * EmWeather no longer defaults to Montréal if there is an error with the location
  * A troubleshooting menu has been added with a database reset option (this may help some users with location/theme problems)
  * Fixed a bug where app language preferences would sometimes not be saved/restored correctly.
  * A few slight interface tweaks here and there.

EmWeather 0.9.6.0
  * The majority of the iconsets have been moved to another APK (EmWeatherIconsets) to reduce download and application size
  * Added a "Weather data obtained from Environment Canada" banner to main app at the request of Environment Canada
  * Fixed changing of city font size in widgets.
  * Fixed rounding error for negative numbers (it was rounding up not down).

EmWeather 0.9.5.6.7
  * Minimum API version is now 7 (Android 2.1 Eclipse)
  * Changes to make locations easier to deal with:
    * - changes are automatic to location in main config
    * - in widget configuration, save button is always visible so people remember to press "save" after changing location
    * - each widget now launches the application with it's own location settings
  * Some null-pointer fixes
  * Fixed the error where sometimes people would get forecast info for the default city (montreal) instead of their configured city. (you may have to uninstall completely and re-install to fix this)
  * Please open a bug report and let me know if these errors are not fixed for you!  Enjoy!

EmWeather 0.9.5.6.6
  * Fixed location automatic detection
  * Added a few progress bars so the GUI doesn't seem to lock up when adding a new location

EmWeather 0.9.5.6.4
  * NOTE: This update will reset your app and widget location settings, I apologize for the inconvenience, but I think you will appreciate the changes.
  * Overhauled location selection
  * Reduced download size by 3mb - there is a bug in the Eclipse export function that  includes the drawables directory twice, if I compile by hand I can save 3mb.
  * Fixed new font themeing in app so all the fonts get themed
  * Fixed some clicker bugs for the widgets
  * Little bug fixes
  * Enjoy :)

EmWeather 0.9.5.6.3
  * Totally overhauled the themeing mechanism!
  * Added customizable themes for both the app and the widget
  * App has a new dark theme
  * Font sizes, colours, and styles are customizable!
  * I would like feedback on the new themeing mechanisms: do you like it, is it intuitive, is it laggy?
  * Coming soon: downloadable iconpacks

EmWeather 0.9.5.6 (Experimental)
  * This contains the new themeing mechanisms.  Please test this out and let me know what you think (I am not uploading it to the market until I've ironed out any remaining bugs).
  * In particular I would like to know:
    * do you find it intuitive?
    * does if feel slower than before?
    * are you getting any strange bugs or crashes?

  * Expect a release later this week.  Once the new themeing mechanism is in place, I will be moving many of the icon packs into downloadable components to reduce the file size of the basic app.

EmWeather 0.9.5.5.2
  * changes made to locale/language within app now take place immediately

EmWeather 0.9.5.5
  * Added french translation
  * Fixed bug that was showing only abbreviated forecast details, full details should now be shown
  * Added support for icon mappings included in the environment canada forecasts, should improve icon accuracy for the envcanada icon set and for when there is no official condition forecasted
  * Fixed some spelling mistakes
  * Few, smaller bugs

EmWeather 0.9.5.4.5
  * Included a fix from Terence that prevents force closes when rotating widgets in 2.3 with LauncherPro.  Thanks for the patch!
  * Fixed bug where if you configured a widget, every new click would bring up the config screen again.

EmWeather 0.9.5.4.4
  * fixed force closes when no condition given (occurs often for small towns) [note: this was supposed to be in the last update but was not for some reason, apologies.]
  * increased gesture sensitivity in app, slightly better but still doesn't always change day smoothly, needs work still.

EmWeather 0.9.5.4.2
  * Fixed display of wind in text summary
  * Better formatting of early morning lows and highs in forecasts in text summary
  * Fixed (hopefully) a crash when there is no condition listed (out of bounds string index exception)

EmWeather 0.9.5.4.1
  * Fixed some formatting errors in the long descriptions
  * Improved gestures in main app
  * Added verbosity for "Lows": if there is a high and a low, the low refers to the early morning of that day, not the evening of that day.  Thanks to Environment Canada for pointing this out to me.

EmWeather 0.9.5.4
  * Fixed widget configuration menu
  * Added gestures in main app to change from day-to-day
  * Made the city-list static, saves time and bandwidth changing cities
  * Reduced API requirements to 1.6.

EmWeather 0.9.5.3
  * Some little tweaks...

EmWeather 0.9.5.2
  * Added windchill and storm warnings to the 4x4 widget as well.

EmWeather 0.9.5.1
  * Complete GUI overhaul for main app
  * Added storm warnings/watches
  * Added windchill, pressure, humidity
  * Shortened wind display
  * Added forecast time
  * Incorporated fix so that "drizzle" is considered very light rain as opposed to heavy rain (thanks to Adam for the patch, sorry for the delay in applying it)
  * Only for Android 2.2 and higher.
  * More changes coming soon.

EmWeather 0.9.4.9 Beta
  * Small bug fixes, exception checking for app/config launch from a widget

EmWeather 0.9.4.8 Beta
  * Ok folks, so I am proud of this release so far.  Lots of things I've been meaning to add.  I think the widgets are almost nailed.
  * The big news: I added the ability to click, double-click, and triple-click on the widgets to refresh the widget, launch the app, or re-configure the widget.  These actions are customizable in the widget config!  For the 4x4 app, the "clicking" area is the text area (clicking on forecast icons still gives the detailed forecast)
  * Added a new widget background "ghost", very pale, translucent, white. (Inspired by digi-clock).
  * Added "auto-detect location" buttons to widget config and app config.
  * Redesigned the app config slightly.
  * Some bug fixes under the hood.
  * Note: if auto-detect is not working for you, try launching google maps, use "my location", then immediately try to reconfigure emweather or the widget and hit "auto-detect".
  * These release seems stable running 7 widgets at a time.  Please let me know if you are having trouble, if you love/hate the changes, and things you would like to see in the future.  It looks like I squashed the "not-responding" bugs, please let me know if you are having any issues with this.
  * Enjoy!

EmWeather 0.9.4.7 Beta
  * I had moved widget updating to a service but I did not have this service running in it's own thread.  I have done this now -> should clear up the ResolveInfo timeouts.
  * Default widget background is now white
  * clicking 4x4 long description text launches the app.

EmWeather 0.9.4.5 Beta
  * Minor bug-fix: in widget configuration, if it fails to fetch the city list for the internet, it aborts the widget configuration rather than crashing.

EmWeather 0.9.4.4 Beta
  * Removed auto-location detection from widget configuration - it slows things down too much.  I will add a button to launch auto-location detection in the future.  It is still enabled in the application (and new widgets will use the application settings by default).
  * Tarball is the same as 0.9.4.3, just with auto-detection commented out in the widget config.

EmWeather 0.9.4.3 Beta
  * Major overhaul of widget configuration.
  * Individually configurable widgets - you can now have weather from several cities on your desktop.
  * Added a 2x1 widget size by request - now up to 8 weather widgets on one home screen.

EmWeather 0.9.4.1 Beta
  * Major overhaul of Widget backend.
  * Internet fetches moved out of main thread to an Activity, will hopefully remove any remaining ResolveInfo timeouts in widgets.
  * Fixed bug that would mess up clicks on widgets if multiple instances of the same size of widget were in use.  Any number of widgets should work now.
  * Major widget code clean-up.
  * Ready for per-widget configurations, so you can have weather in Montreal, Toronto, and Vancouver all on your phone at the same time :) - coming next!
  * Note: I'm not pushing this to the market as there are no major fixes that most users will notice and I want to do a longer testing period - some of my earlier updates were too buggy.  Please download this APK and let me know how it runs for you :)

EmWeather 0.9.4 Beta
  * Configurable widget backgrounds!  I finally figured out a nasty hack to make this happen!
  * Configurable app backgrounds as well!
  * Hoping to add more themeing soon (font size/color, per-widget color and city choices, etc.)

EmWeather 0.9.3.8.4-blackwidgets Beta
  * Added an alternative download with black widgets for those who use black themes.  Looks fun with flatwhite icon set.

EmWeather 0.9.3.8.4 Beta
  * Fixed widget force closes on 1.5 Cupcake and 1.6 Donut
  * NOTE: You may need to re-add your widgets if coming from a previous version

EmWeather 0.9.3.8.3 Beta
  * Bug fixes, some regression bugs
  * More checking for bad/no/half-working internet connections
  * Better way of using icon sets, mildly faster
  * Seems pretty stable here.

EmWeather 0.9.3.8.2 Beta
  * Fixed the icon mappings so "Sunny" is a sunshine, not a rain-cloud :)  Whoops.

EmWeather 0.9.3.8.1 Beta
  * Removed Um and weather.com icon packs, to avoid licensing issues and reduce file-size
  * Fixed auto city detect regression

EmWeather 0.9.3.8 Beta
  * Hopefully fixed the timeout errors on data fetching (most noticeable in the widgets if you go underground)
  * Also, added the ability to choose new icon sets!
  * Enjoy!

EmWeather 0.9.3.7.7 Beta:
  * Hopefully squashed the remaining widget FCs... please let me know if they are working for you
  * Corrected rounding errors in widgets (thanks Clayton)
  * Added preliminary automatic location detection.  To try it, just go to "change city", if it works, your spinners should be updated and a pop-up will inform you that your city was correctly detected.  Obviously, this requires gps, wifi, or mobile internet.

EmWeather 0.9.3.7.5 Beta:
  * Seems like I got most of the force-closes
  * Sometimes this means it's going to show blank temperatures or no icons - this is preferable to force closing the app I think.


EmWeather 0.9.3.7.4 Beta:
  * Ooops, messed up the day names in 0.9.7.3.
  * Also some more FCs,

Note:  Most of these FCs are being caused by xml errors.  I am not sure why but the Android URL fetching or the Android XML parser occasionally lose characters.  This means that my code can never trust a value will be there, even if the rest of the xml file was received properly... it's a bit tricky to catch all of these errors.  I apologize again for the flurry of new versions.

EmWeather 0.9.3.7.3 Beta:
  * Bughunt... fixed more crashes
  * Sorry for all the updates on the marketplace.  Everytime I push a new version, I find a new bug :)

EmWeather 0.9.3.7.2 Beta:
  * Hopefully fixed random crashes on weather fetch (app and widget) due to null-pointer exception.

EmWeather 0.9.3.7.1 Beta:
  * Truncated city names longer than 10 chars in 4x4 and 4x2 widgets

EmWeather 0.9.3.7 Beta:
  * Overhauled the backend of the widgets - updating bugs should be fixed
  * Because updating is now Alarm based, battery drain should be decreased as it will not update while phone is sleeping.
  * Changed the default widget background to white to increase visibility
  * Added update-rate configuration.

Notes: Currently the only way to configure the update-rate is to add a new EmWeather widget.  Also, the update rates will be the same for all EmWeather widgets running on your phone.  Ie. if you have 4 widgets, the rate for all 4 of them will be the last rate you set.  On that subject: environment canada only updates at most every 15 minutes, having an update rate greater than 10 minutes is a waste of battery/bandwidth.

For the future: I would like to add some functionality to the app itself (auto location detection, customization) and leave the widgets alone for awhile.  Please let me know if there are issues with the widgets in this version either via email or the issue tracker.

EmWeather 0.9.3.6 Beta:
  * Handles no city being set more gracefully
  * Small bug fixes
  * Coming soon: automatic location, status bar temperature (if I can figure it out...)

EmWeather 0.9.3.5 Beta:
  * Small bug fix: error message missing remote\_viewer when clicking on current weather.  Could potentially propogate to orientation errors.  Fixed with this.  Not pushing to Market (going to wait for more enhancements)

EmWeather 0.9.3.4 Beta:
  * Sorry for all the small updates.  Had to fix the major bug with 4x4 widget, should be fine with orientation changes now.
  * Also, shrunk the 4x2 widget slightly so it actually fits in a 4x2 space :)

EmWeather 0.9.3.3 Beta:
  * There are now 3 widgets, 2x2, 4x2, and 4x4.  Still hardcoded to update every 10 minutes but there is now a refresh button.  I made a mistake in 0.9.3.2 so that the icons would update but the verbose forecasts would not, this is now fixed.
  * Both app and widget are fully moved to dip (density independent pixels), hopefully this will help with newer devices with bigger screens.
  * Both app and widget now have the verbose forecasts examine to try and prevent duplicate mentions of Wind, UV, etc.
  * There is a bug with the 4x4 widget: for some reason if you change the orientation, android redraws the app but does not refresh it... this includes not activating the refresh button!  So far, I have had no lucking figuring out how to intercept the orientation change.  Working on it...

EmWeather 0.9.3.2 Beta:
  * There is now a 2x2 Widget and a 4x4 Widget, both are fairly functional.  Please let me know what you think.  Personally, the 4x4 seems pretty nice to me :)
  * The widgets are hardcoded to update every 10mins, I will add a configuration screen soon
  * I accidentally bumped the sdk version to 2.1 for the last few releases, sorry, it is now back to 1.5 (should work on most android devices).
  * I can't figure out how to make a status-bar widget, if someone knows how to do this, let me know.
  * There is still no error checking in the widgets to make sure the city is set.  Please set the city in the application first before using the widgets.

EmWeather 0.9.3.1 Beta:
  * Work in progress on the widgets.  There is a 2x2 and a 4x4.  The 2x2 now takes you to the app when you click it.  The 4x4 is basically set-up but non-clickable right now.  Remember to set the city first in the app.

EmWeather 0.9.3 Beta:
  * I have begun actively developing again, sorry this has taken awhile
  * This release provides some changes under the hood and an experimental 2x2 widget.  The widget is hardcoded to update every 10 minutes.  Note: set your city first in the app before using the widget.
  * Things that are coming soon: 4x4 widget, configuration options for the widgets, status-bar temperature, address the bug reports logged on google market, automatic location.
  * Things that will come this summer: different icon sets, ability to load custom icons sets, ability to change text colours, whatever else I have time for :)


EmWeather 0.9.1 Beta Release

  * Fixed bug that showed everyday as an "Evening"
  * Added some additional checks against null and non-properly-terminated Strings.  The SAX reader occasionally acts up so I hope this will help.


EmWeather 0.9 Beta Release

Changes:

- Major GUI Overhaul: transparencies, background, re-designed icon layout, + many more
- Added extra forecast information
- Determine if there is a network connection or not
- Show progress bars on internet access
- published on Android Market!