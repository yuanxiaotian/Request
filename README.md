![Request](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526616733298&di=3d0e5f640dcb007c75d654eee9ee0284&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2Fb67919843bdb57d61de63aa4370a864473fce44b.jpg)
Request
 基于RxJava2+retrofit2+okHttp二次封装框架
-
# - 更新:  #

    5/18/2018 18:09:00 PM 更新
    V1.0.3:调整整体框架结构,使用更为简单,使用方法稍后更新

    5/18/2018 10:23:02 AM 更新
	V1.0.2:修改引入第三方框架的方式由Implementation更改为api引入


# - 引入步骤:  #

### 1.  在project的build.gradle添加 ###
	allprojects {
	    repositories {
	        ...
	        ...
	        maven { url 'https://jitpack.io' }
	    }
	}
###  2.  在Module的build.gradle添加依赖 ###
	com.github.yuanxiaotian:Request:1.0.4


# - 使用步骤:  #
###  0.  请求配置 ###
> 在你的Application配置你的baseUrl

	Config.S_HTTP_ROOT_URL ="http://www.baidu.com/";

> 普通请求api.class接口写法

     url   :完整的请求地址
     param :请求参数Map集合
     @POST
     @FormUrlEncoded
     Observable<Loginpost(@Url String url, @FieldMap Map<String, Objectmap);


###  1.  普通请求 ###
	clazz         :服务接口
	url           :完整的请求地址
	param         :请求参数Map集合
	observer      :回调:BaseObserver
	HttpManage.getInstance().create(UserApi.class).post(url, map).compose(RxSchedulers.<Login>io_main())
                    .subscribe(new BaseObserver<Login>(TAG) {
                        @Override
                        public void success(Login login) {

                        }

                        @Override
                        public void fail(String err) {

                        }
                    });
###  2.  带进度回调的文件上传 ###
	url           :完整的请求地址
	file          :上传的文件
	param         :请求参数Map集合
	observer      :回调:BaseFileObserver
	HttpManage.getInstance().upLoadFile(String url, File file, BaseFileObserver fileUploadObserver)
	HttpManage.getInstance().upLoadFile(String url, File file, Map<String, RequestBody> map, BaseFileObserver fileUploadObserver)
###  3.  带进度回调的文件下载 ###
	url           :完整的请求地址
	observer      :回调:BaseFileObserver
	HttpManage.getInstance().downFile(String url, BaseFileObserver observer)
###  4.  取消请求 ###
	tag           :在服务请求的时候框架已经自动加入了Tag
	RxHttpMange.getInstance().remove(String tag);
	RxHttpMange.getInstance().removeAll();
# - 使用说明:  #
- 本框架有太多的不足之处,希望使用的人多多给建议,重要的是不喜勿喷
- 如果有好的建议请联系本人QQ:781414750
- 基于RecyclerView封装adapter框架地址:[https://github.com/yuanxiaotian/Lib_Adapter](https://github.com/yuanxiaotian/Lib_Adapter)







