package com.itis.artapp.domain.converters

private const val IMAGE_URL_PREFIX = "https://www.artic.edu/iiif/2/"
private const val IMAGE_URL_SUFFIX = "/full/843,/0/default.jpg"

class ArtworkDataConverter {

    fun createImageIconUrl(id: String): String = IMAGE_URL_PREFIX + id + IMAGE_URL_SUFFIX
}
