package com.ljl.www.dao;

import com.ljl.www.po.Post;
import com.ljl.www.po.Remark;
import com.ljl.www.po.Remark;
import com.ljl.www.util.MyID;
import com.ljl.www.util.PostListControlPacket;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author 22427(king0liam)
 * @version 1.0
 * @ClassName: remarkSql
 * @Description
 * @Date: 2021/7/3 16:49
 * @since version-0.0
 */
public class RemarkSql {
    public RemarkSql(){}
    public Remark insertRemark(Remark remark){
        /**
         * @description 新建事件并插入
         * @exception SQLException
         * @param [remark]
         * @return [com.ljl.www.po.Remark]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:46
         */
        Connection connection=null;
        PreparedStatement statement=null;
        PreparedStatement statementPost=null;
        ResultSet resultSets=null;
        try{
            int floor=1;
            connection = DriverUtils.getConnection();
            String sql0="select count(*) from remark where father_id=?";
            statement=connection.prepareStatement(sql0);
            statement.setLong(1,remark.getFatherId());
            resultSets=statement.executeQuery();
            int index=0;
            for(;resultSets.next();index++){
                floor=resultSets.getInt("count(*)")+1;
            }
            String sql="INSERT INTO remark(father_id,floor,to_floor,client_id,remark_article,remark_new_date,remark_edit_date,visible)" +
                    " VALUES(?,?,?,?,?,?,?,true);";
            statement=connection.prepareStatement(sql);
            statement.setLong(1,remark.getFatherId());
            statement.setLong(2,floor);
            statement.setLong(3,remark.getToFloor());
            statement.setLong(4,remark.getClientId());
            statement.setString(5,remark.getRemarkArticle());
            statement.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(7,new Timestamp(System.currentTimeMillis()));

            String sqlPost="UPDATE POST SET " +
                    "remark_count=remark_count+1" +
                    " WHERE post_id=? and visible!=0;";
            statementPost=connection.prepareStatement(sqlPost);
            statementPost.setLong(1,remark.getFatherId());
            if(statement.executeUpdate()>0&&statementPost.executeUpdate()>0){
                remark=new Remark();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return remark;
    }
    public ArrayList<Remark> queryRemark(ArrayList<Remark> remarkList){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        int index=0;
        try{
            connection = DriverUtils.getConnection();
            String sql="SELECT * FROM remark WHERE father_id =? and visible!=0 ORDER BY floor ASC ";
            statement=connection.prepareStatement(sql);
            //statement.setLong(1,postListControlPacket.paginationList.get(postListControlPacket.pageParam*postListControlPacket.limit));
            statement.setLong(1,remarkList.get(0).getFatherId());
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done  显示评论");

            while(resultSets.next()){
                if (index==0){remarkList=new ArrayList<>();}
                remarkList.add(new Remark());
                remarkList.get(index).setFatherId(resultSets.getLong("father_id"));
                remarkList.get(index).setClientId(resultSets.getLong("client_id"));
                remarkList.get(index).setFloor(resultSets.getInt("floor"));
                remarkList.get(index).setToFloor(resultSets.getInt("to_floor"));
                remarkList.get(index).setRemarkArticle(resultSets.getString("remark_article"));
                remarkList.get(index).setRemarkEditDate(resultSets.getTimestamp("remark_edit_date"));
                remarkList.get(index).setRemarkNewDate(resultSets.getTimestamp("remark_new_date"));
                remarkList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                index++;
            }

        }catch (SQLException e){
            System.out.println("Hint.pop(空事件表)");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Hint.pop(\"事件列表索引越界\")");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        //if(index==0)return
        return remarkList;
    }
    public Remark editRemark(Remark remark){
        /**
         * @description 新建事件并插入
         * @exception SQLException
         * @param [remark]
         * @return [com.ljl.www.po.Remark]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:46
         */
        /**
         * @description 新建事件并插入
         * @exception SQLException
         * @param [remark]
         * @return [com.ljl.www.po.Remark]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:46
         */
        Connection connection=null;
        PreparedStatement statement=null;
        PreparedStatement statementPost=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            String sql="update remark set remark_edit_date=?,remark_article=? where father_id=? and floor=?";
            statement=connection.prepareStatement(sql);
            statement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
            statement.setString(2,remark.getRemarkArticle());
            statement.setLong(3,remark.getFatherId());
            statement.setLong(4,remark.getFloor());

            String sqlPost="UPDATE POST SET " +
                    "remark_count=remark_count-1" +
                    " WHERE post_id=? and visible!=0;";
            statementPost=connection.prepareStatement(sqlPost);
            statementPost.setLong(1,remark.getFatherId());
            if(statement.executeUpdate()>0&&statementPost.executeUpdate()>0){
                remark=new Remark();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return remark;
    }
public Remark deleteRemark(Remark remark){
    /**
     * @description 删除评论
     * @exception SQLException
     * @param [remark]
     * @return [com.ljl.www.po.Remark]
     * @since version-1.0
     * @author 22427(king0liam)
     * @date 2021/6/18 14:46
     */
    Connection connection=null;
    PreparedStatement statement=null;
    ResultSet resultSets=null;
    try{
        connection = DriverUtils.getConnection();
        String sql="update remark set visible=0,remark_edit_date=? where father_id=? and floor=?";
        statement=connection.prepareStatement(sql);
        statement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
        statement.setLong(2,remark.getFatherId());
        statement.setLong(3,remark.getFloor());
        if(statement.executeUpdate()>0) {
           remark=new Remark();
        }
    }catch (SQLException e){
        e.printStackTrace();
    }finally {
        DriverUtils.release(connection,statement,resultSets);
    }
    return remark;
}
    static public PostListControlPacket getMyCommentList(PostListControlPacket postListControlPacket){
        /**
         * @description 查询登录此账号用户评论过的所有帖子
         * @exception
         * @param [com.ljl.www.util.PostListControlPacket] [postListControlPacket]
         * @return [com.ljl.www.util.PostListControlPacket]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/6/18 14:49
         */
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSets=null;
        try{
            connection = DriverUtils.getConnection();
            //todo 此distinct 无用!因为*是连接后的结果
            String sql="SELECT distinct * FROM post inner join remark ON remark.client_id=? and post.visible!=0 and remark.visible!=0 and remark.father_id=post.post_id ORDER BY post.post_id DESC " ;
            statement=connection.prepareStatement(sql);
            statement.setLong(1,postListControlPacket.clientId);
            resultSets=statement.executeQuery();//这里不用参数
            System.out.println("done getMyCommentList");
            int index=0;
            postListControlPacket.postList=new ArrayList<>();
            for(;resultSets.next();index++){
                postListControlPacket.postList.add(new Post());
                postListControlPacket.postList.get(index).setPostId(resultSets.getLong("post.post_id"));
                postListControlPacket.postList.get(index).setClientId(resultSets.getLong("post.client_id"));
                postListControlPacket.postList.get(index).setPostNewDate(resultSets.getTimestamp("post_new_date"));
                postListControlPacket.postList.get(index).setPostTitle(resultSets.getString("post_title"));
                postListControlPacket.postList.get(index).setPostArticle(resultSets.getString("post_article"));
                postListControlPacket.postList.get(index).setThumbsUpCount(resultSets.getLong("thumbs_up_count"));
                postListControlPacket.postList.get(index).setFavoriteCount(resultSets.getLong("favorite_count"));
                postListControlPacket.postList.get(index).setRemarkCount(resultSets.getLong("remark_count"));
                postListControlPacket.postList.get(index).setVisible(resultSets.getByte("visible"));
            }
            postListControlPacket.postCount=index;

        }catch (SQLException e){
            System.out.println("空事件表！");
            e.printStackTrace();
        }finally {
            DriverUtils.release(connection,statement,resultSets);
        }
        return  postListControlPacket;
    }
}

