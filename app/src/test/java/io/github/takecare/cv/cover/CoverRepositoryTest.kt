package io.github.takecare.cv.cover

import com.google.common.truth.Truth
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.cv
import io.github.takecare.cv.model.CoverItem
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class CoverRepositoryTest {

    @Mock
    private lateinit var cvRepository: CvRepository

    private lateinit var coverRepository: CoverRepository

    @Before
    fun setUp() {
        initMocks(this)
        coverRepository = CoverRepositoryImpl(cvRepository)
    }

    @Test
    fun `personal url is added as link item`() {
        val cv = cv()
        whenever(cvRepository.cv()).thenReturn(Single.just(cv))

        var coverIems: List<CoverItem> = emptyList()
        coverRepository.cover()
            .doOnSuccess { coverIems = it.items }
            .test()

        val personal = coverIems.find { it -> it is CoverItem.Link && it.url == cv.personalUrl }
        assertTrue(personal != null)
    }

    @Test
    fun `github username is added as link item`() {
        val cv = cv()
        whenever(cvRepository.cv()).thenReturn(Single.just(cv))

        var coverIems: List<CoverItem> = emptyList()
        coverRepository.cover()
            .doOnSuccess { coverIems = it.items }
            .test()

        val link = coverIems.find { it -> it is CoverItem.Link && it.url.contains(cv.githubUsername) }
        assertTrue(link != null)
    }

    @Test
    fun `cover items are included`() {
        val cv = cv()
        whenever(cvRepository.cv()).thenReturn(Single.just(cv))

        var coverIems: List<CoverItem> = emptyList()
        coverRepository.cover()
            .doOnSuccess { coverIems = it.items }
            .test()

        Truth.assertThat(coverIems).containsAllIn(cv.cover.items)
    }
}
