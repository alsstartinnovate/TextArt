package com.anilax.model;

public class BottomItemOneModel {
    private String name;
    private int drawble;
    private int fontSize;
    private int textColor;
    private String fontName;
    private boolean isChecked = false;

    public BottomItemOneModel(String name, int drawble, int fontSize, String fontName, int textColor, boolean isChecked) {
        this.name = name;
        this.drawble = drawble;
        this.fontSize = fontSize;
        this.drawble = drawble;
        this.fontName = fontName;
        this.textColor = textColor;
        this.isChecked = isChecked;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawble() {
        return drawble;
    }

    public void setDrawble(int drawble) {
        this.drawble = drawble;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
