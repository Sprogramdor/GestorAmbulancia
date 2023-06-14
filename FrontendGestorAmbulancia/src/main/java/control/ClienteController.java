/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import view.FrmCPeticion;
import view.FrmMenu;

/**
 *
 * @author fbrz
 */
public class ClienteController {
    
    public String url = "https://backendambulancia.onrender.com/vv/api/v1/registrarUsuario";

    public boolean Registrar(String cedula, String nombres, String apellidos, String sexo, String correo, String fechaNacimiento, String usuario, String contrasena, String secretario) {
        OkHttpClient cliente = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"cedula\":\"" + cedula + "\",\"nombres\":\"" + nombres + "\",\"apellidos\":\"" + apellidos + "\",\"sexo\":\"" + sexo + "\",\"correo\":\"" + correo + "\",\"fechaNacimiento\":\"" + fechaNacimiento + "\",\"fechaContrato\":\"\",\"nombreUsuario\":\"" + usuario + "\",\"contrasena\":\"" + contrasena + "\",\"tipo\":\"" + "Secretario" + "\"}";
        RequestBody resquestBody = RequestBody.create(mediaType, requestBody);
        System.out.println(requestBody);
        
        Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();
        
        try (Response response = cliente.newCall(resquest).execute()) {
            String responseBody = response.body().string();
            System.out.println("Respuesta del servidor: " + responseBody);

            // Analizar el JSON de respuesta
            JSONObject jsonObject = new JSONObject(responseBody);
            System.out.println(jsonObject.toString());
            return true;
            
        } catch (IOException e) {
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
        }
        return false;
    }

}
