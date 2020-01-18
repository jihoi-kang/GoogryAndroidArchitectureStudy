package com.example.handnew04.ui.main

import com.example.handnew04.data.NaverMovieResponse
import com.example.handnew04.ui.base.BaseContract

interface MainContract : BaseContract {
    interface View : BaseContract.View {
        fun showEmptyResult()

        fun showInputLengthZero()

        fun showNotConnectedNetwork()

        fun showSuccessSearchMovie(data: NaverMovieResponse)

        fun showMovieDetailActivity(position: Int)

        fun showFailSearchMovie(message: String?)
    }

    interface Presenter : BaseContract.Presenter {
        fun serchMovie(inputText: String)

        fun checkInputQuery(): Boolean

        fun isConnectedNetwork(): Boolean
    }
}