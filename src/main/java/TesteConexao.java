import java.sql.Connection;
import java.sql.DriverManager;

public class TesteConexao {

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vollmed_api", "root", "root");
            System.out.println("conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}