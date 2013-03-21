package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBatch
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
            String sql = "insert into t_test values(?,?)";
            st = conn.prepareStatement(sql);
            for(int i=0,count=0;i<10;i++,count++)
            {
                st.setInt(1, i);
                st.setString(2, i+"");
                st.addBatch();
                if(count%5==0)
                {
                    st.executeBatch();
                    conn.commit();
                    st.clearBatch();
                }
            }
            st.executeBatch();
            conn.commit();
            st.clearBatch();
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
