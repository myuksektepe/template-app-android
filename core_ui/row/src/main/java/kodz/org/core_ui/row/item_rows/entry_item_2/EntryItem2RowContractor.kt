package kodz.org.core_ui.row.item_rows.entry_item_2

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kodz.org.core.GlideApp
import kodz.org.core.R
import kodz.org.core.base.handler.ItemClickHandler
import kodz.org.core.base.row.contractor.BaseItemRowContractor
import kodz.org.core.domain.extensions.makeSlidable
import kodz.org.core.domain.extensions.prepareForGroup
import kodz.org.core.domain.extensions.setSpamProtectedClickListener
import kodz.org.core_ui.row.databinding.RowEntryItem2Binding

class EntryItem2RowContractor(
    override val isInCarousel: Boolean? = null,
    override val isInList: Boolean? = null
) : BaseItemRowContractor() {
    override var viewBinding: ViewDataBinding? = null
    lateinit var binding: RowEntryItem2Binding
    override var itemClickHandler: ItemClickHandler? = null

    override fun initBinding(viewDataBinding: ViewDataBinding) {
        viewBinding = if (isInCarousel == true) viewDataBinding.makeSlidable() else viewDataBinding
        binding = viewBinding as RowEntryItem2Binding
        initRow()
    }

    private fun initRow() {
        binding.run {
            data?.let { data ->

                // Paddings
                rowEntryItem2Root.prepareForGroup(isInList, isInCarousel)

                // Title
                txtEntryTitle.text = data.title

                // Image
                GlideApp.with(this.imgEntry.context)
                    .load(data.imageUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .error(R.drawable.placeholder)
                    .into(imgEntry.imageView)

                // EventHandler
                rowEntryItem2Root.setSpamProtectedClickListener {
                    itemClickHandler?.onItemClick(data.clickEventModel)
                }

            }
        }
    }
}