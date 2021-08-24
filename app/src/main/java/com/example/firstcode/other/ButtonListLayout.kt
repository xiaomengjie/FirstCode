package com.example.firstcode.other

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton

class ButtonListLayout(context: Context, attributeSet: AttributeSet): LinearLayout(context, attributeSet) {

    init {
        orientation = VERTICAL
    }

    fun setData(list: List<ButtonBean>, clickListener: OnClickListener){
        list.forEach {
            val button = MaterialButton(context)
            button.text = it.name
            button.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            button.isAllCaps = false
            button.id = it.id
            button.setOnClickListener(clickListener)
            addView(button)
        }
    }
}

data class ButtonBean(val id: Int, val name: String)