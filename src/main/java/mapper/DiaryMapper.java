package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Diary;

import java.util.List;

public interface DiaryMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Diary> selectById(@Param("id") Integer id,@Param("mateId") Integer mateId);
    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("user_id") int user_id);

    /**
     * 通过用户ID作为筛选条件查询
     *
     * @param user_id  查询条件
     * @return 对象列表
     */
    List<Diary> selectForPage(@Param("user_id")int user_id);

    /**
     * 新增数据
     *
     * @param diary 实例对象
     */
    void insert(Diary diary);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}