import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExtractJson {

    public static void main(String[] args) throws IOException {

        consultaJson();
        consultaJsonList();
        consultaJsonListParam();
    }

    public static void consultaJson() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        CustomerDetailsAppium customerDetailsAppium = obj.readValue(new File("C:\\JsonJava\\src\\test\\resources\\infoJson\\customerInfo1.json"), CustomerDetailsAppium.class);

        System.out.println(customerDetailsAppium.getCourseName());
    }

    public static void consultaJsonList() throws IOException {
        JSONParser parser = new JSONParser();
        try {
        Object obj = parser.parse(new FileReader("C:\\JsonJava\\src\\test\\resources\\infoJson\\sigleJson.json"));

        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject jsonObject = (JSONObject) obj;

        // A JSON array. JSONObject supports java.util.List interface.
        JSONArray companyList = (JSONArray) jsonObject.get("data");

        // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
        // Iterators differ from enumerations in two ways:
        // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
        // 2. Method names have been improved.
        Iterator<JSONObject> iterator = companyList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONString());

        }
    } catch (Exception e) {
        e.printStackTrace();
    }


    }

    public static void consultaJsonListParam() throws IOException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\JsonJava\\src\\test\\resources\\infoJson\\sigleJson.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("data");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().get("courseName"));
                //System.out.println(iterator.next().toString().contains("Appium"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
