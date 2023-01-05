import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        // 1. adim: Driver'a kaydol
        // 2. adim Database'e baglan
        JdbcUtils.connectToDataBase("localhost","postgres", "postgres", "javaogreniyorum");

        //3. adim : Statement olustur.

        Statement statement = JdbcUtils.createStatement();

        // 4. adim : Query'i calistir.

        //JdbcUtils.execute("create table students(name varchar(20), id int, adres varchar(80))");

        // 5. adim : Baglanti ve Statement'i kapat.
        JdbcUtils.createTable("school", "teacher_name varchar(20)", "id int", "adres varchar(50)");
        JdbcUtils.closeConnectionAndStatement();


    }
}
