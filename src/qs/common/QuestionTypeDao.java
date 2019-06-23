package qs.common;

import java.sql.*;
import java.util.ArrayList;

public class QuestionTypeDao extends DAO{
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
}
