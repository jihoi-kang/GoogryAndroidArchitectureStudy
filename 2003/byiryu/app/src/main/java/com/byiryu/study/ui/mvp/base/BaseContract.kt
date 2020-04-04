package com.byiryu.study.ui.mvp.base

import android.content.Intent
import androidx.annotation.StringRes

interface BaseContract {

    interface View {
        val presenter : Presenter<View>

        fun showMsg(@StringRes res: Int)

        fun showMsg(msg: String)

        fun showLoading()

        fun hideLoading()

        fun goActivity(clazz: Class<*>)

        fun goActivity(intent: Intent)
    }

    interface Presenter<T : View> {
        fun onAttach(view: T)

        fun onDetach()
    }
}