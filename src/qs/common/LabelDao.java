package qs.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelDao extends DAO {

    public LabelDao(){
        super();
    }

    public synchronized Long nextId() throws SQLException {
        String sql = "select max(id) from Label";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }

    public synchronized Long createLabel(String name) throws SQLException{
        String sql = "insert into Label(id, name) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setString(2,name);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update Label set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }
}
