package openthos.com.findbrowser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.provider.DocumentFile;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView mTvBrowserShow;
    private ListView mListView;
    private List<ResolveInfo> mBrowsersList;
    private List<ResolveInfo> mImportBrowsers;
    private PackageManager mPackageManager;
    private ResolveAdapter mAdapter;
    private List<String> mSelectedBrowser;
    private Switch aSwitch;
    private Runtime runtime = Runtime.getRuntime();

    private ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SeafileThread thread = new SeafileThread();
        thread.start();
    }

    class SeafileThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                String token = Utils.getToken(MainActivity.this, "test@openthos.org", "test");
                Log.d("nnnnnn", "---------------------token = " + token);
                String result = Utils.getResult(token);
                Log.d("nnnnnn", "---------------------result = " + result);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPackageManager = getPackageManager();

        String[] strings = Utils.listFiles("/data/data/");
        for (String s :
                strings) {
            Log.d("ccccccc", "-----------" + s);
        }

        chownFile("/data/data/org.videolan.vlc", 10099);

        File file = new File("/data/sea", "seafile001@163.com" + "/" + "UserConfig");
        Log.d("kkkkkk", "-------------------" + file.getParent());
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"su", "-c", "mkdir -m 777 -p " + file.getAbsolutePath()});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                Log.d("kkkkkk", "-------------------" + line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ApplicationInfo infoo = mPackageManager.getApplicationInfo("", PackageManager.GET_ACTIVITIES);
            int uid = infoo.uid;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        List<PackageInfo> packages = mPackageManager.getInstalledPackages(0);
//        for (int i = 0; i < packages.size(); i++) {
//            Log.d("aaaaaaa", "-----------------" + i + "---" + packages.get(i).applicationInfo.className + "---" + packages.get(i).packageName);
//            if (packages.get(i).packageName.equals("org.videolan.vlc")) {
//                packages.get(i).applicationInfo.uid = Integer.parseInt("10030");
//            }
//        }

        try {
            int i = 10088;
//            Runtime.getRuntime().exec(new String[]{"su", "-c", "chown -R " + i + ":" + i + " /data/data/org.videolan.vlc"});
            Runtime.getRuntime().exec(new String[]{"su", "-c", "tar -xzvpf /data/sea/data/seafile001@163.com/UserConfig/browser/org.mozilla.fennec_openthos.tar.gz && chown -R " + i + ":" + i + " /data/data/org.mozilla.fennec_openthos"});
            int uid = mPackageManager.getApplicationInfo("org.mozilla.fennec_openthos", 0).uid;
            Log.d("bbbbbbbbbb", "---------------------------------------------------------" + uid);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            Runtime.getRuntime().exec(new String[]{"su", "-c", "chmod 777 /data/system/packages.list"});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileInputStream inputStream;
//        try {
//            inputStream = new FileInputStream("/data/system/packages.list");
//            byte temp[] = new byte[1024];
//            StringBuilder sb = new StringBuilder("");
//            int len = 0;
//            while ((len = inputStream.read(temp)) > 0){
//                sb.append(new String(temp, 0, len));
//            }
//            Log.d("msg", "readSaveFile: \n" + sb.toString());
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

//        Intent intent = new Intent(Intent.ACTION_MAIN, null);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        List<ResolveInfo> resolveInfos = mPackageManager.queryIntentActivities(intent, 0);
//        for (int i = 0; i < resolveInfos.size(); i++) {
//            Log.d("bbbbbbbb", "-----------------" + i + "---" + resolveInfos.get(i).activityInfo.name + "---" + resolveInfos.get(i).activityInfo.packageName);
//            List<ResolveInfo> b = null;
//            resolveInfos.removeAll(b);
//        }
//
//        try {
//            ApplicationInfo info = mPackageManager.getApplicationInfo("packageName", 0);
//            info.loadLabel(mPackageManager);
//            info.loadIcon(mPackageManager);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 查询uid
//        try {
//            PackageManager pm = getPackageManager();
//            @SuppressLint("WrongConstant") ApplicationInfo ai = pm.getApplicationInfo("com.speedsoftware.rootexplorer", PackageManager.GET_ACTIVITIES);
//            Log.d("!!", "!!" + ai.uid);
//            Toast.makeText(MainActivity.this, Integer.toString(ai.uid,10), Toast.LENGTH_SHORT).show();
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
    }**/

    public static void chownFile(String path, int uid) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        Log.d("ppppppp", "-----------------" + path + "---" + uid);
        try {
            File f = new File(path);
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("su");//Root
            DataOutputStream dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes("chown -R " + uid + ":" + uid + " " + path + "\n");
            dos.writeBytes("exit\n");
            dos.flush();
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mmmmmmmm", "------------------------" + resultCode);
        Uri treeUri = data.getData();
        DocumentFile pickedDir = DocumentFile.fromTreeUri(this, treeUri);

        for (DocumentFile file : pickedDir.listFiles()) {
            Log.d("mmmmmmmm", "------------------------" + file.getName() + "---" + file.length());
        }

        getContentResolver().takePersistableUriPermission(Uri.fromFile(new File("/data/sea/data/seafile001@163.com/DATA/")),
                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        try {
//            Runtime.getRuntime().exec(new String[]{"su", "-c", "mkdir -p /data/sea/data/mysea"}, new String[]{"su", "-c", "chmod 777 /data/sea/data/mysea"});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ContentResolver resolver = getContentResolver();
//        ContentValues values = new ContentValues();
//
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        startActivityForResult(intent, 22);

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivity(intent);
//    }


//        new ChooseBrowserDialog(this).showDialog();
//        mTvBrowserShow = findViewById(R.id.browser_show);
//        mListView = findViewById(R.id.lv_browser);
//        mTvBrowserShow.setOnClickListener(this);
//        mTvBrowserShow.setEnabled(true);
//
//        aSwitch = findViewById(R.id.switch_s);
//        aSwitch.setOnCheckedChangeListener(this);
//
//        initData();
//        importBrowsers();
//        String[] s = new String[]{};
//        s[0] = "";
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        mPackageManager = getPackageManager();
//        try {
//            PackageInfo packageInfo = mPackageManager.getPackageInfo("com.UCMobile", PackageManager.GET_SIGNATURES);
//            Log.d("ggggggggggg", "-------------" + packageInfo.activities[0].name);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        mTvBrowserShow.setBackgroundColor(Color.WHITE);
//
//        List<String> strings = Arrays.asList(s);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        String[] items = new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};
//        final boolean[] selectedItems = new boolean[]{false, false, false, false, false, false, false, false};
//        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                selectedItems[which] = !selectedItems[which];
//
//                if (which == 5) {
//                    showBrowsers();
//                }
//
//            }
//        });
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        builder.setCancelable(false);
//        builder.create().show();
//        exec(new String[]{"su", "-c",
//                "tar -czpf /sdcard/com.openthos.tar.gz /data/data/org.mozilla.fennec_openthos"});
////                "tar -czpf /data/sea/data/seafile001@163.com/UserConfig/browser/org.mozilla.fennec_openthos.tar.gz /data/data/org.mozilla.fennec_openthos"});
//        exec("tar -czpf /sdcard/com.openthos.tar.gz /data/data/org.mozilla.fennec_openthos");
//}

    public static String exec(String cmd) {
        try {
            if (cmd != null) {
                Runtime rt = Runtime.getRuntime();
                Process process = rt.exec("su");//Root   //Process process = rt.exec("sh");//
                DataOutputStream dos = new DataOutputStream(process.getOutputStream());
                dos.writeBytes(cmd + "\n");
                dos.flush();
                dos.writeBytes("exit\n");
                dos.flush();
                InputStream myin = process.getInputStream();
                InputStreamReader is = new InputStreamReader(myin);
                char[] buffer = new char[1024];
                int bytes_read = is.read(buffer);
                StringBuffer aOutputBuffer = new StringBuffer();
                while (bytes_read > 0) {
                    aOutputBuffer.append(buffer, 0, bytes_read);
                    bytes_read = is.read(buffer);
                }
                return aOutputBuffer.toString();
            } else {
                return "please input true cmd";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "operater err";
        }
    }

    public static void exec(String[] commands) {
        Process pro;
        BufferedReader in = null;
        try {
            pro = Runtime.getRuntime().exec(commands);
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                Log.d("eeeeeeeeeee", "--------" + line);
                if (line.contains("Started: seafile daemon")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showBrowsers() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = new String[]{"a", "b"};
        boolean[] selectedItems = new boolean[]{true, true};
        builder.setMultiChoiceItems(items, selectedItems, null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @SuppressLint("WrongConstant")
    private void initData() {
        mSelectedBrowser = new ArrayList<>();
        mBrowsersList = new ArrayList();
        mImportBrowsers = new ArrayList<>();
        mAdapter = new ResolveAdapter();
        mListView.setAdapter(mAdapter);
        mPackageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        Uri uri = Uri.parse("https://");
        intent.setData(uri);

        List<ResolveInfo> list = mPackageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
        for (int i = 0; i < list.size(); i++) {
            ActivityInfo info = list.get(i).activityInfo;
            Log.d("vvvvvvvvvv", "-----------" + info.packageName + "------" + info.name + "---");
        }
        mBrowsersList = list;

        mListView.setOnItemClickListener(this);
    }

    private void setListViewHeight() {
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        if (mAdapter.getCount() > 0) {
            View item = mAdapter.getView(0, null, mListView);
            item.measure(0, 0);
            params.height = item.getMeasuredHeight() * mAdapter.getCount()
                    + mListView.getDividerHeight() * (mAdapter.getCount() - 1);
            mListView.setLayoutParams(params);
        }
    }

    private void importBrowsers() {
        PackageInfo packageInfo;
        try {
            packageInfo = mPackageManager.getPackageInfo("com.openthos.filemanager", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            Log.d("ppppppp", "---------------com.openthos.filemanager---false");
        } else {
            Log.d("ppppppp", "---------------com.openthos.filemanager---true");
        }

        try {
            packageInfo = mPackageManager.getPackageInfo("com.tencent.mqq", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            Log.d("ppppppp", "---------------com.tencent.mqq---false");
        } else {
            Log.d("ppppppp", "---------------com.tencent.mqq---true");
        }

        try {
            packageInfo = mPackageManager.getPackageInfo("co.f.qq", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            Log.d("ppppppp", "---------------co.f.qq---false");
        } else {
            Log.d("ppppppp", "---------------co.f.qq---true");
        }

        File file = new File("/data/data/com.ledu");
        File[] files = file.listFiles();
        for (int i = 0; i < mBrowsersList.size(); i++) {
            for (int j = 0; j < files.length; j++) {
                if (mBrowsersList.get(i).activityInfo.packageName.equals(files[j])) {
                    mImportBrowsers.add(mBrowsersList.get(i));
//                } else {
//                    mBrowsersList.remove(i);
//                    i--;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("kkkkkkkk", "------------" + position);
    }

    private class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            buttonView.getId();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    private class ResolveAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {

        @Override
        public int getCount() {
            return mBrowsersList.size();
        }

        @Override
        public Object getItem(int i) {
            return mBrowsersList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            View convertView = view;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).
                        inflate(R.layout.list_item, viewGroup, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.text.setText(mBrowsersList.get(i).loadLabel(mPackageManager));
            holder.image.setImageDrawable(mBrowsersList.get(i).loadIcon(mPackageManager));
            holder.check.setTag(mBrowsersList.get(i).loadLabel(mPackageManager));
            holder.check.setOnCheckedChangeListener(this);
            return convertView;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String s = "";
            if (isChecked) {
                s = (String) buttonView.getTag() + "---true";
                mSelectedBrowser.add(s);
            } else {
                s = (String) buttonView.getTag() + "---false";
                mSelectedBrowser.remove(s);
            }

            Log.d("lllllll", "-----------------" + s + "---");
        }
    }

    private class ViewHolder {
        public TextView text;
        public ImageView image;
        public CheckBox check;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.tv_list_item);
            image = (ImageView) view.findViewById(R.id.iv_list_item);
            check = view.findViewById(R.id.cb_list_item);
        }
    }
}
