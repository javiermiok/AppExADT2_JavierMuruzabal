package com.example.a21752434.appexadt2_javiermuruzabal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class MunicipioDatasource {

    private EventosDBSQLiteHelper esh;

    /*--------------------------------          CONSTRUCTOR           ----------------------------*/

    public MunicipioDatasource(Context contexto) {
        esh = new EventosDBSQLiteHelper(contexto);
    }

    /*--------------------------------            ACCESO DB           ----------------------------*/
    public SQLiteDatabase openReadable() {
        return esh.getReadableDatabase();
    }
    /*public SQLiteDatabase openWriteable() {
        return esh.getWritableDatabase();
    }*/
    public void close(SQLiteDatabase database) {
        database.close();
    }


    /*--------------------------------             CONSULTA           ----------------------------*/
    public boolean consultarMunicipioNombre(String nombre) {
        boolean existe = false;

        SQLiteDatabase sdb = openReadable();

        String select = "SELECT * FROM " + EventodDBContract.MunicipioEntry.TABLE_NAME +
                " WHERE " + EventodDBContract.MunicipioEntry.COLUMN_NAME + " = ?";
        String[] args = {nombre};

        Cursor cursor = sdb.rawQuery(select, args);

        if(cursor.moveToFirst()) {
            existe = true;
        }

        cursor.close();
        sdb.close();

        return existe;
    }
}
