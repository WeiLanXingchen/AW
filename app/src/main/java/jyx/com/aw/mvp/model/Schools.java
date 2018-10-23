package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/11/15 0015.
 * 功能：
 */

public class Schools {

    /**
     * code : 200
     * schools : [{"province":"江苏","colleges":[{"id":"19","name":"南京大学"},{"id":"49","name":"南京航空航天大学"},{"id":"32","name":"南京理工大学"},{"id":"31","name":"河海大学"},{"id":"22","name":"南京师范大学"},{"id":1377,"name":"中国矿业大学"},{"id":"1378","name":"江南大学"},{"id":1379,"name":"中国药科大学"},{"id":24,"name":"南京农业大学"},{"id":23,"name":"苏州大学"},{"id":"20","name":"东南大学"}]},{"province":"上海","colleges":[{"id":"26","name":"复旦大学"},{"id":25,"name":"上海交通大学"},{"id":1353,"name":"东华大学"},{"id":"28","name":"华东师范大学"},{"id":"30","name":"华东政法大学"},{"id":"43","name":"上海外国语大学"},{"id":29,"name":"上海财经大学"},{"id":27,"name":"同济大学"},{"id":"1348","name":"华东理工大学"},{"id":"42","name":"上海大学"}]},{"province":"北京","colleges":[{"id":"46","name":"北京师范大学"},{"id":41,"name":"中国人民大学"},{"id":"48","name":"北京交通大学"},{"id":40,"name":"中国传媒大学"},{"id":"1350","name":"北京外国语大学"},{"id":"1351","name":"北京工业大学"},{"id":"47","name":"北京理工大学"},{"id":"1345","name":"北京化工大学"},{"id":"1356","name":"北京邮电大学"},{"id":"1349","name":"北京林业大学"},{"id":"33","name":"对外经济贸易大学"},{"id":1358,"name":"中央音乐学院"},{"id":1361,"name":"华北电力大学"},{"id":2347,"name":"中国科学院大学"},{"id":1363,"name":"中央民族大学"},{"id":1364,"name":"中央财经大学"},{"id":"1354","name":"北京体育大学"},{"id":1377,"name":"中国矿业大学"},{"id":"1355","name":"北京大学"},{"id":"1365","name":"北京中医药大学"},{"id":"1357","name":"北京科技大学"},{"id":"1347","name":"北京航空航天大学"},{"id":1343,"name":"清华大学"},{"id":34,"name":"中国政法大学"},{"id":1346,"name":"中国农业大学"}]},{"province":"天津","colleges":[{"id":"44","name":"南开大学"},{"id":45,"name":"天津大学"},{"id":"1360","name":"天津医科大学"}]},{"province":"重庆","colleges":[{"id":1362,"name":"西南大学"},{"id":56,"name":"重庆大学"}]},{"province":"福建","colleges":[{"id":1383,"name":"厦门大学"},{"id":1384,"name":"福州大学"}]},{"province":"湖北","colleges":[{"id":51,"name":"华中师范大学"},{"id":"37","name":"华中科技大学"},{"id":"53","name":"中南财经政法大学"},{"id":38,"name":"武汉大学"},{"id":"1392","name":"武汉理工大学"},{"id":1390,"name":"华中农业大学"},{"id":1391,"name":"中国地质大学(武汉)"}]},{"province":"河北","colleges":[{"id":1344,"name":"河北工业大学"},{"id":1361,"name":"华北电力大学"}]},{"province":"山西","colleges":[{"id":"1366","name":"太原理工大学"}]},{"province":"内蒙古","colleges":[{"id":1367,"name":"内蒙古大学"}]},{"province":"辽宁","colleges":[{"id":"1368","name":"东北大学"},{"id":1369,"name":"辽宁大学"},{"id":57,"name":"大连理工大学"},{"id":"1370","name":"大连海事大学"}]},{"province":"吉林","colleges":[{"id":"39","name":"吉林大学"},{"id":"1371","name":"东北师范大学"},{"id":1372,"name":"延边大学"}]},{"province":"黑龙江","colleges":[{"id":"1373","name":"哈尔滨工业大学"},{"id":1376,"name":"东北林业大学"},{"id":1374,"name":"哈尔滨工程大学"},{"id":"1375","name":"东北农业大学"}]},{"province":"浙江","colleges":[{"id":18,"name":"浙江大学"}]},{"province":"安徽","colleges":[{"id":1380,"name":"中国科学技术大学"},{"id":"1381","name":"安徽大学"},{"id":1382,"name":"合肥工业大学"}]},{"province":"江西","colleges":[{"id":1385,"name":"南昌大学"}]},{"province":"山东","colleges":[{"id":1386,"name":"山东大学"},{"id":1387,"name":"中国海洋大学"},{"id":"1388","name":"中国石油大学(华东)"}]},{"province":"河南","colleges":[{"id":1389,"name":"郑州大学"}]},{"province":"湖南","colleges":[{"id":1393,"name":"湖南大学"},{"id":54,"name":"中南大学"}]},{"province":"广东","colleges":[{"id":1395,"name":"华南理工大学"},{"id":35,"name":"暨南大学"},{"id":36,"name":"中山大学"},{"id":"50","name":"华南师范大学"},{"id":55,"name":"深圳大学"}]},{"province":"广西","colleges":[{"id":"1396","name":"广西大学"}]},{"province":"海南","colleges":[]},{"province":"四川","colleges":[{"id":1398,"name":"四川农业大学"},{"id":"52","name":"西南交通大学"},{"id":"1399","name":"西南财经大学"},{"id":"1397","name":"电子科技大学"},{"id":21,"name":"四川大学"}]},{"province":"贵州","colleges":[{"id":"1401","name":"贵州大学"}]},{"province":"云南","colleges":[{"id":1400,"name":"云南大学"}]},{"province":"西藏","colleges":[]},{"province":"陕西","colleges":[{"id":1402,"name":"西北大学"},{"id":1404,"name":"西安交通大学"},{"id":1406,"name":"西北工业大学"},{"id":1408,"name":"西安电子科技大学"},{"id":1405,"name":"西北农林科技大学"},{"id":1407,"name":"陕西师范大学"},{"id":"1403","name":"长安大学"}]},{"province":"甘肃","colleges":[{"id":1409,"name":"兰州大学"}]},{"province":"青海","colleges":[]},{"province":"宁夏","colleges":[]},{"province":"新疆","colleges":[{"id":1410,"name":"新疆大学"}]}]
     */

    private int code;
    private List<SchoolsBean> schools;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<SchoolsBean> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolsBean> schools) {
        this.schools = schools;
    }

    public static class SchoolsBean {
        /**
         * province : 江苏
         * colleges : [{"id":"19","name":"南京大学"},{"id":"49","name":"南京航空航天大学"},{"id":"32","name":"南京理工大学"},{"id":"31","name":"河海大学"},{"id":"22","name":"南京师范大学"},{"id":1377,"name":"中国矿业大学"},{"id":"1378","name":"江南大学"},{"id":1379,"name":"中国药科大学"},{"id":24,"name":"南京农业大学"},{"id":23,"name":"苏州大学"},{"id":"20","name":"东南大学"}]
         */

        private String province;
        private List<CollegesBean> colleges;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<CollegesBean> getColleges() {
            return colleges;
        }

        public void setColleges(List<CollegesBean> colleges) {
            this.colleges = colleges;
        }

        public static class CollegesBean {
            /**
             * id : 19
             * name : 南京大学
             */

            private String id;
            private String name;
            private boolean isCheck;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
