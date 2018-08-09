package com.cangmaomao.network.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.RxHttpMange
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var map: MutableMap<String, Any>

    private val url = "http://www.seteat.com/Login/app_client_login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        map = mutableMapOf()
        map["devid"] = "6af67ec1-a504-3848-ba8c-4fa32eaefba9"
        map["ct"] = 1
        map["password"] = 1212
        map["loginid"] = 1212







        RxHttpMange.getInstance().add(MainActivity::class.java.simpleName, loading())
    }

    private fun loading(): Disposable {
        return HttpManage.getInstance()
                .loadingView(constraintLayout, false)
                .create(UserApi::class.java)
                .post(url, map)
                .compose(RxSchedulers.io_main())
                .subscribe({ HttpManage.getInstance().loadingEnd() }, { showErr(it) })
    }


    private fun loading3() {
        val manage = HttpManage.getInstance().loadingView(constraintLayout, false)
        val api = manage.create(UserApi::class.java)
        manage.concat(api.post(url, map), api.post(url, map))
                .compose(RxSchedulers.io_main())
                .subscribe({}, {})
    }


    private fun loading2(): Disposable {
        return HttpManage.getInstance()
                .create(UserApi::class.java)
                .post(url, map)
                .compose(RxSchedulers.io_main())
                .subscribe({ HttpManage.getInstance().loadingEnd() }, { showErr(it) })
    }


    private fun showErr(it: Throwable) {
        RxHttpMange.getInstance().remove(MainActivity::class.java.simpleName)
        HttpManage.getInstance().loadingErr { loading() }
    }

}

