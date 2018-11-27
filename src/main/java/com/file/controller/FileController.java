package com.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.file.constants.ResponseStatus;
import com.file.model.FileInfo;
import com.file.service.FileService;
import com.file.utils.FastDfsClientWrapper;
import com.file.utils.StatusMsg;
import com.file.utils.TransJsonUtil;
import com.file.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 文件管理Controller
 * 1.文件上传 2.文件删除 3.文件列表
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /** 状态码取值key, 避免魔法值. */
    private static final String STATUS = "status";

    /** 文件下载路径前缀. */
    @Value("${downloadUrlPrefix}")
    private String downloadUrlPrefix;

    @Autowired
    private FastDfsClientWrapper clientWrapper;

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        logger.info("开始上传文件，fileName：" + fileName);
        //开始上传
        String uploadResult = clientWrapper.uploadFile(file);
        JSONObject result = JSONObject.parseObject(uploadResult);
        if (ResponseStatus.SUCCESS.getStatusCode().equals(result.getInteger(STATUS))) {
            logger.info("文件上传成功，fileUrl=" + result.getString("data"));
            String uuid = UUIDUtil.getUUID();
            String fileUrl = downloadUrlPrefix + uuid;
            //保存文件信息到数据库
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(uuid);
            fileInfo.setFileName(fileName);
            fileInfo.setExtName(fileName.substring(fileName.lastIndexOf(".")));
            fileInfo.setFileSize(new BigDecimal(file.getSize()).divide(new BigDecimal(1024)).setScale(1, BigDecimal.ROUND_HALF_UP).toString() + "KB");
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setStatus(1);
            fileInfo.setUploadTime(new Date());
            fileInfo.setFileRealUrl(result.getString("data"));
            String num = fileService.saveFileInfo(fileInfo);
            if (Integer.valueOf(num) > 0) {
                logger.info("文件信息保存成功");
            } else {
                //文件信息保存失败, 不返回文件url, 提示用户重新上传
                result.put("status", ResponseStatus.DATA_SAVE_ERROR.getStatusCode());
                result.put("msg", "文件信息保存失败, 请重新上传");
                uploadResult = result.toJSONString();
                //删除已上传到服务器的文件
                clientWrapper.deleteFile(fileInfo.getFileRealUrl());
                logger.info("文件信息保存失败, 已删除服务器上的文件");
            }
        } else {
            logger.info("文件上传失败");
        }
        return uploadResult;
    }

    /**
     * 获取数据总条数
     *
     * @param fileInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTotal")
    public String getTotal(@RequestBody FileInfo fileInfo) {
        logger.info("获取分页总条数请求参数：" + fileInfo.toString());
        int total = fileService.getTotal(fileInfo);
        logger.info("获取分页总条数total=" + total);
        return TransJsonUtil.Obj2Json(total, new StatusMsg());
    }

    /**
     * 获取文件信息分页数据
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFileInfo")
    public String getFileInfo(@RequestBody JSONObject param) {
        FileInfo fileInfo = new FileInfo();
        List<FileInfo> list = fileService.getList(fileInfo, param.getIntValue("curr"), param.getIntValue("limit"));
        return TransJsonUtil.Obj2Json(list, new StatusMsg());
    }

    /**
     * 删除文件
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public String deleteFile() {
        clientWrapper.deleteFile("");
        return TransJsonUtil.Obj2Json("", new StatusMsg());
    }

}