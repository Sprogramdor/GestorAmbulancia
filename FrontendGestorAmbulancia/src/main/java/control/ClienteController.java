/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

        Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();

        try (Response response = client.newCall(resquest).execute()) {
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public UsuarioDTO ConsultarCN(boolean cedula, boolean nombre, String dato) {
        if (cedula) {
            for (UsuarioDTO c : this.obtenerClientes()) {
                if (dato.equals(c.getCedula())) {
                    return c;
                }
            }
        } else if (nombre) {
            for (UsuarioDTO c : this.obtenerClientes()) {
                if (dato.equals(c.getNombres())) {
                    return c;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public List<UsuarioDTO> obtenerClientes() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://backendambulancia.onrender.com/vv/api/v1/listarUsuarios/Secretario")
                .build();
        List<UsuarioDTO> clientes = new ArrayList<>();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray clientesArray = new JSONArray(responseBody);
                for (int i = 0; i < clientesArray.length(); i++) {
                    JSONObject clienteJson = clientesArray.getJSONObject(i);
                    UsuarioDTO cliente = new UsuarioDTO();
                    cliente.setId(clienteJson.getLong("id"));
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
            } else {
                System.out.println("Error" + response.code() + " XD" + response.message());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return clientes;
    }

    public boolean Actualizar(String nombres, String apellidos, String sexo, String correo, String nombreUsuario, String id) {
        OkHttpClient client = new OkHttpClient();
        String jsonBody = "{\"nombres\": \"" + nombres + "\", "
                + "\"apellidos\": \"" + apellidos + "\", "
                + "\"sexo\": \"" + sexo + "\", "
                + "\"correo\": \"" + correo + "\", "
                + "\"nombreUsuario\": \"" + nombreUsuario + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url("https://backendambulancia.onrender.com/vv/api/v1/actualizarUsuario?id=" + id)
                .put(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(null, "¡Se ha actualizado los datos correctamente!", "Actualización correcta", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error: Datos faltantes", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean eliminar(long id) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://backendambulancia.onrender.com/vv/api/v1/eliminarUsuario?id=" + id;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar al usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        // Verificar la respuesta
        if (respuesta == JOptionPane.YES_OPTION) {
            try (Response response = client.newCall(request).execute()) {
                System.out.println(response.toString());
                if (response.isSuccessful()) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } 
        return false;
    }

}
