package com.example.a21752434.appexadt2_javiermuruzabal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a21752434.appexadt2_javiermuruzabal.model.Evento;

public class EventoDatasource {

    private EventosDBSQLiteHelper esh;

    /*--------------------------------          CONSTRUCTOR           ----------------------------*/

    public EventoDatasource(Context contexto) {
        esh = new EventosDBSQLiteHelper(contexto);
    }

    /*--------------------------------            ACCESO DB           ----------------------------*/
    public SQLiteDatabase openReadable() {
        return esh.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return esh.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    /*--------------------------------             CONSULTA           ----------------------------*/
    /**
     *
     * @param nEvento  nombre de un Evento
     * @return null si no encuentra nada, si no el objeto del evento con ese nombre
     */
    public Evento consultarEvento(String nEvento) {
        SQLiteDatabase sdb = openReadable();

        String select = "SELECT * FROM " + EventodDBContract.EventoEntry.TABLE_NAME +
                " WHERE " + EventodDBContract.EventoEntry.COLUMN_NAME_EVENT+ " = ?";
        String[] args = {nEvento};

        Cursor cursor = sdb.rawQuery(select, args);

        Evento evento = null;
        long id;
        String nMun;
        String descripcion;
        String fecha;
        String hora;

        if(cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(EventodDBContract.EventoEntry.COLUMN_ID));
            nMun = cursor.getString(cursor.getColumnIndex(EventodDBContract.EventoEntry.COLUMN_NAME_MUN));
            descripcion = cursor.getString(cursor.getColumnIndex(EventodDBContract.EventoEntry.COLUMN_DESCRIPTION));
            fecha = cursor.getString(cursor.getColumnIndex(EventodDBContract.EventoEntry.COLUMN_DATE));
            hora = cursor.getString(cursor.getColumnIndex(EventodDBContract.EventoEntry.COLUMN_HOUR));
            evento = new Evento(id, nEvento, nMun, descripcion, fecha, hora);
        }

        cursor.close();
        sdb.close();
        return  evento;

    }


    /*--------------------------------             ALTA             ----------------------------*/
    public long insertarEvento(Evento evento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(EventodDBContract.EventoEntry.COLUMN_NAME_EVENT, evento.getnEvento());
        cv.put(EventodDBContract.EventoEntry.COLUMN_NAME_MUN, evento.getnMunicipio());
        cv.put(EventodDBContract.EventoEntry.COLUMN_DESCRIPTION, evento.getDescripcion());
        cv.put(EventodDBContract.EventoEntry.COLUMN_DATE, evento.getFecha());
        cv.put(EventodDBContract.EventoEntry.COLUMN_HOUR, evento.getHora());


        long id = sdb.insert(EventodDBContract.EventoEntry.TABLE_NAME, null, cv);

        if(id != -1) {
            sdb.setTransactionSuccessful();
        }

        sdb.endTransaction();
        close(sdb);
        return id;
    }

    /*--------------------------------            MODIFICAR           ----------------------------*/
    public int modificarEvento(Evento ev) {
        SQLiteDatabase sdb = openWriteable();

        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(EventodDBContract.EventoEntry.COLUMN_NAME_EVENT, ev.getnEvento());
        cv.put(EventodDBContract.EventoEntry.COLUMN_NAME_MUN, ev.getnMunicipio());
        cv.put(EventodDBContract.EventoEntry.COLUMN_DESCRIPTION, ev.getDescripcion());
        cv.put(EventodDBContract.EventoEntry.COLUMN_DATE, ev.getFecha());
        cv.put(EventodDBContract.EventoEntry.COLUMN_HOUR, ev.getHora());

        String clausulaWhere = EventodDBContract.EventoEntry.COLUMN_ID+" = ?";
        String[] args = {String.valueOf(ev.getId())};

        int result = sdb.update(EventodDBContract.EventoEntry.TABLE_NAME, cv, clausulaWhere,args);

        if (result == 1) { // el número de filas modificadas
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);
        return result;
    }
    /*--------------------------------            ELIMINAR          ----------------------------*/
    public int borrarEvento(long idEvento) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        String clausulaWhere = EventodDBContract.EventoEntry.COLUMN_ID + " = " + idEvento;
        int i = sdb.delete(EventodDBContract.EventoEntry.TABLE_NAME,
                clausulaWhere, null);

        if (i == 1) {
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);
        return i;
    }
}
