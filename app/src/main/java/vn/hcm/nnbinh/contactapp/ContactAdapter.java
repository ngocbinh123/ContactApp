package vn.hcm.nnbinh.contactapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import vn.hcm.nnbinh.contactapp.db.Contact;
import vn.hcm.nnbinh.contactapp.ui.CircleImageView;
import vn.hcm.nnbinh.contactapp.utils.CommonUtils;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mContacts;
//    private LinkedHashMap<String, Integer> mMapIndex;
//    private ArrayList<String> mSectionList;
    //private String[] mSections;

    public ContactAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    private void fillSections() {
//        mMapIndex = new LinkedHashMap<String, Integer>();
//        for (int x = 0; x < mContacts.size(); x++) {
//            String fruit = mContacts.get(x).getName();
//            if (fruit.length() > 1) {
//                String ch = fruit.substring(0, 1);
//                ch = ch.toUpperCase();
//                if (!mMapIndex.containsKey(ch)) {
//                    mMapIndex.put(ch, x);
//                }
//            }
//        }
//        Set<String> sectionLetters = mMapIndex.keySet();
//        // create a list from the set to sort
//        mSectionList = new ArrayList<String>(sectionLetters);
//        Collections.sort(mSectionList);
//
//        mSections = new String[mSectionList.size()];
//        mSectionList.toArray(mSections);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_contact, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact curContact = getItem(position);
        boolean isShowSection = true;
        if (position > 0)
            isShowSection = !curContact.getSection().equals(getItem(position-1).getSection() );
        holder.bind(curContact, isShowSection);
    }

    private Contact getItem(int pos) {
        return mContacts.get(pos);
    }

    @Override
    public int getItemCount() {
        if (mContacts == null)
            return 0;
        return mContacts.size();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView mSectionName;
        private TextView mName;
        private TextView mState;
        private CircleImageView thumbnail;
        private CheckBox mCheckView;
        public ContactViewHolder(View view) {
            super(view);
            mSectionName = (TextView) view.findViewById(R.id.section_title);
            mName = (TextView) view.findViewById(R.id.tv_name);
            thumbnail = (CircleImageView) view.findViewById(R.id.thumbnail);
            mState = (TextView) view.findViewById(R.id.tv_state);
            mCheckView = (CheckBox) view.findViewById(R.id.chkbox_item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCheckView.isChecked()) {
                        mCheckView.setChecked(false);
                    }else {
                        mCheckView.setChecked(true);
                    }
                }
            });
        }

        public void bind(Contact contact, boolean isShowSection) {
            mName.setText(contact.getName());
            mState.setText(contact.isOnline() ? "Online":"Offline");
            mSectionName.setText(contact.getSection());
            mSectionName.setVisibility(isShowSection ? View.VISIBLE : View.GONE);
            CommonUtils.loadImage(itemView.getContext(), contact.getThumbnail(), thumbnail);
        }
    }
}
