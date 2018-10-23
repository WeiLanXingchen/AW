package jyx.com.aw.adapter;

/**
 * Created by JiangYunxian on 2017/12/14 0014.
 * 功能：
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.impl.OnBuyInformationInnerClickListener;
import jyx.com.aw.impl.OnBuyOfSchoolListener;
import jyx.com.aw.mvp.model.BuyInformation;
import jyx.com.aw.mvp.model.BuyInformationHeader;

/**
 * Created by
 */
public class HeaderBottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount = 1;//头部View个数
    private int mBottomCount = 1;//底部View个数
    private List<BuyInformation.SchoolsBean> data;
    private BuyInformation.OtoLitpicBean oto_litpic;
    private BuyInformation.PublicLitpicBean public_litpic;
    private BuyInformationHeader header;
    private final int HOME_AD_RESULT = 1;
    private int mSwitcherCount=0;
    private List<String> buy_record;
    private TextSwitcher tv_notice;
    private boolean flag=true;
    private OnBuyInformationInnerClickListener onBuyInformationInnerClickListener;
    private OnBuyOfSchoolListener onBuyOfSchoolListener;
    private Handler  mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    tv_notice.setText(buy_record.get(mSwitcherCount % buy_record.size()));
                    mSwitcherCount++;
                    mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
                    break;
            }

        }
    };
    public void setBuyOfSchoolClickListener(OnBuyOfSchoolListener onBuyOfSchoolListener) {
        this.onBuyOfSchoolListener = onBuyOfSchoolListener;
    }
    public void setOnBuyInformationInnerClickListener(OnBuyInformationInnerClickListener onBuyInformationInnerClickListener){
       this.onBuyInformationInnerClickListener=onBuyInformationInnerClickListener;
   }
    public HeaderBottomAdapter(Context context, List<BuyInformation.SchoolsBean> data,BuyInformation.OtoLitpicBean oto_litpic,
                               BuyInformation.PublicLitpicBean public_litpic,BuyInformationHeader header) {
        mContext = context;
        this.data = data;
        this.oto_litpic=oto_litpic;
        this.public_litpic=public_litpic;
        this.header=header;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //内容长度
    public int getContentItemCount() {
        return data.size();
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewHomeCenterName;
        private ImageView mImageViewHomeCenterIcon;
        public ContentViewHolder(View itemView) {
            super(itemView);
            mImageViewHomeCenterIcon = (ImageView) itemView.findViewById(R.id.mImageViewHomeCenterIcon);
            mTextViewHomeCenterName = (TextView) itemView.findViewById(R.id.mTextViewHomeCenterName);
        }
    }

    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewHeaderOto;
        private ImageView mImageViewHeaderGeneral;
        private ImageView mImageViewHeaderAboutUs;
        private ImageView mImageViewHeaderNotice;
        private TextSwitcher tv_notice;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            mImageViewHeaderOto= (ImageView) itemView.findViewById(R.id.mImageViewHeaderOto);
            mImageViewHeaderGeneral= (ImageView) itemView.findViewById(R.id.mImageViewHeaderGeneral);
            mImageViewHeaderAboutUs= (ImageView) itemView.findViewById(R.id.mImageViewHeaderAboutUs);
            mImageViewHeaderNotice= (ImageView) itemView.findViewById(R.id.mImageViewHeaderNotice);
            tv_notice= (TextSwitcher) itemView.findViewById(R.id.tv_notice);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.buy_information_header, parent, false));
        } else if (viewType == mHeaderCount) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.buy_information, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.buy_information_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            Glide.with(mContext)
                    .load(header.getBanner().get(0).getImage())
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((HeaderViewHolder) holder).mImageViewHeaderOto);
            Glide.with(mContext)
                    .load(public_litpic.getLitpic())
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((HeaderViewHolder) holder).mImageViewHeaderGeneral);
            Glide.with(mContext)
                    .load(header.getBanner().get(1).getImage())
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((HeaderViewHolder) holder).mImageViewHeaderAboutUs);
            Glide.with(mContext)
                    .load(header.getBanner().get(2).getImage())
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((HeaderViewHolder) holder).mImageViewHeaderNotice);
            ((HeaderViewHolder) holder).mImageViewHeaderNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onBuyInformationInnerClickListener.onBuyInformationInner(header.getBanner().get(2).getInner_image()
                             ,header.getBanner().get(2).getTitle(),position);
                }
            });
            ((HeaderViewHolder) holder).mImageViewHeaderGeneral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onBuyInformationInnerClickListener.onBuyInformationInner("","",1);
                }
            });
            ((HeaderViewHolder) holder).mImageViewHeaderAboutUs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onBuyInformationInnerClickListener.onBuyInformationInner(header.getBanner().get(1).getInner_image()
                     ,header.getBanner().get(1).getTitle(),position);
                }
            });
            ((HeaderViewHolder) holder).mImageViewHeaderOto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onBuyInformationInnerClickListener.onBuyInformationInner(header.getBanner().get(0).getInner_image()
                             ,header.getBanner().get(0).getTitle(),position);
                }
            });
            buy_record = header.getBuy_record();
            tv_notice=((HeaderViewHolder) holder).tv_notice;
            getBanner(tv_notice);
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).mTextViewHomeCenterName.setText(data.get(position - mHeaderCount).getName());
            Glide.with(mContext)
                    .load(data.get(position - mHeaderCount).getLitpic())
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((ContentViewHolder) holder).mImageViewHomeCenterIcon);
            ((ContentViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBuyOfSchoolListener!=null){
                        onBuyOfSchoolListener.buyOfSchoolClickListener(data.get(position - mHeaderCount).getId(),data.get(position - mHeaderCount).getName());
                    }
                }
            });
        } else if (holder instanceof BottomViewHolder) {

        }
    }

    private void getBanner(TextSwitcher tv_notice) {
            if (flag==true){//flag标志只能创建一次ViewFactory
                tv_notice.setFactory(new ViewSwitcher.ViewFactory() {//注：这里只能创建一次，多次会报错
                    // 这里用来创建内部的视图，这里创建TextView，用来显示文字
                    public View makeView() {
                        TextView tv = new TextView(mContext);
                        // 设置文字的显示单位以及文字的大小
//                    tv.setTextSize(13);
//                    tv.setTextColor(R.);
                        return tv;
                    }
                });
                tv_notice.setInAnimation(mContext, R.anim.enter_bottom);
                tv_notice.setOutAnimation(mContext, R.anim.leave_top);
                mHandler.sendEmptyMessage(HOME_AD_RESULT);
                flag=false;
            }
    }


    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }
}


