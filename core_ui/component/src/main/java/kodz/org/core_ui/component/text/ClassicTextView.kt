package kodz.org.core_ui.component.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import kodz.org.core.model.TextAlignType
import kodz.org.core.model.TextWeightType
import kodz.org.core_ui.component.R


/**
 * Created by Murat Yüksektepe - yuksektepemurat@gmail.com on 12.10.2023.
 */

@SuppressLint("UseCompatLoadingForDrawables", "RestrictedApi")
class ClassicTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.component_classic_text_view, null)

        // Attributes
        context.obtainStyledAttributes(attrs, R.styleable.ClassicTextView).run {

            // Text Weight
            getString(R.styleable.ClassicTextView_textWeight)?.let { textWeight ->
                setTextWeight(textWeight)
            } ?: kotlin.run { setTextWeight("normal") }

            // Text Align
            getString(R.styleable.ClassicTextView_textAlign)?.let { textWeight ->
                setTextAlign(textWeight)
            }

            if (getBoolean(R.styleable.ClassicTextView_showShadow, false)) {
                setShadowLayer(8f, 0f, 2f, kodz.org.core.R.color.black80)
            }
            recycle()
        }
    }

    fun setTextWeight(weight: String) {
        typeface = when (weight) {
            TextWeightType.THIN.name.lowercase() -> {
                ResourcesCompat.getFont(context, R.font.averta_light)
            }

            TextWeightType.NORMAL.name.lowercase() -> {
                ResourcesCompat.getFont(context, R.font.averta_regular)
            }

            TextWeightType.BOLD.name.lowercase() -> {
                ResourcesCompat.getFont(context, R.font.averta_bold)
            }

            else -> {
                ResourcesCompat.getFont(context, R.font.averta_regular)
            }
        }
    }

    fun setTextAlign(align: String) {
        textAlignment = when (align) {
            TextAlignType.LEFT.name.lowercase() -> {
                TEXT_ALIGNMENT_TEXT_START
            }

            TextAlignType.CENTER.name.lowercase() -> {
                TEXT_ALIGNMENT_CENTER
            }

            TextAlignType.RIGHT.name.lowercase() -> {
                TEXT_ALIGNMENT_TEXT_END
            }

            else -> {
                TEXT_ALIGNMENT_TEXT_START
            }
        }
    }
}