/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import model.dto.AmbulanciaDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Controlador para la funcionalidad de AmbulanciaDTO.
 *
 * @author Nelson Angel Zamora Zhingri
 */
public class AmbulanciaController {
    private String API_URL = "https://backendambulancia.onrender.com/vv/api/v1/registrarAmbulancia";
    private String API2_URL = "https://backendambulancia.onrender.com/vv/api/v1/listarAmbulancias";
    private JTable tbAmbulancia;
    JTextField tfPlaca = new JTextField();
    JTextField tfModelo = new JTextField();
    JComboBox<String> cbTipo = new JComboBox<>();
    JComboBox<String> cbEstado = new JComboBox<>();
    JTextArea taObservaciones = new JTextArea();
    
    /**
     * Registra una nueva ambulancia.
     * 
     * @param ambulancia Objeto AmbulanciaDTO a registrar.
     */
    public void registrarAmbulancia(AmbulanciaDTO ambulancia) {
    try {
        // Crear la conexión HTTP
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Crear el objeto JSON para la ambulancia
        JSONObject jsonAmbulancia = new JSONObject();
        jsonAmbulancia.put("numeroPlaca", ambulancia.getPlaca());
        jsonAmbulancia.put("modelo", ambulancia.getModelo());
        jsonAmbulancia.put("tipo", ambulancia.getTipo());
        jsonAmbulancia.put("estado", ambulancia.getEstado());
        
        // Obtener las observaciones y manejar el valor nulo
        String observaciones = ambulancia.getObservacion();
        if (observaciones == null) {
            observaciones = " "; // Cambiar observaciones nulas a una cadena vacía
        }
        jsonAmbulancia.put("observaciones", observaciones);

        // Enviar la solicitud POST con el objeto JSON
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(jsonAmbulancia.toString().getBytes());
            outputStream.flush();
        }

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            JOptionPane.showMessageDialog(null, "Ambulancia registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la ambulancia. Código de respuesta: " + responseCode);
        }

