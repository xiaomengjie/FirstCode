package com.example.firstcode.chapter9.camera

import android.Manifest
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.DocumentsProvider
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.firstcode.R
import com.example.firstcode.other.BaseActivity
import java.io.File

private const val TAKE_PHOTO = 1
private const val PICK_FROM_PHOTO_ALBUM = 2

class CameraActivity : BaseActivity() {

    private val phoneImageView: ImageView by lazy {
        findViewById(R.id.photoImage)
    }

    private lateinit var outputImage: File
    private lateinit var imageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        findViewById<Button>(R.id.takePhoto).setOnClickListener {
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists())outputImage.delete()
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this, "$packageName.provider", outputImage)
            }else{
                Uri.fromFile(outputImage)
            }
            Log.e(TAG, "$imageUri")
            Log.e(TAG, outputImage.path)
            startActivityForResult(Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
                putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            }, TAKE_PHOTO)
        }

        findViewById<Button>(R.id.pickFromPhotoAlbum).setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }, PICK_FROM_PHOTO_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            TAKE_PHOTO -> {
                if (resultCode == RESULT_OK){
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    phoneImageView.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
            PICK_FROM_PHOTO_ALBUM -> {
                if (resultCode == RESULT_OK && data != null){
                    data.data?.let { uri ->
                        Log.e(TAG, "$uri")
                        phoneImageView.setImageBitmap(getBitmapFromUri(uri))

                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                            contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media.DATA),
                                "${MediaStore.Images.ImageColumns._ID} = ?", arrayOf("26326"), null)?.use {
                                    if (it.moveToFirst()){
                                        Log.e(TAG, it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
                                    }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return contentResolver.openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        return when(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)){
            ExifInterface.ORIENTATION_ROTATE_90 -> { rotateBitmap(bitmap, 90) }
            ExifInterface.ORIENTATION_ROTATE_180 -> { rotateBitmap(bitmap, 180) }
            ExifInterface.ORIENTATION_ROTATE_270 -> { rotateBitmap(bitmap, 270) }
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotateBitmap
    }
}