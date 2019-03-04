package com.example.a21752434.appexadt2_javiermuruzabal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21752434.appexadt2_javiermuruzabal.db.EventoDatasource;
import com.example.a21752434.appexadt2_javiermuruzabal.db.MunicipioDatasource;
import com.example.a21752434.appexadt2_javiermuruzabal.model.Evento;

public class MainActivity extends AppCompatActivity {

    private EditText etNEvento;
    private EditText etNMun;
    private EditText etDesc;
    private EditText etFecha;
    private EditText etHora;

    private Button btnReg;
    private Button btnCons;
    private Button btnMod;
    private Button btnBorrar;

    private EventoDatasource eds;
    private MunicipioDatasource mds;

    private Evento ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNEvento = findViewById(R.id.etNEventoMain);
        etNMun = findViewById(R.id.etNMunMain);
        etDesc = findViewById(R.id.etDescMain);
        etFecha = findViewById(R.id.etFechaMain);
        etHora = findViewById(R.id.etHoraMain);

        btnReg = findViewById(R.id.btnRegistrarMain);
        btnCons = findViewById(R.id.btnConsultarMain);
        btnMod = findViewById(R.id.btnModificarMain);
        btnBorrar = findViewById(R.id.btnEliminarMain);

        eds = new EventoDatasource(this);
        mds = new MunicipioDatasource(this);

        ev = null;

    }

    public void registrar(View v) {
        String nEvento = etNEvento.getText().toString().trim();
        String nMun = etNMun.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        String hora = etHora.getText().toString().trim();

        // 1ºComprobamos campos vacios
        if(nEvento.isEmpty() || nMun.isEmpty() || desc.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            mostrarMensaje(R.string.toast_error_campos);

            // 2º Buscamos si existe el municipio sin considerar MAYUSC o MINUSC
        } else if (!mds.consultarMunicipioNombre(nMun.toUpperCase())) {
            mostrarMensaje(R.string.toast_error_no_mun);

        } else {
            Evento ev = new Evento(nEvento, nMun, desc, fecha, hora);

            long resultado = eds.insertarEvento(ev);

            if(resultado == -1 ){
                mostrarMensaje(R.string.toast_error_insertar);
            } else {
                mostrarMensaje(R.string.toast_insertar_exito);
            }

            limpiarCampos();
        }
    }

    public void consultar(View v) {
        String nEvento = etNEvento.getText().toString().trim();

        // 1º Comprobamos si el nombre de evento está vacio
        if(nEvento.isEmpty()) {
            mostrarMensaje(R.string.toast_error_no_nevento);

        } else {
            // 2º Se consulta el evento por el nombre introducido
            ev = eds.consultarEvento(nEvento);
            // 3º Si no se encuentra
            if(ev == null) {
                mostrarMensaje(R.string.toast_no_evento);
            } else {
                // 3º Si se encuentra
                etNMun.setText(ev.getnMunicipio());
                etDesc.setText(ev.getDescripcion());
                etFecha.setText(ev.getFecha());
                etHora.setText(ev.getHora());

                etNEvento.setEnabled(false);

                btnReg.setEnabled(false);
                btnCons.setEnabled(false);
                btnMod.setEnabled(true);
                btnBorrar.setEnabled(true);

            }
        }
    }

    public void modificar(View v) {
        String nMun = etNMun.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        String hora = etHora.getText().toString().trim();

        // 1ºComprobamos campos vacios
        if(nMun.isEmpty() || desc.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            mostrarMensaje(R.string.toast_error_campos);


        } else if (nMun.equals(ev.getnMunicipio()) && desc.equals(ev.getDescripcion()) && fecha.equals(ev.getFecha()) && hora.equals(ev.getHora())){
            mostrarMensaje(R.string.toast_error_campos_nomod);

            // 2º Buscamos si existe el municipio sin considerar MAYUSC o MINUSC
        } else if (!mds.consultarMunicipioNombre(nMun.toUpperCase())) {
            mostrarMensaje(R.string.toast_error_no_mun);

        } else {

            Evento eventoMod = new Evento(ev.getId(), ev.getnEvento(), nMun, desc, fecha, hora);

            int resultado = eds.modificarEvento(eventoMod);

            if(resultado != 1 ){
                mostrarMensaje(R.string.toast_error_modificar);
            } else {
                mostrarMensaje(R.string.toast_modificar_exito);
            }

            limpiarActivar();
        }
    }

    public void eliminar(View v) {
        int resultado = eds.borrarEvento(ev.getId());
        if (resultado != 1) {
            mostrarMensaje(R.string.toast_eliminar_error);
        } else {
            mostrarMensaje(R.string.toast_eliminar_exito);
        }
        limpiarActivar();
    }

    private void limpiarActivar() {
        limpiarCampos();
        etNEvento.setEnabled(true);

        btnReg.setEnabled(true);
        btnCons.setEnabled(true);
        btnMod.setEnabled(false);
        btnBorrar.setEnabled(false);
    }

    private void limpiarCampos() {
        etNEvento.setText("");
        etNMun.setText("");
        etDesc.setText("");
        etFecha.setText("");
        etHora.setText("");
    }

    private void mostrarMensaje(int id) {
        Toast t = Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0,0);
        t.show();
    }
}
