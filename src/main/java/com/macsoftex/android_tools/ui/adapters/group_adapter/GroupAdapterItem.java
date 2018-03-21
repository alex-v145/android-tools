package com.macsoftex.android_tools.ui.adapters.group_adapter;

/**
 * Created by alex on 09.01.18.
 */

public class GroupAdapterItem {
    private static final int HEADER_TYPE = 0;
    private static final int ITEM_TYPE = 1;

    private Object object;
    private int type;

    public static GroupAdapterItem createHeader(String title) {
        return new GroupAdapterItem(title, HEADER_TYPE);
    }

    public static GroupAdapterItem createItem(Object object) {
        return new GroupAdapterItem(object, ITEM_TYPE);
    }

    public GroupAdapterItem(Object object, int type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public int getType() {
        return type;
    }

    public boolean isHeader() {
        return type == HEADER_TYPE;
    }
}
