package whut.yy.yy_delicacy.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/***
 * druid加密
 */
public class DruidPasswordEncrypt {
    public static void main(String[] args) throws Exception {
        // 需要加密的明文命名
        String password = "root"; // 【注意：这里要改为你自己的密码】
        // 调用 druid 生成私钥、公钥、密文
        ConfigTools.main(new String[]{password});
    }
}
