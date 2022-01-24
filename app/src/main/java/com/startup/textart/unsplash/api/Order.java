package com.startup.textart.unsplash.api;

public enum Order {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular");
    
    private final String order;

    Order(String str) {
        this.order = str;
    }

    public String getOrder() {
        return this.order;
    }
}
