package com.macsoftex.android_tools.ui.adapters.group_adapter;

import java.util.List;

/**
 * Created by alex on 09.01.18.
 */

public class Group {
    private String title;
    private List<Object> items;

    public Group(String title, List<Object> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public List<Object> getItems() {
        return items;
    }
}
