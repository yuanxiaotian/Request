package com.cangmaomao.network.myapplication

<<<<<<< Updated upstream
import android.content.Intent
=======
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
>>>>>>> Stashed changes
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.RxHttpMange
import com.cangmaomao.network.request.utils.RxSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream


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

<<<<<<< Updated upstream
        RxHttpMange.getInstance().add(MainActivity::class.java.simpleName, loading())
=======
        createFiles("test")
>>>>>>> Stashed changes

        val options = BitmapFactory.Options()

<<<<<<< Updated upstream
        appCompatButton.setOnClickListener { startActivity(Intent(this,IndexActivity::class.java)) }
=======
        options.inSampleSize = 2

        options.inJustDecodeBounds = false


        val bitmap = BitmapFactory.decodeFile(externalCacheDir.absolutePath + "/test/timg.jpg", options)



        appCompatImageView.setImageBitmap(bitmap)


        saveDraw(externalCacheDir.absolutePath + "/test","test1",bitmap)
>>>>>>> Stashed changes
    }

    //生成文件文件夹
    fun createFiles(loginId: String) {
        val accountFile = externalCacheDir.absolutePath + "/" + loginId
        val file = File(accountFile)
        if (!file.exists())
            file.mkdirs()
    }


    //保存画板内容
    fun saveDraw(path: String, name: String, bitmap: Bitmap?) {
        val fileName = "$name.jpg"
        val file = File(path, fileName)
        val fos = FileOutputStream(file)
        if (bitmap == null) {
            return
        }
        bitmap.compress(Bitmap.CompressFormat.WEBP, 80, fos)
        fos.flush()
        fos.close()
    }


    private fun loading(): Disposable {
        return HttpManage.getInstance()
                .loadingView(constraintLayout, true)
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

