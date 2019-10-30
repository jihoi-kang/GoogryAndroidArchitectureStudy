package study.architecture.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    open fun onPause() {
        compositeDisposable.clear()
    }

    abstract fun onResume()
}