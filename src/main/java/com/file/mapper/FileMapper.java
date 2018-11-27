package com.file.mapper;

import com.file.model.FileInfo;

import java.util.List;

/**
 * 文件上传DAO接口
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public interface FileMapper {

    /**
     * 选择性插入上传文件信息
     *
     * @param fileInfo
     * @return
     */
    int insertSelective(FileInfo fileInfo);

    /**
     * 获取数据总条数
     *
     * @param fileInfo
     * @return
     */
    int getTotal(FileInfo fileInfo);

    /**
     * 获取文件信息数据
     *
     * @param fileInfo
     * @return
     */
    List<FileInfo> getList(FileInfo fileInfo);

/*    int deleteByPrimaryKey(String id);

    int insert(FileInfo fileInfo);

    FileInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileInfo fileInfo);

    int updateByPrimaryKey(FileInfo fileInfo);*/

}