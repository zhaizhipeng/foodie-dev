package com.ysdrzp.service;

import com.ysdrzp.pojo.Stu;

public interface IStuService {

    /**
     * 查询
     * @param id
     * @return
     */
    Stu getStu(Integer id);

    /**
     * 保存
     * @param stu
     */
    void saveStu(Stu stu);

    /**
     * 删除
     * @param id
     */
    void deleteStu(Integer id);

}
