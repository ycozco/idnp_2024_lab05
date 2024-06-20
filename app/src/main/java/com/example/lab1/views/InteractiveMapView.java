package com.example.lab1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class InteractiveMapView extends View {
    private Paint paint;
    private OnMapClickListener listener;

    // Definimos las áreas de las galerías
    private GalleryArea[] galleryAreas;

    public InteractiveMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setTextSize(20);

        // Inicializar áreas de galerías
        galleryAreas = new GalleryArea[]{
                new GalleryArea("LA SALA 1", 0.02f, 0.02f, 0.45f, 0.20f),
                new GalleryArea("LA SALA 2", 0.55f, 0.02f, 0.98f, 0.20f),
                new GalleryArea("Galería VI", 0.02f, 0.25f, 0.35f, 0.45f),
                new GalleryArea("Galería V", 0.40f, 0.25f, 0.65f, 0.45f),
                new GalleryArea("Galería IV", 0.70f, 0.25f, 0.98f, 0.70f),
                new GalleryArea("Galería III", 0.02f, 0.50f, 0.35f, 0.70f),
                new GalleryArea("Galería II", 0.40f, 0.50f, 0.65f, 0.70f),
                new GalleryArea("Galería I", 0.02f, 0.75f, 0.98f, 0.95f)
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
    }

    private void drawMap(Canvas canvas) {
        // Fondo
        canvas.drawColor(Color.parseColor("#FFF8DC"));

        // Dibujar galerías y salas
        for (GalleryArea area : galleryAreas) {
            drawGallery(canvas, area.x1, area.y1, area.x2, area.y2, area.label);
        }
    }

    private void drawGallery(Canvas canvas, float x1, float y1, float x2, float y2, String label) {
        float width = getWidth();
        float height = getHeight();
        float left = width * x1;
        float top = height * y1;
        float right = width * x2;
        float bottom = height * y2;

        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(left, top, right, bottom, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);
        canvas.drawText(label, left + 10, top + 40, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            Log.d("InteractiveMapView", "Touched at: (" + x + ", " + y + ")");
            handleTouch(x, y);
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void handleTouch(float x, float y) {
        float width = getWidth();
        float height = getHeight();

        for (GalleryArea area : galleryAreas) {
            float left = width * area.x1;
            float top = height * area.y1;
            float right = width * area.x2;
            float bottom = height * area.y2;

            if (x >= left && x <= right && y >= top && y <= bottom) {
                Log.d("InteractiveMapView", "Touched area: " + area.label);
                if (listener != null) {
                    listener.onAreaClick(area.label);
                }
                break;
            }
        }
    }

    public void setOnMapClickListener(OnMapClickListener listener) {
        this.listener = listener;
    }

    public interface OnMapClickListener {
        void onAreaClick(String area);
    }

    private static class GalleryArea {
        String label;
        float x1, y1, x2, y2;

        GalleryArea(String label, float x1, float y1, float x2, float y2) {
            this.label = label;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
