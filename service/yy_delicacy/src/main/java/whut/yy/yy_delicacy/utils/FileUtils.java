package whut.yy.yy_delicacy.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FileUtils {

    public static String uploadImg(MultipartFile file, String dir) throws Exception {
        //1.构建文件名
        String randomNumber = UUID.randomUUID().toString().replace("-", "");//使用UUID生成唯一标识
        //jpg
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());//使用FilenameUtils获得文件的后缀
        //新文件名
        String newFileName = randomNumber + "." + extension; //生成新的文件名(随机数+后缀名)

        //2.上传
//        String fileUploadDir = ResourceUtils.getURL("classpath:").getPath() + dir; //使用ResourceUtils类路径再获取文件保存的路径
        String fileUploadDir = new File("src/main/resources/static").getCanonicalPath() + "/" + dir; //使用ResourceUtils类路径再获取文件保存的路径
        fileUploadDir = URLDecoder.decode(fileUploadDir.replaceAll("%20", " "), StandardCharsets.UTF_8);//处理目录名为中文以及空格问题
        File destDir = new File(fileUploadDir);
        if (!destDir.exists()) {
            destDir.mkdirs();//判断目录是否存在，不存在则直接创建
        }
        file.transferTo(new File(fileUploadDir, newFileName));

        //返回相对路径
        return "/" + dir + "/" + newFileName; //文件保存的相对路径
    }

    //删除具体图片
    public static void deleteImg(String path) {
        File file = new File("src/main/resources/static" + path);
        if (file.exists()) {
            file.delete();
        }
    }

    //删除目录及其下所有文件
    public static void deleteDir(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                deleteDir(f);
            } else {
                f.delete();
            }
        }
        //删除空文件夹 for循环已经把上一层节点的目录清空
        file.delete();
    }
}
