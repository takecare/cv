package io.github.takecare.cv.cover

import io.github.takecare.network.CvService

class CoverService(val cvService: CvService) {

    fun cover() {
        cvService.getCv()
                .map {  }
    }
}
