package kg.ruslan.core.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.WindowInsets
import kg.ruslan.core.R
import kg.ruslan.core.databinding.DialogLoadingBinding

class DialogLoading {

    private var dialog: CustomDialog? = null
    private lateinit var binding: DialogLoadingBinding

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        binding = DialogLoadingBinding.inflate(inflater)
        binding.cpBgView.setOnTouchListener { _, _ -> false }

        // Option Title
        binding.cpTitle.text = title

        // Card Color
        binding.cpCardview.setCardBackgroundColor(Color.parseColor("#70000000"))

        // Text Color
        binding.cpTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog?.setContentView(binding.root)
        dialog?.show()
        return dialog ?: show(context, title)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun dismissIfOpen() {
        dialog?.dismiss()
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(R.color.semiTransparentBackground)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                return@setOnApplyWindowInsetsListener if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    WindowInsets.CONSUMED
                } else {
                    @Suppress("DEPRECATION")
                    insets.consumeSystemWindowInsets()
                }
            }
        }
    }

}