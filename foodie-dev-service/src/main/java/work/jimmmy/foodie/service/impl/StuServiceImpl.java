package work.jimmmy.foodie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import work.jimmmy.foodie.mapper.StuMapper;
import work.jimmmy.foodie.pojo.Stu;
import work.jimmmy.foodie.service.StuService;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStu(String id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int createStu(Stu stu) {
        return stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateStu(String id) {
        return stuMapper.updateByPrimaryKey();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int deleteStu(String id) {
        return stuMapper.selectByPrimaryKey(id);
    }
}
