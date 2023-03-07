package service.impl;

import mapper.DiaryMapper;
import mapper.MateMapper;
import org.springframework.stereotype.Service;
import pojo.Diary;
import pojo.Mate;
import pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName DiaryServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月22日 21:30:00
 */
@Service("DiaryService")
public class DiaryServiceImpl {
    @Resource
    private DiaryMapper diaryMapper;

    @Resource
    private MateMapper mateMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param httpServletRequest 主键
     * @return 实例对象
     */
    public Map<String, Object> selectById(HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        int id = user.getId();
        Mate mate = this.mateMapper.selectByUserid(user.getId());
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diaryMapper.selectById(id,mate.getMateId()));
        return map;
    }
    /**
     * 根据模糊条件查询总个数
     *
     * @param user_id 查询条件
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectForCount(int user_id){
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diaryMapper.selectForCount(user_id));
        return map;
    }
    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param user_id 查询条件
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, int user_id){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("data", this.diaryMapper.selectForPage(user_id));
        return map;
    }
    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(HttpServletRequest httpServletRequest,String diary){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        Mate mate = this.mateMapper.selectByUserid(user.getId());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Diary diary1 = new Diary();
        diary1.setUserId(mate.getUserId());
        diary1.setMateId(mate.getMateId());
        diary1.setContent(diary);
        diary1.setTime(format.format(date));
        Map<String, Object> map = new HashMap<>();
        this.diaryMapper.insert(diary1);
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id){
        this.diaryMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "删除成功");
        return map;
    }
}
