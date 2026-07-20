package com.example.achyuta.actions

import android.content.Context

object AssistantActions {

    fun handleCommand(context: Context, command: String): Boolean {

        val text = command.lowercase().trim()

        // ---------------- Camera ----------------
        if (text.contains("camera")) {
            return AppActions.openCamera(context)
        }

        // ---------------- Gallery ----------------
        if (text.contains("gallery") ||
            text.contains("photos") ||
            text.contains("images")
        ) {
            return AppActions.openGallery(context)
        }

        // ---------------- Browser ----------------
        if (text.contains("browser")) {
            return AppActions.openBrowser(context)
        }

        // ---------------- Settings ----------------
        if (text.contains("settings")) {
            return AppActions.openSettings(context)
        }

        // Calculator
        if (text.contains("calculator") || text.contains("calculate")) {
            return AppActions.openCalculator(context)
        }



        // ---------------- Contacts ----------------
        if (text.contains("contacts") ||
            text.contains("contact")
        ) {
            return AppActions.openContacts(context)
        }

        // ---------------- Google Search ----------------
        if (text.startsWith("search ")) {

            val query = command.removePrefix("search").trim()

            if (query.isNotEmpty()) {
                return UtilityActions.googleSearch(context, query)
            }
        }

        if (text.contains("google")) {

            val query = text
                .replace("google", "")
                .trim()

            return UtilityActions.googleSearch(context, query)
        }

        // ---------------- YouTube Search ----------------
        if (text.contains("youtube") || text.contains("play")) {

            val query = text
                .replace("youtube", "")
                .replace("play", "")
                .trim()

            return UtilityActions.youtubeSearch(context, query)
        }

        if (text.startsWith("play ")) {

            val query = command.removePrefix("play").trim()

            if (query.isNotEmpty()) {
                return UtilityActions.youtubeSearch(context, query)
            }
        }

        // ---------------- Call ----------------
        if (text.startsWith("call ")) {

            val number = command.removePrefix("call").trim()

            if (number.isNotEmpty()) {
                return UtilityActions.callNumber(context, number)
            }
        }

        // ---------------- SMS ----------------
        if (text.startsWith("message ")) {

            val number = command.removePrefix("message").trim()

            if (number.isNotEmpty()) {
                return UtilityActions.sendSMS(context, number)
            }
        }

        if (text.startsWith("sms ")) {

            val number = command.removePrefix("sms").trim()

            if (number.isNotEmpty()) {
                return UtilityActions.sendSMS(context, number)
            }
        }

        // ---------------- Alarm ----------------
        if (text.contains("alarm")) {

            // Temporary default alarm
            return UtilityActions.setAlarm(
                context,
                hour = 7,
                minute = 0
            )
        }

        // ---------------- Timer ----------------
        if (text.contains("timer")) {

            // Temporary default timer
            return UtilityActions.setTimer(
                context,
                seconds = 60
            )
        }

        // ---------------- Open Apps ----------------
        if (text.startsWith("open ")) {

            val appName = text.removePrefix("open").trim()

            if (appName.isNotEmpty()) {
                return AppActions.openApp(context, appName)
            }
        }

        if (text.startsWith("launch ")) {

            val appName = command.removePrefix("launch").trim()

            if (appName.isNotEmpty()) {
                return AppActions.openApp(context, appName)
            }
        }

        if (text.startsWith("start ")) {

            val appName = command.removePrefix("start").trim()

            if (appName.isNotEmpty()) {
                return AppActions.openApp(context, appName)
            }
        }

        // Let Groq handle anything else
        return false
    }
}