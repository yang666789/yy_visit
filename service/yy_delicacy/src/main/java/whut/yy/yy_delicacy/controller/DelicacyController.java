package whut.yy.yy_delicacy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import whut.yy.utils.utils.R;
import whut.yy.yy_delicacy.model.Delicacy;
import whut.yy.yy_delicacy.service.DelicacyService;
import whut.yy.yy_delicacy.utils.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/delicacy")
public class DelicacyController {

    @Autowired
    DelicacyService delicacyService;

    @GetMapping("/page")
    public R<List<Delicacy>> getDelicacyPage(String name, Integer tId, int pageNum, int pageSize,
                                             HttpServletResponse response) {
        //1.获取分页数据
        PageInfo<Delicacy> pageInfo = delicacyService.getPages(name, tId, pageNum, pageSize);

        //2.将数据总数加入到头部
        response.addHeader("Access-Control-Expose-Headers", "total");
        response.addHeader("total", JSON.toJSONString(pageInfo.getTotal()));

        //3.返回分页数据
        return R.ok(pageInfo.getList());
    }

    /**
     * 按条件获取用户收藏的小店(分页)
     * 跳转到收藏界面用
     *
     * @param uId
     * @param name
     * @param tId
     * @param pageNum
     * @param pageSize
     * @param response
     * @return
     */
    @GetMapping("/page/{id}")
    public R<List<Delicacy>> getUserDelicacyCollection(@PathVariable("id") int uId,
                                                       String name, Integer tId,
                                                       int pageNum, int pageSize,
                                                       HttpServletResponse response) {
        //1.获取分页数据
        PageInfo<Delicacy> pageInfo = delicacyService.getPagesByUId(uId, name, tId, pageNum, pageSize);

        //2.将数据总数加入到头部
        response.addHeader("Access-Control-Expose-Headers", "total");
        response.addHeader("total", JSON.toJSONString(pageInfo.getTotal()));

        //3.返回分页数据
        return R.ok(pageInfo.getList());
    }

    /**
     * 获取某用户收藏的所有小店(无分页)
     * 生成地图用
     *
     * @param uId：用户id
     * @return
     */
    @GetMapping("/{id}")
    public R<List<Delicacy>> getUserDelicacyCollection(@PathVariable("id") int uId) {
        return R.ok(delicacyService.getDelicacyByUId(uId));
    }

    @PostMapping("/")
    public R addDelicacy(Delicacy delicacy,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        //1.先保存数据库
        delicacyService.addDelicacy(delicacy);

        //2.返回生成的id，将图片存入该id下
        String relativePath = "";
        if (file != null) {
            relativePath = FileUtils.uploadImg(file, "static/img/" + delicacy.getId());
        }
        //3.添加图片相对路径，只用更新路径(待插入成功生成id后，再添加图片路径)
        delicacyService.updateDelicacy(
                Delicacy.builder()
                        .id(delicacy.getId())
                        .imgPath(relativePath)
                        .build()
        );

        return R.ok(null);
    }

    @PutMapping("/")
    public R updateDelicacy(Delicacy delicacy,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        //1.先删除原图片，再添加新图片，然后设置地址
        if (file != null) {
            FileUtils.deleteImg(delicacy.getImgPath());
            String relativePath = FileUtils.uploadImg(file, "upload/img/" + delicacy.getId());
            delicacy.setImgPath(relativePath);
        }

        //2.更新
        delicacyService.updateDelicacy(delicacy);
        return R.ok(null);
    }

    @DeleteMapping("/{id}")
    public R deleteDelicacy(@PathVariable("id") int id) {
        //1.删除文件夹下所有东西
        FileUtils.deleteDir(new File("src/main/resources/static/upload/img/" + id));

        //2.从数据库中删除
        delicacyService.deleteDelicacy(id);

        return R.ok(null);
    }
}
