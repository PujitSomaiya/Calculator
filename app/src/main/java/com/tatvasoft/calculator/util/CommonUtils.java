package com.tatvasoft.calculator.util;

import android.text.TextUtils;
import android.widget.EditText;

public class CommonUtils {
    public static boolean isEmptyEditText(EditText edt)
    {
        return TextUtils.isEmpty(edt.getText().toString().trim());
    }
    public  static boolean isNotNull(EditText editText)
    {
        return editText!=null;
    }

}
