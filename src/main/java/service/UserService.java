package service;

import pojo.User;
import pojo.MessageRecord;

import java.util.Map;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description 用户表(User)表服务接口类
 * @createTime 2022年04月05日 21:40:00
 */
public interface UserService {
    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    Map<String, Object> selectForCount(String name);

    /**
     * 查询所有数据
     *
     * @return  返回所有数据
     */
    Map<String, Object> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Map<String, Object> selectById(Integer id);

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name 查询条件
     * @return 对象列表
     */
    Map<String, Object> selectForPage(int index, String name);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Map<String, Object> updateById(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);

    /**
     * 通过username查询单条数据
     * @param name
     * @return实例对象
     */
    User getUserByName(String name);


    /**
     * 通过账号和密码进行验证
     * @param name 账号
     * @param password 密码
     * @return 是否成功
     */
    Map<String, Object> login(String name, String password);

    /**
     * 添加消息记录
     * @param messageRecord
     */
    void addUserMessageRecord(MessageRecord messageRecord);


}

