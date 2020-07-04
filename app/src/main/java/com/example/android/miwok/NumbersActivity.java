/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final List<Word> words=new ArrayList<Word>();
        words.add(new Word("lutti","one",R.raw.number_one,R.drawable.number_one));
        words.add(new Word("otiiko","two",R.raw.number_two,R.drawable.number_two));
        words.add(new Word("tolookosu","three",R.raw.number_three,R.drawable.number_three));
        words.add(new Word("oyissa","four",R.raw.number_four,R.drawable.number_four));
        words.add(new Word("massoka","five",R.raw.number_five,R.drawable.number_five));
        words.add(new Word("temmokka","six",R.raw.number_six,R.drawable.number_six));
        words.add(new Word("kenekaku","seven",R.raw.number_seven,R.drawable.number_seven));
        words.add(new Word("kawinta","eight",R.raw.number_eight,R.drawable.number_eight));
        words.add(new Word("wo'e","nine",R.raw.number_nine,R.drawable.number_nine));
        words.add(new Word("na'aacha","ten",R.raw.number_ten,R.drawable.number_ten));

        WordAdapter adapter=new WordAdapter(this,words, R.color.category_numbers);
        ListView listview=(ListView) findViewById(R.id.rootviewnumbers);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                releaseM();
                media=MediaPlayer.create(NumbersActivity.this, word.getMusic());
                media.start();
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast toast=Toast.makeText(NumbersActivity.this, "Done!",Toast.LENGTH_SHORT);
                        toast.show();
                        releaseM();
                    }
                });
            }
        });
    }
    private void releaseM()
    {
        if(media!=null)
            media.release();
        media=null;
    }
}
