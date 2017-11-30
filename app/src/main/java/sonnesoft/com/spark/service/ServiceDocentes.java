package sonnesoft.com.spark.service;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import sonnesoft.com.spark.model.Docente;
import sonnesoft.com.spark.model.Hit;
import sonnesoft.com.spark.utils.WebserviceImpl;

/**
 * Created by johnnylee on 30/11/17.
 */

public class ServiceDocentes {
    public static List<Docente> getDocentes(Context context) {
        try {

            ObjectMapper mapper = new ObjectMapper();
                        String result = WebserviceImpl.get("docentes", context);
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonHits = new JSONArray(jsonObject.getString("hits"));
            return mapper.readValue(jsonHits.toString(),
                    TypeFactory.defaultInstance().constructCollectionType(List.class,
                            Docente.class));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
