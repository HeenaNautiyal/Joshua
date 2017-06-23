package com.example.heena.joshuafreetv;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MenuPage extends AppCompatActivity {
ImageView ivTv,ivContact,ivclasses,ivabout,ivshare,ivrateing;
    String st_tv,st_contact,st_classes,st_about,st_share,st_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        ivTv=(ImageView)findViewById(R.id.iv_tv);
        ivContact=(ImageView)findViewById(R.id.iv_contact);
        ivclasses=(ImageView)findViewById(R.id.iv_classes);
        ivabout=(ImageView)findViewById(R.id.iv_about);
        ivshare=(ImageView)findViewById(R.id.iv_share);
        ivrateing=(ImageView)findViewById(R.id.iv_rate);

        ivTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://emotionallyfree.tv/"));
                startActivity(browserIntent);
            }
        });
        ivclasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://emotionallyfree.tv/index.php/classes-more/"));
                startActivity(browserIntent);
            }
        });
        ivshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "http://emotionallyfree.tv/";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
             }
        });
        ivrateing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://search?q=foo"));
                PackageManager pm = getActivity().getPackageManager();
                List<ResolveInfo> list = pm
                        .queryIntentActivities(intent, 0);
                for (int a = 0; a < list.size(); a++) {
                    ResolveInfo info = list.get(a);

                    ActivityInfo activity = info.activityInfo;
                    if (activity.name.contains("com.google.android")) {
                        ComponentName name = new ComponentName(
                                activity.applicationInfo.packageName,
                                activity.name);
                        Intent i = new Intent(
                                Intent.ACTION_MAIN,
                                Uri.parse("http://play.google.com/store/apps/details?id="
                                        + packageName));

                        i.addCategory(Intent.CATEGORY_LAUNCHER);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        i.setComponent(name);
                        startActivity(i);
                        getActivity().finish();*/
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));

                }
            }
        });
    }
}
