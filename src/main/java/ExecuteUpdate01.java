import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","javaogreniyorum");
        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();
        String sql1 = "update companies\n" +
                " set number_of_employees = 16000\n" +
                " where number_of_employees < (select avg(number_of_employees)\n" +
                "                              from companies);";

        int updateEdilenSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi : "+updateEdilenSatirSayisi);

        ResultSet resultSet1 = st.executeQuery("select * from companies");

        while(resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+"--"+resultSet1.getInt(3));
        }
    }
}
