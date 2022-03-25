import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Class.*;

public class JsonToJava {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","admin");

        //Object os statement class will help us to execute queries
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from customerinfo where Location ='Asia';");

        ArrayList<CustomerDetails> listCustomerDetails = new ArrayList<CustomerDetails>();
        while (rs.next()){
            CustomerDetails customerDetails = new CustomerDetails();
            System.out.println(rs.getString("CourseName") +"-("+ rs.getString(2) +")-"+ rs.getInt(3) +"-"+ rs.getString(4));
            customerDetails.setCourseName(rs.getString("CourseName"));
            customerDetails.setPurchaseDate(rs.getString(2));
            customerDetails.setAmount(rs.getInt(3));
            customerDetails.setLocation(rs.getString(4));

            listCustomerDetails.add(customerDetails);

        }

        for(int i=0; i< listCustomerDetails.size();i++){
            ObjectMapper obj = new ObjectMapper();
            obj.writeValue(new File("C:\\JsonJava\\src\\test\\resources\\infoJson\\customerInfo"+(i+1)+".json"),listCustomerDetails.get(i));
        }

        conn.close();

    }
}
