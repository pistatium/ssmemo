package com.appspot.pistatium.ssmemo;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by kimihiro on 2015/11/13.
 */
public class SSMemoApplication extends Application {

    private Typeface app_font;
    @Override
    public void onCreate() {
        //アセットからTypeface作成
        super.onCreate();
        loadAppFont();
    }

    public void loadAppFont(){
        if (app_font != null){
            return;
        }

        String font_file = unzipFile("fonts/JKG-M_3.zip");
        app_font = Typeface.createFromFile(font_file);
    }

    public void setAppFont(TextView view){
        if(app_font != null){
            view.setTypeface(app_font);
        }
    }

    private String unzipFile(String filename) {
        String unzip_name = (filename + ".unzip").replace("/", "_");
        String unzip_path = getFilesDir().toString() + "/" + unzip_name;
        if (new File(unzip_path).exists()) {
            return unzip_path;
        }
        try {
            AssetManager am	= getResources().getAssets();
            InputStream is	= am.open(filename, AssetManager.ACCESS_STREAMING);
            ZipInputStream zis	= new ZipInputStream(is);
            ZipEntry ze	= zis.getNextEntry();

            if (ze != null) {

                FileOutputStream fos = openFileOutput(unzip_name, MODE_PRIVATE);
                byte[] buf = new byte[1024];
                int size = 0;

                while ((size = zis.read(buf, 0, buf.length)) > -1) {
                    fos.write(buf, 0, size);
                }
                fos.close();
                zis.closeEntry();
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unzip_path;
    }
}
