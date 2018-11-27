package com.file.utils;

import com.file.config.FastDfsConfig;
import com.file.constants.GlobalConstants;
import com.file.constants.ResponseStatus;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传及下载接口类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Component
public class FastDfsClientWrapper {

    private static final Logger logger = LoggerFactory.getLogger(FastDfsClientWrapper.class);

    @Autowired
    private FastDfsConfig fastDFSConfig;

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 上传文件
     * 多文件上传是多线程, 要控制并发
     *
     * @param file
     * @return
     */
    public synchronized String uploadFile(MultipartFile file) {
        StorePath storePath;
        try {
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (IOException e) {
            logger.info("文件上传系统异常", e);
            return TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.SYSTEM_ERROR, "文件上传系统异常"));
        }
        return getUploadResult(storePath);
    }

    /**
     * 上传文件
     *
     * @param filePath 文件本地所在路径
     * @return
     */
    public String uploadFile(String filePath) {
        logger.info("文件本地所在路径:" + filePath);
        StorePath storePath;
        File file = new File(filePath);
        if (!file.exists()) {
            logger.info("找不到文件:" + filePath);
            return TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.FAIL, "找不到文件:" + filePath));
        }
        try {
            storePath = storageClient.uploadFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()), null);
        } catch (FileNotFoundException e) {
            logger.info("文件上传系统异常", e);
            return TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.SYSTEM_ERROR, "文件上传系统异常"));
        }
        return getUploadResult(storePath);
    }

    /**
     * 删除文件
     *
     * @param url
     * @return
     */
    public String deleteFile(String url) {
        storageClient.deleteFile(url);
        return TransJsonUtil.Obj2Json("", new StatusMsg());
    }

    /**
     * 批量删除文件
     *
     * @param urlList
     * @return
     */
    public String deleteFile(List<String> urlList) {
        storageClient.deleteFile("http://192.168.1.88:80/group1/M00/00/02/wKgBWFuZ0maAJjGmAARoEB0i_yA2.IN3Nv");
        return TransJsonUtil.Obj2Json("", new StatusMsg());
    }

    /**
     * 处理上传结果返回文件url
     *
     * @param storePath
     * @return
     */
    private String getUploadResult(StorePath storePath) {
        if (storePath == null) {
            logger.info("storePath为空, 文件上传失败");
            return TransJsonUtil.Obj2Json("", new StatusMsg(ResponseStatus.FAIL));
        }
        String fileUrl = GlobalConstants.HTTP_PREFIX + fastDFSConfig.getResHost() + ":" + fastDFSConfig.getStoragePort() + "/" + storePath.getFullPath();
        return TransJsonUtil.Obj2Json(fileUrl, new StatusMsg());
    }

}