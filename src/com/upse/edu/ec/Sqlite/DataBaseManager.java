package com.upse.edu.ec.Sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DataBaseManager {
	public static final String TABLE_NAME="departamento";
	public static final String CN_ID="_id";
	public static final String CN_NOMBRE="nombre";
	public static final String CN_TEXTO="texto";
	
	public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
			+ CN_ID +" integer primary key autoincrement,"
			+ CN_NOMBRE +" text,"
			+ CN_TEXTO +" text);";

	private DbHelper helper;
	private SQLiteDatabase db;
	public DataBaseManager(Context context) {
		//instanciamos la base de datos
		 helper=new DbHelper(context);
		//abrimos la DB a modo writable si no existe la BD la crea y la devuelve en modo escritura
		//si existe solo la devuelve
		 db=helper.getWritableDatabase();
		 System.out.println("el sql es : "+CREATE_TABLE);		
	}
	
	public ContentValues generarContentValues(String nombre, String texto){
		ContentValues valores=new ContentValues();
		valores.put(CN_NOMBRE,nombre);
		valores.put(CN_TEXTO,texto);
		return valores;
	}
	//insertar con metodos q provee android
	public void insertar(String nombre, String texto){
		db.insert(TABLE_NAME, null,generarContentValues(nombre, texto));
	}
	//insertar con sentencia
	/*public void insertar2(String telefono, String correo){
		db.execSQL("Insert into "+TABLE_NAME+ "values (null,'"+CN_NOMBRE+"','"+CN_TEXTO+"');");
	}*/
	
	//Eliminar de a uno
	public void eliminar(String nombre){
		db.delete(TABLE_NAME, CN_NOMBRE+"=?", new String[] {nombre});
	}
	//eleiminar muchos
	public void eliminarMultiple(String nom1,String num2){
		db.delete(TABLE_NAME, CN_NOMBRE + "IN (?,?)",new String []{nom1,num2});
	}
	public void modificarEscaneo(String nombre, String texto){
		db.update(TABLE_NAME, generarContentValues(nombre,texto ), CN_NOMBRE + "=?",new String[] {nombre});
	}
	public Cursor CargarcursorInformacion(){
		String [] columnas=new String[]{CN_ID,CN_NOMBRE,CN_TEXTO};
		return db.query(TABLE_NAME,columnas,null,null,null,null,null);
	}
	public Cursor buscarPorTextoEscaneado(String texto){
		String [] columnas=new String[]{CN_ID,CN_NOMBRE,CN_TEXTO};
		return db.query(TABLE_NAME,columnas,CN_TEXTO+"=?",new String[] {texto},null,null,null);
	}
}
