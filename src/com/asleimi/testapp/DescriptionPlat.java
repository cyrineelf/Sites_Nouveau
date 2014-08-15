package com.asleimi.testapp;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.asleimi.testapp.model.Plat;

public class DescriptionPlat extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description_plat);
		
		
		final TextView platNomTextView = (TextView) findViewById(R.id.platTextView);
		final TextView ingredienTextView = (TextView) findViewById(R.id.ingredientTextView);
		final TextView etapeTextView = (TextView) findViewById(R.id.etapeTextView);
		//final ImageView imagePlat =(ImageView) findViewById(R.id.imageView1);
		
		Serializable extra = getIntent().getSerializableExtra("Plat");
		
		if (extra != null)
		{
			Plat plat = (Plat)extra;
			/*try {
				
				String imageUrl = ListPlat.PHOTOS_BASE_URL + plat.getPhotoPlat();
				InputStream in = (InputStream) new URL(imageUrl).getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				//plat.setBitmap(bitmap);
				imagePlat.setImageBitmap(bitmap);
				in.close();
			
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			platNomTextView.setText(plat.getNomPlat());
			ingredienTextView.setText(plat.getIngredientPlat());
			etapeTextView.setText(plat.getEtapePlat());
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description_plat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
