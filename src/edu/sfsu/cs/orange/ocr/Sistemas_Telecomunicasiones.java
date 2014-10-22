package edu.sfsu.cs.orange.ocr;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class Sistemas_Telecomunicasiones extends Activity {
	private ImageView imgFacebook;
	private ImageView imgTwitter;
	private ImageButton imgBLogoElectronica;
	private ImageButton imgBLogoInformatica;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sistemas__telecomunicasiones);
		imgFacebook = (ImageView) findViewById(R.id.imgFacebook);
		imgTwitter = (ImageView) findViewById(R.id.imgTwitter);
		imgBLogoElectronica = (ImageButton) findViewById(R.id.logoElectronica);
		imgBLogoInformatica = (ImageButton) findViewById(R.id.logoInformatica);
	}

	/*public void Animar(View view){

		switch (view.getId()) {
		case R.id.imgFacebook:
		Animation trans=AnimationUtils.loadAnimation(this, R.anim.escalar);
		trans.reset();
		imgTwitter.startAnimation(trans);
			break;

		default:
			break;
		}
	}*/
	public void onClickBtnFacebook(View view){
		/*Toast mensaje= Toast.makeText(this,"Facebook presionado",Toast.LENGTH_SHORT);
		mensaje.show();*/
	//	Animar(view);
		
		Animation trans=AnimationUtils.loadAnimation(this, R.anim.rotar);
		trans.reset();
		imgFacebook.startAnimation(trans);
	}
	public void onClickTwitter(View view){
		Animation trans=AnimationUtils.loadAnimation(this, R.anim.rotar);
		trans.reset();
		imgTwitter.startAnimation(trans);
	}
	public void onClickLogoElectronica(View view){
		Animation trans=AnimationUtils.loadAnimation(this, R.anim.rotar);
		trans.reset();
		imgBLogoElectronica.startAnimation(trans);
	}
	public void onClickLogoInformatica(View view){
		Animation trans=AnimationUtils.loadAnimation(this, R.anim.rotar);
		trans.reset();
		imgBLogoInformatica.startAnimation(trans);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sistemas__telecomunicasiones, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_sistemas__telecomunicasiones, container,
					false);
			return rootView;
		}
	}

}
