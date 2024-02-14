package com.example.effectivemobiletr.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobiletr.R
import com.example.effectivemobiletr.databinding.FragmentDetailBinding
import com.example.effectivemobiletr.model.ImagesModel
import com.example.effectivemobiletr.ui.adapters.ImageSlideAdapter
import com.example.effectivemobiletr.ui.adapters.ImageSliderAdapter
import com.example.effectivemobiletr.viewmodel.ImageSliderViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var g = 0
    private lateinit var viewModel: ImageSliderViewModel
    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val oldPrice = arguments?.getString("oldPrice")
        val discount = arguments?.getString("discount")
        val actualPrice = arguments?.getString("actualPrice")
        val title = arguments?.getString("title")
        val subTitle = arguments?.getString("subTitle")
        val description = arguments?.getString("description")
        val rate = arguments?.getString("rate")
        val rateCounter = arguments?.getString("rateCounter")
        val available = arguments?.getString("available")
        val id = arguments?.getString("id")
        val compounds = arguments?.getString("compound")
        val articul = arguments?.getString("articul")
        val articulTitle = arguments?.getString("articulTitle")
        val images = arguments?.getString("images")

        binding.apply {

            viewModel.imageUrls.observe(viewLifecycleOwner) { imageUrls ->
                setupImageSlider(view, imageUrls)
            }

            val imageUrls = listOf(images)
            viewModel.setImageUrls(imageUrls)


            ratingBar.rating = rate!!.toFloat()
            // id
            productImageId.text = id
            // title
            detailPageProductTitle.text = title
            detailPageProductSubTitle.text = subTitle
            // available amount
            detailPageProductAvailableAmount.text = "Доступно для заказа $available штук"
            // ratings
            ratingAmount.text = rate
            ratingCounter.text = rateCounter
            // prices
            detailPageProductActualPrice.text = actualPrice
            detailPageProductOldPrice.text = oldPrice
            detailPageProductOldPrice.paintFlags =
                detailPageProductOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            detailPageProductDiscountAmount.text = discount
            // description
            productTitleTv.text = title
            detailPageProductDescription.text = description

            val containerView = view.findViewById<ConstraintLayout>(R.id.child_layout_container)

            val childLayout = layoutInflater.inflate(R.layout.layout_child, containerView, false)

            val compound = childLayout.findViewById<TextView>(R.id.compound_description_tv)
            val articulTv = childLayout.findViewById<TextView>(R.id.articul_tv)
            val articulId = childLayout.findViewById<TextView>(R.id.articul_id_tv)
            val usageAreaDesignation =
                childLayout.findViewById<TextView>(R.id.usage_area_designation)
            val madeInCountry = childLayout.findViewById<TextView>(R.id.made_in_country)
            val addToBasketActualPrice =
                childLayout.findViewById<TextView>(R.id.add_to_basket_actual_price)
            val addToBasketOldPrice =
                childLayout.findViewById<TextView>(R.id.add_to_basket_old_price)

            containerView.addView(childLayout)
            compound.text = compounds
            articulId.text = articul
            articulTv.text = articulTitle
            usageAreaDesignation.text = resources.getString(R.string.not_found)
            madeInCountry.text = resources.getString(R.string.not_found)
            addToBasketActualPrice.text = actualPrice
            addToBasketOldPrice.text = oldPrice

            detailPageBackBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }

            detailPageProductFunctionTv.setOnClickListener {
                if (g == 0) {
                    g = 1
                    expandable.expand()
                    detailPageProductFunctionTv.text = "Скрыть"
                } else {
                    g = 0
                    expandable.collapse()
                    detailPageProductFunctionTv.text = "Подробнее"
                }
            }
        }
    }

    private fun setupImageSlider(rootView: View, imageUrls: List<String>) {
        // Implement image slider logic using the library of your choice
        // For example, if using ViewPager2:
        val viewPager: ViewPager2 = rootView.findViewById(R.id.view_pager)
        val adapter = ImageSliderAdapter(imageUrls)
        viewPager.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}