package c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ComboPoolWithXML
{
    public static void main(String[] args)
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            conn = cpds.getConnection();
            conn.setAutoCommit(false);
            String sql = "select a,b from T_Test where b=?";
            st = conn.prepareStatement(sql);
            st.setDouble(1, 1.2);
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
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
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
