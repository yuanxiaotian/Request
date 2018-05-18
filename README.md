![Request](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526616733298&di=3d0e5f640dcb007c75d654eee9ee0284&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2Fb67919843bdb57d61de63aa4370a864473fce44b.jpg)
Request
 基于RxJava2+retrofit2+okHttp二次封装框架
-
# - 引入步骤:  #

## 1.  在project的build.gradle添加 ##
		allprojects {
    		repositories {
        		...
       			maven { url "https://jitpack.io" }
    		}
		}
##  2.  在Module的build.gradle添加依赖 ##
		dependencies {
	        implementation 'com.github.yuanxiaotian:Request:1.0.1'
		}


# - 使用步骤:  #
##  1.  普通请求 ##
		baseRetrofit:传入参数1
		observer    :传入参数2
		HttpManage.getInstance().map(BaseRetrofit baseRetrofit, Observer observer);
##  2.  带进度回调的文件上传 ##
		file        :传入参数1
		baseRetrofit:传入参数2
		observer    :传入参数3
		HttpManage.getInstance().upLoadFile(File file, BaseFileRetrofit baseRetrofit, Observer observer);
##  3.  带进度回调的文件下载 ##
		baseRetrofit:传入参数2
		observer    :传入参数3
		HttpManage.getInstance().downFile(BaseRetrofit baseRetrofit, BaseFileObserver observer);
# - 使用说明:  #
- 本框架有太多的不足之处,希望使用的人多多给建议,重要的是不喜勿喷
- 如果有好的建议请联系本人QQ:781414750
- 基于RecyclerView封装adapter框架地址:[https://github.com/yuanxiaotian/Lib_Adapter](https://github.com/yuanxiaotian/Lib_Adapter)







