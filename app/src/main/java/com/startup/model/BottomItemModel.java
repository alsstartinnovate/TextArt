package com.startup.model;

public class BottomItemModel {
    private String name;
    private int drawble;
    private boolean isChecked = false;

    public BottomItemModel(String name, int drawble) {
        this.name = name;
        this.drawble = drawble;
        this.isChecked = false;
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
