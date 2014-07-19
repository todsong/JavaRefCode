package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String passwd = "sys";
        Connection conn = null;
        PreparedStatement st = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            String sql = "delete from T_TEST where a=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            st.execute();
            conn.commit();
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
                st.close();
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}
