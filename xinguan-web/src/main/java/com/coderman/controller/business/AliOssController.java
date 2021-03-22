package com.coderman.controller.business;

import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.response.ResponseBean;
import com.coderman.system.service.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author NieChangan
 */
//Api是用swagger  第一个是解决跨域
@CrossOrigin
@Slf4j
@Api(tags = "系统模块-我")
@RestController
@RequestMapping("/system/uploadd")
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @ApiOperation(value = "上传图片文件")
    @PostMapping("/uploadImgFile")
    public ResponseBean uploadImgFile(MultipartFile file){
        String s = aliOssService.upload(file);
        return ResponseBean.success(s);
    }

    @ApiOperation(value = "删除替换之后的头像")
    @PostMapping("/deleteImgFile")
    public ResponseBean deleteImgFile(String file){
        //https://xinguan-parent.oss-cn-hangzhou.aliyuncs.com/2020/10/20/300f7c9d6546486eb55d825d4edcf668.png
        //.com/前面的多余
        try {
            String[] split = file.split(".com/");
            System.out.println(split[1]);
            aliOssService.deleteFile(split[1]);
            return ResponseBean.success();
        }catch (Exception e){
            //需要打印错误日志到本地系统中(服务器系统)
            return ResponseBean.error(SystemCodeEnum.PARAMETER_ERROR);
        }
    }


}
