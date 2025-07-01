import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnlaceAPI {


    public static double convertirUSD(double cantidadUSD, String monedaDestino) throws IOException, InterruptedException {
        String apiKey = "15cd922a1ea459bc861f8267";
        String baseCurrency = "USD";
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, baseCurrency);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        String json = response.body();
        Gson gson = new Gson();
        JsonObject root = gson.fromJson(json, JsonObject.class);
        JsonObject rates = root.getAsJsonObject("conversion_rates");


        if (!rates.has(monedaDestino)) {
            System.err.println("⚠️ Moneda no reconocida por la API: " + monedaDestino);
            return -1;
        }

        double tasa = rates.get(monedaDestino).getAsDouble();
        return cantidadUSD * tasa;
    }
}
