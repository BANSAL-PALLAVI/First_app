package com.msg_app.pallavi.msg_app;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);

        if(smsread()!=null)
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, smsread());
            lViewSMS.setAdapter(adapter);
        }
    }

    public ArrayList smsread()
    {
        ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address"}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        for(int i=0; i<3; i++){

                String address = cursor.getString(1);
                System.out.println("======&gt; Mobile number =&gt; " + address);
                sms.add("contact:" + address);
                cursor.moveToNext();

        }
        return sms;

    }
}

