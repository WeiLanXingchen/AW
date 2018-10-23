package jyx.com.aw.test;

/**
 * Created by hy on 2017/12/26.
 * <p>
 * 功能：
 * <p>
 * 注意事项：
 */

public class CourseBean {
    private String oldPrice;
    private String newPrice;
    private int count;
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
