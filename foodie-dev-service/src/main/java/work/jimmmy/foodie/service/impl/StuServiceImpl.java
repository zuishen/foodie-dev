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
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStu(Stu stu) {

    }

    @Override
    public void insertStu(Stu stu) {

    }

    @Override
    public void deleteStu(int id) {

    }
}
