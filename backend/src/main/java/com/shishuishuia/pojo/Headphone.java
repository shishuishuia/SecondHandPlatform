package com.shishuishuia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName Product
 * @Description TODO
 * @date 2025/4/25 11:58
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Headphone {
    private int id;
    private int state;
    private double price;
    private int browse;
    private Date publishedtime;
    private String headline;
    private String detail;
    private String quality;

//    @ElementCollection
    private List<String> photos;

}
