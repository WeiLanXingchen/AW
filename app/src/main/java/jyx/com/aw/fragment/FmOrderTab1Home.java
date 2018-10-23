package jyx.com.aw.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jyx.com.aw.R;
import jyx.com.aw.act.ActDailyKaoYanList;
import jyx.com.aw.act.ActGoodsRecommend;
import jyx.com.aw.act.ActPromotionDetail;
import jyx.com.aw.act.ActWebView;
import jyx.com.aw.adapter.HomeCenterAdapter;
import jyx.com.aw.adapter.MyGridViewAdapter;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListFragment;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.GridViewItemClickListener;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.mvp.model.Banner;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.mvp.model.SeniorRecommendation;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.FixedGridView;
import jyx.com.aw.view.activities.ActivityCollector;
import jyx.com.aw.view.calender.DateAdapter;
import jyx.com.aw.view.calender.SpecialCalendar;
import jyx.com.aw.view.percentcircle.PercentLemon;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static jyx.com.aw.mvp.model.HomeCenterItem.data;
import static jyx.com.aw.mvp.model.HomeCenterItem.initView;
import static jyx.com.aw.util.DateFormatUtil.transForDate;


/**
 * 作者：
 * 邮箱：
 * 功能：
 */

public class FmOrderTab1Home extends BaseListFragment<DailyList.ArticleBean> implements GestureDetector.OnGestureListener,
        AdapterView.OnItemClickListener,
        View.OnClickListener,
        HomeCenterClickListener ,
        GridViewItemClickListener
{
    private PercentLemon mPercentLemon;
    private ImageView mImageViewBanner;
    private TextView mTextViewDay, mTextViewTime, mTextViewSlogan, mTextViewDailyTask, mTextViewAddTask;
    private RecyclerView mRecyclerViewHomeCenter;
    private ViewFlipper flipper1 = null;
    private GridView gridView = null;
    private GestureDetector gestureDetector = null;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private int week_c = 0;
    private int week_num = 0;
    private String currentDate = "";
    private DateAdapter dateAdapter;
    private int daysOfMonth = 0;
    private int dayOfWeek = 0;
    private int weeksOfMonth = 0;
    private SpecialCalendar sc = null;
    private boolean isLeapyear = false;
    private int selectPostion = 0;
    private String dayNumbers[] = new String[7];
    //	private TextView tvDate;
    private int currentYear;
    private int currentMonth;
    private int currentWeek;
    private int currentDay;
    private int currentNum;
    private Context context;
    private HomeCenterAdapter adapter;
    private MyGridViewAdapter mMyGridViewAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public FmOrderTab1Home() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        currentYear = year_c;
        currentMonth = month_c;
        currentDay = day_c;
        sc = new SpecialCalendar();
        getCalendar(year_c, month_c);
        week_num = getWeeksOfMonth();
        currentNum = week_num;
        if (dayOfWeek == 7) {
            week_c = currentDay / 7 + 1;
        } else {
            if (currentDay <= (7 - dayOfWeek)) {
                week_c = 1;
            } else {
                if ((currentDay - (7 - dayOfWeek)) % 7 == 0) {
                    week_c = (currentDay - (7 - dayOfWeek)) / 7 + 1;
                } else {
                    week_c = (currentDay - (7 - dayOfWeek)) / 7 + 2;
                }
            }
        }
        currentWeek = week_c;
        getCurrent();
    }


    public int getWeeksOfMonth(int year, int month) {
        int preMonthRelax = 0;
        int dayFirst = getWhichDayOfWeek(year, month);
        int days = sc.getDaysOfMonth(sc.isLeapYear(year), month);
        if (dayFirst != 7) {
            preMonthRelax = dayFirst;
        }
        if ((days + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (days + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (days + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;

    }


    public int getWhichDayOfWeek(int year, int month) {
        return sc.getWeekdayOfMonth(year, month);

    }


    public int getLastDayOfWeek(int year, int month) {
        return sc.getWeekDayOfLastMonth(year, month,
                sc.getDaysOfMonth(isLeapyear, month));
    }

    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year);
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);
        dayOfWeek = sc.getWeekdayOfMonth(year, month);
    }

    public int getWeeksOfMonth() {
        // getCalendar(year, month);
        int preMonthRelax = 0;
        if (dayOfWeek != 7) {
            preMonthRelax = dayOfWeek;
        }
        if ((daysOfMonth + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        gridView = new GridView(context);
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Log.i(TAG, "day:" + dayNumbers[position]);
                selectPostion = position;
                dateAdapter.setSeclection(position);
                dateAdapter.notifyDataSetChanged();
//				tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
//						+ dateAdapter.getCurrentMonth(selectPostion) + "月"
//						+ dayNumbers[position] + "日");
            }
        });
        gridView.setLayoutParams(params);
    }


    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public void getCurrent() {
        if (currentWeek > currentNum) {
            if (currentMonth + 1 <= 12) {
                currentMonth++;
            } else {
                currentMonth = 1;
                currentYear++;
            }
            currentWeek = 1;
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
        } else if (currentWeek == currentNum) {
            if (getLastDayOfWeek(currentYear, currentMonth) == 6) {
            } else {
                if (currentMonth + 1 <= 12) {
                    currentMonth++;
                } else {
                    currentMonth = 1;
                    currentYear++;
                }
                currentWeek = 1;
                currentNum = getWeeksOfMonth(currentYear, currentMonth);
            }

        } else if (currentWeek < 1) {
            if (currentMonth - 1 >= 1) {
                currentMonth--;
            } else {
                currentMonth = 12;
                currentYear--;
            }
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
            currentWeek = currentNum - 1;
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        int gvFlag = 0;
        if (e1.getX() - e2.getX() > 80) {
            addGridView();
            currentWeek++;
            getCurrent();
            dateAdapter = new DateAdapter(context, currentYear, currentMonth,
                    currentWeek, currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
//			tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
//					+ dateAdapter.getCurrentMonth(selectPostion) + "月"
//					+ dayNumbers[selectPostion] + "日");
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            flipper1.setInAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.push_left_in));
            flipper1.setOutAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.push_left_out));
            flipper1.showNext();
            flipper1.removeViewAt(0);
            return true;
        } else if (e1.getX() - e2.getX() < -80) {
            addGridView();
            currentWeek--;
            getCurrent();
            dateAdapter = new DateAdapter(context, currentYear, currentMonth,
                    currentWeek, currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
//			tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
//					+ dateAdapter.getCurrentMonth(selectPostion) + "月"
//					+ dayNumbers[selectPostion] + "日");
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            flipper1.setInAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.push_right_in));
            flipper1.setOutAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.push_right_out));
            flipper1.showPrevious();
            flipper1.removeViewAt(0);
            return true;
        }
        return false;
    }

    private void getCalender(View view) {
        gestureDetector = new GestureDetector(this);
        flipper1 = (ViewFlipper) view.findViewById(R.id.flipper1);
        dateAdapter = new DateAdapter(context, currentYear, currentMonth, currentWeek, currentWeek == 1 ? true : false);
        addGridView();
        dayNumbers = dateAdapter.getDayNumbers();
        gridView.setAdapter(dateAdapter);
        selectPostion = dateAdapter.getTodayPosition();
        gridView.setSelection(selectPostion);
        flipper1.addView(gridView, 0);
    }

    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private boolean mShow = true;
    private int mTouchSlop;

    @Override
    protected void initViews() {
        super.initViews();
        mListView.setOnItemClickListener(this);
        initList();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void scrollListener() {
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        if (mCurrentY - mFirstY > mTouchSlop) {
                            direction = 0;// down
                            Log.i("@@@","下滑");
                        } else if (mFirstY - mCurrentY > mTouchSlop) {
                            direction = 1;// up
                            Log.i("@@@","上滑");
                        }
                        if (direction == 1) {
                            //上滑todo
//                            Log.i("@@@","上滑");
                        } else if (direction == 0) {
//                            Log.i("@@@","下滑");
                            //下滑todo
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    private void initList() {
        addSubscription(mHttpApi1.getDailyListData(Config.M_CODE, 1),
                new SubscriberCallBack<>(new ApiCallback<DailyList>() {
                    @Override
                    public void onSuccess(DailyList model) {
                        List<DailyList.ArticleBean> article = model.getArticle();
//                        Log.i("@@@", Config.BASE_URL[1] + "");
//                        Log.e("@@@", article.toString());
                        if (article != null && article.size() > 0) {
                            mAdapter.putData(article);
                            setLoadMoreEnabled(false);
                            setRefreshEnabled(false);
                            dismissLoadingView();
                        } else {
                            if (mAdapter.getCount() > 0) {
                                mAdapter.clear();
                            }
                            AppUtil.showToast("未知错误！");
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        AppUtil.showToast(msg);
                        dismissLoadingView();
                    }

                    @Override
                    public void onCompleted() {
                    }

                }));

    }

    @Override
    protected View addFooterView() {
        View footer_view = LayoutInflater.from(context).inflate(R.layout.home_footer, null);
        ImageView mImageViewSeeMore = (ImageView) footer_view.findViewById(R.id.mImageViewSeeMore);
        mImageViewSeeMore.setOnClickListener(this);
        return footer_view;
    }

    @Override
    protected View addHeaderView() {
        View header_view = LayoutInflater.from(context).inflate(R.layout.home_header, null);
        mPercentLemon = (PercentLemon) header_view.findViewById(R.id.mPercentLemon);
        mTextViewTime = (TextView) header_view.findViewById(R.id.mTextViewTime);
        mTextViewDay = (TextView) header_view.findViewById(R.id.mTextViewDay);
        mTextViewSlogan = (TextView) header_view.findViewById(R.id.mTextViewSlogan);
        mImageViewBanner = (ImageView) header_view.findViewById(R.id.mImageViewBanner);
        mRecyclerViewHomeCenter = (RecyclerView) header_view.findViewById(R.id.mRecyclerViewHomeCenter);
        mTextViewDailyTask = (TextView) header_view.findViewById(R.id.mTextViewDailyTask);
        mTextViewAddTask = (TextView) header_view.findViewById(R.id.mTextViewAddTask);
        TextView mTextViewMoreRecommend = (TextView) header_view.findViewById(R.id.mTextViewMoreRecommend);
        mTextViewMoreRecommend.setOnClickListener(this);
        FixedGridView mGridView = (FixedGridView) header_view.findViewById(R.id.mGridView);
        getBannerData();
        getCalender(header_view);
        initView(context);
        if (data != null) {
            adapter = new HomeCenterAdapter(context, data);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mRecyclerViewHomeCenter.setLayoutManager(gridLayoutManager);
            mRecyclerViewHomeCenter.setAdapter(adapter);
            adapter.setHomeCenterClickListener(this);
        }
        mMyGridViewAdapter = new MyGridViewAdapter(context, 2);
        getGridViewData();
        mGridView.setAdapter(mMyGridViewAdapter);
        mMyGridViewAdapter.setGridViewItemClickListener(this);
        return header_view;
    }

    private void getGridViewData() {
        addSubscription(mHttpApi2.getSeniorReData(Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<SeniorRecommendation>() {
            @Override
            public void onSuccess(SeniorRecommendation model) {
//                Log.i("@@@",model.toString());
                mMyGridViewAdapter.setList(model.getProducts());
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onCompleted() {

            }
        }));
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.daily_list, parent, false);
        }
        ImageView mImageViewDailyListIcon = ViewHolder.get(convertView, R.id.mImageViewDailyListIcon);
        TextView mTextViewDailyListTitle = ViewHolder.get(convertView, R.id.mTextViewDailyListTitle);
        TextView mTextViewDailyListUpdateTime = ViewHolder.get(convertView, R.id.mTextViewDailyListUpdateTime);
        TextView mTextViewDailyListViews = ViewHolder.get(convertView, R.id.mTextViewDailyListViews);
        ImageView mImageViewDailyListIconFirst = ViewHolder.get(convertView, R.id.mImageViewDailyListIconFirst);
        TextView mTextViewDailyListTitleFirst = ViewHolder.get(convertView, R.id.mTextViewDailyListTitleFirst);
        TextView mTextViewDailyListUpdateTimeFirst = ViewHolder.get(convertView, R.id.mTextViewDailyListUpdateTimeFirst);
        TextView mTextViewDailyListViewsFirst = ViewHolder.get(convertView, R.id.mTextViewDailyListViewsFirst);
        LinearLayout mLinearLayout = ViewHolder.get(convertView, R.id.mLinearLayout);
        LinearLayout mLinearLayoutFirst = ViewHolder.get(convertView, R.id.mLinearLayoutFirst);
        if (position == 0) {
            mLinearLayout.setVisibility(View.GONE);
            mLinearLayoutFirst.setVisibility(View.VISIBLE);
            DailyList.ArticleBean item = mAdapter.getItem(position);
            if (item != null) {
                Glide.with(context)
                        .load(item.getPic())
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageViewDailyListIconFirst);
                mTextViewDailyListTitleFirst.setText(item.getTitle());
                mTextViewDailyListUpdateTimeFirst.setText(transForDate(item.getPubdate()));
                mTextViewDailyListViewsFirst.setText(item.getViews() + "个浏览");
            }
        } else {
            mLinearLayoutFirst.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            DailyList.ArticleBean item = mAdapter.getItem(position);
            if (item != null) {
                Glide.with(context)
                        .load(item.getPic())
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageViewDailyListIcon);
                mTextViewDailyListTitle.setText(item.getTitle());
                mTextViewDailyListUpdateTime.setText(transForDate(item.getPubdate()));
                mTextViewDailyListViews.setText(item.getViews() + "个浏览");
            }
        }
        return convertView;
    }


    private void getBannerData() {
        addSubscription(mHttpApi.getBannerData(Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<Banner>() {
            @Override
            public void onSuccess(Banner model) {
                setBannerData(model);
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onCompleted() {

            }
        }));

    }

    private void setBannerData(Banner model) {
        mTextViewDay.setText(model.getDay() + "");
        mPercentLemon.animatToPercent(model.getPercent());
        setTime();
        Glide.with(context)
                .load(Config.BASE_HEAD_URL[0] + model.getBanner().getImage())
                .skipMemoryCache(true)
                .placeholder(R.mipmap.ic_launcher)
                .into(mImageViewBanner);
        mTextViewSlogan.setText(model.getBanner().getText());
    }

    private void setTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Calendar cal = Calendar.getInstance();
                //设置预定的时间
                cal.set(2018, 11, 23, 00, 0, 0);//0代表1月 ，11代表12月
                // 返回历元到指定时间的毫秒数。
                long longTime = cal.getTimeInMillis();
                // 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
                long currentTime = new Date().getTime();
                //距离的时间
                long distTime = longTime - currentTime;
                //得到天数
                long day = ((distTime / 1000) / (3600 * 24));
                //得到小时数
                final long hour = ((distTime / 1000) - day * 86400) / 3600;
                //得到分钟数
                final long minutes = ((distTime / 1000) - day * 86400 - hour * 3600) / 60;
                //得到秒数
                final long seconds = (distTime / 1000) - day * 86400 - hour * 3600 - minutes * 60;
//                Log.i("@@@","倒计时: "+day+"天"+hour+"小时"+ minutes+"分钟"+seconds +"秒");
                mTextViewTime.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewTime.setText(hour + ":" + minutes + ":" + seconds);
                    }
                });

            }
        }, 0, 1000);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppUtil.isFastClick()) {
            return;
        }
        DailyList.ArticleBean item = mAdapter.getItem(position - 1);
        Bundle bundle = new Bundle();
        int ID = item.getId();
        String pic = item.getPic();
//        Log.i("@@@",ID+" "+pic+" "+position);
        bundle.putInt("ID", ID);
        bundle.putString("pic", pic);
        turnToActivity(ActWebView.class, bundle, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTextViewMoreRecommend:
                turnToActivity(ActGoodsRecommend.class, false);
                break;
            case R.id.mImageViewSeeMore:
                turnToActivity(ActDailyKaoYanList.class, false);
                break;
        }

    }

    @Override
    public void itemDataListener(int position) {
//        Log.i("@@@",position+"");
        Bundle bundle = new Bundle();
        bundle.putString("title", Config.TITLE[position]);
        turnToActivity(ActivityCollector.addActivity().get(position).getClass(), bundle, false);
    }

    @Override
    public void itemGridViewClickListener(int id) {
//        Log.i("@@@",id+"");
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        turnToActivity(ActPromotionDetail.class, bundle, false);
    }
}
