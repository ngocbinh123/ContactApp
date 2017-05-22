package vn.hcm.nnbinh.contactapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.db.Contact;
import vn.hcm.nnbinh.contactapp.ui.CircleImageView;
import vn.hcm.nnbinh.contactapp.utils.CommonUtils;

/**
 * Created by nguyenngocbinh on 5/21/17.
 */

public class ContactListAdapter extends BaseRecyclerViewAdapter<Contact> implements Filterable{
    private HashMap<Contact,Boolean> mCheckboxState = new HashMap<>();
    private OnClicklistener mListener;
    private ContactFilter mFilter;
    public ContactListAdapter(List<Contact> contacts) {
        setData(contacts);
        mFilter = new ContactFilter(ContactListAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public interface OnClicklistener {
        void onClick(Contact contact, int position, boolean isSelected);
    }

    public void setListener(OnClicklistener listener) {
        this.mListener = listener;
    }

    @Override
    public BaseViewHolder<Contact> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(parent, R.layout.layout_item_contact);

    }

    class ContactViewHolder extends BaseViewHolder<Contact> {
        private final TextView mSectionName;
        private final TextView mName;
        private final TextView mState;
        private final CircleImageView thumbnail;
        private final CheckBox mCheckView;
        private final LinearLayout mViewAction;
        public ContactViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
            mSectionName = (TextView) findById(R.id.section_title);
            mName = (TextView) findById(R.id.tv_name);
            thumbnail = (CircleImageView) findById(R.id.thumbnail);
            mState = (TextView) findById(R.id.tv_state);
            mCheckView = (CheckBox) findById(R.id.chkbox_item);
            mViewAction = (LinearLayout) findById(R.id.view_action);
        }

        @Override
        public void onBindItem(final Contact contact, final int position) {
            CommonUtils.loadImage(itemView.getContext(), contact.getThumbnail(), thumbnail);
            mName.setText(contact.getName());
            mState.setText(contact.isOnline() ? "Online":"Offline");

            mSectionName.setText(contact.getSection());
            boolean isShowSection = true;
            if (position > 0)
                isShowSection = !contact.getSection().equals(getItem(position-1).getSection() );

            mSectionName.setVisibility(isShowSection ? View.VISIBLE : View.GONE);

            Boolean state = mCheckboxState.get(contact);
            if (state == null || state == false)
                mCheckView.setChecked(false);
            else
                mCheckView.setChecked(true);

            mCheckView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickOnItem(contact, position, mCheckView.isChecked());
                }
            });

            mViewAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = !mCheckView.isChecked();
                    mCheckView.setChecked(isChecked);
                    clickOnItem(contact, position, isChecked);
                }
            });

        }

        private void clickOnItem(Contact contact, int position, boolean isChecked) {
            mCheckboxState.put(contact, isChecked);
            if (mListener != null)
                mListener.onClick(contact, position, isChecked);
        }
    }

    class ContactFilter extends Filter {
        private ContactListAdapter mAdapter;
        private List<Contact> mFullData;
        public ContactFilter(ContactListAdapter adapter) {
            super();
            mAdapter = adapter;
            mFullData = new ArrayList<>();
            mFullData.addAll(adapter.getData());
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            mAdapter.setData(new ArrayList<Contact>());
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                mAdapter.setData(mFullData);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Contact contact : mFullData) {
                    if (contact.getName().toLowerCase().startsWith(filterPattern)) {
                        mAdapter.addItem(contact);
                    }
                }
            }

            results.values = getData();
            results.count = getData().size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
