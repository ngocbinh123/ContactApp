package vn.hcm.nnbinh.contactapp.adapter;
import android.view.ViewGroup;

import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.db.Contact;
import vn.hcm.nnbinh.contactapp.ui.CircleImageView;
import vn.hcm.nnbinh.contactapp.utils.CommonUtils;

/**
 * Created by nguyenngocbinh on 5/21/17.
 */

public class SelectedAdapter extends BaseRecyclerViewAdapter<Contact> {
    public SelectedAdapter() {
    }

    @Override
    public BaseViewHolder<Contact> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedViewHolder(parent, R.layout.layout_item_selected);
    }

    public class SelectedViewHolder extends BaseViewHolder<Contact> {
        CircleImageView mThumbnail;
        public SelectedViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
            mThumbnail = (CircleImageView) findById(R.id.thumbnail);
        }

        @Override
        public void onBindItem(Contact item, int position) {
            CommonUtils.loadImage(itemView.getContext(), item.getThumbnail(), mThumbnail);
        }
    }
}
