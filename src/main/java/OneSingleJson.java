import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.deploy.net.MessageHeader;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Class.forName;

public class OneSingleJson {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "admin");

        //Object os statement class will help us to execute queries
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from customerinfo where Location ='Asia';");

        JSONArray js = new JSONArray();

        ArrayList<CustomerDetails> listCustomerDetails = new ArrayList<CustomerDetails>();
        while (rs.next()) {
            CustomerDetails customerDetails = new CustomerDetails();
            System.out.println(rs.getString("CourseName") + "-(" + rs.getString(2) + ")-" + rs.getInt(3) + "-" + rs.getString(4));

            String teste = rs.getString(1);
            if (rs.getString(1).contains("Appium")) {
                System.out.println("Curso cadastrado :" + rs.getString(1));
            }
            customerDetails.setCourseName(rs.getString("CourseName"));
            customerDetails.setPurchaseDate(rs.getString(2));
            customerDetails.setAmount(rs.getInt(3));
            customerDetails.setLocation(rs.getString(4));

            listCustomerDetails.add(customerDetails);
        }


        for (int i = 0; i < listCustomerDetails.size(); i++) {
            ObjectMapper obj = new ObjectMapper();
            obj.writeValue(new File("C:\\JsonJava\\src\\test\\resources\\infoJson\\customerInfo" + (i + 1) + ".json"), listCustomerDetails.get(i));
            //Create json String Java Object
            Gson gson = new Gson();
            String jsonString = gson.toJson(listCustomerDetails.get(i));
            js.add(jsonString);
        }

        //JsonSimples
        JSONObject json = new JSONObject();
        json.put("data",js);
        System.out.println(json.toJSONString());

        String unescapeString = StringEscapeUtils.unescapeJava(json.toJSONString());
        System.out.println(unescapeString);
        String string1 = unescapeString.replace("\"{","{").replace("}\"","}");
        System.out.println(string1);

        try (FileWriter file = new FileWriter("C:\\JsonJava\\src\\test\\resources\\infoJson\\sigleJson.json")){
            file.write(string1);
            System.out.println();
        }

        conn.close();

    }
}
