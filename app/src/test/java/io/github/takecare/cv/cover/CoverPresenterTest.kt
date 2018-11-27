package io.github.takecare.cv.cover

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.cv.cover
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class CoverPresenterTest {

    @Mock
    private lateinit var coverRepository: CoverRepository

    @Mock
    private lateinit var view: CoverView

    @Mock
    private lateinit var linkOpener: LinkOpener

    private lateinit var coverPresenter: CoverPresenter

    @Before
    fun setUp() {
        initMocks(this)
        coverPresenter = CoverPresenter(
            coverRepository,
            linkOpener,
            CompositeDisposable(),
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun `cover view model is displayed through view`() {
        whenever(coverRepository.cover()).thenReturn(Single.just(cover()))

        coverPresenter.startPresenting(view)

        verify(view).show(
            CoverViewModel(
                listOf(
                    CoverItemViewModel.Letter("cover"),
                    CoverItemViewModel.Link("link", "link", 0),
                    CoverItemViewModel.Knowledge("title", "description")
                )
            )
        )
    }

    @Test
    fun `opening a link is routed through link opener`() {
        whenever(coverRepository.cover()).thenReturn(Single.just(cover()))
        coverPresenter.startPresenting(view)

        coverPresenter.openLink("http://www.link")

        verify(linkOpener).openLink("http://www.link")
    }

    @Test
    fun `error is displayed`() {
        val error = Throwable("boom!")
        whenever(coverRepository.cover()).thenReturn(Single.error(error))

        coverPresenter.startPresenting(view)

        verify(view).showError(error)
    }
}
