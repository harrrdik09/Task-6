import java.sql.*;

public class Task6 {
    private static final String url = "jdbc:mysql://localhost:3306/intership1";
    private static final String userName = "root";
    private static final String password = "meneger";

   static Connection connection;
   static PreparedStatement ps;
  // static  Statement st;
   static  ResultSet rs;


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
             connection = DriverManager.getConnection(url, userName, password);
           // Statement statement = connection.createStatement();
            System.out.println("Connection Established");

            String qry= "insert into users (id,name,email) values (?,?,?)";

             ps = connection.prepareStatement(qry);

            ps.setInt(1,101);
            ps.setString(2,"Hardik Prajapati");
            ps.setString(3,"hardikprajapati8097@gmail.com");

            ps.setInt(1,102);
            ps.setString(2,"Deepak Paramar");
            ps.setString(3,"deepak978@gmail.com");


            int x =ps.executeUpdate();
            if (x>0)
            {
                System.out.println("Data Add to Database");
            }
            else {
                System.out.println("not add to db");
            }

            try {
                String qry1 = "select * from users";

                ps = connection.prepareStatement(qry1);

                rs =ps.executeQuery();


                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");

                    System.out.println("Id  | " + id);
                    System.out.println("Name  |" + name);
                    System.out.println("Email  |" + email);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (rs != null)
                {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
