package com.example.rydenassignment1bobs_pizza;

import android.widget.CompoundButton;

public class RadioGroupCheckListener implements CompoundButton.OnCheckedChangeListener {

    private CompoundButton[] allies;

    /**
     * public generator - indicate allies
     * @param allies all other buttons in the same RadioGroup
     */
    public RadioGroupCheckListener(CompoundButton... allies){
        this.allies = allies;
    }

    /**
     * listener for a button to turn other allies unchecked when it turn checked
     * @param buttonView change check occur with this button
     * @param isChecked result of changing
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            for (CompoundButton aly : allies) {
                aly.setChecked(false);
            }
        }
    }

    private static CompoundButton[] exceptMe(CompoundButton[] buttons, int me){
        CompoundButton[] result = new CompoundButton[buttons.length-1];
        for(int i=0,j=0;i<buttons.length;i++){
            if(i==me){
                continue;
            }
            result[j]=buttons[i];
            j++;
        }
        return result;
    }

    /**
     * static function to create a RadioGroup
     * if a button turn to checked state all other buttons is group turn unchecked
     * @param buttons the buttons that we want to act like RadioGroup
     */
    public static void makeGroup(CompoundButton... buttons){
        for(int i=0;i<buttons.length;i++){
            buttons[i].setOnCheckedChangeListener(new RadioGroupCheckListener(exceptMe(buttons, i)));
        }
    }
}
