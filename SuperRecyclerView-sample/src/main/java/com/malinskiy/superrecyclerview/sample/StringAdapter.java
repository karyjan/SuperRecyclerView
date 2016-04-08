package com.malinskiy.superrecyclerview.sample;

import android.view.View;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerAdapter;
import com.malinskiy.superrecyclerview.SuperViewHolder;

public class StringAdapter extends SuperRecyclerAdapter<String> {

    @Override
    protected int getViewLayoutId(int viewType) {
        return R.layout.item_string;
    }

    @Override
    protected SuperViewHolder getViewHolder(View view, int viewType) {
        return new StringViewHolder(view);
    }

    class StringViewHolder extends SuperViewHolder{

        private TextView textView;
        public StringViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        @Override
        public void setModel(Object model) {
            super.setModel(model);
            textView.setText((String)model);
        }
    }

}
