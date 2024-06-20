package com.example.lab1.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class MapElement {
    public enum ElementType {
        AREA_VERDE, PORTAL, PUERTA, VENTANA, PINTURA, BANQUETA, TEXTO, LINEA
    }

    private ElementType type;
    private float x1, y1, x2, y2;
    private String label;

    public MapElement(ElementType type, float x1, float y1, float x2, float y2, String label) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
    }

    public void draw(Canvas canvas, Paint paint) {
        switch (type) {
            case AREA_VERDE:
                paint.setColor(Color.GREEN);
                canvas.drawRect(x1, y1, x2, y2, paint);
                break;
            case PORTAL:
                paint.setColor(Color.YELLOW);
                canvas.drawRect(x1, y1, x2, y2, paint);
                break;
            case PUERTA:
                paint.setColor(Color.parseColor("#A52A2A"));
                drawTrapezoid(canvas, paint, x1, y1, x2, y2);
                break;
            case VENTANA:
                paint.setColor(Color.BLUE);
                drawTrapezoid(canvas, paint, x1, y1, x2, y2);
                break;
            case PINTURA:
                paint.setColor(Color.RED);
                canvas.drawCircle((x1 + x2) / 2, (y1 + y2) / 2, (x2 - x1) / 2, paint);
                break;
            case BANQUETA:
                paint.setColor(Color.DKGRAY);
                canvas.drawRect(x1, y1, x2, y2, paint);
                break;
            case TEXTO:
                paint.setColor(Color.BLACK);
                paint.setTextSize(30);
                canvas.drawText(label, x1, y1, paint);
                break;
            case LINEA:
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(5);
                canvas.drawLine(x1, y1, x2, y2, paint);
                break;
        }

        if (type != ElementType.TEXTO && type != ElementType.LINEA) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            canvas.drawText(label, x1, y1 - 10, paint);
        }
    }

    private void drawTrapezoid(Canvas canvas, Paint paint, float x1, float y1, float x2, float y2) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y1);
        path.lineTo(x2 - (x2 - x1) / 8, y2);
        path.lineTo(x1 + (x2 - x1) / 8, y2);
        path.close();
        canvas.drawPath(path, paint);
    }

    public boolean contains(float x, float y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public ElementType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }
}
