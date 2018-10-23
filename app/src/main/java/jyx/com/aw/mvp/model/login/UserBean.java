package jyx.com.aw.mvp.model.login;

/**
 * 作者：-zy- on 16/8/19
 * 邮箱：zhouyong@cdzmd.com
 * 功能：
 */

public class UserBean {
    private int RoleId; //1=维修人员，2=验房工程师
    private String HeadImg; //头像
    private String RoleName; //角色名称
    private String RealName; //真实姓名
    private String Token; //授权码
    private int RepairListCount; //我的维修单数
    private int HouseListCount; //我的验房问题数

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getRepairListCount() {
        return RepairListCount;
    }

    public void setRepairListCount(int repairListCount) {
        RepairListCount = repairListCount;
    }

    public int getHouseListCount() {
        return HouseListCount;
    }

    public void setHouseListCount(int houseListCount) {
        HouseListCount = houseListCount;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
