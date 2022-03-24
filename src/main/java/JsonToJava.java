import java.sql.*;

import static java.lang.Class.*;

public class JsonToJava {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","admin");

        //Object os statement class will help us to execute queries
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select * from customerinfo where Location ='Asia';");

        while (rs.next()){
            System.out.println(rs.getString("CourseName") +"-("+ rs.getString(2) +")-"+ rs.getInt(3) +"-"+ rs.getString(4));
        }

        conn.close();

    }
}
