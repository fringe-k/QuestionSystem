package qs.common;

import java.sql.*;
import java.util.ArrayList;
public class AnswerDao extends DAO{

    public AnswerDao(){
        super();
    }

    public synchronized Long nextId() throws SQLException{
        String sql = "select max(id) from Answer";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) {
            return rs.getLong(1) + 1;
        }else throw new SQLException();
    }

    public synchronized Long createAnswer(Long questionId,Long userId) throws SQLException{
        String sql = "insert into Answer(id,questionId,userId) values (?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        Long id = nextId();
        ptmt.setLong(1,id);
        ptmt.setLong(2,questionId);
        ptmt.setLong(3,userId);
        if(ptmt.executeUpdate() > 0)
            return id;
        else throw new SQLException();
    }

    public Boolean delete(Long id) throws SQLException{
        String sql = "delete from Answer where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        return ptmt.executeUpdate() > 0;
    }
    public Answer selectById(Long id) throws SQLException{
        String sql = "select * from Answer where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,id);
        ResultSet rs = ptmt.executeQuery();
        Answer ans = null;
        while(rs.next()){
            ans = new Answer();
            ans.setId(rs.getLong("id"));
            ans.setUserId(rs.getLong("userId"));
            ans.setQuestionId(rs.getLong("questionId"));
            ans.setContent(rs.getString("content"));
            ans.setTime(rs.getTimestamp("time"));
            ans.setNumOfAgree(rs.getLong("numOfAgree"));
        }
        return ans;
    }

    public ArrayList<Answer> selectByUserId(Long userId) throws SQLException{
        ArrayList<Answer> answers = new ArrayList<Answer>();
        String sql = "select * from Answer where  userId = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,userId);
        ResultSet rs = ptmt.executeQuery();
        Answer ans = null;
        while(rs.next()){
            ans = new Answer();
            ans.setId(rs.getLong("id"));
            ans.setUserId(rs.getLong("userId"));
            ans.setQuestionId(rs.getLong("questionId"));
            ans.setContent(rs.getString("content"));
            ans.setTime(rs.getTimestamp("time"));
            ans.setNumOfAgree(rs.getLong("numOfAgree"));
            answers.add(ans);
        }
        return answers;
    }

    public ArrayList<Answer> selectByQuestionId(Long questionId) throws SQLException{
        ArrayList<Answer> answers = new ArrayList<Answer>();
        String sql = "select * from Answer where  questionId = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,questionId);
        ResultSet rs = ptmt.executeQuery();
        Answer ans = null;
        while(rs.next()){
            ans = new Answer();
            ans.setId(rs.getLong("id"));
            ans.setUserId(rs.getLong("userId"));
            ans.setQuestionId(rs.getLong("questionId"));
            ans.setContent(rs.getString("content"));
            ans.setTime(rs.getTimestamp("time"));
            ans.setNumOfAgree(rs.getLong("numOfAgree"));
            answers.add(ans);
        }
        return answers;
    }

    public <T> Boolean update(Long id,String attribute,T value) throws SQLException{
        String sql = "update Answer set " + attribute + " = ? where id = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setObject(1,value, getSqlType(value));
        ptmt.setLong(2,id);
        return ptmt.executeUpdate() > 0;
    }

    public int countByQuestionId(Long questionId) throws SQLException{
        String sql = "select count(*) from Answer where questionId = ?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setLong(1,questionId);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()) return rs.getInt(1);
        else throw new SQLException();
    }


}
