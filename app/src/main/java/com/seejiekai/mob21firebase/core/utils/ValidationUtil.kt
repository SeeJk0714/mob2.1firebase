package com.seejiekai.mob21firebase.core.utils

import com.seejiekai.mob21firebase.data.model.ValidationField

object ValidationUtil {
    fun validate(vararg fields: ValidationField): String? {
        fields.forEach {field ->
            if (!Regex(field.regExp).matches(field.value)) {
                return field.errMsg
            }
        }
        return null
    }
}