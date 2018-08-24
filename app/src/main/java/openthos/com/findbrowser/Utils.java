package openthos.com.findbrowser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ln on 18-4-25.
 */

public class Utils {

    Context context;

    public static String[] listFiles(String path) {
        if (TextUtils.isEmpty(path)) {
            return new String[]{};
        }
        Process pro = null;
        BufferedReader in = null;
        String[] files = null;
        try {
            pro = Runtime.getRuntime().exec(new String[]{"su", "-c", "ls "
                    + path.replace(" ", "\\ ")});
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;
            ArrayList<String> temp = new ArrayList();
            while ((line = in.readLine()) != null) {
                temp.add(line);
//                files[files.length] = line;
            }
//            files = new String[temp.size()];
            files = (String[]) temp.toArray(new String[0]);
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
            if (pro != null) {
                pro.destroy();
            }
        }
        return files;
    }

    private void showSyncOptionDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] items = new String[]{"", "", ""};
        final boolean[] selectedItems = new boolean[]{
                false, false, false, false, false, false};
        final List<String> syncBrowsers = new ArrayList();
        final List<String> syncAppdata = new ArrayList();
        final List<ResolveInfo> browsers = new ArrayList<>();
        Log.d("mmmmmm", "--------------create---line = ");
        final List<ResolveInfo> appdata = get();
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override


                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selectedItems[which] = !selectedItems[which];
                        if (isChecked) {
                            if (which == 1) {
//                                    browsers = get();
                                showSyncAppDialog(browsers, syncBrowsers);

                            } else if (which == 2) {
                                showSyncAppDialog(appdata, syncAppdata);

                            }

                        }

                    }

                });

    }

    private List<ResolveInfo> get() {
        return new ArrayList<ResolveInfo>();
    }

    private void showSyncAppDialog(List<ResolveInfo> localList, List<String> syncList) {
        localList = get();
        String[] names = new String[localList.size()];
        final boolean[] selects = new boolean[localList.size()];
        int i = 0;
        for (ResolveInfo info : localList) {
            syncList.add(info.activityInfo.packageName);
            names[i] = info.loadLabel(context.getPackageManager()).toString();
            selects[i] = true;
            i++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMultiChoiceItems(names, selects,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selects[which] = !selects[which];
                    }
                });
        builder.setPositiveButton("dd",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.create().show();
    }

    public static String getResult(String token) {
        HttpRequest ret = null;
        try {
//            String object = new JSONObject("").toString();

            ret = HttpRequest.get("http://166.111.120.235/api2/repos/", null, false);
            ret.readTimeout(30000)
                    .connectTimeout(15000)
                    .followRedirects(false)
                    .header("Authorization", "Token " + token);
            Log.d("mmmmmm", "--------------------getResult.code---" + ret.code());
            if (ret.ok()) {
                return new String(ret.bytes(), "UTF-8");
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String getToken(Context context, String userId, String password)
            throws UnsupportedEncodingException, JSONException,
            HttpRequest.HttpRequestException, PackageManager.NameNotFoundException {
        HttpRequest rep = null;
        rep = HttpRequest.post("http://166.111.120.235/api2/auth-token/", null, false)
                .followRedirects(true).connectTimeout(15000);
        rep.form("username", userId);
        rep.form("password", password);
        PackageInfo packageInfo = null;
        packageInfo = context.getPackageManager().
                getPackageInfo(context.getPackageName(), 0);
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        rep.form("platform", "android");
        rep.form("device_id", deviceId);
        rep.form("device_name", Build.MODEL);
        rep.form("client_version", packageInfo.versionName);
        rep.form("platform_version", Build.VERSION.RELEASE);
        String contentAsString = null;
        Log.d("nnnnnn", "---------------------req.code = " + rep.code());
//        if (rep.ok()) {
        contentAsString = new String(rep.bytes(), "UTF-8");
        Log.d("nnnnnn", "---------------------contentAsString = " + contentAsString);

        return new JSONObject(contentAsString).getString("token");
//        } else {
//            throw new UnsupportedEncodingException();
//        }
    }
}
