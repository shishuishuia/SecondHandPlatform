package com.shishuishuia.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FileStorageService {

    @Value("${app.upload.base-dir}")
    private String configuredUploadDir;

    // 日期格式化为 "月-日"（如：4-25）
    private static final DateTimeFormatter MONTH_DAY_FORMATTER =
            DateTimeFormatter.ofPattern("M-d");

    public String storeFile(MultipartFile file) throws IOException {
        // 1. 确定基础存储路径
        Path basePath = determineUploadPath();

        // 2. 创建日期子文件夹（如：4-25）
        String dateFolder = getTodayMonthDayFolder();
        Path datePath = basePath.resolve(dateFolder);

        // 3. 确保目录存在
        Files.createDirectories(datePath);

        // 4. 生成唯一文件名
        String fileName = generateUniqueFileName(file.getOriginalFilename());
        Path targetPath = datePath.resolve(fileName);

        // 5. 存储文件
        file.transferTo(targetPath.toFile());

        // 返回可访问的URL路径（如：/uploads/4-25/filename.jpg）
        return "/uploads/" + dateFolder + "/" + fileName;
    }

    private Path determineUploadPath() throws IOException {
        // 1. 优先使用配置的路径
        if (StringUtils.hasText(configuredUploadDir)) {
            return Paths.get(configuredUploadDir).toAbsolutePath();
        }

        // 2. 开发环境备用路径
        try {
            Path devPath = new ClassPathResource("static").getFile()
                    .toPath()
                    .resolve("uploads");
            if (Files.isWritable(devPath)) {
                return devPath;
            }
        } catch (Exception ignored) {}

        // 3. 系统临时目录作为兜底
        return Paths.get(System.getProperty("java.io.tmpdir"), "uploads");
    }

    private String getTodayMonthDayFolder() {
        return LocalDate.now().format(MONTH_DAY_FORMATTER);
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}