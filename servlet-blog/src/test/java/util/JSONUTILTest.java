package util;

import com.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Date;

/**
 * @Author:xiang
 */
public class JSONUTILTest {

    @Test
    public void t1(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Article article = new Article();
            article.setId(1);
            article.setTitle("优秀");
            article.setContent("实时监控上课");
            article.setUserId(1);
            article.setCreateTime(new Date());
            String s=mapper.writeValueAsString(article);
            System.out.println(s);
            Article des=mapper.readValue(s,Article.class);
            System.out.println(des);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
