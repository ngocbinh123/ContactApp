package vn.hcm.nnbinh.contactapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenngocbinh on 5/21/17.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private List<T> mData;

    public BaseRecyclerViewAdapter() {
        mData =new ArrayList<>();
    }

    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> mData) {
        this.mData = mData;
    }

    public void addItem(T item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public T getItem(int pos) {
        return mData.get(pos);
    }

    public void addAll(List<T> items) {
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        remove(mData.get(position));
    }

    public void remove(T item) {
        mData.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindItem(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }
}
