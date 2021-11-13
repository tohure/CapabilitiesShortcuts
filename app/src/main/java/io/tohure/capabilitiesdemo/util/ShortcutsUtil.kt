package io.tohure.capabilitiesdemo.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import io.tohure.capabilitiesdemo.R

object ShortcutsUtil {

    fun setDynamicShortcut(context: Context) {
        ShortcutManagerCompat.pushDynamicShortcut(context, getShortCutInfo(context))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setPinnedShortcut(context: Context) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            val pinnedShortcutIntent =
                ShortcutManagerCompat.createShortcutResultIntent(context, getShortCutInfo(context))

            val pinnedShortcutCallback = PendingIntent.getBroadcast(
                context,
                0,
                pinnedShortcutIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            ShortcutManagerCompat.requestPinShortcut(
                context,
                getShortCutInfo(context),
                pinnedShortcutCallback.intentSender
            )
        }
    }

    private fun getShortCutInfo(context: Context) =
        ShortcutInfoCompat.Builder(context, "website")
            .setShortLabel("Abrir Github")
            .setLongLabel("Abrir Github de ejemplo")
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_webview))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/Tohure/CapabilitiesShortcuts")
                )
            )
            .build()
}