package com.xh229050532.Asian_Game_Knowledge;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public static List<TiBean> ti = new ArrayList<>();
    public static List<FenBean> fens = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        addTi();
//        创建问题
        TiBean tiBean = new TiBean();
//        题号
        tiBean.setId(1);
//        答案解析
        tiBean.setInfo("杭州是互联网之城");
//        题目
        tiBean.setTi("杭州亚运会的口号是");
//        选项
        tiBean.setDaan(new String[]{"A.心心相融，@未来", "B.心相约，梦闪耀", "C.亚洲能量", "D.多元化闪耀于此"});
//        正确选项
        tiBean.setDui(1);
//        添加到题库
        ti.add(tiBean);

        TiBean tiBean2 = new TiBean();
        tiBean2.setId(2);
        tiBean2.setInfo("以钱江潮为灵感来源");
        tiBean2.setTi("杭州亚运会会徽的名字是");
        tiBean2.setDaan(new String[]{"A.潮涌", "B.向前", "C.向后", "D.钱潮"});
        tiBean2.setDui(1);
        ti.add(tiBean2);
        TiBean tiBean3 = new TiBean();
        tiBean3.setId(3);
        tiBean3.setInfo("随着科技发展，游戏逐渐成为第九艺术");
        tiBean3.setTi("下列哪个项目是杭州亚运会新纳入的正式比赛项目");
        tiBean3.setDaan(new String[]{"A.电子竞技", "B.卡巴迪", "C.水上摩托", "D.藤球"});
        tiBean3.setDui(1);
        ti.add(tiBean3);
        TiBean tiBean4 = new TiBean();
        tiBean4.setId(4);
        tiBean4.setInfo("上海还没有举办过亚运会");
        tiBean4.setTi("下列没有举办过亚运会的城市是哪座");
        tiBean4.setDaan(new String[]{"A.北京", "B. 杭州", "C.上海", "D.广州"});
        tiBean4.setDui(3);
        ti.add(tiBean4);
        TiBean tiBean5 = new TiBean();
        tiBean5.setId(5);
        tiBean5.setInfo("是第四届亚残运会哦");
        tiBean5.setTi("杭州是第几届亚残运会的举办城市");
        tiBean5.setDaan(new String[]{"A.3", "B.1", "C.4", "D.5"});
        tiBean5.setDui(3);
        ti.add(tiBean5);
        TiBean tiBean6 = new TiBean();
        tiBean6.setId(6);
        tiBean6.setInfo("吉祥物灵感来源于西湖、拱宸桥、良渚玉琮");
        tiBean6.setTi("下面哪个不是杭州亚运会吉祥物的名字");
        tiBean6.setDaan(new String[]{"A.琮琮", "B.飞飞", "C.莲莲", "D.宸宸"});
        tiBean6.setDui(2);
        ti.add(tiBean6);
        TiBean tiBean7 = new TiBean();
        tiBean7.setId(7);
        tiBean7.setInfo("西湖的荷叶是杭州的象征之一哦");
        tiBean7.setTi("我们称杭州亚运会志愿者为（）");
        tiBean7.setDaan(new String[]{"A.小青柠", "B.小可爱", "C.小信工", "D.小青荷"});
        tiBean7.setDui(4);
        ti.add(tiBean7);
        TiBean tiBean8 = new TiBean();
        tiBean8.setId(8);
        tiBean8.setInfo("飞飞中还有i，表示互联网之城");
        tiBean8.setTi("杭州亚残运会吉祥物“飞飞”设计灵感来源于（）");
        tiBean8.setDaan(new String[]{"A.白蛇传", "B.金牛出水", "C.良渚文化神鸟", "D.凤凰山"});
        tiBean8.setDui(3);
        ti.add(tiBean8);
        TiBean tiBean9 = new TiBean();
        tiBean9.setId(9);
        tiBean9.setInfo("杭州亚运会为期16天");
        tiBean9.setTi("杭州亚运会在2023举办时间是什么时候");
        tiBean9.setDaan(new String[]{"A.2023.9.10-25", "B.2023.10.10-25", "C.2023.8.23-9.8", "D.2023.9.23-10.8"});
        tiBean9.setDui(4);
        ti.add(tiBean9);
        TiBean tiBean10 = new TiBean();
        tiBean10.setId(10);
        tiBean10.setInfo("亚组委的标识可以在杭州亚运会会徽右上角看见，是一个太阳");
        tiBean10.setTi("亚组委标识是什么");
        tiBean10.setDaan(new String[]{"A.龙", "B.大陆", "C.太阳", "D.月亮"});
        tiBean10.setDui(3);
        ti.add(tiBean10);
        TiBean tiBean11 = new TiBean();
        tiBean11.setId(11);
        tiBean11.setInfo("啊吧啊吧");
        tiBean11.setTi("凑数题阿巴阿巴");
        tiBean11.setDaan(new String[]{"A.选我", "B.阿巴", "C.阿巴", "D.阿巴"});
        tiBean11.setDui(1);
        ti.add(tiBean11);
        TiBean tiBean12 = new TiBean();
        tiBean12.setId(12);
        tiBean12.setInfo("你选对了吗");
        tiBean12.setTi("阿巴阿巴阿巴");
        tiBean12.setDaan(new String[]{"A.选我...吗？", "B.钝角", "C.尼古丁·真", "D.肯定选我"});
        tiBean12.setDui(4);
        ti.add(tiBean12);
        TiBean tiBean13 = new TiBean();
        tiBean13.setId(13);
        tiBean13.setInfo("维新派才是最厉害的");
        tiBean13.setTi("谁是理塘王，理塘王会关注亚运会吗");
        tiBean13.setDaan(new String[]{"A.芙蓉王·源", "B.大笨钟·229050841·方阳", "C.尼古丁真", "D.低分低分低分"});
        tiBean13.setDui(3);
        ti.add(tiBean13);
    }

    private void addTi() {

    }

    public void setQuestion(String string) {
    }

    public void setOption1(String string) {
    }

    public void setOption2(String string) {
    }

    public void setOption3(String string) {
    }

    public void setAnswerNr(int anInt) {
    }

    public String getQuestion() {
        return null;
    }

    public String getOption1() {
        return null;
    }

    public String getOption2() {
        return null;
    }

    public String getOption3() {
        return null;
    }

    public int getAnswerNr() {
        return 0;
    }
}
