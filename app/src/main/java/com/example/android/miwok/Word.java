package com.example.android.miwok;

public class Word
{
    private String MiwokTranslation;
    private String DefaultTranslation;
    public Word(String MiwokTranslation, String DefaultTranslation)
    {
        this.MiwokTranslation=MiwokTranslation;
        this.DefaultTranslation=DefaultTranslation;
    }
    public String getMiwokTranslation()
    {
        return MiwokTranslation;
    }
    public String getDefaultTranslation()
    {
        return DefaultTranslation;
    }

}
