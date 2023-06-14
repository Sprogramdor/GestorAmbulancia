/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.dto.ClienteDTO;
import model.dto.UsuarioDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
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
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"cedula\":\"" + cedula + "\",\"nombres\":\"" + nombres + "\",\"apellidos\":\"" + apellidos + "\",\"sexo\":\"" + sexo + "\",\"correo\":\"" + correo + "\",\"fechaNacimiento\":\"" + fechaNacimiento + "\",\"fechaContrato\":\"\",\"nombreUsuario\":\"" + usuario + "\",\"contrasena\":\"" + contrasena + "\",\"tipo\":\"" + "Secretario" + "\"}";
        RequestBody resquestBody = RequestBody.create(mediaType, requestBody);
        System.out.println(requestBody);
        
        Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();
        
        try (Response response = client.newCall(resquest).execute()) {
            String responseBody = response.body().string();
            System.out.println("Respuesta del servidor: " + responseBody);
            return true;

            // Analizar el JSON de respuesta
            //JSONObject jsonObject = new JSONObject(responseBody);
            //System.out.println(jsonObject.toString());
            
        } catch (IOException e) {
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
        }
        return false;
    }

    public boolean Consultar(){
        return false;
    }
    
    public List<UsuarioDTO> obtenerClientes(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://backendambulancia.onrender.com/vv/api/v1/listarUsuarios/Secretario")
                .build();
        List<UsuarioDTO> clientes = new ArrayList<>();
        try{
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                String responseBody = response.body().string();
                JSONArray clientesArray = new JSONArray(responseBody);
                for(int i = 0; i< clientesArray.length(); i++){
                    JSONObject clienteJson = clientesArray.getJSONObject(i);
                    UsuarioDTO cliente = new UsuarioDTO();
                    cliente.setCedula(clienteJson.getString("cedula"));
                    cliente.setNombres(clienteJson.getString("nombres"));
                    cliente.setApellidos(clienteJson.getString("apellidos"));
                    cliente.setSexo(clienteJson.getString("sexo"));
                    cliente.setCorreo(clienteJson.getString("correo"));
                    cliente.setFechaNacimiento(clienteJson.getString("fechaNacimiento"));
                    cliente.setNombreUsuario(clienteJson.getString("nombreUsuario"));
                    cliente.setContrasena(null);
                    cliente.setTipo("Cliente");
                    
                    clientes.add(cliente);
                }
            }else{
                System.out.println("Error" + response.code() + " XD" + response.message());
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        System.out.println(clientes.toString());
        return clientes;
    }
}
