package service;

import pojo.Mate;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName MateService.java
 * @Description TODO
 * @createTime 2022年05月18日 18:26:00
 */
public interface MateService {
    void insert(Mate mate);
    Mate selectByUserid(Integer id);
}
