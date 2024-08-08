package com.seejiekai.mob21firebase.data.model

data class Extra(
    val location: String = "Location",
    val time: String = "Time"
) {
    companion object {
        fun fromMap(map: Map<*, *>?): Extra? {
            return map?.let {
                Extra(
                    location = it["location"].toString(),
                    time = it["time"].toString()
                )
            }
        }
    }
}
