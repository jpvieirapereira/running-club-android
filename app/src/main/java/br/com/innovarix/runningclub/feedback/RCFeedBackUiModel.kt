package br.com.innovarix.runningclub.feedback

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.innovarix.runningclub.R
import br.com.innovarix.runningclub.core_theme.R.drawable

data class RCFeedBackUiModel(val feedBackType: RCFeedBackType)

enum class RCFeedBackType(
    @DrawableRes val icon: Int,
    @StringRes val feedBack: Int,
    @StringRes val feedBackDescription: Int,
    @StringRes val buttonText: Int
) {
    SUCCESS(
        icon = drawable.ic_check_success,
        feedBack = R.string.rc_feedback_success,
        feedBackDescription = R.string.rc_feedback_success_description,
        buttonText = R.string.rc_feedback_button_success_text
    ),
    ERROR(
        icon = drawable.ic_check_erro,
        feedBack = R.string.rc_feedback_error,
        feedBackDescription = R.string.rc_feedback_error_description,
        buttonText = R.string.rc_feedback_button_error_text
    )
}