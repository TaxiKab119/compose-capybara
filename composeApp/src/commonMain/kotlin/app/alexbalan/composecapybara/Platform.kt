package app.alexbalan.composecapybara

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform