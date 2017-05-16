package vn.hcm.nnbinh.contactapp.activities;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.hcm.nnbinh.contactapp.R;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends BaseActivity {
    private final static int MAIN_ACTIVITY_REQUEST_CODE = 100;
    private static final int PICK_READ_CONTACT = MAIN_ACTIVITY_REQUEST_CODE + 1;
    @BindView(R.id.contact_list)
    TextView tvContactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // check permission to read contact list in provider
        if (isNeedRequestPermission(READ_CONTACTS))
            requestPermission(READ_CONTACTS, PICK_READ_CONTACT);
        else
            init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_READ_CONTACT &&
                grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED)
            init();
        else
            quitApp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE) {

        }

    }

    @OnClick(R.id.act_contact)
    void onclickButtonContact() {
        goToActivityResult(ContactListActivity.class, MAIN_ACTIVITY_REQUEST_CODE);
    }

    private void init() {

    }
}
