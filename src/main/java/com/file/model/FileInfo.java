package com.file.model;

import lombok.Data;

import java.util.Date;

/**
 * 上传文件实体类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Data
public class FileInfo {

    /** t_upload_file表主键, UUID. */
    private String id;

    /** 文件名. */
    private String fileName;

    /** 文件大小. */
    private String fileSize;

    /** 文件扩展名. */
    private String extName;

    /** 文件下载路径(程序接口路径). */
    private String fileUrl;

    /** 文件下载路径(服务器上的真实存放路径). */
    private String fileRealUrl;

    /** 文件状态, 0:无效 1:有效. */
    private Integer status;

    /** 文件上传时间. */
    private Date uploadTime;

}