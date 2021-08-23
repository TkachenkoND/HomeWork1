package com.example.lucky_ticket_java;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText number;
    ImageView lamp;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.editText);
        lamp = findViewById(R.id.lamp);
        drawable = getDrawable(R.drawable.shape);
    }

    public void changeColor(View view){
        String num = number.getText().toString().trim();

        if (TextUtils.isEmpty(num) || num.length() != 6 ) {
            Toast.makeText(getApplicationContext(), "incorrectly entered number", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isLucky(num)){
            drawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            lamp.setBackground(drawable);
        }else {
            drawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            lamp.setBackground(drawable);
        }

    }

    static boolean isLucky(String num) {
        return sumOfDigits(num,0) == sumOfDigits(num,num.length() - 3);
    }

    static int sumOfDigits(String num, int start) {
        int sum = 0;
        for (int i = start; i < start + 3; ++i)
            sum += Character.digit(num.charAt(i), 10);
        return sum;
    }
}