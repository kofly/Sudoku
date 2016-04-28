package com.kof.sudoku;

import android.support.v4.app.Fragment;

public class SudokuActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SudokuFragment.newInstance();
    }
}
