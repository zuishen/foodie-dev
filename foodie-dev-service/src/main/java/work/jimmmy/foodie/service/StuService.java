package work.jimmmy.foodie.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import work.jimmmy.foodie.pojo.Stu;

public interface StuService {
    Stu getStu(String id);

    int createStu(Stu stu);

    int updateStu(String id);

    int deleteStu(String id);
}
