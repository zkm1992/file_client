package com.file.service.impl;

import com.file.mapper.FileMapper;
import com.file.model.FileInfo;
import com.file.service.FileService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件上传接口实现类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public String saveFileInfo(FileInfo fileInfo) {
        int num = fileMapper.insertSelective(fileInfo);
        return String.valueOf(num);
    }

    @Override
    public int getTotal(FileInfo fileInfo) {
        return fileMapper.getTotal(fileInfo);
    }

    @Override
    public List<FileInfo> getList(FileInfo fileInfo, int curr, int limit) {
        PageHelper.startPage(curr, limit);
        List<FileInfo> fileList = fileMapper.getList(fileInfo);
        return fileList;
    }

}