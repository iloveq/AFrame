package com.woaiqw.aframe.adapter;

import android.text.TextUtils;
import android.view.View;

import com.woaiqw.adapter.BaseSmartAdapter;
import com.woaiqw.adapter.BaseViewHolder;
import com.woaiqw.aframe.R;
import com.woaiqw.aframe.bean.CardListBean;
import com.woaiqw.aframe.view.widget.RatioImageView;
import com.woaiqw.base.utils.ImageLoader;

/**
 * Created by haoran on 2018/6/28.
 */

public class CardListAdapter extends BaseSmartAdapter<CardListBean.CardBean, CardListAdapter.MyViewHolder> {


    public CardListAdapter() {
        super(R.layout.item_card_list);
    }

    @Override
    protected void convert(MyViewHolder holder, CardListBean.CardBean cardBean) {

        int position = holder.getLayoutPosition();
        holder.iv.setImageRatio(position % 2 == 0 ? 0.7f : 0.6f);
        if (!TextUtils.isEmpty(cardBean.getImgurl()))
            // 加载图片
            ImageLoader.loadImage(holder.iv, cardBean.getImgurl());

    }

    static class MyViewHolder extends BaseViewHolder {

        private RatioImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = getView(R.id.category_iv);
        }

    }

}
