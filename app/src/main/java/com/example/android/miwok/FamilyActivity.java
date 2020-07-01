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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        List<Word> list=new ArrayList<Word>();
        list.add(new Word("әpә","father"));
        list.add(new Word("әṭa","mother"));
        list.add(new Word("angsi","son"));
        list.add(new Word("tune","daughter"));
        list.add(new Word("taachi","older brother"));
        list.add(new Word("chalitti","younger brother"));
        list.add(new Word("teṭe","older sister"));
        list.add(new Word("kolliti","younger sister"));
        list.add(new Word("ama","grandmother"));
        list.add(new Word("paapa","grandfather"));

        WordAdapter adapter=new WordAdapter(this,list);
        ListView listview=(ListView)findViewById(R.id.rootviewfamily);
        listview.setAdapter(adapter);

    }
}
