package com.file.service;

import com.file.model.FileInfo;

import java.util.List;

/**
 * 文件上传Service接口
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public interface FileService {

    /**
     * 保存文件信息
     *
     * @param fileInfo
     * @return
     */
    String saveFileInfo(FileInfo fileInfo);

    /**
     * 获取数据总条数
     *
     * @param fileInfo
     * @return
     */
    int getTotal(FileInfo fileInfo);

    /**
     * 获取文件信息分页数据
     *
     * @param fileInfo
     * @param curr
     * @param limit
     * @return
     */
    List<FileInfo> getList(FileInfo fileInfo, int curr, int limit);

}