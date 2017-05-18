package vn.hcm.nnbinh.contactapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.hcm.nnbinh.contactapp.ContactAdapter;
import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.app.DBManager;
import vn.hcm.nnbinh.contactapp.db.Contact;

/**
 * Created by nguyenngocbinh on 5/15/17.
 */

public class ContactListActivity extends BaseActivity{
    private Toolbar toolbar;
    private ContactAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
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

    /**
     * seteu
     * */
    private void setupRecyclerView() {
        mAdapter = new ContactAdapter(getContacts());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * get contact list from db
     * */
    private List<Contact> getContacts() {
        return DBManager.get(this).getContacts();
    }
}
