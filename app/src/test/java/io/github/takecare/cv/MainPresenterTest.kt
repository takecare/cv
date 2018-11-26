package io.github.takecare.cv

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class MainPresenterTest {

    @Mock
    private lateinit var cvRepository: CvRepository

    @Mock
    private lateinit var view: MainView

    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        initMocks(this)
        mainPresenter = MainPresenter(
            cvRepository,
            CompositeDisposable(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `main view model is displayed through view`() {
        val cv = cv()
        whenever(cvRepository.cv()).thenReturn(Single.just(cv))

        mainPresenter.startPresenting(view)

        verify(view).show(MainViewModel(cv.name, cv.githubUsername, cv.photoUrl))
    }

    @Test
    fun `error is displayed`() {
        val error = Throwable("boom!")
        whenever(cvRepository.cv()).thenReturn(Single.error(error))

        mainPresenter.startPresenting(view)

        verify(view).showError(error)
    }
}
