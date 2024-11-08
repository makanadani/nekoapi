/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.service;

import br.com.nekoapi.model.vo.CatVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CatService {

    private static final String API_URL = "https://api.thecatapi.com/v1/breeds?attach_breed=0&page=0&limit=5";
    private static final String API_KEY = "live_iQRJjxtV33z5EbifGlGBcYMM6RJcyXZaH8txS1wonynQCwEXuRYj3XIlD1PCbohf";

    public CatService() {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("API Key não configurada! Defina a variável de ambiente CAT_API_KEY.");
        }
    }

    public static CatVO[] searchCatBreed() throws IOException {
        
    	CatVO[] catModels = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_URL);
            request.addHeader("x-api-key", API_KEY);

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity);
                        JsonArray jsonArray = JsonParser.parseString(result).getAsJsonArray();

                        catModels = new CatVO[jsonArray.size()];
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject jsonCat = jsonArray.get(i).getAsJsonObject();
                            String id = jsonCat.get("id").getAsString();
                            String name = jsonCat.get("name").getAsString();
                            String description = jsonCat.has("description") ? jsonCat.get("description").getAsString() : "";
                            String temperament = jsonCat.has("temperament") ? jsonCat.get("temperament").getAsString() : "";
                            String origin = jsonCat.has("origin") ? jsonCat.get("origin").getAsString() : "";
                            String lifeSpan = jsonCat.has("life_span") ? jsonCat.get("life_span").getAsString() : "";
                            String imageUrl = jsonCat.has("image") ? jsonCat.get("image").getAsJsonObject().get("url").getAsString() : null;

                            CatVO catModel = new CatVO(id, name, description, temperament, origin, lifeSpan, imageUrl);
                            catModels[i] = catModel;
                        }
                    }
                } else {
                    System.err.println("Falha ao buscar raças de gatos: HTTP status " + statusCode);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar raças de gatos: " + e.getMessage());
            throw e;
        }

        return catModels;
    }
}