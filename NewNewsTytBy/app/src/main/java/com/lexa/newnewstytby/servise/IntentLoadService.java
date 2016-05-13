package com.lexa.newnewstytby.servise;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lexa.newnewstytby.R;
import com.lexa.newnewstytby.ormLite.BaseClass;
import com.lexa.newnewstytby.ormLite.HelperFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lexa on 12.05.2016.
 */
public class IntentLoadService extends IntentService {

    private boolean isNewNews = true;

    public IntentLoadService() {
        super("IntentLoadService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HelperFactory.releaseHelper();
        if(isNewNews){
        Toast.makeText(getBaseContext(),getResources().getString(R.string.Toast2),Toast.LENGTH_SHORT).show();}
        else{
        Toast.makeText(getBaseContext(),getResources().getString(R.string.Toast1),Toast.LENGTH_SHORT).show();}
        Log.e("ddgfdgfdgf1", "serverstop");
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        List<BaseClass> needObjectNews = intent.getExtras().getParcelableArrayList("needObjectNews");


        HelperFactory.setHelper(getBaseContext());

        List<BaseClass> rssInfoList = new ArrayList<BaseClass>();
        try {
            rssInfoList = HelperFactory.getHelper().getBaseClassDAO().getAllBaseClasses();
            if(rssInfoList.size() != 0) {
                if (rssInfoList.get(0).getName().equalsIgnoreCase(needObjectNews.get(0).getName())) {
                    Log.e("ddgfdgfdgf1", "Нет новых новостей");
                    isNewNews = false;
                } else {
                    HelperFactory.getHelper().getBaseClassDAO().delete(rssInfoList);
                    for (int i = 0; i < needObjectNews.size(); i++) {
                        HelperFactory.getHelper().getBaseClassDAO().createIfNotExists(needObjectNews.get(i));
                    }
                }
            }
            else{
                for (int i = 0; i < needObjectNews.size(); i++) {
                    HelperFactory.getHelper().getBaseClassDAO().createIfNotExists(needObjectNews.get(i));
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
}
