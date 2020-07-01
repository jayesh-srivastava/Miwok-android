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

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        List<Word> words=new ArrayList<Word>();
        words.add(new Word("lutti","one"));
        words.add(new Word("otiiko","two"));
        words.add(new Word("tolookosu","three"));
        words.add(new Word("oyissa","four"));
        words.add(new Word("massoka","five"));
        words.add(new Word("temmokka","six"));
        words.add(new Word("kenekaku","seven"));
        words.add(new Word("kawinta","eight"));
        words.add(new Word("wo'e","nine"));
        words.add(new Word("na'aacha","ten"));

        WordAdapter adapter=new WordAdapter(this, words);
        ListView listview=(ListView) findViewById(R.id.rootviewnumbers);
        listview.setAdapter(adapter);
    }
}
