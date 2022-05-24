package dreamcraft.main.models

import android.location.Location

enum class Offices(val value: String) {
    WESLACO("Weslaco"),
    HARLINGEN("Harlingen"),
    CORPUS_CHRISTI("Corpus Christi");

    companion object {
        @JvmStatic
        fun find(location: Location): String {
            if (location.latitude > 26.13 && 26.225 > location.latitude) {
                if (location.longitude < -97.95 && -98.02 < location.longitude) {
                    return WESLACO.value
                } else if (location.longitude < -97.66 && -97.76 < location.longitude) {
                    return HARLINGEN.value
                }
            } else if (
                location.longitude < -97.19 && -97.48 < location.longitude &&
                location.latitude > 27.61 && 27.83 > location.latitude
            ) {
                return CORPUS_CHRISTI.value
            }

            return ""
        }
    }
}
