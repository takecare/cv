package io.github.takecare.cv

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.network.ImageLoader
import io.github.takecare.network.ImageLoaderModule
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

    @Before
    fun setUp() {
        initMocks(this)
        val app = getInstrumentation().targetContext.applicationContext as CvApplication

        val glide = Glide.with(getInstrumentation().targetContext.applicationContext)
        val captor = argumentCaptor<ImageView>()
        whenever(imageLoader.load(anyString(), captor.capture())).then {
            //glide.load(R.drawable.dummy).into(captor.firstValue)
        }

        val activityComponent = DaggerActivityComponent.builder()
            .imageLoaderModule(ImageLoaderModule(app.applicationContext))
            .build()
        app.componentProvider = TestComponentProvider(activityComponent = activityComponent)
    }

    @Test
    fun test() {

        testRule.launchActivity(null)

//        onView(withId(R.id.articles_recyclerview)).check { view, _ ->
//            val recyclerView = view as RecyclerView
//            assertThat(recyclerView.adapter?.itemCount).isEqualTo(fakeArticles.size)
//        }
    }

}
