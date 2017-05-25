package vn.hcm.nnbinh.contactapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.adapter.ContactListAdapter;
import vn.hcm.nnbinh.contactapp.adapter.SelectedAdapter;
import vn.hcm.nnbinh.contactapp.app.DBManager;
import vn.hcm.nnbinh.contactapp.db.Contact;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class ContactListActivity extends BaseActivity{
    private Toolbar toolbar;
    private ContactListAdapter mAdapter;
    private SelectedAdapter mSelectedAdapter;
    private List<Contact> mContacts;
    @BindView(R.id.recycler_view)
    RecyclerView mContactView;
    @BindView(R.id.view_select_list)
    RecyclerView mSelectedView;
    @BindView(R.id.txt_search)
    EditText mTxtSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        setTitle(R.string.title_contact_list);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTxtSearch = null;
    }

    private void init() {
        setupRecyclerView();
        setupSelectedView();
        setupEditSearch();
    }

    private void setupEditSearch() {
        mTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //mAdapter.getFilter().filter(s);
                ArrayList<Contact> contacts = new ArrayList<Contact>();
                if (s.toString() == "")
                    mAdapter.setData(mContacts);
                else {
                    for (Contact con: mContacts) {
                        if (con.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            contacts.add(con);
                        }
                    }
                    mAdapter.setData(contacts);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * setup contact view
     * */
    private void setupRecyclerView() {
        mContacts = getContacts();
        mAdapter = new ContactListAdapter(mContacts);
        mAdapter.setListener(new ContactListAdapter.OnClicklistener() {
            @Override
            public void onClick(Contact contact, int position, boolean isSelected) {
                if (isSelected)
                    mSelectedAdapter.addItem(contact);
                else
                    mSelectedAdapter.remove(contact);
            }
        });
        mContactView.setHasFixedSize(true);
        mContactView.setLayoutManager(new LinearLayoutManager(this));
        mContactView.setAdapter(mAdapter);
    }

    /**
     * setup selected view
     * */
    private void setupSelectedView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        mSelectedAdapter = new SelectedAdapter();
        mSelectedView.setHasFixedSize(true);
        mSelectedView.setLayoutManager(layoutManager);
        mSelectedView.setAdapter(mSelectedAdapter);
    }

    /**
     * get contact list from db
     * */
    private List<Contact> getContacts() {
        return DBManager.get(this).getContacts();
    }
}
