package com.example.recipes.presentation.ui.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.recipes.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class FullScreenBottomSheetDialogFragment(
    private val isDecorate: Boolean = true
) : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {

            if (!isDecorate) window?.setBackgroundDrawableResource(R.color.white)

            setOnShowListener { dialogInterface ->
                ((dialogInterface as? BottomSheetDialog)
                    ?.findViewById(R.id.design_bottom_sheet) as? FrameLayout)
                    ?.let { frameLayout ->
                        frameLayout.elevation = 0F
                        frameLayout.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

                        BottomSheetBehavior.from(frameLayout).apply {
                            peekHeight = resources.displayMetrics.heightPixels
                            state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
            }
        }
    }

}