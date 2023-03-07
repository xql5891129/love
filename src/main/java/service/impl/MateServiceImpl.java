package service.impl;

import pojo.Mate;
import mapper.MateMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName MateServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月18日 18:32:00
 */
@Service("MateService")
public class MateServiceImpl{
    @Resource
    private MateMapper mateMapper;



    public void insert(Mate mate) {
        mateMapper.insert(mate);
    }
    public Mate selectByUserid(Integer id){
        return mateMapper.selectByUserid(id);
    }
}
