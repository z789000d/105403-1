package com.example.draggridview;

import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ListViewCheckboxesActivity extends Activity
{
    URL url;
    String te[] = new String[100];
    Button b01;
    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        b01 = (Button) findViewById(R.id.b01);

        b01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayListView();

            }
        });
        new Thread(new Runnable() {
            public void run(){
                Element title[] = new Element[100];
                try {

                    url=new URL("http://www.ntub.edu.tw/bin/home.php");
                    Document doc =  Jsoup.parse(url, 3000);        //連結該網址
                    doc.select("img").removeAttr("src");
                    doc.select("div").removeAttr("class");
                    doc.select("span").remove();			//去除不要的屬性

                    for (int x=1;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a

                        title[x] = doc.select("a").get(x);//抓取為tr且有class屬性的所有Tag get動態抓第幾段li
                        te[x] = title[x].toString();
                        Log.e("123","111");
                        //Log.e("123123", Integer.toString(cloclk));
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("123", "error");
                }

            }
        }).start();

        //Generate list View from ArrayList


        checkButtonClick();

    }

    private void displayListView()
    {

        //Array list of countries
        ArrayList<States> stateList = new ArrayList<States>();
        for (int x=1;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a
            States _states = new States("a",te[x],false);
            stateList.add(_states);
            Log.e("123","333");

        }

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,R.layout.state_info, stateList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // When clicked, show a toast with the TextView text
                States state = (States) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Clicked on Row: " + state.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<States>
    {

        private ArrayList<States> stateList;

        public MyCustomAdapter(Context context, int textViewResourceId,

                               ArrayList<States> stateList)
        {
            super(context, textViewResourceId, stateList);
            this.stateList = new ArrayList<States>();
            this.stateList.addAll(stateList);
        }

        private class ViewHolder
        {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null)
            {

                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.state_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);

                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        CheckBox cb = (CheckBox) v;
                        States _state = (States) cb.getTag();

//                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(),
//                                Toast.LENGTH_LONG).show();

                        _state.setSelected(cb.isChecked());
                    }
                });

            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            States state = stateList.get(position);

            holder.code.setText(" (" + state.getCode() + ")");
            holder.name.setText(Html.fromHtml(state.getName()));
            holder.name.setChecked(state.isSelected());

            holder.name.setTag(state);

            return convertView;
        }

    }

    private void checkButtonClick()
    {

        Button myButton = (Button) findViewById(R.id.findSelected);

        myButton.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                StringBuffer responseText = new StringBuffer();
               // responseText.append("The following were selected...\n");

                ArrayList<States> stateList = dataAdapter.stateList;


                for(int i=0;i<stateList.size();i++)
                {
                    States state = stateList.get(i);

                    if(state.isSelected())
                    {
                        responseText.append("\n"+"----" + state.getName());
                    }
                }
                String ArrayText =  responseText.toString();


//                Toast.makeText(getApplicationContext(),
//                        responseText, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("Array",ArrayText);
                intent.setClass(ListViewCheckboxesActivity.this,ShowInWebView.class);
                startActivity(intent);
            }


        });
    }

}
