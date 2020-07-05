
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer media;
    AudioManager audio;
    AudioManager.OnAudioFocusChangeListener listener;

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
                audio=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                listener=new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
                        {
                            media.start();
                        }
                        else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
                        {
                            releaseM();
                        }
                        else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                        {
                            media.pause();
                            media.seekTo(0);
                        }
                    }
                };
                int result=audio.requestAudioFocus(listener , AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(NumbersActivity.this, word.getMusic());
                    media.start();
                    media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseM();
                        }
                    });
                }
            }
        });
    }
    private void releaseM()
    {
        if(media!=null) {
            media.release();
            media = null;
            audio.abandonAudioFocus(listener);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        releaseM();
    }
}
