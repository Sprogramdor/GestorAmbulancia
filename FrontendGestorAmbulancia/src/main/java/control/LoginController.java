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



/**
 *
 * @author MAURO FABRIZIO RAMOS MESIAS

 * Controlador para el inicio de sesión y recuperación de contraseña.
 */
public class LoginController {

    public String url = "https://backendambulancia.onrender.com/vv/api/v1/ingresarAlSistema";
 
    /**
     * Inicia sesión en el sistema con las credenciales proporcionadas.
     *
     * @param usuario    El nombre de usuario.
     * @param contrasena La contraseña.
     * @return true si el inicio de sesión fue exitoso, false si hubo un error.
     */
    public boolean iniciarSesion(String usuario, String contrasena) {

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
        OkHttpClient cliente = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"nombreUsuario\": \"" + usuario + "\", \"contrasena\": \"" + contrasena + "\"}";
        RequestBody resquestBody = RequestBody.create(mediaType, requestBody);

        Request resquest = new Request.Builder()
                .url(url)
                .post(resquestBody)
                .build();

        try (Response response = cliente.newCall(resquest).execute()) {
            String responseBody = response.body().string();
            System.out.println("Respuesta del servidor: " + responseBody);

            // Analizar el JSON de respuesta
            JSONObject jsonObject = new JSONObject(responseBody);

            // Obtener el valor del campo "tipo"
            String tipo = jsonObject.getString("tipo");

            // Utilizar el valor de "tipo" según sea necesario
            if (tipo.equals("Gerente")) {
                FrmMenu frmMenu = new FrmMenu();
                frmMenu.show();
                return true;
            } else if (tipo.equals("Secretario")) {
                FrmCPeticion frmcPeticion = new FrmCPeticion();
                frmcPeticion.show();
                return true;
            }
            System.out.println("Tipo: " + tipo);
        } catch (IOException e) {
            System.out.println("Error al realizar la solicitud: " + e.getMessage());
        }

        return false;
    }
    
/**
     * Recupera la contraseña para el usuario especificado.
     *
     * @param usuario El nombre de usuario para el cual se desea recuperar la contraseña.
     * @return true si la recuperación de contraseña fue exitosa, false si hubo un error.
     */
    public boolean recuperarContrasena(String usuario) {
        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Error: no ha ingresado el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        OkHttpClient cliente = new OkHttpClient();

        // Construir la URL con el parámetro usuario
        String url = "https://backendambulancia.onrender.com/vv/api/v1/recuperarContrasena?correoElectronico=" + usuario;

        Request resquest = new Request.Builder()
                .url(url)
                .post(RequestBody.create(null, new byte[0]))
                .build();
        try (Response response = cliente.newCall(resquest).execute()) {
            String responseBody = response.body().string();
            JOptionPane.showMessageDialog(null, "Se ha enviado su contraseña a su correo, por favor espere unos minutos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la solicitud:", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
