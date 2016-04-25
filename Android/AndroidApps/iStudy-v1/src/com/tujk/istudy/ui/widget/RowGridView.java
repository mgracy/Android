/**
 * 
 */
package com.tujk.istudy.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.GridView;

import com.tujk.istudy.R;

/**
 * title  : RowGridView.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class RowGridView extends GridView {

	/**
	 * @param context
	 */
	 private static String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
     
	    private Bitmap rowBackground;
	    private int rowBgHeight;
	    private int columnNum;
	              
	    public RowGridView(Context context, AttributeSet attrs){
	        super(context,attrs);
	        columnNum = attrs.getAttributeIntValue(NAMESPACE_ANDROID,"numColumns",3);
	        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.row_bg_grid_view);
	        rowBgHeight = a.getDimensionPixelSize(R.styleable.row_bg_grid_view_row_bg_height,50);
	        int row_bg_resource = a.getResourceId(R.styleable.row_bg_grid_view_row_background,-1);
	        rowBackground = BitmapFactory.decodeResource(getResources(),row_bg_resource);
	    }
	              
	    @Override
	    protected void dispatchDraw(Canvas canvas){
	        int rHeight = getChildAt(0).getHeight();
	        int width = getWidth();
	        int rowNum = (int)Math.ceil(getChildCount()/(double)columnNum);
	        Rect src = new Rect(0,0,rowBackground.getWidth(),rowBackground.getHeight());
	        for(int i=0,y=rHeight-(rowBgHeight/2); i<rowNum; i++,y += rHeight){
	            Rect dst = new Rect(0,y,width,y+rowBgHeight);
	            canvas.drawBitmap(rowBackground,src,dst,null);
	        }
	        super.dispatchDraw(canvas);
	    }
}
