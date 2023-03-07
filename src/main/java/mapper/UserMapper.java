package mapper;

import pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2022年04月05日 21:36:00
 */
public interface UserMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<User> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectById(@Param("id") Integer id);
    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @return 对象列表
     */
    List<User> selectForPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param user 实例对象
     */
    void insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int updateById(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUserByName(@Param("name") String name);
}
