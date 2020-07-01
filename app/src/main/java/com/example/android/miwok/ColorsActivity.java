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

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        List<Word> list=new ArrayList<Word>();
        list.add(new Word("weṭeṭṭi","red"));
        list.add(new Word("chokokki","green"));
        list.add(new Word("ṭakaakki","brown"));
        list.add(new Word("ṭopoppi","gray"));
        list.add(new Word("kululli","black"));
        list.add(new Word("kelelli","white"));
        list.add(new Word("ṭopiisә","dusty yellow"));
        list.add(new Word("chiwiiṭә","mustard yellow"));

        WordAdapter adapter= new WordAdapter(this,list);
        ListView listview=(ListView)findViewById(R.id.rootviewcolors);
        listview.setAdapter(adapter);
    }
}
