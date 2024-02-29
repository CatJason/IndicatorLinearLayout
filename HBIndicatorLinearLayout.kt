package com.max.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class HBIndicatorLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedItemIndex: Int = -1
    private var selectedColor: Int = android.graphics.Color.RED
    private var unselectedColor: Int = android.graphics.Color.GREEN

    fun <T> setItems(items: ArrayList<T>, selectedIndex: Int) {
        this.selectedItemIndex = selectedIndex
        this.removeAllViews()
        val layoutParams = if (this.orientation == HORIZONTAL) {
            LayoutParams(
                0,
                LayoutParams.MATCH_PARENT,
                1.0f
            )
        } else {
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                0,
                1.0f
            )
        }
        items.forEachIndexed { index, _ ->
            val view = View(context).apply {
                this.layoutParams = layoutParams.also {
                    it.setMargins(5, 5, 5, 5)
                }
                setBackgroundColor(if (index == selectedIndex) selectedColor else unselectedColor)
            }
            this.addView(view)
        }
    }

    fun setSelectedItemIndex(selectedIndex: Int) {
        this.selectedItemIndex = selectedIndex
        updateViewBackgrounds()
    }

    fun setSelectedColor(color: Int) {
        this.selectedColor = color
        updateViewBackgrounds()
    }

    fun setUnselectedColor(color: Int) {
        this.unselectedColor = color
        updateViewBackgrounds()
    }

    private fun updateViewBackgrounds() {
        for (i in 0 until childCount) {
            getChildAt(i).setBackgroundColor(if (i == selectedItemIndex) selectedColor else unselectedColor)
        }
    }
}

/*
    val customLinearLayout = CustomLinearLayout(context).apply {
        setItems(arrayOf("Item 1", "Item 2", "Item 3"), 1)
    }

    // 如果需要更改选中项或颜色
    customLinearLayout.setSelectedItemIndex(2)
    customLinearLayout.setSelectedColor(android.graphics.Color.BLUE)
    customLinearLayout.setUnselectedColor(android.graphics.Color.YELLOW)
 */