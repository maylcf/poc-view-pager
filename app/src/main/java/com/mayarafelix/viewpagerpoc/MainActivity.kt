package com.mayarafelix.viewpagerpoc

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var adapter: OnBoardingAdapter
    lateinit var layoutOnboardingIndicators: LinearLayout
    lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutOnboardingIndicators = findViewById(R.id.onboarding_indicators)
        actionButton = findViewById(R.id.activity_main_button)

        adapter = OnBoardingAdapter(getOnBoardingItems())
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = adapter

        setupOnBoardingIndicators()
        setupCurrentOnBoardingIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupCurrentOnBoardingIndicator(position)
            }
        })

        actionButton.setOnClickListener {
            if (viewPager.currentItem == adapter.getLastPosition()) {
                launchHomeActivity()
            } else {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }
    }

    private fun launchHomeActivity() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getOnBoardingItems(): ArrayList<OnBoardingItem> {
        return ArrayList<OnBoardingItem>().apply {
            add(OnBoardingItem(R.string.slide1_title, R.string.slide1_description, R.drawable.slide1))
            add(OnBoardingItem(R.string.slide2_title, R.string.slide2_description, R.drawable.slide2))
            add(OnBoardingItem(R.string.slide3_title, R.string.slide3_description, R.drawable.slide3))
            add(OnBoardingItem(R.string.slide4_title, R.string.slide4_description, R.drawable.slide4))
        }
    }

    private fun setupOnBoardingIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(8, 0, 8, 0)

        indicators.forEachIndexed { index, imageView ->
            indicators[index] = ImageView(applicationContext)
            indicators[index]?.setImageDrawable(getInactiveIndicatorDrawable())
            indicators[index]?.layoutParams = layoutParams
            layoutOnboardingIndicators.addView(indicators[index])
        }
    }

    private fun setupCurrentOnBoardingIndicator(index: Int) {
        val childCount = layoutOnboardingIndicators.childCount - 1

        for (i in 0..childCount) {
            val imageView: ImageView = layoutOnboardingIndicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(getActiveIndicatorDrawable())
            } else {
                imageView.setImageDrawable(getInactiveIndicatorDrawable())
            }
        }

        updateActionButtonLabel(index, childCount)
    }

    private fun updateActionButtonLabel(currentPosition: Int, lastPosition: Int) {
        if (currentPosition == lastPosition) {
            actionButton.text = getString(R.string.onboarding_activity_button_start)
        } else {
            actionButton.text = getString(R.string.onboarding_activity_button_next)
        }
    }

    private fun getActiveIndicatorDrawable(): Drawable? {
        return ContextCompat.getDrawable(
            applicationContext,
            R.drawable.shape_onboarding_indicator_active
        )
    }

    private fun getInactiveIndicatorDrawable(): Drawable? {
        return ContextCompat.getDrawable(
            applicationContext,
            R.drawable.shape_onboarding_indicator_inactive
        )
    }
}
