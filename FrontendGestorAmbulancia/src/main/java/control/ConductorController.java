package control;

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

    public List<ConductorDTO> obtenerConductores() throws IOException {
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
                    if (conductor != null) {
                    conductor.setId(conductorJson.getLong("id"));
                    conductor.setCedula(conductorJson.getString("cedula"));
                    conductor.setNombre(conductorJson.getString("nombre"));
                    conductor.setApellidos(conductorJson.getString("apellidos"));
                    conductor.setCorreo(conductorJson.getString("correo"));
                    conductor.setFechaContrato(conductorJson.getString("fechaContrato"));
                    conductor.setFechaNacimiento(conductorJson.getString("fechaNacimiento"));
                    conductor.setSexo(conductorJson.getString("sexo"));
                    conductor.setEstado(conductorJson.getBoolean("estado"));
                    // Agregar más propiedades según el modelo de Conductor

                    conductores.add(conductor);}
                    else{
                        System.out.println("no se puedo crear objeto ");
                    }
                }
            } else {
                 conductores=null;
                //System.out.println("Error: " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            //System.out.println(e.toString());
        }

        return conductores;
    }

    /**
     * Consulta un conductor por cédula.
     *
     * @param cedula La cédula del conductor a buscar.
     * @return El objeto ConductorDTO correspondiente al conductor encontrado, o
     * null si no se encuentra ningún conductor.
     * @throws IOException Si ocurre un error durante la consulta.
     */
    public ConductorDTO consultabyCedula(String cedula) throws IOException {
        for (ConductorDTO c : this.obtenerConductores()) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Registra un conductor enviando los datos a la API.
     *
     * @param conductor Objeto de tipo Conductor con los datos del conductor a
     * registrar
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

        try ( Response response = client.newCall(request).execute()) {
            // Manejar la respuesta de la API, como obtener el código de estado, el cuerpo de la respuesta, etc.
            // Devolver true si el registro fue exitoso, false en caso contrario
            return response.isSuccessful();
        } catch (IOException e) {
            // Manejar errores de conexión o solicitud HTTP
            //e.printStackTrace();
        }

        return false;
    }

    /**
     * Actualiza los datos de un conductor.
     *
     * @param cdt El objeto ConductorDTO que contiene los datos actualizados del
     * conductor.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
    public boolean actualizarDatos(ConductorDTO cdt) {

        JSONObject json = new JSONObject();
        json.put("cedula", cdt.getCedula());
        json.put("nombre", cdt.getNombre());
        json.put("apellidos", cdt.getApellidos());
        json.put("correo", cdt.getCorreo());
        json.put("fechaNacimiento", cdt.getFechaNacimiento());
        json.put("fechaContrato", cdt.getFechaContrato());
        json.put("sexo", cdt.getSexo());
        json.put("estado", cdt.isEstado());
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString());

        // Supongamos que tienes una variable llamada "idConductor" que contiene el ID del conductor seleccionado
        // Construye la URL con el ID del conductor
        String url = "https://backendambulancia.onrender.com/vv/api/v1/modificarConductor?id=" + cdt.getId();

        // Construye la solicitud HTTP utilizando la URL adecuada y el método HTTP correcto
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody) // o .post(requestBody) si es un método POST
                .build();

        try ( Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // La solicitud fue exitosa

                // Manejar la respuesta según sea necesario
                // Retorna true para indicar que la respuesta fue exitosa
                return true;
            } else {
                // La solicitud no fue exitosa
                // Manejar el error

                // Retorna false para indicar que la respuesta no fue exitosa
                return false;
            }
        } catch (IOException e) {
            // Ocurrió un error de red u otra excepción

            // Retorna false para indicar que ocurrió un error
            return false;
        }
    }

    /**
     * Elimina un conductor según su ID.
     *
     * @param idConductor El ID del conductor a eliminar.
     * @return true si la eliminación fue exitosa, false si ocurrió un error.
     */
    public boolean eliminarConductor(long idConductor) {
        OkHttpClient client = new OkHttpClient();

        // Construye la URL con el ID del conductor
        String url = "https://backendambulancia.onrender.com/vv/api/v1/eliminarConductor?id=" + idConductor;

        // Construye la solicitud HTTP utilizando la URL adecuada y el método DELETE
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try ( Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // La solicitud fue exitosa
                // Manejar la respuesta según sea necesario

                // Retorna true para indicar que la eliminación fue exitosa
                return true;
            } else {
                // La solicitud no fue exitosa
                // Manejar el error

                // Retorna false para indicar que la eliminación no fue exitosa
                return false;
            }
        } catch (IOException e) {
            // Ocurrió un error de red u otra excepción

            // Retorna false para indicar que ocurrió un error
            return false;
        }
    }

}
