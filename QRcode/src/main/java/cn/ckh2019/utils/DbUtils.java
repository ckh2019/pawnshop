package cn.ckh2019.utils;

import cn.ckh2019.pojo.Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Chen Kaihong
 * 2019-09-26 22:58
 */
public class DbUtils {
    private static Connection conn;
    public static Statement statement;
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

    public static int insert(Code code) throws SQLException {
        String sql = "insert into code(content, time) values('" + code.getContent() + "','" + code.getTime() + "')";
        return statement.executeUpdate(sql);
    }
}
