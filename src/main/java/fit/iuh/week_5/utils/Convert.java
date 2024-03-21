package fit.iuh.week_5.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class Convert<T> {


  public String convertObjToJSON(T object) {
    Gson gson = new Gson();
    return gson.toJson(object);
  }

  public T convertJsonToObj(String json, Class<T> clazz) {
    Gson gson = new Gson();
    return gson.fromJson(json, clazz);
  }

  //use:Type listType = new TypeToken<List<Product>>(){}.getType();
  //List<Product> products = converter.convertListObjToJSON(json, listType);

  public List<T> convertListObjToJSON(String json, Type typeOfT) {
    Gson gson = new Gson();
    return gson.fromJson(json, typeOfT);
  }

  public String convertListJSONToObj(List<T> objects) {
    Gson gson = new Gson();
    return gson.toJson(objects);
  }

}
