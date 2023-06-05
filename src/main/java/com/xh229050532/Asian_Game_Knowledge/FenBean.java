package com.xh229050532.Asian_Game_Knowledge;

import java.util.ArrayList;
import java.util.List;

//表示答题的得分记录
public class FenBean {
    //用FenBean对象记录下得分记录列表
    private List<FenBean2> list = new ArrayList<>();

    //表示用户一次答题的得分和时间记录
    public static class FenBean2 {

        //得分
        private int fen;
        //记录时间
        private long time;

        //获取得分
        public int getFen() {
            return fen;
        }

        //设置得分
        public void setFen(int fen) {
            this.fen = fen;
        }

        //获取记录时间
        public long getTime() {
            return time;
        }

        //设置记录时间
        public void setTime(long time) {
            this.time = time;
        }
    }

    //获取得分记录列表
    public List<FenBean2> getList() {
        return list;
    }

    //获取得分记录列表
    public void setList(List<FenBean2> list) {
        this.list = list;
    }
}
