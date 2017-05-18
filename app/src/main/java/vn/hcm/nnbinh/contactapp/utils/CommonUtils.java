package vn.hcm.nnbinh.contactapp.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import vn.hcm.nnbinh.contactapp.R;
import vn.hcm.nnbinh.contactapp.ui.CircleImageView;

/**
 * Created by nguyenngocbinh on 5/17/17.
 */

public class CommonUtils {

    public static void loadImage(Context context, String url, CircleImageView imageView) {
        try {
            Glide.with(context).load(url)
                    .error(R.mipmap.ic_user)
                    .placeholder(R.mipmap.ic_user)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }catch (Exception e) {
            Log.e("CommonUtils", e.getMessage());
        }
    }
}
