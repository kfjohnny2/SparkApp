package sonnesoft.com.spark.service;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import sonnesoft.com.spark.model.Docente;
import sonnesoft.com.spark.utils.WebserviceImpl;

/**
 * Created by johnnylee on 30/11/17.
 */

public class ServiceDocentes {
    public static List<Docente> getDocentes(Context context){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = WebserviceImpl.get("docentes", context);
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonHits = new JSONArray(jsonObject.getString("hits"));
            return mapper.convertValue(jsonHits, new TypeReference<List<Docente>>() {});
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
