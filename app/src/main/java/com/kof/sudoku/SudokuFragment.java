package com.kof.sudoku;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Kof on 2016/4/24.
 */
public class SudokuFragment extends Fragment{
    private Button startButton;
    private Button continueButton;

    public static SudokuFragment newInstance() {
        SudokuFragment fragment = new SudokuFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sudoku, parent, false);

        startButton = (Button)v.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewGameDialog();
            }
        });

        continueButton = (Button)v.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(Game.DIFFICULTY_CONTINUE);
            }
        });
        return v;


    }

    private void openNewGameDialog() {
        new AlertDialog.Builder(getActivity()).
                setTitle("difficulty")
                .setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        startGame(i);
                    }
                })
                .show();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sudoku_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(getActivity(), Prefs.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startGame(int i){
        Intent intent = new Intent(getActivity(), Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }

}
