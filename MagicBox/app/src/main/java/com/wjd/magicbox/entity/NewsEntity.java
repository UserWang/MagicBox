package com.wjd.magicbox.entity;

import com.android.common.api.ResponseData;

import java.util.List;

/**
 * Created by wangjundong on 2015/12/9.
 */
public class NewsEntity extends ResponseData{

    private List<DetailEntity> detail;

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public static class DetailEntity {
        private String title;
        private String source;
        private String article_url;
        private int publish_time;
        private long behot_time;
        private int create_time;
        private int digg_count;
        private int bury_count;
        private int repin_count;
        private String group_id;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public void setPublish_time(int publish_time) {
            this.publish_time = publish_time;
        }

        public void setBehot_time(long behot_time) {
            this.behot_time = behot_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getTitle() {
            return title;
        }

        public String getSource() {
            return source;
        }

        public String getArticle_url() {
            return article_url;
        }

        public int getPublish_time() {
            return publish_time;
        }

        public long getBehot_time() {
            return behot_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public int getBury_count() {
            return bury_count;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public String getGroup_id() {
            return group_id;
        }

        @Override
        public String toString() {
            return "DetailEntity{" +
                    "title='" + title + '\'' +
                    ", source='" + source + '\'' +
                    ", article_url='" + article_url + '\'' +
                    ", publish_time=" + publish_time +
                    ", behot_time=" + behot_time +
                    ", create_time=" + create_time +
                    ", digg_count=" + digg_count +
                    ", bury_count=" + bury_count +
                    ", repin_count=" + repin_count +
                    ", group_id='" + group_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "detail=" + detail +
                '}';
    }
}
