package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.dto.UsuarioDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author MAURO FABRIZIO RAMOS MESIAS
 */
public class ClienteController {

    public String url = "https://backendambulancia.onrender.com/vv/api/v1/registrarUsuario";

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController() {
        this.url = "https://backendambulancia.onrender.com/vv/api/v1/registrarUsuario";
    }

    /**
     * Registra un nuevo usuario Secretario.
     *
     * @param  cedula          La cédula del usuario.
     * @param  nombres         Los nombres del usuario.
     * @param  apellidos       Los apellidos del usuario.
     * @param  sexo            El sexo del usuario.
     * @param  correo          El correo del usuario.
     * @param  fechaNacimiento La fecha de nacimiento del usuario.
     * @param  usuario         El nombre de usuario.
     * @param  contrasena      La contraseña del usuario.
     * @param  secretario      El tipo de usuario (en este caso, "Secretario").
     * @return true si el registro se realiza con éxito, false de lo contrario.
     */
    public boolean registrar(final String cedula, final String nombres, final String apellidos, final String sexo, final String correo, final String fechaNacimiento, final String usuario, final String contrasena, final String secretario) {
        boolean flag = false;
        final OkHttpClient client = new OkHttpClient();
        final MediaType mediaType = MediaType.parse("application/json");
        final String requestBody = "{\"cedula\":\"" + cedula + "\",\"nombres\":\"" + nombres + "\",\"apellidos\":\"" + apellidos + "\",\"sexo\":\"" + sexo + "\",\"correo\":\"" + correo + "\",\"fechaNacimiento\":\"" + fechaNacimiento + "\",\"fechaContrato\":\"\",\"nombreUsuario\":\"" + usuario + "\",\"contrasena\":\"" + contrasena + "\",\"tipo\":\"" + "Secretario" + "\"}";
        final RequestBody resquestBody = RequestBody.create(mediaType, requestBody);

        final Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();

        try (Response response = client.newCall(resquest).execute()) {
            flag = true;
        } catch (IOException e) {
            LOGGER.error("Se produjo una excepción al registrar al Cliente", e);
            flag = false;
        }
        return flag;
    }

    /**
     * Consulta un usuario Cliente por cédula o nombre.
     *
     * @param  cedula Indica si se realizará la búsqueda por cédula.
     * @param  nombre Indica si se realizará la búsqueda por nombre.
     * @param  dato   El dato a buscar (cedula o nombre).
     * @return        El objeto UsuarioDTO correspondiente al cliente encontrado, o
     *                null si no se encuentra ningún cliente.
     */
    public UsuarioDTO consultarCN(final boolean cedula, final boolean nombre, final String dato) {
        UsuarioDTO usuario = new UsuarioDTO();
        if (cedula) {
            for (final UsuarioDTO c : this.obtenerClientes()) {
                if (dato.equals(c.getCedula())) {
                    // Cambia el return c por el usuario igual a lo que tiene c
                    usuario = c;
                }
            }
        } else if (nombre) {
            for (final UsuarioDTO c : this.obtenerClientes()) {
                if (dato.equals(c.getNombres())) {
                    usuario = c;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: El usuario no ha podido ser encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return usuario;
    }

    /**
     * Obtiene una lista de clientes.
     *
     * @return La lista de clientes obtenida.
     */
    public List<UsuarioDTO> obtenerClientes() {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://backendambulancia.onrender.com/vv/api/v1/listarUsuarios/Secretario")
                .build();
        final List<UsuarioDTO> clientes = new ArrayList<>();
        try {
            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                final String responseBody = response.body().string();
                final JSONArray clientesArray = new JSONArray(responseBody);
                for (int i = 0; i < clientesArray.length(); i++) {
                    final JSONObject clienteJson = clientesArray.getJSONObject(i);
                    final UsuarioDTO cliente = new UsuarioDTO();
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
            }
        } catch (IOException e) {
            LOGGER.error("Se produjo una excepción al obtener los clientes", e);
        }
        return clientes;
    }

    /**
     * Actualiza los datos de un usuario.
     *
     * @param  nombres       Los nuevos nombres del usuario.
     * @param  apellidos     Los nuevos apellidos del usuario.
     * @param  sexo          El nuevo sexo del usuario.
     * @param  correo        El nuevo correo electrónico del usuario.
     * @param  nombreUsuario El nuevo nombre de usuario del usuario.
     * @param  identificador El ID del usuario a actualizar.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizar(final String nombres, final String apellidos, final String sexo, final String correo, final String nombreUsuario, final String identificador) {
        boolean flag = false;
        final OkHttpClient client = new OkHttpClient();
        final String jsonBody = "{\"nombres\": \"" + nombres + "\", "
                + "\"apellidos\": \"" + apellidos + "\", "
                + "\"sexo\": \"" + sexo + "\", "
                + "\"correo\": \"" + correo + "\", "
                + "\"nombreUsuario\": \"" + nombreUsuario + "\"}";
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        final Request request = new Request.Builder()
                .url("https://backendambulancia.onrender.com/vv/api/v1/actualizarUsuario?id=" + identificador)
                .put(requestBody)
                .build();
        try {
            final Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(null, "¡Se ha actualizado los datos correctamente!", "Actualización correcta", JOptionPane.INFORMATION_MESSAGE);
                flag = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error: Datos faltantes", "Error", JOptionPane.ERROR_MESSAGE);
                flag = false;
            }
        } catch (Exception e) {
            LOGGER.error("Error al ejecutar la solicitud HTTP para actualizar los datos", e);
            flag = false;
        }
        return false;
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param  identificador El ID del usuario a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminar(final long identificador) {
        boolean flag = false;
        final OkHttpClient client = new OkHttpClient();
        final String url = "https://backendambulancia.onrender.com/vv/api/v1/eliminarUsuario?id=" + identificador;
        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        final int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar al usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try (Response response = client.newCall(request).execute()) {

                if (response.isSuccessful()) {
                    flag = true;
                }
            } catch (IOException e) {
                LOGGER.error("Error al ejecutar la solicitud HTTP, al eliminar", e);
                flag = false;
            }
        }
        return flag;
    }
}