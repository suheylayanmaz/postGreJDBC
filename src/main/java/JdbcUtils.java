import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private static Statement statement;
    private static Connection connection;
    public static Connection connectToDataBase(String hostName, String dbName, String username, String password){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","javaogreniyorum");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection!= null){
            System.out.println("Connection Success");
        }else{
            System.out.println("Connection Fail");
        }

        return connection;
    }
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return statement;

    }

    //4. adim : Query calistir.
    public static boolean execute(String sql){
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //ExecuteQuery ve Execute methodlari odev.
    // 5. adim Baglanti ve Statement'i kapat.
    public static void closeConnectionAndStatement(){
        try {
            connection.close();;
            statement.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed!");

            }else {
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Table olusturan method
    public static void createTable(String tableName, String... columnName_dataType){
        StringBuilder columnName_dataValue = new StringBuilder("");
        for(String w : columnName_dataType){
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);
       // columnName_dataValue.deleteCharAt(columnName_dataValue.lastIndexOf(",");

        try{
            statement.execute("create table "+ tableName + "("+columnName_dataValue+")");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }













}
