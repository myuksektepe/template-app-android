package kodz.org.core_ui.row.carousel

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.BaseRow
import kodz.org.core.base.row.BaseRowContractor
import kodz.org.core.common.HorizontalMarginItemDecoration
import kodz.org.core.extension.gone
import kodz.org.core_ui.row.carousel.carousel_item.CarouselItemRow
import kodz.org.core_ui.row.common.MultipleTypeAdapter
import kodz.org.core_ui.row.databinding.RowCarouselBinding

class CarouselRowContractor : BaseRowContractor() {
    override var binding: ViewDataBinding? = null
    private val carouselAdapter by lazy { MultipleTypeAdapter() }
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        binding = viewDataBinding
        initComponent()
    }

    private fun initComponent() {
        (binding as? RowCarouselBinding)?.run {
            data?.itemList?.let { list ->
                prepareCarousel(this.viewPagerVertical)
                this.viewPagerVertical.adapter = carouselAdapter

                val itemList = mutableListOf<CarouselItemRow>()
                list.forEach {
                    itemList.add(
                        CarouselItemRow(it).apply {
                            contractor.itemClickHandler = itemClickHandler
                        }
                    )
                }

                carouselAdapter.submitList(itemList as List<BaseRow>?)
            } ?: run {
                binding?.root?.gone()
            }
        }
    }

    private fun prepareCarousel(viewPager: ViewPager2) {
        val context = viewPager.context
        viewPager.offscreenPageLimit = 1
        val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            // If you want a fading effect uncomment the next line:
            page.alpha = 0.47f + (1 - kotlin.math.abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)
        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        if (viewPager.itemDecorationCount == 0) viewPager.addItemDecoration(itemDecoration)
    }
}