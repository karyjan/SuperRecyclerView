package com.malinskiy.superrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kouyongzan on 16/4/8.
 */
public abstract class SuperRecyclerAdapter<M> extends RecyclerView.Adapter<SuperViewHolder> {

    private List<M> results = new ArrayList<M>();

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewLayoutId(viewType),parent,false);
        return getViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        M item = results.get(position);
        if(item instanceof SuperViewItem)
        {
            holder.setModel(((SuperViewItem) item).model);
        }else{
            holder.setModel(item);
        }
    }

    public void addMore(List<M> data){
        int startPosition = results.size();
        results.addAll(data);
        notifyItemRangeChanged(startPosition, data.size());
    }

    /**
     *
     * @param data array
     */
    public void addAll(M[] data){
        int startPosition = results.size();
        results.addAll(Arrays.asList(data));
        notifyItemRangeChanged(startPosition, data.length);
    }

    public void clear(){
        results.clear();
        notifyDataSetChanged();
    }

    public void add(M item) {
        insert(item, results.size());
    }

    public void insert(M item, int position) {
        results.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        results.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public int getItemViewType(int position) {
        M item = results.get(position);
        if(item instanceof SuperViewItem)
        {
            return ((SuperViewItem) item).viewType;
        }
        return super.getItemViewType(position);
    }

    abstract protected int getViewLayoutId(int viewType);

    protected SuperViewHolder getViewHolder(View view,int viewType){
        return new SuperViewHolder(view);
    }
}
