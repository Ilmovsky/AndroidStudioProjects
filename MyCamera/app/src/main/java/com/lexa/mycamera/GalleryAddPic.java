package com.lexa.mycamera;

import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Lexa on 21.04.2016.
 */
public class GalleryAddPic {

    public Intent galleryAddPic(String fpath) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(fpath);
        Uri contentUri = Uri.fromFile(f);
        return mediaScanIntent.setData(contentUri);
    }

}
