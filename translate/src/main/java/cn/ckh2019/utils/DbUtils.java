package cn.ckh2019.utils;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chen Kaihong
 * 2019-09-26 22:58
 */
public class DbUtils {
    private static Connection conn;
    public static Statement statement;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String URL = "jdbc:mysql://129.211.17.8:3306/QRCode?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,"root", "123456");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String from, String to, String src, String dst) throws SQLException {
        String sql = "insert into translate(froms, tos, src, dst, time) values(?, ?, ?, ?, ?)";
        PreparedStatement updateSales = conn.prepareStatement(sql);
        updateSales.setString(1, from);
        updateSales.setString(2, to);
        updateSales.setString(3, src);
        updateSales.setString(4, dst);
        updateSales.setString(5, format.format(new Date()));
        updateSales.executeUpdate();
    }
}
