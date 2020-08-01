package util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author:xiang
 */
public class DBUtilTest {

    @Test
    public void testConnection(){
        Assert.assertNotNull(DBUtil.getConnection());
    }
}
