package com.appspot.pistatium.ssmemo

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.widget.TextView

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Created by kimihiro on 2015/11/13.
 */
class SSMemoApplication : Application() {

    private var app_font: Typeface? = null
    override fun onCreate() {
        //アセットからTypeface作成
        super.onCreate()
        loadAppFont()
    }

    fun loadAppFont() {
        if (app_font != null) {
            return
        }

        val font_file = unzipFile("fonts/JKG-M_3.zip")
        app_font = Typeface.createFromFile(font_file)
    }

    fun setAppFont(view: TextView) {
        if (app_font != null) {
            view.typeface = app_font
        }
    }

    private fun unzipFile(filename: String): String {
        val unzip_name = (filename + ".unzip").replace("/", "_")
        val unzip_path = filesDir.toString() + "/" + unzip_name
        if (File(unzip_path).exists()) {
            return unzip_path
        }

        try {
            val fos = openFileOutput(unzip_name, Context.MODE_PRIVATE)
            val zip = ZipInputStream(assets.open(filename))
            zip.nextEntry
            zip.copyTo(fos)
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return unzip_path
    }
}
