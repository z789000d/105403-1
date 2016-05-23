package com.example.draggridview;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.draggridview.DragGridView.OnChanageListener;

public class ShowInWebView extends Activity {

	List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
	URL url;

	EditText ed01;
	Button b01;
	String  geturl;
	DragGridView mDragGridView;
	RecordAdapter mSimpleAdapter;
	String te[] = new String[100];
	Handler handler=new Handler();






	@Override
	protected void onCreate(Bundle savedInstanceState) {



		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showinwebview);


		mSimpleAdapter = new RecordAdapter(this,dataSourceList);
//		LayoutInflater inflater = getLayoutInflater();
//		View otherLayout = inflater.inflate(R.layout.grid_item, null);
//		TextView otherTv1 = (TextView)otherLayout.findViewById(R.id.words_home_function_1);


		mDragGridView = (DragGridView) findViewById(R.id.dragGridView);


		ed01 = (EditText) findViewById(R.id.ed01);
		b01 = (Button) findViewById(R.id.b01);


		b01.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				geturl = ed01.getText().toString();
				ed01.setText(null); //重設內容

				Thread threadc =new Thread(){

					Thread threadB = new Thread(new Runnable() {
						public void run(){
							Element title[] = new Element[100];
							try {

								Log.e("thb", "start");
								url=new URL(geturl);
								Document doc =  Jsoup.parse(url, 3000);        //連結該網址
//								doc.select("img").removeAttr("src");zz
								doc.select("div").removeAttr("class");
								doc.select("span").remove();			//去除不要的屬性



								for (int x=1;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a
									title[x] = doc.select("a").get(x);//抓取為tr且有class屬性的所有Tag get動態抓第幾段li
									Log.d("div",doc.select("div").get(x).toString());
									te[x] = title[x].toString();
									//以下去除div標籤
									te[x] = te[x].replace("<div>", "");
									te[x] = te[x].replace("</div>","");
									Log.e("123123",te[x]);
									//Log.e("123123", Integer.toString(cloclk));
								}

								Log.e("thb","end");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});

					public void run(){
						threadB.start();
						try {
							threadB.join();
							Log.e("tha", "start");
							for (int i = 1; i < 100; i++) {
								HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
								itemHashMap.put("words", te[i]);
								dataSourceList.add(itemHashMap);
								handler.post(runnableUi);
							}
							Log.e("tha", "end");
						}
						catch (Exception e){
							e.printStackTrace();
						}

					}
				};
				threadc.start();

			}
		});



	}


	Runnable runnableUi = new Runnable(){
		@Override
		public void run() {
			//更新界面
			mDragGridView.setAdapter(mSimpleAdapter);
			mDragGridView.setOnChangeListener(new OnChanageListener() {

				@Override
				public void onChange(int from, int to) {
					HashMap<String, Object> temp = dataSourceList.get(from);
					//直接交互item
//									dataSourceList.set(from, dataSourceList.get(to));
					//				dataSourceList.set(to, temp);


					//这里的处理需要注意下
					if (from < to) {
						for (int i = from; i < to; i++) {
							Collections.swap(dataSourceList, i, i + 1);
						}
					} else if (from > to) {
						for (int i = from; i > to; i--) {
							Collections.swap(dataSourceList, i, i - 1);
						}
					}


					dataSourceList.set(to, temp);

					mSimpleAdapter.notifyDataSetChanged();


				}
			});
		}

	};

}