        // Cerrar la conexión
        connection.disconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
    }
}


    
    
    /**
     * Consulta las ambulancias por número de placa y actualiza la tabla con los resultados.
     *
     * @param numeroPlaca Número de placa de la ambulancia a consultar.
     * @return Lista de ambulancias encontradas que coinciden con el número de placa especificado.
     */
    public List<AmbulanciaDTO> consultarAmbulanciaPorPlaca(String numeroPlaca) {
    List<AmbulanciaDTO> ambulancias = new ArrayList<>();
    try {
        // Crear la conexión HTTP con la URL de consulta por placa
        URL url = new URL(API2_URL + "?placa=" + numeroPlaca);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try ( // Leer la respuesta JSON
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Parsear la respuesta JSON y crear objetos AmbulanciaDTO
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonAmbulancia = jsonArray.getJSONObject(i);
                String placa = jsonAmbulancia.optString("numeroPlaca", "");
                if (!placa.equals(numeroPlaca)) {
                    continue; // Saltar a la siguiente iteración si la placa no coincide
                }
                String modelo = jsonAmbulancia.optString("modelo", "");
                String tipo = jsonAmbulancia.optString("tipo", "");
                boolean estado = jsonAmbulancia.optBoolean("estado", false);
                String observaciones = jsonAmbulancia.optString("observaciones", "");

                AmbulanciaDTO ambulancia = new AmbulanciaDTO(placa, modelo, tipo, estado, observaciones);
                ambulancias.add(ambulancia);
            }

            // Actualizar la tabla con los resultados
            mostrarAmbulanciasEnTabla(ambulancias);
        } else {
            JOptionPane.showMessageDialog(null, "Error al consultar la ambulancia. Código de respuesta: " + responseCode);
        }

        // Cerrar la conexión
        connection.disconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
    }

    return ambulancias;
}

    
    /**
     * Muestra la lista de ambulancias en la tabla.
     * 
     * @param ambulancias Lista de ambulancias a mostrar.
     */
    public void mostrarAmbulanciasEnTabla(List<AmbulanciaDTO> ambulancias) {
    DefaultTableModel tableModel = (DefaultTableModel) tbAmbulancia.getModel();
    tableModel.setRowCount(0); // Limpiar filas existentes

    for (AmbulanciaDTO ambulancia : ambulancias) {
        String estado = ambulancia.getEstado() ? "Disponible" : "No disponible"; // Cambiar estado booleano a cadena

        Object[] row = {
            ambulancia.getPlaca(),
            ambulancia.getModelo(),
            ambulancia.getTipo(),
            estado, // Mostrar estado como "Disponible" o "No disponible"
            ambulancia.getObservacion()
        };
        tableModel.addRow(row);
    }

    tbAmbulancia.setModel(tableModel); // Actualizar la tabla con los nuevos datos
}


    /**
     * Establece la referencia de la tabla de ambulancias.
     * 
     * @param tbAmbulancia Tabla de ambulancias.
     */
    public void setTablaAmbulancia(JTable tbAmbulancia) {
        this.tbAmbulancia = tbAmbulancia;
    }
 
    /**
    * Consulta las ambulancias por número de placa y actualiza el formulario con los datos encontrados.
    *
    * @param numeroPlaca Número de placa de la ambulancia a consultar.
    * @return El objeto AmbulanciaDTO con los datos de la ambulancia encontrada. Si no se encuentra ninguna ambulancia con la placa especificada, devuelve null.
    * @throws IOException Si ocurre un error de comunicación con la API.
    */
    public AmbulanciaDTO buscarPorPlaca(String numeroPlaca) {
    try {
        // Crear la conexión HTTP
        URL url = new URL(API2_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Convertir la respuesta JSON en objetos AmbulanciaDTO
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String placa = jsonObject.optString("numeroPlaca");
                if (placa.equalsIgnoreCase(numeroPlaca)) {
                    String modelo = jsonObject.optString("modelo");
                    String tipo = jsonObject.optString("tipo");
                    boolean estado = jsonObject.optBoolean("estado");
                    String observaciones = jsonObject.optString("observaciones");
                    int id = jsonObject.optInt("id"); // Obtener el ID de la ambulancia

                    // Crear un objeto AmbulanciaDTO con los datos encontrados
                    AmbulanciaDTO ambulancia = new AmbulanciaDTO(placa, modelo, tipo, estado, observaciones);

                    // Setear los datos encontrados en el formulario
                    tfPlaca.setText(placa);
                    tfModelo.setText(modelo);
                    cbTipo.setSelectedItem(tipo);
                    cbEstado.setSelectedItem(estado ? "Disponible" : "No disponible");
                    taObservaciones.setText(observaciones);

                    // Actualizar los datos de la ambulancia en la API
                    boolean actualizado = actualizarDatos(id, numeroPlaca, estado, tipo, modelo, observaciones);

                    if (actualizado) {
                        // Si se actualizan los datos correctamente, mostrar un mensaje de éxito
                        //JOptionPane.showMessageDialog(this, "Los datos de la ambulancia han sido actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        // Limpiar los campos del formulario
                        tfPlaca.setText("");
                        cbEstado.setSelectedIndex(0);
                        cbTipo.setSelectedIndex(0);
                        tfModelo.setText("");
                        taObservaciones.setText("");
                    } else {
                        // Si no se pueden actualizar los datos, mostrar un mensaje de error
                        //JOptionPane.showMessageDialog(this, "No se pudo actualizar los datos de la ambulancia.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Devolver la ambulancia encontrada
                    return ambulancia;
                }
            }

            // Mostrar un mensaje si no se encontró ninguna ambulancia con la placa especificada
            JOptionPane.showMessageDialog(null, "No se encontró ninguna ambulancia con la placa especificada.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al consultar las ambulancias. Código de respuesta: " + responseCode);
        }

        // Cerrar la conexión
        connection.disconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
    }

    // Si no se encontró ninguna ambulancia, devolver null o lanzar una excepción según lo que sea apropiado en tu caso.
    return null;
}


    

    /**
     * Actualiza los datos de la ambulancia en la API.
     *
     * @param id             El ID de la ambulancia que se va a actualizar.
     * @param numeroPlaca    El número de placa de la ambulancia.
     * @param estado         El estado de la ambulancia (true si está activa, false si no lo está).
     * @param tipo           El tipo de la ambulancia.
     * @param modelo         El modelo de la ambulancia.
     * @param observaciones  Las observaciones de la ambulancia.
     * @return true si los datos se actualizaron correctamente, false en caso contrario.
     */
    public boolean actualizarDatos(int id, String numeroPlaca, boolean estado, String tipo, String modelo, String observaciones) {
    try {
        String url = "https://backendambulancia.onrender.com/vv/api/v1/modificarAmbulancia?id=" + id; // URL de la API

        // Crear la conexión HTTP
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Convertir los datos de la ambulancia a formato JSON
        JSONObject jsonAmbulancia = new JSONObject();
        jsonAmbulancia.put("numeroPlaca", numeroPlaca);
        jsonAmbulancia.put("estado", estado);
        jsonAmbulancia.put("modelo", modelo);
        jsonAmbulancia.put("tipo", tipo);
        jsonAmbulancia.put("observaciones", observaciones);

        // Enviar la solicitud PUT con los datos de la ambulancia en formato JSON
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonAmbulancia.toString().getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Cerrar la conexión
            connection.disconnect();
            return true;
        } else {
            // Cerrar la conexión
            connection.disconnect();
            return false;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
        return true;
    }
}

    /**
    * Obtiene el ID de una ambulancia dado su número de placa.
    *
    * @param numeroPlaca El número de placa de la ambulancia.
    * @return El ID de la ambulancia correspondiente al número de placa especificado. Si no se encuentra ninguna ambulancia con la placa especificada, devuelve -1.
    * @throws IOException Si ocurre un error de comunicación con la API.
    */ 
    public int obtenerIdAmbulancia(String numeroPlaca) {
    try {
        // Crear la conexión HTTP con la URL de la API
        URL url = new URL(API2_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Convertir la respuesta JSON en objetos AmbulanciaDTO
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String placa = jsonObject.optString("numeroPlaca");
                if (placa.equalsIgnoreCase(numeroPlaca)) {
                    // Obtener el ID de la ambulancia
                    int id = jsonObject.optInt("id");
                    return id;
                }
            }

            // Mostrar un mensaje si no se encontró ninguna ambulancia con la placa especificada
            JOptionPane.showMessageDialog(null, "No se encontró ninguna ambulancia con la placa especificada.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al consultar las ambulancias. Código de respuesta: " + responseCode);
        }

        // Cerrar la conexión
        connection.disconnect();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
    }

    // Si no se encontró ninguna ambulancia con la placa especificada, devolver -1 o lanzar una excepción según lo que sea apropiado en tu caso.
    return -1;
}
/**
 * Elimina una ambulancia por su ID.
 *
 * @param id ID de la ambulancia a eliminar.
 * @return true si la ambulancia se eliminó correctamente, false en caso contrario.
 */
public boolean eliminarAmbulancia(int id) {
    try {
        // Crear la conexión HTTP con la URL de la API
        URL url = new URL("https://backendambulancia.onrender.com/vv/api/v1/eliminarAmbulancia?id=" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        // Obtener la respuesta de la API
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            JOptionPane.showMessageDialog(null, "Ambulancia eliminada correctamente.");
            return true;
        } else {
            //JOptionPane.showMessageDialog(null, "Error al eliminar la ambulancia. Código de respuesta: " + responseCode);
            return true;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al comunicarse con la API: " + e.getMessage());
        return false;
    }
}

}


