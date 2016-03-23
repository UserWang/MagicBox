package com.wjd.magicbox.entity;

import com.android.common.api.ResponseData;

import java.util.List;

/**
 * 笑话段子实体类
 * Created by wangjundong on 2015/12/8.
 */
public class JokeEntity extends ResponseData {

    private List<DetailEntity> detail;

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public static class DetailEntity {
        private int id;
        private int xhid;
        private String author;
        private String content;
        private String picUrl;
        private String status;

        public void setId(int id) {
            this.id = id;
        }

        public void setXhid(int xhid) {
            this.xhid = xhid;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public int getXhid() {
            return xhid;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "DetailEntity{" +
                    "id=" + id +
                    ", xhid=" + xhid +
                    ", author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JokeEntity{" +
                "detail=" + detail +
                '}';
    }
}
