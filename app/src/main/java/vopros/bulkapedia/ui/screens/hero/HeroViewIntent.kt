package vopros.bulkapedia.ui.screens.hero

sealed class HeroViewIntent {
    data class Fetch(val heroId: String): HeroViewIntent()
    object Dispose: HeroViewIntent()
}