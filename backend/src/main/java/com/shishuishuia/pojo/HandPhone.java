package com.shishuishuia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
public class HandPhone {
    private int id;
    private int state;
    private double price;
    private int browse;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime publishedtime;
    private String headline;
    private String detail;
    private String quality;
    private String location;
//    @ElementCollection
    private List<String> photos;

}
