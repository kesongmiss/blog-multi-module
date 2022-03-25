package com.tenero.blog.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author kesong
 * @description 连接oss
 * @date 2022/3/17 16:08
 */
@Slf4j
public class OSSClientUtils {


    public OSS OssClient(String endpoint,String accessKeyID, String accessKeySecret){
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);

    }

    /**
     *
     * @param ossClient OSS实例
     * @param bucket 桶
     * @param path 文件路径
     * @param firstKey objectName
     * @return
     */
    public boolean uplodeFile(OSS ossClient,String bucket,String path,String firstKey){
        try{
            if(ossClient.doesBucketExist(bucket)){
                log.info("您已经创建Bucket：" + bucket + "。");
                return false;
            }
            File musicFile = new File(path);
            if (!musicFile.exists()){
                log.info("文件不存在");
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(musicFile);
            log.info("待上传文件名： {} " + musicFile.getName());
            // 文件存入OSS
            PutObjectResult putObjectResult = ossClient.putObject(bucket, firstKey+musicFile.getName(), fileInputStream);
            log.info("上传完成");
        }catch (Exception e){

        }finally {
            ossClient.shutdown();
        }
        return true;
    }


}
