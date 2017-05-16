package vn.hcm.nnbinh.contactapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.hcm.nnbinh.contactapp.db.Contact;
import vn.hcm.nnbinh.contactapp.ui.CircleImageView;
import vn.hcm.nnbinh.contactapp.utils.CommonUtils;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> mContacts;
    private LinkedHashMap<String, Integer> mMapIndex;
    private ArrayList<String> mSectionList;
    private String[] mSections;

    public ContactAdapter(List<Contact> contacts) {
        mContacts = contacts;
        fillSections();
    }


    private void fillSections() {
        mMapIndex = new LinkedHashMap<String, Integer>();
        for (int x = 0; x < mContacts.size(); x++) {
            String fruit = mContacts.get(x).getName();
            if (fruit.length() > 1) {
                String ch = fruit.substring(0, 1);
                ch = ch.toUpperCase();
                if (!mMapIndex.containsKey(ch)) {
                    mMapIndex.put(ch, x);
                }
            }
        }
        Set<String> sectionLetters = mMapIndex.keySet();
        // create a list from the set to sort
        mSectionList = new ArrayList<String>(sectionLetters);
        Collections.sort(mSectionList);

        mSections = new String[mSectionList.size()];
        mSectionList.toArray(mSections);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_contact, null);
        return new ContactViewHolder(content);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact lContact = getItem(position);
        String section = getSection(lContact);
        holder.bind(lContact, section, mMapIndex.get(section) == position);
    }

    private String getSection(Contact pContact) {
        return pContact.getName().substring(0, 1).toUpperCase();
    }

    private Contact getItem(int pPosition) {
        return mContacts.get(pPosition);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView mName;
        private final TextView mSectionName;
        private CircleImageView thumbnail;
        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mSectionName = (TextView) itemView.findViewById(R.id.section_title);
            thumbnail = (CircleImageView) itemView.findViewById(R.id.thumbnail);

        }

        public void bind(Contact pItem, String pSection, boolean bShowSection) {
            mName.setText(pItem.getName());
            mSectionName.setText(pSection);
            mSectionName.setVisibility(bShowSection ? View.VISIBLE : View.GONE);
            CommonUtils.loadImage(itemView.getContext(), pItem.getThumbnail(), thumbnail);
        }
    }
}
