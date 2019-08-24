package com.appsnipp.travelnotes

/**
 * Created by kapil on 20/01/17.
 */
import android.content.Context
import android.content.SharedPreferences


class DarkModePrefManager(internal var _context: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    // shared pref mode
    internal var PRIVATE_MODE = 0

    val isNightMode: Boolean
        get() = pref.getBoolean(IS_NIGHT_MODE, true)


    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setDarkMode(isFirstTime: Boolean) {
        editor.putBoolean(IS_NIGHT_MODE, isFirstTime)
        editor.commit()
    }

    companion object {

        // Shared preferences file name
        private val PREF_NAME = "education-dark-mode"

        private val IS_NIGHT_MODE = "IsNightMode"
    }

}