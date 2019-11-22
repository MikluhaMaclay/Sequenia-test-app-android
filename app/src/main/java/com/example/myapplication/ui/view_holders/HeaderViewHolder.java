package com.example.myapplication.ui.view_holders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entities.Header;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private TextView headerTitle;

    public HeaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_header, parent, false));

        headerTitle = itemView.findViewById(R.id.header_title);
    }

    public void bind(final Header header) {
        headerTitle.setText(header.getDisplayTitle());
    }

}

