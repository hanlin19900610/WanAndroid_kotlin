package com.mufeng.wanandroid.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference

import androidx.preference.PreferenceFragmentCompat
import com.afollestad.materialdialogs.color.ColorChooserDialog
import com.mufeng.wanandroid.R
import com.mufeng.wanandroid.event.RefreshHomeEvent
import com.mufeng.wanandroid.utils.CacheDataUtil
import com.mufeng.wanandroid.utils.ext.showSnackMsg
import com.mufeng.wanandroid.utils.rx.SchedulerUtils
import com.mufeng.wanandroid.widget.IconPreference
import com.tencent.bugly.beta.Beta
import io.reactivex.Observable
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit


class SettingFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private var context: SettingActivity? = null
    private lateinit var colorPreview: IconPreference

    companion object {
        fun getInstance(): SettingFragment {
            return SettingFragment()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_setting, rootKey)
        setHasOptionsMenu(true)
        context = activity as SettingActivity

        colorPreview = findPreference("color") as IconPreference

        setDefaultText()

        findPreference<CheckBoxPreference>("switch_show_top").setOnPreferenceChangeListener { preference, newValue ->
            // 通知首页刷新数据
            // 延迟发送通知：为了保证刷新数据时 SettingUtil.getIsShowTopArticle() 得到最新的值
            Observable.timer(100, TimeUnit.MILLISECONDS)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({
                    EventBus.getDefault().post(RefreshHomeEvent(true))
                }, {})
            true
        }

        findPreference<Preference>("auto_nightMode").setOnPreferenceClickListener {
            context?.startWithFragment(AutoNightModeFragment::class.java.name, null, null, 0, null)
            true
        }

        findPreference<IconPreference>("color").setOnPreferenceClickListener {
            ColorChooserDialog.Builder(context!!, R.string.choose_theme_color)
                .backButton(R.string.back)
                .cancelButton(R.string.cancel)
                .doneButton(R.string.done)
                .customButton(R.string.custom)
                .presetsButton(R.string.back)
                .allowUserColorInputAlpha(false)
                .show(activity)
            false
        }

        findPreference<Preference>("clearCache").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            CacheDataUtil.clearAllCache(context!!)
            context?.showSnackMsg(getString(R.string.clear_cache_successfully))
            setDefaultText()
            false
        }

        try {
            val version = "当前版本 " + context?.packageManager?.getPackageInfo(context?.packageName, 0)?.versionName
            findPreference<Preference>("version").summary = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        findPreference<Preference>("version").setOnPreferenceClickListener {
            Beta.checkUpgrade()
            false
        }

        findPreference<Preference>("changelog").setOnPreferenceClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.changelog_url))))
            false
        }

        findPreference<Preference>("sourceCode").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.source_code_url))))
            false
        }

        findPreference<Preference>("copyRight").onPreferenceClickListener = Preference.OnPreferenceClickListener {
            AlertDialog.Builder(context!!)
                .setTitle(R.string.copyright)
                .setMessage(R.string.copyright_content)
                .setCancelable(true)
                .show()
            false
        }

    }

    private fun setDefaultText() {
        try {
            findPreference<Preference>("clearCache").summary = CacheDataUtil.getTotalCacheSize(context!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key ?: return
        if (key == "color") {
            colorPreview.setView()
        }
    }


}