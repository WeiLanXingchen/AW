package jyx.com.aw.test;

import java.util.List;

/**
 * Created by hy on 2017/12/26.
 * <p>
 * 功能：
 * <p>
 * 注意事项：
 */

public class TestBean {
    private String typeName;
    private List<CourseBean> courses;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<CourseBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseBean> courses) {
        this.courses = courses;
    }
}
