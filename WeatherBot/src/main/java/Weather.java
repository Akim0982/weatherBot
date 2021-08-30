import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {


    public static String getWeather (String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message +
                "&units=metric&appid=23ccdb77f38dd78878b82bcc663c3527");

        Scanner sc = new Scanner((InputStream) url.getContent());
        String result = "";
        while (sc.hasNext()){
            result += sc.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));

        }

        return "Город - " + model.getName() + "\n" +
               "Температура - " + model.getTemp() + "C" + "\n" +
               "Влажность - " + model.getHumidity() + "%" + "\n" +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";
    }
}
