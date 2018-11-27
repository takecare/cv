package io.github.takecare.cv

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.network.ImageLoader
import io.github.takecare.network.ImageLoaderModule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Mock
    lateinit var imageLoader: ImageLoader

    @Mock
    lateinit var cvRepository: CvRepository

    private val data = io.github.takecare.cv()

    @Before
    fun setUp() {
        initMocks(this)

        val app = getInstrumentation().targetContext.applicationContext as CvApplication

        val glide = Glide.with(getInstrumentation().targetContext.applicationContext)
        val captor = argumentCaptor<ImageView>()
        whenever(imageLoader.load(anyString(), captor.capture())).then {
            glide.load(R.drawable.dummy).into(captor.firstValue)
        }

        whenever(cvRepository.cv()).thenReturn(Single.just(data))

        val activityComponent = DaggerMainActivityComponent.builder()
            .imageLoaderModule(ImageLoaderModule(app.applicationContext))
            .cvModule(CvModule(cvRepository))
            .build()
        ComponentProviderHolder.componentProvider = TestComponentProvider(activityComponent = activityComponent)
    }

    @Test
    fun test() {

        testRule.launchActivity(null)

        onView(withId(R.id.cover_recyclerview)).check { view, _ ->
            val recyclerView = view as RecyclerView
            assertThat(recyclerView.adapter?.itemCount).isEqualTo(data.cover.items.size + 3)
        }
    }
}
