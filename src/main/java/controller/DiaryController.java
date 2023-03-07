package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.impl.DiaryServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName DiaryController.java
 * @Description 回忆录请求处理
 * @createTime 2022年05月22日 21:41:00
 */
@RestController
@RequestMapping("/diary")
@Api(value = "回忆录表(Diary)管理",tags = "回忆录表(Diary)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class DiaryController {
    @Resource
    private DiaryServiceImpl diaryServiceImpl;

    /**
     * 通过ID查询单条数据
     *
     * @param httpServletRequest 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个**的接口",notes = "通过主键查询一个**的接口",httpMethod = "GET")
    public Map<String, Object> selectById(HttpServletRequest httpServletRequest)
    {
        return this.diaryServiceImpl.selectById(httpServletRequest);
    }


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Map<String,Object> insert(HttpServletRequest httpServletRequest, String diary){
        System.out.println(diary);
        return this.diaryServiceImpl.insert(httpServletRequest,diary);
    }



}
