package io.github.takecare.cv.experience

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.cv.experience
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.time.Instant
import java.util.*

class ExperiencePresenterTest {

    @Mock
    private lateinit var experienceRepository: ExperienceRepository

    @Mock
    private lateinit var view: ExperienceView

    private lateinit var experiencePresenter: ExperiencePresenter

    @Before
    fun setUp() {
        initMocks(this)
        experiencePresenter = ExperiencePresenter(
            experienceRepository,
            CompositeDisposable(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `experience view model is displayed through view`() {
        whenever(experienceRepository.experience()).thenReturn(Single.just(experience()))

        experiencePresenter.startPresenting(view)

        verify(view).show(
            ExperienceViewModel(
                listOf(
                    ExperienceItemViewModel(
                        "name",
                        "logoUrl",
                        "role",
                        Date.from(Instant.now()).toString(),
                        Date.from(Instant.now()).toString(),
                        "description"
                    )
                )
            )
        )
    }


    @Test
    fun `error is displayed`() {
        val error = Throwable("boom!")
        whenever(experienceRepository.experience()).thenReturn(Single.error(error))

        experiencePresenter.startPresenting(view)

        verify(view).showError(error)
    }
}
