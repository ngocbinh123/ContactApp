package vn.hcm.nnbinh.contactapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nguyenngocbinh on 5/21/17.
 */

public abstract class BaseViewHolder<Type> extends RecyclerView.ViewHolder{
    private int mPosition = 0;
    private Type mItem;
    public BaseViewHolder(ViewGroup parent, int layoutId) {
        // create item view for view holder
        this(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public interface OnClickListener<Type> {
        void onItemClick(View view, Type item, int position);
    }

    public void bindItem(Type item, int position) {
        mPosition = position;
        mItem = item;
        onBindItem(item, position);
    }

    public abstract void onBindItem(Type item, int position);

    public View findById(int id) {
        if (this.itemView == null)
            return null;
        return this.itemView.findViewById(id);
    }

    public void setOnClickListener(final OnClickListener listener) {
        View view = this.itemView;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, mItem, mPosition);
                }
            });
        }
    }
}
