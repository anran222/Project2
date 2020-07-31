package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.DictionaryTag;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/12 14:41
 */
public class DictionaryTagDao {
        public static List<DictionaryTag> query(String key) {
        List<DictionaryTag> list=new ArrayList<>();
            Connection c=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                c= DBUtil.getConnection();
                String sql="select concat(d.dictionary_key, dictionary_tag_key) dictionary_tag_key," +
                        "       dt.dictionary_tag_value" +
                        "   from dictionary d" +
                        "         join dictionary_tag dt on d.id = dt.dictionary_id" +
                        "   where d.dictionary_key = ?";
                ps=c.prepareStatement(sql);
                ps.setString(1,key);
                rs=ps.executeQuery();
                while (rs.next()){
                    DictionaryTag tag=new DictionaryTag();
                    tag.setDictionaryTagKey(rs.getString("dictionary_tag_key"));
                    tag.setDictionaryTagValue(rs.getString("dictionary_tag_value"));
                    list.add(tag);
            }
            } catch (SQLException e) {
                throw new RuntimeException("查询数据字典标签出错",e);
            }finally {
                DBUtil.close(c,ps,rs);
            }
            return list;
        }
}
