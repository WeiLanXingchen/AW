package jyx.com.aw.mvp.model;

/**
 * Created by JiangYunxian on 2017/12/21 0021.
 * 功能：
 */

public class BuyOfSchoolDetailHeader {

    /**
     * code : 200
     * text :         以下资料由南京师范大学研究生团队整理提供，其团队成员覆盖各个院系，专门搜集本校的考研真题和高分笔记、题库等资料。
     专业课资料作为考研核心资料，部分专业重题概率极高，必须吃透，反复复习。如有需要高分研究生学长一对一辅导的，也可联系我们安排。
     考研派作为国内最大的考研专业课辅导品牌，为大家提供安全的交易平台，资料有任何问题，均可向我们投诉，我们会督促南京师范大学研究生团队解决问题，保障同学们的权益。
     */

    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
