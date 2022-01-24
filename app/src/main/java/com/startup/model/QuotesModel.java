package com.startup.model;

import java.io.Serializable;

public class QuotesModel implements Serializable {
    public QuotesModel(String name) {
        this.name = name;
    }

    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
