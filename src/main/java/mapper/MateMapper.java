package mapper;

import pojo.Mate;

public interface MateMapper {

    void insert(Mate record);

    Mate selectByUserid(Integer id);

}