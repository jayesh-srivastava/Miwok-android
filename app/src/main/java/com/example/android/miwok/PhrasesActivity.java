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

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        List<Word> list=new ArrayList<Word>();
        list.add(new Word("minto wuksus","Where are you going?"));
        list.add(new Word("tinnә oyaase'nә","What is your name?"));
        list.add(new Word("oyaaset...","My name is..."));
        list.add(new Word("michәksәs?","How are you feeling?"));
        list.add(new Word("kuchi achit","I’m feeling good."));
        list.add(new Word("әәnәs'aa?","Are you coming?"));
        list.add(new Word("hәә’ әәnәm","Yes, I’m coming."));
        list.add(new Word("әәnәm","I'm coming."));
        list.add(new Word("yoowutis","Let’s go."));
        list.add(new Word("әnni'nem","Come here."));

        WordAdapter adapter=new WordAdapter(this,list);
        ListView listview=(ListView)findViewById(R.id.rootviewphrases);
        listview.setAdapter(adapter);
    }
}
