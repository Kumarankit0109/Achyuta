package com.example.achyuta.actions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock

object UtilityActions {

    fun googleSearch(context: Context, query: String): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=${Uri.encode(query)}")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun youtubeSearch(context: Context, query: String): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=${Uri.encode(query)}")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun callNumber(context: Context, number: String): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:$number")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun sendSMS(context: Context, number: String, message: String = ""): Boolean {
        return try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$number")
                putExtra("sms_body", message)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun setAlarm(
        context: Context,
        hour: Int,
        minute: Int,
        label: String = "Achyuta Alarm"
    ): Boolean {
        return try {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, hour)
                putExtra(AlarmClock.EXTRA_MINUTES, minute)
                putExtra(AlarmClock.EXTRA_MESSAGE, label)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun setTimer(
        context: Context,
        seconds: Int,
        label: String = "Achyuta Timer"
    ): Boolean {
        return try {
            val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                putExtra(AlarmClock.EXTRA_MESSAGE, label)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }
}