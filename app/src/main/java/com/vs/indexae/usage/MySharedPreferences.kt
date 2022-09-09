package com.vs.indexae.usage
import android.content.*

object MySharedPreferences {
    private const val APP_SETTINGS = "com.vs.indexae"
    private fun getSharedPreferencesManager(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
    }

    /**
     * method to set string value
     * @param context context of the application
     * @param KEY key of the value
     * @param Value value
     */
    fun setStringValue(context: Context, KEY: String?, Value: String?) {
        val editor = getSharedPreferencesManager(context).edit()
        editor.putString(KEY, Value)
        editor.apply()
    }

    /**
     * method to get the value from the following key
     * @param context context of the application
     * @param KEY key of the value
     * @param DefaultValue Default Value
     */
    @JvmStatic
    fun getStringValue(context: Context, KEY: String?, DefaultValue: String?): String? {
        return getSharedPreferencesManager(context).getString(KEY, DefaultValue)
    }

    /**
     * method to set values in the integer files of the applications..
     * @param context context of the application
     * @param KEY Key of the application
     * @param Value value of the application
     */
    fun setIntegerValue(context: Context, KEY: String?, Value: Int) {
        val editor = getSharedPreferencesManager(context).edit()
        editor.putInt(KEY, Value)
        editor.apply()
    }

    /**
     * method to get the value from the following key
     * @param context context of the application
     * @param KEY key of the value
     * @param DefaultValue Default Value
     */
    fun getBooleanValue(context: Context, KEY: String?, DefaultValue: Boolean): Boolean {
        return getSharedPreferencesManager(context).getBoolean(KEY, DefaultValue)
    }

    /**
     * method to set values in the integer files of the applications..
     * @param context context of the application
     * @param KEY Key of the application
     * @param Value value of the application
     */
    fun setBooleanValue(context: Context, KEY: String?, Value: Boolean) {
        val editor = getSharedPreferencesManager(context).edit()
        editor.putBoolean(KEY, Value)
        editor.apply()
    }

    /**
     * method to get the value from the following key
     * @param context context of the application
     * @param KEY key of the value
     * @param DefaultValue Default Value
     */
    fun getIntegerValue(context: Context, KEY: String?, DefaultValue: Int): Int {
        return getSharedPreferencesManager(context).getInt(KEY, DefaultValue)
    }
}