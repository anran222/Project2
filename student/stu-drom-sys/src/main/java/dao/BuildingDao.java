package dao;

import model.DictionaryTag;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/12 15:41
 */
public class BuildingDao {

    public static List<DictionaryTag> query() {
        List<DictionaryTag> list=new ArrayList<>();
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            c= DBUtil.getConnection();
            String sql="select id,building_name from building";
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                DictionaryTag tag=new DictionaryTag();
                tag.setDictionaryTagKey(rs.getString("id"));
                tag.setDictionaryTagValue(rs.getString("building_name"));
                list.add(tag);
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询宿舍楼数据字典出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
        return list;
    }
}
