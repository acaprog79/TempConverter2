package com.alejandria.Project1_TempConverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.text.NumberFormat;

public class TempConverterActivity extends AppCompatActivity implements OnEditorActionListener{

    private EditText mfarenheitED;
    private TextView mconvertTV;

    private SharedPreferences savedValues;

    private String inputString="";
    private float result= 15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);

        mfarenheitED = (EditText) findViewById(R.id.farenheitED);
        mconvertTV = (TextView) findViewById(R.id.convertTV);

        mfarenheitED.setOnEditorActionListener(this);

        savedValues = getSharedPreferences("savedValues", MODE_PRIVATE);
    }
    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("mfarenheitED", inputString);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        inputString = savedValues.getString("mfarenheitED","");

        calculateAndDisplay();
    }

    public void calculateAndDisplay() {
        inputString = mfarenheitED.getText().toString();
        float amount;
        if (inputString.equals("")){
            amount=0;
        }
        else {
            amount = Float.parseFloat(inputString);
        }

        float totalamount = (amount-32)*5/9;

        NumberFormat number = NumberFormat.getNumberInstance();
        mconvertTV.setText(number.format(totalamount));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            calculateAndDisplay();
        }
        return false;
    }
}
