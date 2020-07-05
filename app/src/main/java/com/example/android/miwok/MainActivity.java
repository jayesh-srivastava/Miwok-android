
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });



        TextView family=(TextView) findViewById(R.id.family);
        family.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyintent=new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyintent);
            }
        });


        TextView colors=(TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorintent=new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorintent);
            }
        });


        TextView phrases=(TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phraseintent=new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phraseintent);
            }
        });
    }
}
