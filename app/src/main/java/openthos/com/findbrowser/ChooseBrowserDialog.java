//package openthos.com.findbrowser;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.BaseAdapter;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by ln on 18-3-21.
// */
//
//public class ChooseBrowserDialog extends Dialog {
//    private Context mContext;
//    private TextView mTvBrowserShow;
//    private ListView mListView;
//    private List<ResolveInfo> mBrowsersList;
//    private PackageManager mPackageManager;
//    private ResolveAdapter mAdapter;
//
//    public ChooseBrowserDialog(@NonNull Context context) {
//        super(context);
//        mContext = context;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTvBrowserShow = findViewById(R.id.browser_show);
//        mListView = findViewById(R.id.lv_browser);
////        mTvBrowserShow.setOnClickListener(new ClickListener());
////
////        File file = new File("");
////        File[] files = file.listFiles();
////        List<File> files1 = Arrays.asList(files);
////
////        String s = "ssssss";
////        s.replace("s", "");
////        initData();
//
//
//    }
//
//    @SuppressLint("WrongConstant")
//    private void initData() {
//        mBrowsersList = new ArrayList();
//        mAdapter = new ResolveAdapter();
//        mListView.setAdapter(mAdapter);
//        mPackageManager = mContext.getPackageManager();
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.BROWSABLE");
//        Uri uri = Uri.parse("https://");
//        intent.setData(uri);
//
//        List<ResolveInfo> list = mPackageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
//        for (int i = 0; i < list.size(); i++) {
//            ActivityInfo info = list.get(i).activityInfo;
//            Log.d("vvvvvvvvvv", "-----------" + info.packageName + "------" + info.name + "---");
//        }
//        mBrowsersList = list;
//    }
//
//    private class ClickListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.browser_show:
//                    if (mListView.getVisibility() == View.VISIBLE) {
//                        mListView.setVisibility(View.GONE);
//                    } else {
//                        mListView.setVisibility(View.VISIBLE);
//                    }
//                    break;
//            }
//        }
//    }
//
//    private class ResolveAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return mBrowsersList.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return mBrowsersList.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            ViewHolder holder;
//            View convertView = view;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(mContext).
//                        inflate(R.layout.list_item, viewGroup, false);
//                holder = new ViewHolder(convertView);
//                convertView.setTag(holder);
//            }
//            holder = (ViewHolder) convertView.getTag();
//            holder.text.setText(mBrowsersList.get(i).loadLabel(mPackageManager));
//            holder.image.setImageDrawable(mBrowsersList.get(i).loadIcon(mPackageManager));
//            holder.image.setTag(0, "");
//            return convertView;
//        }
//    }
//
//    private void s(List<String> s) {
//
//    }
//
//    private class ViewHolder {
//        public TextView text;
//        public ImageView image;
//        public CheckBox check;
//
//        public ViewHolder(View view) {
//            text = (TextView) view.findViewById(R.id.tv_list_item);
//            image = (ImageView) view.findViewById(R.id.iv_list_item);
//            check = view.findViewById(R.id.cb_list_item);
//        }
//    }
//
//    public void showDialog() {
//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.dimAmount = 0.0f;
//        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        show();
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setAttributes(lp);
//    }
//}