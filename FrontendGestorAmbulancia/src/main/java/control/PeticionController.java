
package control;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.dto.AmbulanciaDTO;
import model.dto.ClienteDTO;
import model.dto.ConductorDTO;
import model.dto.HospitalDTO;
import model.dto.PeticionDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class PeticionController {
    private static final String API_REGISTRAR_PETICION = "https://backendambulancia.onrender.com/vv/api/v1/registrarPeticion";
    private static final String API_LISTAR_CONDUCTORES = "https://backendambulancia.onrender.com/vv/api/v1/listarConductores";
    private static final String AMBULANCIA_API_URL = "https://backendambulancia.onrender.com/vv/api/v1/listarAmbulancias";
    private static final String API_URL = "https://backendambulancia.onrender.com/vv/api/v1/listarPeticiones";
    
    /**
     * Obtiene la lista de ambulancias.
     *
     * @return Lista de objetos AmbulanciaDTO.
     * @throws Exception Si ocurre un error al obtener las ambulancias.
     */
    public List<AmbulanciaDTO> obtenerAmbulancias() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(AMBULANCIA_API_URL)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String jsonResponse = response.body().string();

            Gson gson = new Gson();
            List<AmbulanciaDTO> ambulancias = gson.fromJson(jsonResponse, new TypeToken<List<AmbulanciaDTO>>(){}.getType());

            return ambulancias;
        } else {
            throw new Exception("Error al obtener las ambulancias. Código de respuesta: " + response.code());
        }
    }
    
/**
 * Obtiene la lista de placas de ambulancias disponibles.
 *
 * @return Lista de placas de ambulancias.
 * @throws Exception Si ocurre un error al obtener las ambulancias.
 */
    public List<String> listarAmbulancias() throws Exception {
        List<AmbulanciaDTO> ambulancias = obtenerAmbulancias();
        List<String> placas = new ArrayList<>();

        for (AmbulanciaDTO ambulancia : ambulancias) {
            placas.add(ambulancia.getPlaca());
        }

        return placas;
    }
    
    
    /**
    * Obtiene una lista de peticiones mediante una llamada a la API.
    *
    * @return la lista de peticiones obtenida desde la API
    * @throws Exception si ocurre un error al obtener las peticiones
    */
    public List<PeticionDTO> obtenerPeticiones() throws Exception {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url(API_URL)
            .build();

    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            throw new Exception("Error al obtener las peticiones: " + response.code());
        }

        String responseBody = response.body().string();
        JSONArray jsonPeticiones = new JSONArray(responseBody);

        List<PeticionDTO> peticiones = new ArrayList<>();

        for (int i = 0; i < jsonPeticiones.length(); i++) {
            JSONObject jsonPeticion = jsonPeticiones.getJSONObject(i);

            Long id = jsonPeticion.getLong("id");
            String puntoOrigen = jsonPeticion.getString("puntoOrigen");
            String puntoDestino = jsonPeticion.getString("puntoDestino");
            boolean estado = jsonPeticion.getBoolean("estado");

            JSONObject jsonHospital = jsonPeticion.getJSONObject("hospital");
            HospitalDTO hospital = new HospitalDTO();
            hospital.setId(jsonHospital.getLong("id"));
            hospital.setNombre(jsonHospital.getString("nombre"));
            hospital.setEstado(jsonHospital.getBoolean("estado"));

            JSONObject jsonCliente = jsonPeticion.getJSONObject("cliente");
            ClienteDTO cliente = new ClienteDTO();
            cliente.setId(jsonCliente.getLong("id"));
            cliente.setCedula(jsonCliente.getString("cedula"));
            cliente.setNombres(jsonCliente.getString("nombres"));
            cliente.setApellidos(jsonCliente.getString("apellidos"));
            cliente.setCorreo(jsonCliente.optString("correo"));  // Use optString to handle possible null value
            cliente.setSexo(jsonCliente.getString("sexo"));
            cliente.setFechaNacimiento(jsonCliente.getString("fechaNacimiento"));

            JSONArray jsonNumeroTelefonico = jsonCliente.optJSONArray("numeroTelefonico");  // Use optJSONArray to handle possible null value
            List<String> numeroTelefonico = new ArrayList<>();
            if (jsonNumeroTelefonico != null) {
                for (int j = 0; j < jsonNumeroTelefonico.length(); j++) {
                    numeroTelefonico.add(jsonNumeroTelefonico.getString(j));
                }
            }
            cliente.setNumeroTelefonico(numeroTelefonico);

            JSONObject jsonAmbulancia = jsonPeticion.getJSONObject("ambulancia");
            AmbulanciaDTO ambulancia = new AmbulanciaDTO(
                    jsonAmbulancia.getString("numeroPlaca"),
                    jsonAmbulancia.getString("modelo"),
                    jsonAmbulancia.getString("tipo"),
                    jsonAmbulancia.getBoolean("estado"),
                    jsonAmbulancia.getString("observaciones")
            );

            JSONObject jsonConductor = jsonPeticion.optJSONObject("conductor");  // Use optJSONObject to handle possible null value
            ConductorDTO conductor = null;
            if (jsonConductor != null) {
                conductor = new ConductorDTO(
                        jsonConductor.getLong("id"),
                        jsonConductor.getString("cedula"),
                        jsonConductor.getString("nombre"),
                        jsonConductor.getString("apellidos"),
                        jsonConductor.optString("correo"),  // Use optString to handle possible null value
                        jsonConductor.optString("fechaNacimiento"),
                        jsonConductor.optString("fechaContrato"),
                        jsonConductor.optString("sexo"),
                        jsonConductor.getBoolean("estado")
                );
            }

            PeticionDTO peticion = new PeticionDTO(puntoOrigen, puntoDestino, hospital, cliente, ambulancia, conductor, estado);
            peticiones.add(peticion);
        }

        return peticiones;
    }
}

/**
     * Guarda una petición utilizando la API.
     *
     * @param peticion La petición a guardar.
     * @return true si la petición se guardó correctamente, false de lo contrario.
     */
    public static boolean guardarPeticion(PeticionDTO peticion) {
        try {
            
            // Convertir la petición a formato JSON utilizando Gson
        Gson gson = new GsonBuilder().create();
          // Convertir la petición a formato JSON
        String jsonBody = gson.toJson(peticion);
            
          

            // Configurar el cliente de OkHttp
            OkHttpClient client = new OkHttpClient();

            // Crear la solicitud POST
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            Request request = new Request.Builder()
                    .url(API_REGISTRAR_PETICION)
                    .post(requestBody)
                    .build();

            // Ejecutar la solicitud y obtener la respuesta
            try (Response response = client.newCall(request).execute()) {
                // Comprobar el código de respuesta para determinar si se guardó correctamente
                return response.isSuccessful();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}


