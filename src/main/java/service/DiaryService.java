package service;

import pojo.Diary;

import java.util.Map;

/**
 * @author XQL
 * @version 1.0.0
 * @ClassName DiaryService.java
 * @Description TODO
 * @createTime 2022年05月22日 21:23:00
 */
public interface DiaryService {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Map<String, Object> selectById(Integer id);
    /**
     * 根据模糊条件查询总个数
     *
     * @param user_id 查询条件
     * @return 返回查询到的总个数
     */
    Map<String, Object> selectForCount(int user_id);
    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param user_id 查询条件
     * @return 对象列表
     */
    Map<String, Object> selectForPage(int index, int user_id);

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(Diary diary);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);
}
