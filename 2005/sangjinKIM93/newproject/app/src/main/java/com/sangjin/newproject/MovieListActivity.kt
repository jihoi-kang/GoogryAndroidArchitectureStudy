package com.sangjin.newproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sangjin.newproject.adapter.MovieListAdapter
import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.model.NaverMovieResponse
import com.sangjin.newproject.data.repository.NaverMoviesRepositoryImpl
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.local.RoomDb
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieListContract.View {

    private lateinit var movieListAdapter: MovieListAdapter
    private val naverMoviesRepositoryImpl by lazy {
        NaverMoviesRepositoryImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(RoomDb.getInstance(applicationContext))
        )
    }

    private val presenter by lazy {
        MovieListPresenter(this, naverMoviesRepositoryImpl)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setRecyclerView()
        presenter.loadCache()
        setKeypad()
        showKeyPad()

    }


    //**리사이클러뷰 셋팅
    private fun setRecyclerView() {

        //각 항목 클릭시 이벤트 처리
        val onItemClickListener: ((Int) -> Unit) = { position ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieListAdapter.getMovieList().get(position).link))
            startActivity(intent)
        }

        movieListAdapter = MovieListAdapter(onItemClickListener)
        movieListView.adapter = movieListAdapter
    }



    //**검색 버튼 클릭 이벤트
    fun onClick(view: View) {

        val keyWord = movieNameET.text.toString().trim()

        presenter.searchMovie(keyWord)
    }


    //**키패드 셋팅
    private fun setKeypad() {
        movieNameET.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                onClick(v)

                true
            } else {
                false
            }
        }
    }


    //**키패드 보여주기
    private fun showKeyPad() {
        movieNameET.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    //**키패드 숨기기
    private fun hideKeyPad() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(movieNameET.windowToken, 0)
    }



    /**
     * Contract.View Interface 구현
     */

    override fun noKeyword() {
        Toast.makeText(this, R.string.no_keyword, Toast.LENGTH_LONG).show()
    }


    override fun noResult(movies: List<Movie>) {
        Toast.makeText(this@MovieListActivity, R.string.no_movie_list, Toast.LENGTH_SHORT).show()
    }


    override fun onError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show()
    }


    override fun refreshMovieList(movies: List<Movie>) {
        movieListAdapter.addList(movies as ArrayList<Movie>)
        hideKeyPad()
    }


    //**해제시 presenter에 있는 disposable 해제
    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}
