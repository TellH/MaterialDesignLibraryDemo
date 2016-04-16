package com.tellh.materialdesignlibrarydemo;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

public class ColoredSnackbar {
    public static final int red = 0xfff44336;
    public static final int green = 0xff4caf50;
    public static final int blue = 0xff2195f3;
    public static final int orange = 0xffffc107;
    private static View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }
  
    private static Snackbar colorSnackBar(Snackbar snackbar, int colorId) {
        View snackBarView = getSnackBarLayout(snackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
        }
        return snackbar;
    }
  
    public static Snackbar info(Snackbar snackbar) {
        return colorSnackBar(snackbar, blue);
    }
  
    public static Snackbar warning(Snackbar snackbar) {
        return colorSnackBar(snackbar, orange);
    }
  
    public static Snackbar alert(Snackbar snackbar) {
        return colorSnackBar(snackbar, red);
    }
  
    public static Snackbar confirm(Snackbar snackbar) {
        return colorSnackBar(snackbar, green);
    }

    public static Snackbar info(@NonNull View view, @NonNull String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        colorSnackBar(snackbar,blue);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar warning(@NonNull View view, @NonNull String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        colorSnackBar(snackbar,orange);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar alert(@NonNull View view, @NonNull String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        colorSnackBar(snackbar,red);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar confirm(@NonNull View view, @NonNull String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        colorSnackBar(snackbar,green);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar display(@NonNull View view, @NonNull String text, int color) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        colorSnackBar(snackbar,color);
        snackbar.show();
        return snackbar;
    }
}
