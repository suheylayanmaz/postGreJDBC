import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","javaogreniyorum");
        Statement st = con.createStatement();
        /*
        Java'da method'lar return type sahibi olsa da olmasa da method olarak adlandırılır.
        SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandırilir
        */
        // CallableStatement ile function cagirmayi parametrelendirecegiz.
            // 1. adim : Function kodunu yaz.
        String sql1 = "create or replace function toplamaF(x numeric,y numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return x + y; \n" +
                "\n" +
                "end\n" +
                "$$";
        // 2. adim : Function'i calistir.
        st.execute(sql1);
        // 3. adim : Function'i cagir.
        CallableStatement cst1 = con.prepareCall("{?=call toplamaF(?, ?)}");

        // 4. adim : Return icin registerOutParametre(), parametreler icin set methodlarini kullanacagiz.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        // 5. adim : Execute method'u ile CallableStatement'i calistir.
        cst1.execute();
        // 6. adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));
        System.out.println();


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        // 1. adim : Function kodunu yaz.
        String sql2 = "create or replace function konininHacmiF(r numeric,h numeric)\n" +
                "returns numeric\n" +
                "language plpgsql\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return (3.14*r*r*h/3); \n" +
                "\n" +
                "end\n" +
                "$$";
        // 2. adim : Function'i calistir.
        st.execute(sql2);
        // 3. adim : Function'i cagir.
        CallableStatement cst2 = con.prepareCall("{?=call konininHacmiF(?, ?)}");
        // 4. adim : Return icin registerOutParametre(), parametreler icin set methodlarini kullanacagiz.
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2, 1);
        cst2.setInt(3, 6);

        // 5. adim : Execute method'u ile CallableStatement'i calistir.
        cst2.execute();
        // 6. adim : Sonucu cagirmak icin return data type tipine gore
        System.out.printf("%.2",cst2.getBigDecimal(1));


    }
}
