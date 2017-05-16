package vn.hcm.nnbinh.contactapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.ButterKnife;
import vn.hcm.nnbinh.contactapp.ContactAdapter;
import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.db.Contact;
import vn.hcm.nnbinh.contactapp.db.DBProvider;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class ContactListActivity extends BaseActivity{
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        setTitle(R.string.title_contact_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new ContactAdapter(getContacts());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Contact> getContacts() {
        return DBProvider.get(this).getContacts();
    }
}
