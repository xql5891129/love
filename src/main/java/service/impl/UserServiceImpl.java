package service.impl;

import mapper.MateMapper;
import org.springframework.web.multipart.MultipartFile;
import pojo.Mate;
import pojo.MessageRecord;
import pojo.User;
import mapper.UserMapper;
import mapper.MessageRecordMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2022年04月05日 21:41:00
 */
@Service("userService")
public class UserServiceImpl {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MessageRecordMapper messageRecordMapper;
    @Resource
    private MateMapper mateMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectForCount(String name) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.userMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.userMapper.selectAll());
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Map<String, Object> selectById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.userMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, String name) {
        // 获取当前表中的总记录
        int tableCount = this.userMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.userMapper.selectForPage(index, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(User user) {
        Map<String, Object> map = new HashMap<>();
        if(this.userMapper.getUserByName(user.getUsername())!=null)
        {
            map.put("code", 500);
            map.put("msg", "用户名已存在");
        }
        else
        {
            this.userMapper.insert(user);
            map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("msg", "新增成功");
        }
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(User user) {
        this.userMapper.updateById(user);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id) {
        this.userMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 通过账号和密码进行验证
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */

    public Map<String, Object> login(String username, String password, HttpServletRequest request){
        User user = this.userMapper.getUserByName(username);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "登录失败");
        if(user!=null&&user.getPassword().equals(password)){
            request.getSession().setAttribute("user",user);
            map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("msg", "登录成功");
            map.put("username",username);
            if(this.mateMapper.selectByUserid(user.getId())==null){
                map.put("matename",null);
            }
            else {
                Mate mate=this.mateMapper.selectByUserid(user.getId());
                User mateuser=this.userMapper.selectById(mate.getMateId());
                map.put("matename",mateuser.getUsername());
            }
        }

        return map;
    }

    /**
     * 显示个人信息
     * @param request
     * @return
     */

    public Map<String, Object> show(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user= (User) request.getSession().getAttribute("user");
        Mate mate=this.mateMapper.selectByUserid(user.getId());
        if(mate!=null) {
            User mateuser=this.userMapper.selectById(mate.getMateId());
            map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("msg", "显示成功");
            map.put("username",user.getUsername());
            map.put("matename",mateuser.getUsername());
            map.put("tel",user.getTel());
            map.put("email",user.getEmail());
            map.put("time",mate.getTime());
        }
        else {
            map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("msg", "显示成功");
            map.put("username",user.getUsername());
            map.put("matename","");
            map.put("tel",user.getTel());
            map.put("email",user.getEmail());
            map.put("time","");
        }
        return map;
    }

    /**
     * 显示用户头像
     * @param request
     * @return
     */
    public Map<String, Object> showAvatar(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        User user= (User) request.getSession().getAttribute("user");
        Mate mate=this.mateMapper.selectByUserid(user.getId());

        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "显示成功");
        if(user.getIsMate()==1){
            User mateuser=this.userMapper.selectById(mate.getMateId());
            map.put("userAvatar",user.getAvatar());
            map.put("mateAvatar",mateuser.getAvatar());
        }
        else {
            map.put("userAvatar",user.getAvatar());
            map.put("mateAvatar","null");
        }
        return map;
    }
    /**
     * 绑定伴侣
     * @param username
     * @param matename
     * @param request
     * @return
     */
    public Map<String, Object> findmate(String username, String matename, HttpServletRequest request) {
        User user = this.userMapper.getUserByName(username);
        User mateuser=this.userMapper.getUserByName(matename);
        Map<String, Object> map = new HashMap<>();
        if(mateuser == null) {
            map.put("code", 500);
            map.put("msg", "查找用户不存在！");
        }
        if(user.getIsMate() == 1 ||mateuser.getIsMate()==1) {
            map.put("code", 500);
            map.put("msg", "你或对方已绑定伴侣，可要专一哦！");
        }
        else {
            user.setIsMate(1);
            mateuser.setIsMate(1);
            userMapper.updateById(user);
            userMapper.updateById(mateuser);
            Timestamp time=new Timestamp(new Date().getTime());
            Mate mate1=new Mate(user.getId(),mateuser.getId(),time);
            Mate mate2=new Mate(mateuser.getId(),user.getId(),time);

            mateMapper.insert(mate1);
            mateMapper.insert(mate2);
            map.put("code", 200);
            map.put("msg", "绑定成功！");
            map.put("matename",matename);
            map.put("time",time);
        }
        return map;
    }

    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     */
    public Map<String, Object> avatar(MultipartFile file, String username,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {
            User user=this.userMapper.getUserByName(username);
            String imageName = user.getId() +".jpg";
            String path = request.getSession().getServletContext().getRealPath("/image/") + imageName;
            System.out.println(path);
            File localImageFile = new File(path);
            try {
                //上传图片到目录
                byte[] bytes = file.getBytes();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localImageFile));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                //更新数据库user头像
                user.setAvatar(request.getContextPath() + "/image/" + imageName);
                this.userMapper.updateById(user);
                map.put("code", 200);
                map.put("msg", "上传成功！");
                map.put("avater",user.getAvatar());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }



    /**
     *添加消息记录
     * @param messageRecord
     */
    public void addUserMessageRecord(MessageRecord messageRecord) {
        messageRecordMapper.addMessageRecord(messageRecord);
    }
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }



}
