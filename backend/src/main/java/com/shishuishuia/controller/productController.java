package com.shishuishuia.controller;

import com.shishuishuia.Service.HeadphoneService;
import com.shishuishuia.pojo.Headphone;
import com.shishuishuia.utils.FileStorageService;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName productController
 * @Description TODO
 * @date 2025/4/22 9:42
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private HeadphoneService headphoneService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/detail/{id}")
    public Result findDetail(@PathVariable long id){
        System.out.println(id+"1");
        return Result.ok(id);
    }

//    -------------------------------------------------------------------------------------------------
    @PostMapping("/upload/{id}")  //id为用户 id
    public Result uploadProduct(
            @PathVariable Long id,
            @RequestParam String headline,
            @RequestParam double price,
            @RequestParam String quality,
            @RequestParam String detail,
            @RequestParam("photos") MultipartFile[] images) {

        try {
            // 1. 处理商品基本信息
            Headphone product = new Headphone();
            product.setHeadline(headline);
            product.setPrice(price);
            product.setQuality(quality);
            product.setDetail(detail);
            System.out.println("upload controller:"+id);

            // 2. 处理图片
            List<String> imageUrls = new ArrayList<>();

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    // 保存图片到文件系统或云存储
                    imageUrls.add(fileStorageService.storeFile(image));
                }
            }
            product.setPhotos(imageUrls);

            // 3. 保存商品到数据库
            headphoneService.uploadHeadphone(id,product);
            System.out.println(product);

            return Result.ok(Map.of(
                    "success", true,
                    "message", "商品发布成功",
                    "productId", product.getId()
            ));

        } catch (Exception e) {
            return  Result.build(Map.of(
                    "success", false,
                    "message", "商品发布失败: " + e.getMessage()
            ), ResultCodeEnum.NOTLOGIN);
        }
    }


}
