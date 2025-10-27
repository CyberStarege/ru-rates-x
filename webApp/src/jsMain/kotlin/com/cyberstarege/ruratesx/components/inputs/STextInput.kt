package com.cyberstarege.ruratesx.components.inputs

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.builders.InputAttrsScope
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.TextInput

@Composable
fun STextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    isError: Boolean = false,
    attrs:  (InputAttrsScope<String>.() -> Unit)? = null,
) {
    Style(STextInputStyle)
    TextInput(
        value = value,
        attrs = {
            placeholder(placeholder ?: "")
            classes(STextInputStyle.base)
            if (isError) classes(STextInputStyle.isInvalid)
            else classes(STextInputStyle.isValid)
            onInput { event ->
                onValueChange(event.value)
            }
            attrs?.invoke(this)
        }
    )
}