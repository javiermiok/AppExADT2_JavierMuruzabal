package com.example.a21752434.appexadt2_javiermuruzabal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a21752434.appexadt2_javiermuruzabal.model.Municipio;

import java.util.ArrayList;

public class EventosDBSQLiteHelper extends SQLiteOpenHelper {

    /*--------------------------                 ATRIBUTOS                    --------------------*/
    private ArrayList<Municipio> datosIniciales; // se carga con los datos de municipio madrid norte.txt

    static final String DATABASE_NAME = "EventosDB";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_TABLE_EVENTOS =
            "CREATE TABLE "+ EventodDBContract.EventoEntry.TABLE_NAME+ "( "+
                    EventodDBContract.EventoEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    EventodDBContract.EventoEntry.COLUMN_NAME_EVENT+" TEXT UNIQUE NOT NULL ," +
                    EventodDBContract.EventoEntry.COLUMN_NAME_MUN+" TEXT NOT NULL," +
                    EventodDBContract.EventoEntry.COLUMN_DESCRIPTION+" TEXT NOT NULL," +
                    EventodDBContract.EventoEntry.COLUMN_DATE+" TEXT NOT NULL," +
                    EventodDBContract.EventoEntry.COLUMN_HOUR +" TEXT NOT NULL);";

    static final String CREATE_TABLE_MUNICIPIOS=
            "CREATE TABLE "+ EventodDBContract.MunicipioEntry.TABLE_NAME+ "( "+
                    EventodDBContract.MunicipioEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    EventodDBContract.MunicipioEntry.COLUMN_NAME +" TEXT NOT NULL);";

    /*--------------------------                CONSTRUCTOR                   --------------------*/
    public EventosDBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*----------------------              MÉTODOS SQLiteOpenHelper                ----------------*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTOS);
        db.execSQL(CREATE_TABLE_MUNICIPIOS);
        iniciarDatos();
        cargarDatos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*----------------------                 MÉTODOS auxiliares                   ----------------*/
    private void iniciarDatos() {
        datosIniciales = new ArrayList<Municipio>();

        datosIniciales.add(new Municipio("LA ACEBEDA"));
        datosIniciales.add(new Municipio("ALALPARDO"));
        datosIniciales.add(new Municipio("ALAMEDA DEL VALLE"));
        datosIniciales.add(new Municipio("ALCOBENDAS"));
        datosIniciales.add(new Municipio("ALGETE"));
        datosIniciales.add(new Municipio("EL ATAZAR"));
        datosIniciales.add(new Municipio("BERZOSA"));
        datosIniciales.add(new Municipio("EL BERRUECO"));
        datosIniciales.add(new Municipio("BRAOJOS"));
        datosIniciales.add(new Municipio("BUITRAGO"));
        datosIniciales.add(new Municipio("BUSTARVIEJO"));
        datosIniciales.add(new Municipio("CABANILLAS DE LA SIERRA"));
        datosIniciales.add(new Municipio("LA CABRERA"));
        datosIniciales.add(new Municipio("CANENCIA"));
        datosIniciales.add(new Municipio("CERVERA DE BUITRAGO"));
        datosIniciales.add(new Municipio("COLMENAR VIEJO"));
        datosIniciales.add(new Municipio("FUENTE EL SAZ"));
        datosIniciales.add(new Municipio("GARGANTA DE LOS MONTES"));
        datosIniciales.add(new Municipio("GARGANTILLA"));
        datosIniciales.add(new Municipio("GASCONES"));
        datosIniciales.add(new Municipio("GUADALIX DE LA SIERRA"));
        datosIniciales.add(new Municipio("LA HIRUELA"));
        datosIniciales.add(new Municipio("HORCAJO DE LA SIERRA"));
        datosIniciales.add(new Municipio("HORCAJUELO DE LA SIERRA"));
        datosIniciales.add(new Municipio("LOZOYA"));
        datosIniciales.add(new Municipio("LOZOYUELA"));
        datosIniciales.add(new Municipio("MADARCOS"));
        datosIniciales.add(new Municipio("MANZANARES EL REAL"));
        datosIniciales.add(new Municipio("MIRAFLORES DE LA SIERRA"));
        datosIniciales.add(new Municipio("EL MOLAR"));
        datosIniciales.add(new Municipio("MONTEJO DE LA SIERRA"));
        datosIniciales.add(new Municipio("NAVALAFUENTE"));
        datosIniciales.add(new Municipio("NAVARREDONDA"));
        datosIniciales.add(new Municipio("OTERUELO DEL VALLE"));
        datosIniciales.add(new Municipio("PRÁDENA DEL RINCÓN"));
        datosIniciales.add(new Municipio("PAREDES DE BUITRAGO"));
        datosIniciales.add(new Municipio("PATONES"));
        datosIniciales.add(new Municipio("PEDREZUELA"));
        datosIniciales.add(new Municipio("PINILLA DEL VALLE"));
        datosIniciales.add(new Municipio("PIÑUECAR"));
        datosIniciales.add(new Municipio("PUEBLA DE LA SIERRA"));
        datosIniciales.add(new Municipio("PUENTES VIEJAS"));
        datosIniciales.add(new Municipio("RASCAFRÍA"));
        datosIniciales.add(new Municipio("RIBATEJADA"));
        datosIniciales.add(new Municipio("RIDUEÑA"));
        datosIniciales.add(new Municipio("ROBLEDILLO"));
        datosIniciales.add(new Municipio("ROBREGORDO"));
        datosIniciales.add(new Municipio("SAN AGUSTÍN DEL GUADALIX"));
        datosIniciales.add(new Municipio("SAN SEBASTIÁN DE LOS REYES"));
        datosIniciales.add(new Municipio("SERNA DEL MONTELA"));
        datosIniciales.add(new Municipio("SOMOSIERRA"));
        datosIniciales.add(new Municipio("SOTO DEL REAL"));
        datosIniciales.add(new Municipio("TALAMANCA DE JARAMA"));
        datosIniciales.add(new Municipio("TORRELAGUNA"));
        datosIniciales.add(new Municipio("TORREMOCHA DE JARAMA"));
        datosIniciales.add(new Municipio("TRES CANTOS"));
        datosIniciales.add(new Municipio("VALDEMANCO"));
        datosIniciales.add(new Municipio("VALDEOLMOS"));
        datosIniciales.add(new Municipio("VALDEPIÉLAGOS"));
        datosIniciales.add(new Municipio("VALDETORRES DEL JARAMA"));
        datosIniciales.add(new Municipio("EL VELLÓN"));
        datosIniciales.add(new Municipio("VENTURADA"));
        datosIniciales.add(new Municipio("VILLAVIEJA LOZOYA"));
    }

    private void cargarDatos(SQLiteDatabase db) {
        db.beginTransaction();

        ContentValues cv = null;

        for (Municipio mun: datosIniciales) {
            cv = new ContentValues();
            cv.put(EventodDBContract.MunicipioEntry.COLUMN_NAME, mun.getNombre());
            db.insert(EventodDBContract.MunicipioEntry.TABLE_NAME, null, cv);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
