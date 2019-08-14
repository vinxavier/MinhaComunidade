package br.edu.ufabc.padm.minhacomunidade

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class App : Application() {

    companion object {
        lateinit var context: Context
            private set


        fun registerBroadcast(receiver: BroadcastReceiver, filter: IntentFilter) {
            LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter)
        }

        fun unregisterBroadcast(receiver: BroadcastReceiver) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
        }

        fun sendBroadcast(intent: Intent) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }
    }


    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

}