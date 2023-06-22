package control;

import java.io.IOException;
import javax.swing.JOptionPane;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import view.FrmCPeticion;
import view.FrmMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author MAURO FABRIZIO RAMOS MESIAS
 *
 * Controlador para el inicio de sesión y recuperación de contraseña.
 */
public class LoginController {

    public String url = "https://backendambulancia.onrender.com/vv/api/v1/ingresarAlSistema";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
 
    /**
     * Inicia sesión en el sistema con las credenciales proporcionadas.
     *
     * @param usuario    El nombre de usuario.
     * @param contrasena La contraseña.
     * @return true si el inicio de sesión fue exitoso, false si hubo un error.
     */
    public boolean iniciarSesion(final String usuario, final String contrasena) {

        if (usuario.equals("") && contrasena.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: no ha ingresado las credenciales", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: no ha ingresado el usuario.", "Error - No ha ingresado usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (contrasena.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: no ha ingresado la contraseña.", "Error - No ha ingresado la contraseña", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        final OkHttpClient cliente = new OkHttpClient();

        final MediaType mediaType = MediaType.parse("application/json");
        final String requestBody = "{\"nombreUsuario\": \"" + usuario + "\", \"contrasena\": \"" + contrasena + "\"}";
        final RequestBody resquestBody = RequestBody.create(mediaType, requestBody);
        final Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();

        try (Response response = cliente.newCall(resquest).execute()) {
            final String responseBody = response.body().string();
            // Analizar el JSON de respuesta
            final JSONObject jsonObject = new JSONObject(responseBody);
            // Obtener el valor del campo "tipo"
            final String tipo = jsonObject.getString("tipo");
             // Obtener el valor del campo "nombres
            final String nombre = jsonObject.getString("nombres");
            // Utilizar el valor de "tipo" según sea necesario
            if (tipo.equals("Gerente")) {
                FrmMenu frmMenu = new FrmMenu();
                frmMenu.show();
                return true;
            } else if (tipo.equals("Secretario")) {
                FrmCPeticion frmcPeticion = new FrmCPeticion(nombre);
                frmcPeticion.show();
                return true;
            }
        } catch (IOException e) {
            LOGGER.error("Se produjo una excepción al obtener los clientes", e);
        }
        return false;
    }
    
/**
     * Recupera la contraseña para el usuario especificado.
     *
     * @param usuario El nombre de usuario para el cual se desea recuperar la contraseña.
     * @return true si la recuperación de contraseña fue exitosa, false si hubo un error.
     */
    public boolean recuperarContrasena(final String usuario) {
        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: no ha ingresado el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        final OkHttpClient cliente = new OkHttpClient();
        // Construir la URL con el parámetro usuario
        final String url = "https://backendambulancia.onrender.com/vv/api/v1/recuperarContrasena?correoElectronico=" + usuario;
        final Request resquest = new Request.Builder()
                .url(url)
                .post(RequestBody.create(null, new byte[0]))
                .build();
        try (Response response = cliente.newCall(resquest).execute()) {
            JOptionPane.showMessageDialog(null, "Se ha enviado su contraseña a su correo, por favor espere unos minutos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la solicitud:", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
