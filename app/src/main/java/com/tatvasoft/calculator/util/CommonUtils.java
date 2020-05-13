package com.tatvasoft.calculator.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

public class CommonUtils {
    public static boolean isEmptyEditText(EditText edt)
    {
        return TextUtils.isEmpty(edt.getText().toString().trim());
    }
    public  static boolean isNotNull(EditText editText)
    {
        return editText!=null;
    }
    public static  void stethoInitialize(Context context) {
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());
    }
}
