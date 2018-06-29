package com.woaiqw.aframe.bean;

import java.util.List;

/**
 * Created by haoran on 2018/6/28.
 */

public class CardListBean {


    private List<CardBean> cardList;

    public List<CardBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardBean> cardList) {
        this.cardList = cardList;
    }

    public static class CardBean {
        /**
         * _id : 5b29bca778747507fc1fb64c
         * username : woaiqw
         * content : 我们都曾付出真心，以不同的方式。我们只是当时理解不了彼此，谁也不欠谁，爱情无需缅怀。你是那些年月里最烈的酒，我是真的认真醉过。
         * imgurl : http://localhost:3001/images/1529461927753_.jpg
         * like : 523
         * share : 0
         * isLike : true
         */

        private String _id;
        private String username;
        private String content;
        private String imgurl;
        private int like;
        private int share;
        private boolean isLike;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }
    }
}
