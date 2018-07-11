package com.woaiqw.aframe.adapter;

import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.woaiqw.adapter.BaseSmartAdapter;
import com.woaiqw.adapter.BaseViewHolder;
import com.woaiqw.aframe.R;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.view.widget.RatioImageView;

/**
 * Created by haoran on 2018/6/28.
 */

public class CardListAdapter extends BaseSmartAdapter<CardListBean.CardBean, CardListAdapter.MyViewHolder> {


    public CardListAdapter() {
        super(R.layout.item_category_list);
    }

    @Override
    protected void convert(MyViewHolder myViewHolder, CardListBean.CardBean cardBean) {
        int position = myViewHolder.getLayoutPosition();
        if ( position% 2 == 0) {
            myViewHolder.getImage().setImageRatio(0.7f);
        } else {
            myViewHolder.getImage().setImageRatio(0.6f);
        }
        // 图片
        if (!TextUtils.isEmpty(cardBean.getImgurl())) {
            Glide.with(myViewHolder.getImage().getContext()).load(cardBean.getImgurl()).into(myViewHolder.getImage());
        }
    }

    static class MyViewHolder extends BaseViewHolder {
        private RatioImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.category_iv);
        }

        private RatioImageView getImage() {
            return iv;
        }
    }
}
