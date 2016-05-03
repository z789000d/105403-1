package com.example.draggridview;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, Object>> listItem;

    public RecordAdapter(Context context,
                         List<HashMap<String, Object>> listitem) {
        super();
        this.context = context;
        this.listItem = listitem;
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int arg0) {
        return listItem.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int position , View convertView, ViewGroup parent) {
        // 取出数据

      String wordsStr = listItem.get(position).get("words").toString();


        View tem;
        if (convertView == null) {
            RelativeLayout temRl = (RelativeLayout) View.inflate(context,
                    R.layout.grid_item, null);



//            TextView words =(TextView) temRl.findViewById(R.id.words_home_function_1);
            WebView words =(WebView) temRl.findViewById(R.id.words_home_function_1);
            words.getSettings().setDefaultTextEncodingName("utf-8");
            words.loadDataWithBaseURL("",wordsStr, "text/html", "utf-8","");
//            words.setMovementMethod(LinkMovementMethod.getInstance());
//            words.setText(Html.fromHtml(wordsStr));

            tem = temRl;
        } else {
            tem = convertView;




//            TextView words =(TextView) tem.findViewById(R.id.words_home_function_1);
            WebView words =(WebView) tem.findViewById(R.id.words_home_function_1);
            words.getSettings().setDefaultTextEncodingName("UTF-8");
            words.loadDataWithBaseURL("",wordsStr, "text/html", "utf-8","");
//            words.setMovementMethod(LinkMovementMethod.getInstance());
//            words.setText(Html.fromHtml(wordsStr));
        }
        return tem;

    }

}
