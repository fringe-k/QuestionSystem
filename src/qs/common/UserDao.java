package qs.common;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
public class UserDao extends DAO{


    public synchronized Long nextId() throws SQLException{
        String sql = "select max(id) from User";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }
    public synchronized Long createUser(String mail) throws SQLException{
        String sql = "insert into User(id,mail) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setString(2,mail);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public Boolean delete(Long id) throws SQLException{
        String sql = "delete from User where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        return ptmt.executeUpdate() > 0;
    }


    public  User selectById(Long id) throws SQLException{
        String sql = "select * from User where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        ResultSet rs = ptmt.executeQuery();
        User user = null;
        while(rs.next()){
            user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setMail(rs.getString("mail"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getLong("role"));
            user.setScore(rs.getLong("score"));
            user.setPhoto(rs.getBytes("photo"));
            user.setNumOfQuery(rs.getLong("numOfQuery"));
            user.setNumOfAnswer(rs.getLong("numOfAnswer"));
            user.setCannotSpeak(rs.getLong("cannotSpeak"));
            user.setCannotLogin(rs.getLong("cannotLogin"));
            user.setAge(rs.getLong("age"));
        }
        return user;
    }

    public  User selectByMail(String mail) throws Exception{
        String sql = "select * from User where mail = ?;";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,mail);
        User user = null;
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setMail(rs.getString("mail"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getLong("role"));
            user.setScore(rs.getLong("score"));
            user.setPhoto(rs.getBytes("photo"));
            user.setNumOfQuery(rs.getLong("numOfQuery"));
            user.setNumOfAnswer(rs.getLong("numOfAnswer"));
            user.setCannotSpeak(rs.getLong("cannotSpeak"));
            user.setCannotLogin(rs.getLong("cannotLogin"));
            user.setAge(rs.getLong("age"));
        }
        return user;
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update User set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }


    public Boolean updateName(Long id,String name) throws SQLException{
        String sql = "update User set name = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,name);
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

    public Boolean updatePhone(Long id,String phone) throws SQLException{
        String sql = "update User set phone = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,phone);
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

    public Boolean updateMail(Long id,String mail) throws SQLException{
        String sql = "update User set mail = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,mail);
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

}
