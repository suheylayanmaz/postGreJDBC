import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","javaogreniyorum");
        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();
        String sql1 = "select company,number_of_employees\n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "fetch next 1 row only";

        ResultSet resultSet1 = st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"--"+resultSet1.getInt("number_of_employees"));
        }

        // 2. yol Subquery kullanarak

        String sql2 = "select company,number_of_employees\n" +
                "from companies\n" +
                "where number_of_employees =(select max(number_of_employees)\n" +
                "                           from companies \n" +
                "                           where number_of_employees\n" +
                "                           < (select max(number_of_employees)\n" +
                "                           from companies))";

        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"--"+resultSet2.getInt(2));
        }

        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();
    }
}
