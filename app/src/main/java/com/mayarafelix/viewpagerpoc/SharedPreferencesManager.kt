package com.mayarafelix.viewpagerpoc

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private var sharedPreferences: SharedPreferences? = null
    private const val firstTimeUserTag: String = "firstTimeUser"

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getFirstTimeUserTag(): Boolean {
        return sharedPreferences?.getBoolean(firstTimeUserTag, true) ?: true
    }

    fun removeFirstTimeUserTag() {
        sharedPreferences?.edit()?.remove(firstTimeUserTag)?.apply()
    }

    fun updateFirstTimeUserTag() {
        sharedPreferences?.edit()?.putBoolean(firstTimeUserTag, false)?.apply()
    }
}