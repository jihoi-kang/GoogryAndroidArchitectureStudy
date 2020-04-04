package com.byiryu.study.ui.mvvm.login.viewmodel

import androidx.databinding.ObservableField
import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.enums.NetStatus
import com.byiryu.study.ui.mvvm.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginViewModel(private val repository: Repository) : BaseViewModel(){

    val netStatus = ObservableField<NetStatus>()
    val id = ObservableField<String>()
    val pw = ObservableField<String>()
    val autoLogin = ObservableField<Boolean>(false)
    val loginStatus = ObservableField<Pair<Boolean, Int>>()

    fun loginProcess(){

        if (id.get()?.isEmpty()!!) {
            loginStatus.set(Pair(false, R.string.msg_invalid_id))
            return
        }

        if (pw.get()?.isEmpty()!!) {
            loginStatus.set(Pair(false, R.string.msg_invalid_pw))
            return
        }

        disposable.add( repository.loginCheck(id.get(), pw.get())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                netStatus.set(NetStatus.LOADING)
            }
            .doOnSuccess{
                netStatus.set(NetStatus.SUCCESS)
            }
            .doOnError {
                netStatus.set(NetStatus.FAIL)
                loginStatus.set(Pair(false, R.string.msg_error_loading))
                loginStatus.notifyChange()
            }.doAfterTerminate {
                netStatus.set(NetStatus.SUCCESS)
            }
            .subscribe({
                if (it) {
                    autoLogin.get()?.run {
                        repository.setAutoLogin()
                    }
                    loginStatus.set(Pair(true, -1))
                } else {
                    loginStatus.set(Pair(false, R.string.msg_incorrect_login))
                    loginStatus.notifyChange()
                }
            }, {
                loginStatus.set(Pair(false, R.string.msg_error_loading))
                loginStatus.notifyChange()
            })
        )
    }

}