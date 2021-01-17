package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Stu;

public interface StuService {
    Stu getStuInfo(int id);

    void updateStu(Stu stu);

    void insertStu(Stu stu);

    void deleteStu(int id);
}
