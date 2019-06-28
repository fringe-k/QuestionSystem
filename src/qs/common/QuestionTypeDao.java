package qs.common;

import java.sql.*;
import java.util.ArrayList;

public class QuestionTypeDao extends DAO{

    public QuestionTypeDao(){
        super();
    }
    public synchronized Long nextId() throws SQLException {
        String sql = "select max(id) from QuestionType";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }

    public synchronized Long createQuestionType(String name) throws SQLException{
        String sql = "insert into QuestionType(id,name) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setString(2,name);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public Boolean delete(Long id) throws SQLException{
        String sql = "delete from QuestionType where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        return ptmt.executeUpdate() > 0;
    }

    public QuestionType selectById(Long id) throws SQLException{
        String sql = "select * from QuestionType where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        ResultSet rs = ptmt.executeQuery();
        QuestionType questionType = null;
        while(rs.next()){
            questionType = new QuestionType();
            questionType.setId(rs.getLong("id"));
            questionType.setName(rs.getString("name"));
        }
        return questionType;
    }

    public QuestionType selectByName(String name) throws SQLException{
        String sql = "select * from QuestionType where name = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, name);
        ResultSet rs = ptmt.executeQuery();
        QuestionType questionType = null;
        while(rs.next()){
            questionType = new QuestionType();
            questionType.setId(rs.getLong("id"));
            questionType.setName(rs.getString("name"));
        }
        return questionType;
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update QuestionType set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }
}
