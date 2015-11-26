package com.appspot.pistatium.ssmemo

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.widget.TextView
import com.appspot.pistatium.ssmemo.models.BooleanPref
import com.appspot.pistatium.ssmemo.models.MemoModel

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
        super.onCreate()
        loadAppFont()
        if (BooleanPref.IS_FIRST_LAUNCH.get(applicationContext)) {
            createInitialMemo()
            BooleanPref.IS_FIRST_LAUNCH.set(applicationContext, false)
        }
    }


    fun setAppFont(view: TextView) {
        app_font.let {
            view.typeface = app_font
        }
    }

    private fun loadAppFont(): Unit {
        if (app_font != null) {
            return
        }
        val font_file = unzipFile("fonts/JKG-M_3.zip")
        app_font = Typeface.createFromFile(font_file)
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

    private fun createInitialMemo() {
        val model = MemoModel(applicationContext)
        val memo = model.create()
        model.beginTransaction()
        memo.memo = getString(R.string.welcome_memo)
        model.commitTransaction()
    }
}
