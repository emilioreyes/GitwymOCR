package edu.sfsu.cs.orange.ocr;

import java.util.StringTokenizer;

import com.upse.edu.ec.Sqlite.DataBaseManager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class InformacionActivity extends Activity {
	TextView textoABuscar;
	String TBuscar;
	boolean bandera = false;
	private Cursor cursor;
	private ListView lista;
	private DataBaseManager manager;
	SimpleCursorAdapter adapter;
	boolean result;
	String palabras[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_informacion);
		// codigo para buscar en la base de datos el texto escaneado,
		/*
		 * manager = new DataBaseManager(this); //buscamos el elemento en la
		 * clase R textoABuscar = (TextView)
		 * findViewById(R.id.textViewEscaneado); //inicaimos el intent para
		 * obtener el parametro q envia la actividad anterior Intent men =
		 * getIntent(); //asignamos a Tbuscar el parametro q nos envio la
		 * actividad anterior TBuscar =
		 * men.getStringExtra(CaptureActivity.ACT_INFO); //mostramos el texto
		 * por consola para asegurarnos q todo esta correco
		 * System.out.println("este es el texto.............:" + TBuscar);
		 * //para mostrar la informacion asignamos el parametro q nos envio la
		 * actividad anterior al elemento textoABuscar
		 * textoABuscar.setText(TBuscar);
		 * 
		 * // manager.insertar("s",TBuscar); //manager.insertar("sara",TBuscar);
		 * manager.insertar("maria",TBuscar);
		 * 
		 * //buscamos el listview de la calse R lista = (ListView)
		 * findViewById(R.id.listView1); //creamos un array de string q estara
		 * formado por el nombre y el texto definidos en la clase
		 * DataBaseManager String[] from = new String[] { manager.CN_NOMBRE,
		 * manager.CN_TEXTO }; //array de int formado por los campos text1 y
		 * texto2 q forman parte del listview1 int[] to = new int[] {
		 * android.R.id.text1, android.R.id.text2 }; //
		 * cursor=manager.CargarcursorInformacion(); //llenamos nuestro cursor
		 * invocando a nuestra clase manager y utilizamos el metodo
		 * //buscarPorTextoEscaneado y le enviamos como parametro el texto a
		 * buscar TBuscar cursor = manager.buscarPorTextoEscaneado(TBuscar); //
		 * buscarporParmaetro(); //llenamos nuestro adaptador con los aarays
		 * anteriormente definidos adapter = new SimpleCursorAdapter(this,
		 * android.R.layout.two_line_list_item, cursor, from, to, 0); //seteamos
		 * el adaptador lista.setAdapter(adapter);
		 */
		// llamamos a la funcion
		// buscamos el elemento texttview en la clase R
		textoABuscar = (TextView) findViewById(R.id.textViewEscaneado);
		// inicaimos el intent para obtener el parametro q envia la actividad
		// anterior
		Intent men = getIntent();
		// asignamos a Tbuscar el parametro q nos envio la actividad anterior
		TBuscar = men.getStringExtra(CaptureActivity.ACT_INFO);
		// mostramos el texto por consola para asegurarnos q todo esta correco
		System.out.println("este es el texto.............:" + TBuscar);
		// para mostrar la informacion asignamos el parametro q nos envio la
		// actividad anterior al elemento textoABuscar

		/*
		 * Intent activity = new Intent(this,Sistemas_Telecomunicasiones.class);
		 * startActivity(activity);
		 */
		// System.out.println(" palabrassssssss "+contarPalabras(TBuscar));
		textoABuscar.setText(TBuscar);
		contarPalabras(TBuscar);
		// BuscarTextoEscaneado();
	}

	public void contarPalabras(String oracion) {
		bandera=false;
		StringTokenizer st = new StringTokenizer(oracion, " ", true);
		while (st.hasMoreTokens()) {
			String sistemas[] = { "sistemas", "facsistel", "electronica",
					"sistema", "telecomunicaciones", "informática","informatica" };
			String upse[] = { "upse", "universidad", "estatal", "peninsula",
					"santa", "elena", "universidades" };
			// buscarPalabra(st.nextToken());
			String palabra = st.nextToken().toString();
			System.out.println(palabra);
			System.out.println("");
			if (palabra.trim().length() > 0) {
				for (int y = 0; y < sistemas.length; y++) {
					System.out.println(" " + sistemas[y].toString());
					if (palabra.toLowerCase().equals(sistemas[y].toString())) {
						System.out.println("palabra encontrada");
						Intent activity = new Intent(this,
								Sistemas_Telecomunicasiones.class);
						startActivity(activity);
						bandera=false;
						return;
					}
				}
				for (int y = 0; y < sistemas.length; y++) {
					System.out.println(" " + upse[y].toString());
					if (palabra.toLowerCase().equals(upse[y].toString())) {
						System.out.println("palabra encontrada");
						Intent activity = new Intent(this, Activity_UPSE.class);
						startActivity(activity);
						bandera=false;
						return;
					} 

				}
			}

		}
		if (bandera==false){
			Toast.makeText(
					getApplicationContext(),
					"No se encuentra ninguna refenencia al texto escaneado, intente escaneando nuevamente...",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void BuscarTextoEscaneado() {
		// preguntamos si el cursor contiene elementos; la palabra no existe en
		// la base de datos
		if (cursor.getCount() == 0) {
			// mensaje guia
			System.out.println("adaptador vacio");
			// creamos un cuadrio de dialogo y le asignamos el contexto actual
			AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
			// asignamos un titulo al cuadro de dialogo
			dialog1.setTitle("Atencion");
			// asignamos el mensaje a mostrar en el cuadro de dialogo
			dialog1.setMessage("¿Desea guardar el nuevo texto encontrado?");
			dialog1.setCancelable(false);
			// seteamos la opcion acptar y el evento q realizaremos
			dialog1.setPositiveButton("Aceptar",
					new DialogInterface.OnClickListener() {
						// evento disparado al dar click en aceptar
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							aceptar();
						}
					});
			// seteamos la opcion cancelar y el evento q realizaremos
			dialog1.setNegativeButton("Cancelar",
					new DialogInterface.OnClickListener() {
						// evento disparado al dar click en cancelar
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							cancelar();
						}
					});
			dialog1.show();
		} else {
			// adptador con informacion, la palabra existe en la base de datos
			System.out.println("adaptador con informacion");
		}

	}

	private void aceptar() {
		Intent activity = new Intent(this, GuardarInformacionActivity.class);
		// System.out.println("esta es la direccion: "+textoEscaneado);
		// activity.putExtra(ACT_INFO, textoEscaneado);
		startActivity(activity);
	}

	private void cancelar() {

	}

	public void buscarporParmaetro() {
		new BuscarTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacion, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_informacion,
					container, false);
			return rootView;
		}
	}

	// codigo implementado para ejecutar el proceso en segundo plano si tenemos
	// una numero alto de datos
	// en nuestra BD, los metodos se ejectutan en el hilo prinsipal
	public class BuscarTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// se ejecuta mientras se inicia la busqueda
			Toast.makeText(getApplicationContext(), "Buscando...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// se jecuta cuando se esta buscando
			cursor = manager.buscarPorTextoEscaneado(TBuscar);
			return null;
		}

		protected void onProgressUpdate(Float... valores) {
			int p = Math.round(100 * valores[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			// se ejecuta cuando se ha terminado la busqueda
			adapter.changeCursor(cursor);
			Toast.makeText(getApplicationContext(), "Finalizada...",
					Toast.LENGTH_SHORT).show();
			cursor.close();
			/*
			 * if (cursor.getCount()==0){ Intent inte = new
			 * Intent(getApplicationContext(),GuardarInformacionActivity.class);
			 * inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 * startActivity(inte); finish(); }
			 */
		}

	}

	/*
	 * @Override protected void onDestroy() { // TODO Auto-generated method stub
	 * super.onDestroy();
	 * System.out.println("destruimos la actividad informacionActivity");
	 * cursor.close(); //
	 * adapter.setDropDownViewResource(TRIM_MEMORY_BACKGROUND); }
	 */
}
