package com.example.a21752434.appexadt2_javiermuruzabal.db;

import android.provider.BaseColumns;

public class EventodDBContract {

    /*TABLA EVENTO*/
    public static abstract class EventoEntry implements BaseColumns {
        public static final String TABLE_NAME = "EVENTOS";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME_EVENT = "NEVENTO";
        public static final String COLUMN_NAME_MUN = "NMUNICIPIO";
        public static final String COLUMN_DESCRIPTION = "DESCRIPCION";
        public static final String COLUMN_DATE = "FECHA";
        public static final String COLUMN_HOUR = "HORA";
    }

    /*TABLA MUNICIPIO*/
    public static abstract class MunicipioEntry implements BaseColumns {
        public static final String TABLE_NAME = "MUNICIPIOS";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";

    }

}
