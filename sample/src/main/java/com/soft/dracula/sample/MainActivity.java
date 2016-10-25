package com.soft.dracula.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.soft.dracula.funty.FuntyText;

/**
 * Created by lion on 10/25/16.
 */
public class MainActivity extends AppCompatActivity {

    private EditText cabinFontField1;
    private TextView cabinFontField2;
    private EditText cabinFontField3;
    private TextView cabinFontField4;

    private EditText raleWayFontField1;
    private TextView raleWayFontField2;
    private EditText raleWayFontField3;
    private TextView raleWayFontField4;

    private EditText bungeeFontField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cabinFontField1 = (EditText) findViewById(R.id.cabin_style_1);
        cabinFontField2 = (TextView) findViewById(R.id.cabin_style_2);
        cabinFontField3 = (EditText) findViewById(R.id.cabin_style_3);
        cabinFontField4 = (TextView) findViewById(R.id.cabin_style_4);

        raleWayFontField1 = (EditText) findViewById(R.id.raley_style_1);
        raleWayFontField2 = (TextView) findViewById(R.id.raley_style_2);
        raleWayFontField3 = (EditText) findViewById(R.id.raley_style_3);
        raleWayFontField4 = (TextView) findViewById(R.id.raley_style_4);

        bungeeFontField = (EditText) findViewById(R.id.lazy_load);

        FuntyText
                .onFields(cabinFontField1, cabinFontField2, cabinFontField3, cabinFontField4)
                .fontName("cabin")
                .apply();

        FuntyText
                .onFields(raleWayFontField1, raleWayFontField2, raleWayFontField3, raleWayFontField4)
                .fontName("raleway")
                .apply();

        FuntyText
                .onFields(bungeeFontField)
                .fontName("bungee_inline")
                .apply();
    }
}
