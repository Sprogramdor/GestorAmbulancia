/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import model.dto.ConductorDTO;
/**
 *
 * @author Jesus
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.dto.ConductorDTO;

/**
 * Controlador que maneja las operaciones relacionadas con los conductores.
 */
public class ConductorController {
    /**
     * Consulta la API de conductores y devuelve una lista de objetos Conductor.
     *
     * @return Una lista de objetos Conductor.
     * @throws IOException Si ocurre un error al realizar la solicitud HTTP.
     */
    
    
    public  List<ConductorDTO> obtenerConductores() throws IOException {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://backendambulancia.onrender.com/vv/api/v1/listarConductores")
                            .build();

                     List<ConductorDTO> conductores = new ArrayList<>();

                    try {
                        Response response = client.newCall(request).execute();
                            if (response.isSuccessful()) {
                                String responseBody = response.body().string();
                                JSONArray conductoresArray = new JSONArray(responseBody);

                                for (int i = 0; i < conductoresArray.length(); i++) {
                                    JSONObject conductorJson = conductoresArray.getJSONObject(i);
                                    ConductorDTO conductor = new ConductorDTO();

                                    conductor.setCedula(conductorJson.getString("cedula"));
                                    conductor.setNombre(conductorJson.getString("nombre"));
                                    conductor.setApellidos(conductorJson.getString("apellidos"));
                                    conductor.setCorreo(conductorJson.getString("correo"));
                                    conductor.setFechaContrato(conductorJson.getString("fechaContrato"));
                                    conductor.setFechaNacimiento(conductorJson.getString("fechaNacimiento"));
                                    conductor.setSexo(conductorJson.getString("sexo"));
                                   conductor.setEstado(conductorJson.getBoolean("estado")   );
                                    // Agregar más propiedades según el modelo de Conductor

                                    conductores.add(conductor);
                                }
            } else {
                System.out.println("Error: " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return conductores;
    }
    
    
    
    public ConductorDTO consultabyCedula(String cedula) throws IOException{
        
        
        for(ConductorDTO c:this.obtenerConductores()){
            if(c.getCedula().equals(cedula)){
                 return c;
            }
        }
        
        return null;
    }
}
