package unal.todosalau.SolarSport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unal.todosalau.SolarSport.modelos.Electricidad;
import unal.todosalau.ahorravoltios.R;

public class Statistics extends AppCompatActivity {
private TableLayout tableLayout;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statistics);

    //Elementos de la interfaz
    Button botonRegresar = findViewById(R.id.botonRetroceso2);
    tableLayout = findViewById(R.id.myTableLayout);
    Intent intent = new Intent(this, Principal.class);

    //Carga de los archivos de electricidad

    File electricidadFile = new File(getFilesDir(), "electricidad.txt");

    //Listas de Electricidad
    List<Electricidad> listaElectricidad = leerArchivoElectricidad(electricidadFile);

    //Crear la Tabla

    addElementElectricidad(listaElectricidad);
    addPromedioElectricidad(listaElectricidad);

    //Boton Regresar
    botonRegresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    });
    }

    private void addPromedioElectricidad(List<Electricidad> electricidadList){
    // Obtén una referencia al TableLayout en tu actividad o fragmento

    float promedioConsumoElectricidad = calcularPromedioKilovatios(electricidadList);
    float promedioPrecioElectricidad = calcularPromedioPrecioElectricidad(electricidadList);

    TableRow row = new TableRow(this);
    //AÑADE LA INFORMACIÓN A LA CELDA 1
    TextView cell1 = new TextView(this);
    cell1.setText("Promedio");
    cell1.setPadding(10, 10, 10, 10);
    cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
    // color deseado

    //AÑADE LA INFORMACIÓN A LA CELDA 2
    TextView cell2 = new TextView(this);
    cell2.setText("electricidad");
    cell2.setPadding(10, 10, 10, 10);
    cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


    //AÑADE LA INFORMACIÓN A LA CELDA 3
    TextView cell3 = new TextView(this);
    cell3.setText(String.valueOf(promedioConsumoElectricidad));
    cell3.setPadding(10, 10, 10, 10);
    cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
    // el color

    //AÑADE LA INFORMACIÓN A LA CELDA 4
    TextView cell4 = new TextView(this);
    cell4.setText(String.valueOf(promedioPrecioElectricidad));
    cell4.setPadding(10, 10, 10, 10);
    cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
    // el color

    // Agrega las celdas a la fila
    row.addView(cell1);
    row.addView(cell2);
    row.addView(cell3);
    row.addView(cell4);
    // Agrega la fila al TableLayout
    tableLayout.addView(row);
}
    private void addElementElectricidad(List<Electricidad> electricidadList){
    // Obtén una referencia al TableLayout en tu actividad o fragmento

    for (Electricidad item: electricidadList) {
        // Crea una nueva fila y agrega las celdas
        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText(item.getMes());

        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("Electricidad");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf((item.getKilovatios())));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf((item.getPrecio())));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }
}

    private static List<Electricidad> leerArchivoElectricidad(File archivo) {
    List<Electricidad> listaElectricidad = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            float kilovatios = Float.parseFloat(datos[0]);
            float precio = Float.parseFloat(datos[1]);
            String mes = datos[2];

            Electricidad electricidad = new Electricidad(kilovatios, precio, mes);
            listaElectricidad.add(electricidad);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return listaElectricidad;
}


    private float calcularPromedioKilovatios(List<Electricidad> electricidadList) {
        float sum = 0;
        for (Electricidad item : electricidadList) {
            sum += item.getKilovatios();
        }
        return sum / electricidadList.size();
    }

    private float calcularPromedioPrecioElectricidad(List<Electricidad> electricidadList) {
        float sum = 0;
        for (Electricidad item : electricidadList) {
            sum += item.getPrecio();
        }
        return sum / electricidadList.size();
    }
}
