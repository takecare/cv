package io.github.takecare.cv.experience

import com.nhaarman.mockito_kotlin.whenever
import io.github.takecare.cv.CvRepository
import io.github.takecare.cv.model.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.time.Instant
import java.util.*

class ExperienceRepositoryTest {

    @Mock
    private lateinit var cvRepository: CvRepository

    private lateinit var experienceRepository: ExperienceRepository

    @Before
    fun setUp() {
        initMocks(this)
        experienceRepository = ExperienceRepositoryImpl(cvRepository)
    }

    @Test
    fun `experience business model is extracted from cv`() {
        val cv = cv()
        whenever(cvRepository.cv()).thenReturn(Single.just(cv))

        experienceRepository.experience()
            .test()
            .assertValue(cv.experience)
    }

    private fun cv(): Cv {
        return Cv(
            "name",
            "photoUrl",
            "githubUsername",
            "personalUrl",
            Cover(
                listOf(
                    CoverItem.Knowledge("name", "description"),
                    CoverItem.Letter("cover")
                )
            ),
            Experience(
                listOf(
                    ExperienceItem(
                        "name",
                        "logoUrl",
                        "role",
                        Date.from(Instant.now()),
                        Date.from(Instant.now()),
                        "description"
                    )
                )
            )
        )
    }
}
