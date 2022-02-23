package com.wombat.mediastore_test

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import java.io.*
import java.lang.Exception
import java.net.URL
import kotlin.concurrent.thread
import kotlin.random.Random

/** MediastoreTestPlugin */
class MediastoreTestPlugin : FlutterPlugin, MethodCallHandler, ActivityAware,
    PluginRegistry.ActivityResultListener {
    private lateinit var channel: MethodChannel
    lateinit var activity: Activity
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "mediastore_test")
        channel.setMethodCallHandler(this)
    }

    private val requestCreateFile = 1000
    private val requestOpenFile = requestCreateFile + 1
    lateinit var fileContentType: String

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
            createFile()
        } else {
            result.notImplemented()
        }
    }

    //    @RequiresApi(Build.VERSION_CODES.Q)
    fun mediaStore() {
        createNewImageFile()
        val projection = arrayOf(
            //you only want to retrieve _ID and DISPLAY_NAME columns
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        activity.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null, null
        )?.use { cursor ->
            //cache column indices
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            Log.d("开始读取image", "${cursor.count}")
            //iterating over all of the found images
            while (cursor.moveToNext()) {
                val imageId = cursor.getString(idColumn)
                val imageName = cursor.getString(nameColumn)
                Log.d("图片", "$imageId:$imageName")
            }
        }

    }

    var index = 0
    private fun createFile() {
        if (index >= FileUrl.values().size) {
            Log.d("创建文件", "总共$index 个, 没了")
            return
        }
        url = FileUrl.values()[index++].url

        thread {
            URL(url).openConnection()
                .apply {
                    Log.d("下载内容类型", contentType)
                    fileContentType = contentType
                }

            val name = Uri.parse(url).lastPathSegment
            activity.runOnUiThread {
                val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = fileContentType
                    putExtra(Intent.EXTRA_TITLE, name)
                }
                activity.startActivityForResult(intent, requestCreateFile)
            }
        }


    }

    fun createNewImageFile() {
        val resolver = activity.contentResolver

        val imageCollection =
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        Log.d("开始创建新图片", "${imageCollection.path}")

        val newImageDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "lcp${Random(100000).nextInt()}.jpg")
        }
        val finalUri = resolver.insert(imageCollection, newImageDetails)
        Log.d("成功创建新图片", "${finalUri?.path}")
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        binding.addActivityResultListener(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }

    var url = Constant.Document.xlsx
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {

        Log.d("结果", "$resultCode")
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == requestCreateFile) {
                data?.data?.let { it ->
                    Log.d("创建新文件", "$it")

                    thread {
                        activity.contentResolver.openFileDescriptor(it, "w")?.use {
                            it.fileDescriptor?.let { fD ->
                                URL(url).openConnection()
                                    .apply {
                                        val bis = BufferedInputStream(getInputStream())
                                        val bos = BufferedOutputStream(FileOutputStream(fD))

                                        val bytes = ByteArray(8 * 1024)
                                        var readCount: Int
                                        while (bis.read(bytes)
                                                .also { count -> readCount = count } != -1
                                        ) {
                                            Log.d("读取数据", "$readCount")
                                            bos.write(bytes, 0, readCount)
                                        }
                                        bis.close()
                                        bos.close()
                                        Log.d("下载文件$url", "下载完成")
                                        activity.runOnUiThread {
                                            createFile()
                                        }
                                    }
                            }

                        }
                    }
                }
            } else if (requestCode == requestOpenFile) {
                data?.data?.let {

                }
            }
        }
        return false
    }
}
