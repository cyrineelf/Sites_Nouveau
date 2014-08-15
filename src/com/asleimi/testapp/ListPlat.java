package com.asleimi.testapp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asleimi.testapp.model.Plat;
import com.asleimi.testapp.parsers.PlatJSONParser;

public class ListPlat extends ListActivity {
		
		private List<Plat> platList = new ArrayList<Plat>();
		public static final String PHOTOS_BASE_URL = 
			"http://www.chahiatayba.com/Photos/Original/";

		
		ProgressBar pb;
		List<MyTask> tasks;
		ListView lv;

		
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_list_plat);
			
			
			
			
			pb = (ProgressBar) findViewById(R.id.progressBar1);
			lv = getListView();
			pb.setVisibility(View.INVISIBLE);
			
			tasks = new ArrayList<>();
			
			if (isOnline()) {
				requestData("http://192.168.77.81:8080/TestWS-master/webresources/entities.plat");
			} else {
				Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
			}
		}
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
			Intent descriptionPlatIntent = new Intent(v.getContext(), DescriptionPlat.class);
			
			//problem to fix bitmap
			
			Plat plat = (Plat) l.getAdapter().getItem(position);
			plat.setBitmap(null);
			descriptionPlatIntent.putExtra("Plat", plat);//l.getItemIdAtPosition(position)
			startActivity(descriptionPlatIntent);
		
		
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.list_plat, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			
			return false;
		}

		private void requestData(String uri) {
			MyTask task = new MyTask();
			task.execute(uri);
		}

		protected void updateDisplay() {
			//Use PlatAdapter to display data
			
			PlatAdapter adapter = new PlatAdapter(this, R.layout.plat_item, platList);
			setListAdapter(adapter);
		}
		
		protected boolean isOnline() {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnectedOrConnecting()) {
				return true;
			} else {
				return false;
			}
		}
		
		private class MyTask extends AsyncTask<String, String, List<Plat>> {

			@Override
			protected void onPreExecute() {
				if (tasks.size() == 0) {
					pb.setVisibility(View.VISIBLE);
				}
				tasks.add(this);
			}
			
			
			
			@Override
			protected List<Plat> doInBackground(String... params) {
				
				String content = HttpManager.getData(params[0]);//, "feeduser", "feedpassword"
				platList= PlatJSONParser.parseFeed(content);
				
				for (Plat plat : platList) {
					
					try {
						
						String imageUrl = PHOTOS_BASE_URL + plat.getPhotoPlat();
						InputStream in = (InputStream) new URL(imageUrl).getContent();
						Bitmap bitmap = BitmapFactory.decodeStream(in);
						plat.setBitmap(bitmap);
						in.close();
					
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			
				return platList;
			}
			
			protected void onPostExecute(List<Plat> result) {
				
				tasks.remove(this);
				if (tasks.size() == 0) {
					pb.setVisibility(View.INVISIBLE);
				}
				
				if (result == null) {
					Toast.makeText(ListPlat.this, "Web service not available", Toast.LENGTH_LONG).show();
					return;
				}
				
				platList = result;
				updateDisplay();

			}
			
		}
}
