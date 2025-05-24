package com.shishuishuia.controller;

import com.shishuishuia.Service.HandphoneService;
import com.shishuishuia.pojo.HandPhone;
import com.shishuishuia.utils.FileStorageService;
import com.shishuishuia.utils.JwtHelper;
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
public class HeadphoneController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private HandphoneService handphoneService;

    @Autowired
    private FileStorageService fileStorageService;

    //id为手机id
    @GetMapping("/detail/{id}")
    public Result getDetail(@PathVariable long id){

        Result detailByphoneId = handphoneService.getDetailByphoneId((int) id);
        System.out.println(id+"1");
        return detailByphoneId;
    }

//    -------------------------------------------------------------------------------------------------
    @PostMapping("/upload")
    public Result uploadHeadphone(
            @RequestParam String token,
            @RequestParam String headline,
            @RequestParam double price,
            @RequestParam String quality,
            @RequestParam String detail,
            @RequestParam String location,
            @RequestParam("photos") MultipartFile[] images) {

        try {
            // 1. 处理商品基本信息
            HandPhone product = new HandPhone();
            product.setHeadline(headline);
            product.setPrice(price);
            product.setQuality(quality);
            product.setDetail(detail);
            product.setLocation(location);

            // 2. 处理图片
            List<String> imageUrls = new ArrayList<>();

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    // 保存图片到文件系统或云存储
                    imageUrls.add(fileStorageService.storeFile(image));
                }
            }
            product.setPhotos(imageUrls);
            System.out.println(token);
            Long id = jwtHelper.getUserId(token);
            // 3. 保存商品到数据库
            handphoneService.uploadHeadphone(id,product);
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

    //id为userId
    @GetMapping("/productlist/{id}")
    public Result getProductList(@PathVariable int id){

        System.out.println(id);
        Result allHandphoneByUserId = handphoneService.getAllHandphoneByUserId(id);
        return allHandphoneByUserId;
    }

    @GetMapping("getlistInfo")
    public Result gethandphoneListByLocation(@RequestParam String location){
        System.out.println(location);
        Result handphoneListBylocation = handphoneService.getHandphoneListBylocation(location);
        return handphoneListBylocation;
    }

    @DeleteMapping("delete/{productId}")
    public Result deleteProductById(@PathVariable int productId){
        Result result = handphoneService.deleteHandphoneById(productId);
        return result;
    }

}
