package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String passwd = "sys";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            String sql = "select a,b from T_Test where b=?";
            st = conn.prepareStatement(sql);
            st.setString(1, "1");
            rs = st.executeQuery();
            if (rs != null)
            {
                while (rs.next())
                {
                    int a = rs.getInt("a");
                    String b = rs.getString("b");
                    System.out.println(a+","+b);
                }
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
