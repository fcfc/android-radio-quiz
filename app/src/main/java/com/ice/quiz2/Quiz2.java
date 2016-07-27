package com.ice.quiz2;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class Quiz2 extends Activity {

    TabHost tabHost;
    private ListView listView;
    int numOfRecords = 500;
    int index = 0;
    int correctAnswers;
    int totalAnswers;

    private List<Answer> answerList = new ArrayList<Answer>();
    String xmlFile = "xml/technicianClass.xml";               // put this value in setup files

    String correcttext = "";
    Button nextButton;
    Button   randomButton;
    Button   answerButton;
    TextView question;
    TextView answera;
    TextView answerb;
    TextView answerc;
    TextView answerd;
    TextView score;
    Answer   currentAnswer;
    Answer   correctAnswer;


    int correct;
    int total;
    private GestureDetector gestureDetector;


    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources res = getResources(); // Resource object to get Drawables

        nextButton = (Button) findViewById(R.id.nextButton);
        randomButton = (Button) findViewById(R.id.randomButton);
        answerButton = (Button) findViewById(R.id.answerButton);
        question = (TextView)findViewById(R.id.question);
        answera = (TextView)findViewById(R.id.a);
        answerb = (TextView)findViewById(R.id.b);
        answerc = (TextView)findViewById(R.id.c);
        answerd = (TextView)findViewById(R.id.d);
        score = (TextView)findViewById(R.id.score);

        Intent intent = getIntent();
        xmlFile = intent.getStringExtra("dataset");

     //   PreferenceScreen prefSet = getPreferenceScreen();
     //   mListPreference = (ListPreference) prefSet.findPreference(SERVER);
        nextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                index += 1;
                if (index >= answerList.size())
                    index = 0;
                currentAnswer = answerList.get(index);
                printQuestion(currentAnswer);

            }
        });

        randomButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int theSize = answerList.size();
                index = (int)Math.ceil(Math.random()*theSize);
                if (index >= theSize)
                    index = 0;
                currentAnswer = answerList.get(index);
                printQuestion(currentAnswer);

            }
        });

        answerButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                correctAnswer = answerList.get(index);
                getCorrectAnswer(correctAnswer);
            }
        });

        answera.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct.equals("A")) {
                    answera.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answera.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct.equals("B")) {
                    answerb.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerb.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerc.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct.equals("C")) {
                    answerc.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerc.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        answerd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (currentAnswer.correct.equals("D")) {
                    answerd.setBackgroundColor(Color.GREEN);
                    correct++;
                } else {
                    answerd.setBackgroundColor(Color.RED);
                }
                updateScore();
            }
        });

        // get xml file from preferences
        // xmlFile =
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        XmlPullParser xrp = grabXML(xmlFile);
        getAnswerFromXML(xrp);
        currentAnswer = answerList.get(index);
        printQuestion(currentAnswer);
    }

    public void onClick(View v) {
        int item = v.getId();
        return;
    }

    public void onPause(Bundle savedInstanceState) {
    }

    public void onResume(Bundle savedInstanceState) {
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); inflater.inflate(R.menu.mainmenu, menu); return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.menu_prefs) {
// Launch to our preferences screen. Intent intent = new Intent().setClass(this, com.androidbook.preferences.sample.FlightPreferenceActivity.class);
            this.startActivityForResult(intent, 0); }
        return true;
    }
*/


    public void printQuestion(Answer a)
    {

        question.setText(a.question);
        answera.setText("A. " + a.a);
        answerb.setText("B. " + a.b);
        answerc.setText("C. " + a.c);
        answerd.setText("D. " + a.d);

        answera.setBackgroundColor(0x777777);
        answerb.setBackgroundColor(0x777777);
        answerc.setBackgroundColor(0x777777);
        answerd.setBackgroundColor(0x777777);
    }

    public void updateScore()
    {
        total++;
        score.setText(correct + "/" + total);
    }

    public void getCorrectAnswer(Answer a)
    {

        if (a.correct.equals("A")) {
            answera.setBackgroundColor(Color.WHITE);
        }
        if (a.correct.equals("B")) {
            answerb.setBackgroundColor(Color.WHITE);
        }
        if (a.correct.equals("C")) {
            answerc.setBackgroundColor(Color.WHITE);
        }
        if (a.correct.equals("D")) {
            answerd.setBackgroundColor(Color.WHITE);
        }
    }


    public XmlPullParser grabXML(String xmlFile)
    {
        // OPEN XML FILE
        InputStream istr = null;
        XmlPullParserFactory factory = null;
        XmlPullParser xrp = null;

        try {
            istr = this.getAssets().open(xmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            factory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        factory.setNamespaceAware(true);

        try {
            xrp = factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        try {
            xrp.setInput(istr, "UTF-8");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return xrp;
    }



    public void getAnswerFromXML(XmlPullParser xrp)
    {
        int i = 0;
        int eventType = 0;
        String tagtext = "";
        String qtext = "";
        String atext = "";
        String btext = "";
        String ctext = "";
        String dtext = "";



        try {
            eventType = xrp.getEventType();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        int  begin = 0;

        String starttag = "";

        while (eventType != XmlPullParser.END_DOCUMENT) {

            if(eventType == XmlPullParser.START_DOCUMENT) {
            }
            else if(eventType == XmlPullParser.START_TAG) {
                starttag = xrp.getName();
                if (starttag.equals("question"))  {
                    begin = 1;
                }
            }
            else if(eventType == XmlPullParser.END_TAG) {
                String endtag = xrp.getName();
                starttag = "";
                if (endtag.equals("question"))  {
                    begin = 0;
                    answerList.add(new Answer(qtext, qtext, atext, btext, ctext, dtext, correcttext));
                }
            }
            else if(eventType == XmlPullParser.TEXT) {
                tagtext = xrp.getText();

                if (starttag.equals("questiontext"))
                        qtext = tagtext;
                else if (starttag.equals("a"))
                        atext = tagtext;
                else if (starttag.equals("b"))
                        btext = tagtext;
                else if (starttag.equals("c"))
                        ctext = tagtext;
                else if (starttag.equals("d"))
                        dtext = tagtext;
                else if (starttag.equals("correct"))
                        correcttext = tagtext;


            }

            try {
                eventType = xrp.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}