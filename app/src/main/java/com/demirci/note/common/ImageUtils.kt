package com.demirci.note.common

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment

class ImageUtils {
    companion object {
        val IMAGE_PICK_CODE = 1000
        val PERMISSION_CODE = 1001

        fun checkPermission(fragment: Fragment) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment.activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) { // izin verilmemişse
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    fragment.activity?.requestPermissions(permissions, PERMISSION_CODE)

                } else { // izin verilmişse
                    pickImageFromGallery(fragment)
                }
            } else {
                pickImageFromGallery(fragment)
            }
        }

        fun pickImageFromGallery(fragment: Fragment) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            fragment.startActivityForResult(intent, IMAGE_PICK_CODE)
        }

    }
}