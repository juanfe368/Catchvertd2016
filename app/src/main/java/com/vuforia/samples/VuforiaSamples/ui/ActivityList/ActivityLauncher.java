/*===============================================================================
Copyright (c) 2016 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2015 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/


package com.vuforia.samples.VuforiaSamples.ui.ActivityList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vuforia.samples.VuforiaSamples.R;


// This activity starts activities which demonstrate the Vuforia features
public class ActivityLauncher extends ListActivity {

    private String mClassToLaunchPackage;

    private String mActivities[] = {
            "Image Targets",
            "VuMark",
            "Cylinder Targets",
            "User Defined Targets"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activities_list_text_view, mActivities);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activities_list);
        setListAdapter(adapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        mClassToLaunchPackage = getPackageName();

        Intent intent = new Intent();

        switch (position) {
            case 0:
                intent.setClassName(mClassToLaunchPackage,mClassToLaunchPackage + ".app.ImageTargets.ImageTargets");
                break;
            case 1:
                intent.setClassName(mClassToLaunchPackage,mClassToLaunchPackage + ".app.VuMark.VuMark");
                break;
            case 2:
                intent.setClassName(mClassToLaunchPackage,mClassToLaunchPackage + ".app.CylinderTargets.CylinderTargets");
                break;
            case 3:
                intent.setClassName(mClassToLaunchPackage,mClassToLaunchPackage + ".app.UserDefinedTargets.UserDefinedTargets");
                break;
        }

        startActivity(intent);

    }
}
