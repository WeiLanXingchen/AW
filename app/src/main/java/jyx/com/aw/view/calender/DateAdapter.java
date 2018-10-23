package jyx.com.aw.view.calender;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;

import jyx.com.aw.R;

public class DateAdapter extends BaseAdapter {
	private boolean isLeapyear = false;
	private int daysOfMonth = 0;
	private int dayOfWeek = 0;
	private int lastDaysOfMonth = 0;
	private Context context;
	private SpecialCalendar sc = null;
	private String[] dayNumber = new String[7];
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

	private String sysDate = "";
	private String sys_year = "";
	private String sys_month = "";
	private String sys_day = "";
	private String currentYear = "";
	private String currentMonth = "";
	private String currentWeek = "";
	private String currentDay = "";
	private int weeksOfMonth;
	private int clickTemp = -1;
	private boolean isStart;
	private String currentDate="";
	private int year_c,month_c,day_c;

	public void setSeclection(int position) {
		clickTemp = position;
	}

	public DateAdapter() {
		Date date = new Date();
		sysDate = sdf.format(date);
		sys_year = sysDate.split("-")[0];
		sys_month = sysDate.split("-")[1];
		sys_day = sysDate.split("-")[2];
	}

	public DateAdapter(Context context, int year_c, int month_c, int week_c,
					   boolean isStart) {
		this();
		this.context = context;
		this.isStart = isStart;
		sc = new SpecialCalendar();

		currentYear = String.valueOf(year_c);
		currentMonth = String.valueOf(month_c);

		currentDay = String.valueOf(sys_day);
		getCalendar(Integer.parseInt(currentYear),
				Integer.parseInt(currentMonth));
		currentWeek = String.valueOf(week_c);
		getWeek(Integer.parseInt(currentYear), Integer.parseInt(currentMonth),
				Integer.parseInt(currentWeek));

	}

	public int getTodayPosition() {
		int todayWeek = sc.getWeekDayOfLastMonth(Integer.parseInt(sys_year),
				Integer.parseInt(sys_month), Integer.parseInt(sys_day));
		if (todayWeek == 7) {
			clickTemp = 0;
		} else {
			clickTemp = todayWeek;
		}
		return clickTemp;
	}


	public int getCurrentMonth(int position) {
		int thisDayOfWeek = sc.getWeekdayOfMonth(Integer.parseInt(currentYear),
				Integer.parseInt(currentMonth));
		if (isStart) {
			if (thisDayOfWeek != 7) {
				if (position < thisDayOfWeek) {
					return Integer.parseInt(currentMonth) - 1 == 0 ? 12
							: Integer.parseInt(currentMonth) - 1;
				} else {
					return Integer.parseInt(currentMonth);
				}
			} else {
				return Integer.parseInt(currentMonth);
			}
		} else {
			return Integer.parseInt(currentMonth);
		}

	}


	public int getCurrentYear(int position) {
		int thisDayOfWeek = sc.getWeekdayOfMonth(Integer.parseInt(currentYear),
				Integer.parseInt(currentMonth));
		if (isStart) {
			if (thisDayOfWeek != 7) {
				if (position < thisDayOfWeek) {
					return Integer.parseInt(currentMonth) - 1 == 0 ? Integer
							.parseInt(currentYear) - 1 : Integer
							.parseInt(currentYear);
				} else {
					return Integer.parseInt(currentYear);
				}
			} else {
				return Integer.parseInt(currentYear);
			}
		} else {
			return Integer.parseInt(currentYear);
		}
	}

	public void getCalendar(int year, int month) {
		isLeapyear = sc.isLeapYear(year);
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);
		dayOfWeek = sc.getWeekdayOfMonth(year, month);
		lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month - 1);
	}

	public void getWeek(int year, int month, int week) {
		for (int i = 0; i < dayNumber.length; i++) {
			if (dayOfWeek == 7) {
				dayNumber[i] = String.valueOf((i + 1) + 7 * (week - 1));
			} else {
				if (week == 1) {
					if (i < dayOfWeek) {
						dayNumber[i] = String.valueOf(lastDaysOfMonth
								- (dayOfWeek - (i + 1)));
					} else {
						dayNumber[i] = String.valueOf(i - dayOfWeek + 1);
					}
				} else {
					dayNumber[i] = String.valueOf((7 - dayOfWeek + 1 + i) + 7
							* (week - 2));
				}
			}

		}
	}

	public String[] getDayNumbers() {
		return dayNumber;
	}


	public int getWeeksOfMonth() {

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


	public void getDayInWeek(int year, int month) {

	}

	@Override
	public int getCount() {
		return dayNumber.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_calendar, null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_calendar);
			holder.tv_month = (TextView) convertView
					.findViewById(R.id.tv_month);
			holder.ll_data = (LinearLayout) convertView
					.findViewById(R.id.ll_data);
			holder.view_line = (View) convertView.findViewById(R.id.view_line);
			holder.view_dot = (View) convertView.findViewById(R.id.view_dot);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv.setText(dayNumber[position]);

		holder.tv_month.setText(getCurrentMonth(position)+".");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		currentDate = sdf.format(date);
		year_c = Integer.parseInt(currentDate.split("-")[0]);
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);
//		View inflate = LayoutInflater.from(context).inflate(R.layout.fm_order_tab1, null);
//		TextView mTextView1 = (TextView) inflate.findViewById(R.id.mWeek1);
//		TextView mTextView2 = (TextView) inflate.findViewById(R.id.mWeek2);
//		TextView mTextView3 = (TextView) inflate.findViewById(R.id.mWeek3);
//		TextView mTextView4 = (TextView) inflate.findViewById(R.id.mWeek4);
//		TextView mTextView5 = (TextView) inflate.findViewById(R.id.mWeek5);
//		TextView mTextView6 = (TextView) inflate.findViewById(R.id.mWeek6);
//		TextView mTextView7 = (TextView) inflate.findViewById(R.id.mWeek7);
		if (getCurrentMonth(position)==month_c&&dayNumber[position].equals(String.valueOf(day_c))){
			holder.view_dot.setVisibility(View.VISIBLE);
//			holder.tv.setTextColor(Color.WHITE);
//			holder.tv_month.setTextColor(Color.WHITE);
		}

		if (clickTemp == position) {
			holder.ll_data.setSelected(true);
			holder.view_line.setVisibility(View.VISIBLE);
//			holder.tv.setTextColor(Color.WHITE);
//			holder.tv_month.setTextColor(Color.WHITE);
		} else {
			holder.ll_data.setSelected(false);
			holder.view_line.setVisibility(View.INVISIBLE);
//			holder.tv.setTextColor(Color.GRAY);
//			holder.tv_month.setTextColor(Color.GRAY);
		}
		return convertView;
	}

	class Holder {
		LinearLayout ll_data;
		TextView tv;
		TextView tv_month;
		View view_line,view_dot;
	}
}
