package controller;

import org.springframework.web.multipart.MultipartFile;
import pojo.User;
import service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description 处理接收请求，返回结果
 * @createTime 2022年04月05日 21:37:00
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户表(User)管理",tags = "用户表(User)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 通过主键查询单条数据
     * @param index  需要查询的页码
     * @param name  模糊查询的内容
     * @return 单条数据
     */
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询分页接口",notes = "列表查询分页接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "name",value = "**名称",paramType = "query",dataType = "String",required = false)
    })
    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, String name) {
        return this.userServiceImpl.selectForPage(index, name);
    }

    /**
     * 查询所有数据
     * @return 返回所有数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据接口",notes = "查询所有数据接口",httpMethod = "GET")
    public Map<String, Object> selectAll()
    {
        return this.userServiceImpl.selectAll();
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据一个主键删除一条**记录的接口",notes = "根据一个主键删除一条**记录的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query",dataType = "String",required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id)
    {
        return this.userServiceImpl.deleteById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个**的接口",notes = "通过主键查询一个**的接口",httpMethod = "GET")
    public Map<String, Object> selectById(Integer id)
    {
        return this.userServiceImpl.selectById(id);
    }

    /**
     * 通过主键更新单条数据
     *
     * @param user 一个数据库对应的POJO数据对象
     * @return 单条数据
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键更新一个**的接口",notes = "通过主键更新一个**的接口",httpMethod = "POST")
    public Map<String, Object> updateById(@RequestBody @ApiParam(name = "user",value = "pojo模型",required = true) User user)
    {
        return this.userServiceImpl.updateById(user);
    }

    /**
     * 通过一个pojo对象新增单条数据
     *
     * @param user 一个数据库对应的POJO数据对象
     * @return 返回插入的主键id
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个**的接口",notes = "根据完整记录新增一个**的接口",httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "user",value = "pojo模型",required = true) User user)
    {
        user.setIsMate(0);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        user.setLastTime(timestamp);
        return this.userServiceImpl.insert(user);
    }

    /**
     *登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户名密码完成验证登录",notes = "根据用户账号密码完成验证登录",httpMethod = "POST")
    public Map<String,Object> login(@RequestParam("username") String username , @RequestParam("password") String password , HttpServletRequest request)
    {
        return this.userServiceImpl.login(username,password,request);
    }

    /**
     * 显示用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public Map<String,Object> show(HttpServletRequest request)
    {
        return this.userServiceImpl.show(request);
    }
    /**
     * 显示用户头像
     * @param request
     * @return
     */
    @RequestMapping(value = "/showAvatar",method = RequestMethod.GET)
    public Map<String,Object> showAvatar(HttpServletRequest request)
    {
        return this.userServiceImpl.showAvatar(request);
    }
    /**
     * 绑定伴侣
     * @param username
     * @param matename
     * @param request
     * @return
     */
    @RequestMapping(value = "/findmate",method = RequestMethod.POST)
    public Map<String,Object> findmate(@RequestParam("username") String username ,@RequestParam("matename") String matename ,HttpServletRequest request)
    {
        return this.userServiceImpl.findmate(username,matename,request);
    }

    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/avatar",method = RequestMethod.POST)
    public Map<String,Object> avatar(@RequestParam("file") MultipartFile file,@RequestParam("username") String username , HttpServletRequest request)
    {
        return this.userServiceImpl.avatar(file,username,request);
    }

}

