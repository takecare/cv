package io.github.takecare.cv

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.cv.model.Cover
import io.github.takecare.cv.model.CoverItem
import io.github.takecare.cv.model.Cv
import io.github.takecare.cv.model.ExperienceItem
import io.github.takecare.network.CvService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.internal.verification.VerificationModeFactory
import io.github.takecare.network.model.Cv as NetworkCv

class CvRepositoryTest {

    @Mock
    private lateinit var cvService: CvService

    private lateinit var cvRepository: CvRepositoryImpl

    @Before
    fun setUp() {
        initMocks(this)
        cvRepository = CvRepositoryImpl(cvService)
    }

    @Test
    fun `network model is converted to business model`() {
        val networkCv = networkCv()
        whenever(cvService.getCv()).thenReturn(Single.just(networkCv))

        cvRepository.cv()
            .test()
            .assertValue(networkCv.asBusinessModel())
    }

    @Test
    fun `cached model avoids multiples consequent requests`() {
        val networkCv = networkCv()
        whenever(cvService.getCv()).thenReturn(Single.just(networkCv))

        cvRepository.cv().test()
        cvRepository.cv().test()

        verify(cvService, VerificationModeFactory.only()).getCv()
    }

    @Test
    fun `errors are not cached`() {
        whenever(cvService.getCv()).thenReturn(Single.error(Throwable("\uD83D\uDCA5")))

        cvRepository.cv().test()
        cvRepository.cv().test()

        verify(cvService, times(2)).getCv()
    }

    private fun NetworkCv.asBusinessModel(): Cv {
        return Cv(
            name,
            photoUrl,
            githubUsername,
            personalUrl,
            Cover(
                listOf(
                    CoverItem.Knowledge(topics[0].name, topics[0].description),
                    CoverItem.Letter(cover)
                )
            ),
            io.github.takecare.cv.model.Experience(
                listOf(
                    ExperienceItem(
                        experience[0].name,
                        experience[0].logoUrl,
                        experience[0].role,
                        experience[0].from,
                        experience[0].to,
                        experience[0].description
                    )
                )
            )
        )
    }
}
