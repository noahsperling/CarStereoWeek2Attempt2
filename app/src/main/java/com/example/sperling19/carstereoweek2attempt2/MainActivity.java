package com.example.sperling19.carstereoweek2attempt2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private boolean powerOn = true;
    private boolean fm = true;
    private TextView display;
    private TextView volumeDisp;
    private Button volPlus;
    private Button volMinus;
    private Button tunerPlus;
    private Button tunerMinus;
    private Button amFm;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button powerButton;
    private SeekBar tuner;
    private int fmValue = 0;
    private int amValue = 0;
    int[] fmPresets = {28, 48, 68, 88, 108}; // we originally gave it 5 preset buttons and were
    int[] amPresets =  {20, 70, 120, 170, 220}; //given the ok, so we are only implementing 5 presets



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView)findViewById(R.id.StationDisplay);
        volumeDisp = (TextView)findViewById(R.id.VolumeDisplay);
        volPlus = (Button)findViewById(R.id.VolumePlus);
        volMinus = (Button)findViewById(R.id.VolumeMinus);
        tunerPlus =(Button)findViewById(R.id.TunerPlus);
        tunerMinus = (Button)findViewById(R.id.TunerMinus);
        amFm = (Button)findViewById(R.id.AMFMButton);
        one = (Button)findViewById(R.id.Preset1);
        two = (Button)findViewById(R.id.Preset2);
        three = (Button)findViewById(R.id.Preset3);
        four = (Button)findViewById(R.id.Preset4);
        five = (Button)findViewById(R.id.Preset5);
        powerButton = (Button)findViewById(R.id.PowerButton);
        tuner = (SeekBar)findViewById(R.id.TunerBar);
        tunerUpdateFm(fmValue);
        one.setOnLongClickListener(this);
        two.setOnLongClickListener(this);
        three.setOnLongClickListener(this);
        four.setOnLongClickListener(this);
        five.setOnLongClickListener(this);
        tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(powerOn) {
                    if (fm) {
                        fmValue = tuner.getProgress();
                        tunerUpdateFm(fmValue);
                    } else {
                        amValue = tuner.getProgress();
                        tunerUpdateAm(amValue);
                    }
                } else {
                    if(fm) {
                        tuner.setProgress(fmValue);
                    } else {
                        tuner.setProgress(amValue);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    public void powerButtonPressed(View v) {
        Log.i("powerButtonPressed", "The Power Button was pressed.");
        powerOn = !powerOn;
        tuner.setProgress(fmValue);

        if(!powerOn) {
            display.setTextColor(Color.BLACK);
            volumeDisp.setTextColor(Color.BLACK);
            volPlus.setBackgroundColor(Color.BLACK);
            volMinus.setBackgroundColor(Color.BLACK);
            tunerPlus.setBackgroundColor(Color.BLACK);
            tunerMinus.setBackgroundColor(Color.BLACK);
            amFm.setBackgroundColor(Color.BLACK);
            one.setBackgroundColor(Color.BLACK);
            two.setBackgroundColor(Color.BLACK);
            three.setBackgroundColor(Color.BLACK);
            four.setBackgroundColor(Color.BLACK);
            five.setBackgroundColor(Color.BLACK);
        } else {
            display.setTextColor(Color.rgb(0, 255, 0));
            volumeDisp.setTextColor(Color.rgb(0, 255, 0));
            volPlus.setBackgroundColor(Color.LTGRAY);
            volMinus.setBackgroundColor(Color.LTGRAY);
            tunerPlus.setBackgroundColor(Color.LTGRAY);
            tunerMinus.setBackgroundColor(Color.LTGRAY);
            amFm.setBackgroundColor(Color.LTGRAY);
            one.setBackgroundColor(Color.LTGRAY);
            two.setBackgroundColor(Color.LTGRAY);
            three.setBackgroundColor(Color.LTGRAY);
            four.setBackgroundColor(Color.LTGRAY);
            five.setBackgroundColor(Color.LTGRAY);

        }
    }
    public void amFmPressed(View v) {
        Log.i("AMFMPressed", "The AM/FM button was pressed.");
        if(!powerOn) {
            return;
        }
        fm = !fm;
        if(fm) {
            amValue = tuner.getProgress();
            tuner.setMax(198);
            tuner.setProgress(fmValue);
            tunerUpdateFm(tuner.getProgress());

        } else {
            fmValue = tuner.getProgress();
            tuner.setMax(1170);
            tuner.setProgress(amValue);
            tunerUpdateAm(tuner.getProgress());
        }
    }
    public void tunerPlusPressed(View v){
        Log.i("tunerPlusPressed", "Tuner Plus was pressed.");
        if(!powerOn) {
            return;
        }
        if(fm) {
            fmValue += 2;
            if (fmValue > 198) {
                fmValue = 0;
            }
            tuner.setProgress(fmValue);
            tunerUpdateFm(tuner.getProgress());
        } else {
            amValue += 10;
            if (amValue > 1170) {
                amValue = 0;
            }
            tuner.setProgress(amValue);
            tunerUpdateAm(tuner.getProgress());
        }
    }
    public void tunerMinusPressed(View v){
        Log.i("tunerMinusPressed", "Tuner Minus was pressed.");
        if(!powerOn) {
            return;
        }
        if(fm) {
            fmValue -= 2;
            if (fmValue < 0) {
                fmValue = 198;
            }
            tuner.setProgress(fmValue);
            tunerUpdateFm(tuner.getProgress());
        } else {
            amValue -= 10;
            if (amValue < 0) {
                amValue = 1170;
            }
            tuner.setProgress(amValue);
            tunerUpdateAm(tuner.getProgress());
        }
    }
    public void sliderBarSlid(View V) {
        Log.i("sliderSlid", "The tuner slider was slid.");

        if(!powerOn) {
            if (fm) {
                tuner.setProgress(fmValue);
                return;
            } else {
                tuner.setProgress(amValue);
                return;
            }

        }
        if(fm) {
            fmValue = tuner.getProgress();
            tunerUpdateFm(fmValue);
        } else {
            amValue = tuner.getProgress();
            tunerUpdateAm(amValue);
        }

    }
    private void tunerUpdateFm(int p) {
        int temp = 881;
        temp += p;
        display.setText(temp / 10 + "." + temp%10 + " FM");
    }
    private void tunerUpdateAm(int p) {
        int temp = 530;
        temp += p;
        display.setText(""+temp+ " AM");
    }
    public void presetButtonPressed(View V) {
         if(powerOn) {
             if (V.equals(one)) {
                presetApply(fmPresets[0], amPresets[0]);
             } else if (V.equals(two)) {
                 presetApply(fmPresets[1], amPresets[1]);
             } else if (V.equals(three)) {
                 presetApply(fmPresets[2], amPresets[2]);
             } else if (V.equals(four)) {
                 presetApply(fmPresets[3], amPresets[3]);
             } else if (V.equals(five)) {
                 presetApply(fmPresets[4], amPresets[4]);
             }
         } else {
             return;
         }
    }
    private void presetApply(int fmPres, int amPres){
        if(fm){
            tuner.setProgress(fmPres);
            tunerUpdateFm(fmPres);
            fmValue = fmPres;
        } else {
            tuner.setProgress(amPres);
            tunerUpdateAm(amPres);
            amValue = amPres;
        }

    }
    public boolean onLongClick(View V) {
        if (powerOn) {
            if(fm) {
                if (V.equals(one)) {
                    fmPresets[0] = fmValue;
                } else if (V.equals(two)) {
                    fmPresets[1] = fmValue;
                } else if (V.equals(three)) {
                    fmPresets[2] = fmValue;
                } else if (V.equals(four)) {
                    fmPresets[3] = fmValue;
                } else if (V.equals(five)) {
                    fmPresets[4] = fmValue;
                }
            } else {
                if (V.equals(one)) {
                    amPresets[0] = amValue;
                } else if (V.equals(two)) {
                    amPresets[1] = amValue;
                } else if (V.equals(three)) {
                    amPresets[2] = amValue;
                } else if (V.equals(four)) {
                    amPresets[3] = amValue;
                } else if (V.equals(five)) {
                    amPresets[4] = amValue;
                }
            }
        }
        return true;
    }

}
