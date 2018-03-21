package com.macsoftex.android_tools.ui.adapters.group_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.macsoftex.antiradarbasemodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 28.12.17.
 */

abstract public class GroupAdapter extends BaseAdapter {
    private ArrayList<GroupAdapterItem> adapterItems;
    private List<Group> groups;

    public GroupAdapter() {
        this.adapterItems = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
        updateData();
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public int getItemViewType(int i) {
        return adapterItems.get(i).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return adapterItems.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GroupAdapterItem item = getGroupAdapterItem(i);

        if (item == null)
            return view;

        if (item.isHeader())
            return getHeaderView((String) item.getObject(), view, viewGroup);
        else
            return getItemView(item.getObject(), view, viewGroup);
    }

    abstract protected View getHeaderView(String title, View view, ViewGroup viewGroup);
    abstract protected View getItemView(Object object, View view, ViewGroup viewGroup);

    public GroupAdapterItem getGroupAdapterItem(int i) {
        return (GroupAdapterItem) getItem(i);
    }

    private void updateData() {
        adapterItems.clear();

        for (Group group : groups) {
            adapterItems.add(GroupAdapterItem.createHeader(group.getTitle()));

            for (Object groupItem : group.getItems()) {
                adapterItems.add(GroupAdapterItem.createItem(groupItem));
            }
        }

        notifyDataSetChanged();
    }
}
