package com.gitrepos.utils;

import android.content.DialogInterface;

public interface NativeDialogClickListener {

    void onPositive(DialogInterface dialog);

    void onNegative(DialogInterface dialog);

    void onNeutral(DialogInterface dialog);
}
