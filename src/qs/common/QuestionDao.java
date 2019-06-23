package qs.common;
import java.sql.*;
import java.util.ArrayList;
public class QuestionDao extends DAO{

    public synchronized Long nextId() throws SQLException{
        String sql = "select max(id) from Question";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }

    public synchronized Long createQuestion(Long userId) throws SQLException{
        String sql = "insert into Question(id,userId) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setLong(2,userId);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public Boolean delete(Long id) throws SQLException{
        String sql = "delete from Question where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        return ptmt.executeUpdate() > 0;
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update Question set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

    public Question selectById(Long id) throws SQLException{
        String sql = "select * from Question where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        ResultSet rs = ptmt.executeQuery();
        Question question = null;
        while(rs.next()){
            question = new Question();
            question.setId(rs.getLong("id"));
            question.setUserId(rs.getLong("userId"));
            question.setType(rs.getString("type"));
            question.setContent(rs.getString("content"));
            question.setTime(rs.getTimestamp("time"));
            question.setLabel(rs.getString("label"));
            question.setFrequency(rs.getLong("frequency"));
            question.setTitle(rs.getString("title"));
        }
        return question;
    }

    public ArrayList<Question> selectByUserId(Long userId) throws SQLException{
        ArrayList<Question> questions = new ArrayList<Question>();
        String sql = "select * from Question where  userId = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,userId);
        ResultSet rs = ptmt.executeQuery();
        Question question = null;
        while(rs.next()){
            question = new Question();
            question.setId(rs.getLong("id"));
            question.setUserId(rs.getLong("userId"));
            question.setType(rs.getString("type"));
            question.setContent(rs.getString("content"));
            question.setTime(rs.getTimestamp("time"));
            question.setLabel(rs.getString("label"));
            questions.add(question);
        }
        return questions;
    }
}
