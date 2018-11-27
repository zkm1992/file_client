package com.file.utils;

import java.util.UUID;

/**
 * UUID生成工具类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public class UUIDUtil {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }

}