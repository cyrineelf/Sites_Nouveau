package com.asleimi.testapp;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asleimi.testapp.model.Plat;

public class PlatAdapter extends ArrayAdapter<Plat> {

	public PlatAdapter(Context context, int resource, List<Plat> objects) {
		super(context, resource, objects);
		this.context = context;
		this.platList = objects;
		// TODO Auto-generated constructor stub
	}

	private Context context;

	private List<Plat> platList;

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.plat_item,
				parent, false);

		// Display flower name in the TextView widget
		Plat plat = platList.get(position);
		TextView tv = (TextView) view.findViewById(R.id.platNomTextView);
		tv.setText(plat.getNomPlat());

		// Display flower photo in ImageView widget
		if(plat.getBitmap() != null)
		{
		ImageView image = (ImageView) view.findViewById(R.id.platImageView);
		image.setImageBitmap(plat.getBitmap());
		}
		else{
			PlatAndView container = new PlatAndView();
			container.plat = plat;
			container.view = view;
			
			ImageLoader loader = new ImageLoader();
			loader.execute(container);
		}

		return view;
	}

	class PlatAndView {
		private Plat plat;
		private View view;
		private Bitmap bitmap;

	}

	private class ImageLoader extends AsyncTask<PlatAndView, Void, PlatAndView> {

		@Override
		protected PlatAndView doInBackground(PlatAndView... params) {
			PlatAndView container = params[0];
			Plat plat = container.plat;

			try {

				String imageUrl = ListPlat.PHOTOS_BASE_URL + plat.getPhotoPlat();
				InputStream in = (InputStream) new URL(imageUrl).getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				plat.setBitmap(bitmap);
				in.close();
				container.bitmap = bitmap;
				return container;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(PlatAndView result) {
			
			ImageView image = (ImageView) result.view.findViewById(R.id.platImageView);
			image.setImageBitmap(result.bitmap);
			result.plat.setBitmap(result.bitmap);
		}

	}
}
