package com.kof.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kof on 2016/4/27.
 */
public class Keypad extends Dialog {
    private final View keys[] = new View[9];
    private View keypad;
    private final int useds[];
    private final PuzzleView puzzleView;
    public Keypad(Context context, int useds[], PuzzleView puzzleView){
        super(context);
        this.useds = useds;
        this.puzzleView = puzzleView;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("Keypad");
        setContentView(R.layout.keypad);
        findViews();
        for (int element : useds){
            if (element != 0){
                keys[element - 1].setVisibility(View.INVISIBLE);
            }
        }
        setListener();
    }

    private void setListener() {
        for (int i = 0; i < keys.length; i++){
            final int t = i + 1;

            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(t);
                }
            });
        }

        keypad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResult(0);
            }
        });

    }

    private void returnResult(int tile) {
        puzzleView.setSelectedTile(tile);
        dismiss();
    }


    private void findViews() {
        keypad = findViewById(R.id.keypad);
            keys[0] = findViewById(R.id.keypad_1);
            keys[1] = findViewById(R.id.keypad_2);
            keys[2] = findViewById(R.id.keypad_3);
            keys[3] = findViewById(R.id.keypad_4);
            keys[4] = findViewById(R.id.keypad_5);
            keys[5] = findViewById(R.id.keypad_6);
            keys[6] = findViewById(R.id.keypad_7);
            keys[7] = findViewById(R.id.keypad_8);
            keys[8] = findViewById(R.id.keypad_9);

    }
}
