import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DMDBExample {
    public static void main(String[] args) {
        String url = "jdbc:dm://192.168.100.40:5236/HUADEAEXP";
        String user = "SYSDBA";
        String password = "Hda@2024...";

        try {
            // 加载JDBC驱动
            Class.forName("dm.jdbc.driver.DmDriver");

            // 建立连接
            Connection conn = DriverManager.getConnection(url, user, password);

            // 创建Statement对象
            Statement stmt = conn.createStatement();

            // 执行查询
            ResultSet rs = stmt.executeQuery("SELECT * FROM HUADEAEXP.user");

            // 处理查询结果
            while (rs.next()) {
                // 假设myTable有一个名为id的列
                System.out.println(rs.getInt("id"));
            }

            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}