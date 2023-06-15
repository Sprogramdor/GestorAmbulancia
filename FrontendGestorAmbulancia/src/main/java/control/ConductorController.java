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

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
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
                                   conductor.setId(conductorJson.getLong("id"));
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
    
    /**
     * Registra un conductor enviando los datos a la API.
     *
     * @param conductor Objeto de tipo Conductor con los datos del conductor a registrar
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarConductor(ConductorDTO conductor) {
        // Crear un objeto Gson para convertir el objeto Model a JSON
        Gson gson = new Gson();

        // Convertir el objeto Model a JSON
        String jsonBody = gson.toJson(conductor);

        // Crear el objeto OkHttpClient
        OkHttpClient client = new OkHttpClient();

        // Configurar la URL de la API
        String url = "https://backendambulancia.onrender.com/vv/api/v1/registrarConductor";

        // Crear el cuerpo de la solicitud
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        // Crear la solicitud POST
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Manejar la respuesta de la API, como obtener el código de estado, el cuerpo de la respuesta, etc.
            // Devolver true si el registro fue exitoso, false en caso contrario
            return response.isSuccessful();
        } catch (IOException e) {
            // Manejar errores de conexión o solicitud HTTP
            e.printStackTrace();
        }

        return false;
    }
    
    
    
}
