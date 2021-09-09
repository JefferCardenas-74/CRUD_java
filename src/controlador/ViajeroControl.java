package controlador;

/**
 *
 * @author Jeffer Cardenas <jecgdevp@gmail.com>
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DatosViajero;
import modelo.Viajero;
import vista.main;

public class ViajeroControl {

    private final DatosViajero dViajero;
    private String mensaje;

    public ViajeroControl() {

        dViajero = new DatosViajero();

    }

    public String getMessage() {

        return this.mensaje;
    }

    public void listarViajeros(main frm) {

        this.mensaje = "";

        /*guardamos la lista que nos retorno el modelo de la base de datos*/
        List<Viajero> viajeros = this.dViajero.listarViajeros();

        /*convertimos la tabla del formulario  a un tipo DefaultTableModel para poder agregar filas
          de manera dinamica
         */
        DefaultTableModel model = (DefaultTableModel) frm.tbl_viajeros.getModel();
        frm.tbl_viajeros.setModel(model);

        /*creamos un array temporal que contendra los datos de la lista para posteriormente
          agregarlos a la tabla
         */
        String[] datos = new String[4];

        for (Viajero item : viajeros) {

            datos[0] = String.valueOf(item.getId());
            datos[1] = item.getNombre();
            datos[2] = item.getEspecie();
            datos[3] = item.getGenero();

            model.addRow(datos);
        }

    }

    public boolean crearViajero(main frm) {
        this.mensaje = "";
        boolean creado = false;

        Viajero viajero = new Viajero();
        viajero.setNombre(frm.txt_nombre.getText());
        viajero.setEspecie(frm.txt_especie.getText());
        viajero.setGenero(frm.txt_genero.getText());

        creado = dViajero.crearViajero(viajero);
        if (creado) {

            this.mensaje = "Viajero creado con exito";

        } else {

            this.mensaje = "Ha ocurrido un error al momento de crear un viajero";
        }

        return creado;

    }

    public void consultarXId(main frm) {
        this.mensaje = "";
        String id = frm.txt_id.getText();
        Viajero viajero = this.dViajero.consultarXId(id);

        if (viajero != null) {

            frm.txt_id.setText(String.valueOf(viajero.getId()));
            frm.txt_nombre.setText(viajero.getNombre());
            frm.txt_especie.setText(viajero.getEspecie());
            frm.txt_genero.setText(viajero.getGenero());
            frm.existe = true;
            
        }else{
            frm.existe = false;
            this.mensaje = dViajero.getMessage();
        }

    }
    
    public boolean actualizarViajero(main frm){
        
        boolean actualizado = false;
        
        Viajero viajero = new Viajero();
        viajero.setId(Integer.parseInt(frm.txt_id.getText()));
        viajero.setNombre(frm.txt_nombre.getText());
        viajero.setEspecie(frm.txt_especie.getText());
        viajero.setGenero(frm.txt_genero.getText());
        
        actualizado = this.dViajero.actualizarViajero(viajero);
        
        this.mensaje = dViajero.getMessage();
        
        return actualizado;
    }
    
    public boolean eliminarViajero(main frm){
        
        boolean eliminado = false;
        String id = frm.txt_id.getText();
        
        eliminado = this.dViajero.eliminarViajero(id);
        
        this.mensaje = dViajero.getMessage();
        
        return eliminado;
    }
}
