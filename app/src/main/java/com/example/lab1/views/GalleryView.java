package com.example.lab1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GalleryView extends View {
    private Paint paint;
    private String galleryName;

    public GalleryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        galleryName = "";
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGallery(canvas);
    }

    private void drawGallery(Canvas canvas) {
        // Fondo
        canvas.drawColor(Color.parseColor("#FFF8DC"));

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Contenido de " + galleryName, 50, 100, paint);

        // Aquí puedes agregar más lógica para dibujar los elementos específicos de la galería
        // Por ejemplo, pintar cuadros, puertas, ventanas, etc.
    }
}
