package com.example.lab1.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InteractiveMapView extends View {
    private Paint paint;
    private OnMapClickListener listener;
    private List<MapElement> elements;

    public InteractiveMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setTextSize(20);

        elements = new ArrayList<>();
        populateMapElements();
    }

    private void populateMapElements() {
        // Aquí se definen las coordenadas para los elementos del mapa basado en el plano proporcionado

        // Ejemplo de áreas verdes
        elements.add(new MapElement(MapElement.ElementType.AREA_VERDE, 50, 50, 150, 150, "Área Verde"));

        // Ejemplo de portales
        elements.add(new MapElement(MapElement.ElementType.PORTAL, 250, 50, 300, 100, "Portal"));

        // Ejemplo de puertas (como trapecios largos con poca altura)
        elements.add(new MapElement(MapElement.ElementType.PUERTA, 350, 50, 500, 70, "Puerta"));

        // Ejemplo de ventanas (como trapecios largos con poca altura)
        elements.add(new MapElement(MapElement.ElementType.VENTANA, 550, 50, 700, 70, "Ventana"));

        // Ejemplo de pinturas (círculos)
        elements.add(new MapElement(MapElement.ElementType.PINTURA, 800, 50, 850, 100, "Pintura"));

        // Ejemplo de banquetas
        elements.add(new MapElement(MapElement.ElementType.BANQUETA, 50, 250, 100, 300, "Banqueta"));

        // Añadir más elementos y ubicaciones según el plano proporcionado
        // Ejemplo de delimitación de galerías
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 100, 400, 0, 0, "GALERÍA I"));
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 300, 400, 0, 0, "GALERÍA II"));
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 500, 400, 0, 0, "GALERÍA III"));
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 700, 400, 0, 0, "GALERÍA IV"));
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 900, 400, 0, 0, "GALERÍA V"));
        elements.add(new MapElement(MapElement.ElementType.TEXTO, 1100, 400, 0, 0, "GALERÍA VI"));

        // Delimitación de galerías con líneas
        elements.add(new MapElement(MapElement.ElementType.LINEA, 50, 200, 1200, 200, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 50, 600, 1200, 600, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 50, 1000, 1200, 1000, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 50, 1400, 1200, 1400, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 50, 1800, 1200, 1800, ""));

        elements.add(new MapElement(MapElement.ElementType.LINEA, 200, 50, 200, 1800, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 600, 50, 600, 1800, ""));
        elements.add(new MapElement(MapElement.ElementType.LINEA, 1000, 50, 1000, 1800, ""));

        // Añadir más elementos según el plano específico proporcionado
        elements.add(new MapElement(MapElement.ElementType.PINTURA, 50, 650, 100, 700, "Pintura 1"));
        elements.add(new MapElement(MapElement.ElementType.PINTURA, 150, 650, 200, 700, "Pintura 2"));
        elements.add(new MapElement(MapElement.ElementType.PINTURA, 250, 650, 300, 700, "Pintura 3"));

        // Completar con más elementos según sea necesario
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap(canvas);
    }

    private void drawMap(Canvas canvas) {
        // Fondo
        canvas.drawColor(Color.parseColor("#EAC555"));

        // Dibujar todos los elementos del mapa
        for (MapElement element : elements) {
            element.draw(canvas, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            handleTouch(x, y);
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void handleTouch(float x, float y) {
        for (MapElement element : elements) {
            if (element.contains(x, y)) {
                if (listener != null) {
                    listener.onAreaClick(element.getLabel());
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
}