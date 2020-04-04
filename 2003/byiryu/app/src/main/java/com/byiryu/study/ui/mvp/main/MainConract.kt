package com.byiryu.study.ui.mvp.main

import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.mvp.base.BaseContract

interface MainConract {

    interface View : BaseContract.View {

        fun setResult(items: List<MovieItem>)

        fun setPrevQuery(query: String)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V>{

        fun search(query: String)

        fun getPrevQuery()
    }
}