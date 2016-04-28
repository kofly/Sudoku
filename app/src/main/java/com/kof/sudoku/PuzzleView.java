package com.kof.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kof on 2016/4/25.
 */
public class PuzzleView extends View{
    private static final String TAG = "Sudoku";
    private final Game game;
    private float width;
    private float height;
    private int selX;
    private int selY;
    private final Rect selRect = new Rect();

    public PuzzleView(Context context){
        super(context);
        this.game = (Game)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        width = w/9f;
        height = h/9f;
        getRect(selX, selY, selRect);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int)(x*width), (int)(y*height), (int)(x*width + width), (int)(y*height+height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the background ...

        //Draw the board
        //minor grid
        Paint board = new Paint();
        board.setColor(getResources().getColor(R.color.board_minor));
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * height, getWidth(), i * height, board);
        }

        for (int j = 0; j < 9; j++) {
            canvas.drawLine(j*width, 0, j*width, getHeight(), board);
        }
        //major grid
        board.setColor(getResources().getColor(R.color.board_major));
        for (int i = 0; i <= 9; i++) {
            if (i % 3 != 0)
                continue;
            canvas.drawLine(0, i * height, getWidth(), i * height, board);
        }

        for (int j = 0; j <= 9; j++) {
            if (j % 3 != 0)
                continue;
            canvas.drawLine(j*width, 0, j*width, getHeight(), board);
        }

    //Draw the numbers
        Paint foreground = new Paint();
        foreground.setColor(getResources().getColor(R.color.foreground));
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextSize(height * 0.75f);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = foreground.getFontMetrics();
        float x = width / 2;
        float y = height / 2 - (fm.ascent + fm.descent)/2;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                canvas.drawText(this.game.getTileString(i, j), i*width + x, j*height + y, foreground);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);
        select((int)(event.getX()/width), (int)(event.getY()/height));

        game.showKeypadOrError(selX , selY);
        return true;
    }

    private void select(int x, int y) {
        invalidate(selRect);
        selX = Math.min(Math.max(x, 0), 8);
        selY = Math.min(Math.max(y, 0), 8);
        getRect(selX, selY, selRect);
        invalidate(selRect);
    }

    public void setSelectedTile(int tile) {
        if(game.setTileIfValid(selX, selY, tile))
            invalidate();
    }
}
