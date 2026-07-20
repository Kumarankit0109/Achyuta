package com.example.achyuta.actions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.Settings

object AppActions {

    fun openApp(context: Context, appName: String): Boolean {

        val pm = context.packageManager
        val app = appName.lowercase().trim()

        val packageName = when (app) {
            "chrome" -> "com.android.chrome"
            "whatsapp" -> "com.whatsapp"
            "instagram" -> "com.instagram.android"
            "facebook" -> "com.facebook.katana"
            "telegram" -> "org.telegram.messenger"
            "gmail" -> "com.google.android.gm"
            "youtube" -> "com.google.android.youtube"
            "maps" -> "com.google.android.apps.maps"
            "play store" -> "com.android.vending"
            "photos", "gallery" -> "com.google.android.apps.photos"
            "calculator" -> "com.google.android.calculator"
            "calendar" -> "com.google.android.calendar"
            else -> null
        }

        // Try known package first
        if (packageName != null) {
            val intent = pm.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return true
            }
        }

        // Fallback: search launcher apps by label
        val launcherIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val apps = pm.queryIntentActivities(launcherIntent, 0)

        for (resolveInfo in apps) {
            val label = resolveInfo.loadLabel(pm).toString().lowercase()

            if (label.contains(app)) {
                val intent = pm.getLaunchIntentForPackage(resolveInfo.activityInfo.packageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    return true
                }
            }
        }

        return false
    }

    fun openCamera(context: Context): Boolean {
        return try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun openGallery(context: Context): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun openBrowser(context: Context): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun openSettings(context: Context): Boolean {
        return try {
            val intent = Intent(Settings.ACTION_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun openCalculator(context: Context): Boolean {

        val calculatorPackages = listOf(
            "com.google.android.calculator",
            "com.sec.android.app.popupcalculator",
            "com.miui.calculator",
            "com.coloros.calculator",
            "com.oneplus.calculator",
            "com.android.calculator2"
        )

        val pm = context.packageManager

        for (pkg in calculatorPackages) {
            val intent = pm.getLaunchIntentForPackage(pkg)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return true
            }
        }

        return false
    }

    fun openContacts(context: Context): Boolean {
        return try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                ContactsContract.Contacts.CONTENT_URI
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }
}