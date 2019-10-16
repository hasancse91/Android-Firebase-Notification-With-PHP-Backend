package com.hellohasan.android_firebase_notification.utils

import android.content.Context
import android.content.SharedPreferences


class Preferences private constructor() {


    var versionCode: Long
        get() = sharedPreferences!!.getLong(VERSION_CODE, -1)
        set(versionCode) {
            editor?.putLong(VERSION_CODE, versionCode)
            editor?.apply()
            editor?.commit()
        }

    companion object {

        val VERSION_CODE = "version_code"

        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        private val preferences = Preferences()


        fun getInstance(context: Context): Preferences {
            sharedPreferences =
                context.getSharedPreferences("sharedPreferences_data", Context.MODE_PRIVATE)
            editor = sharedPreferences?.edit()
            return preferences
        }
    }

}