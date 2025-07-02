package ru.bulkapedia.domain.model

data class Draft(
    val id: Int,
    val team1: String,
    val team2: String,
    val heroBanTime: Int,
    val heroBanCount: Int,
    val team1HeroBans: List<Hero>,
    val team2HeroBans: List<Hero>,
    val team1HeroPicks: List<Hero>,
    val team2HeroPicks: List<Hero>,
    val timer: Int,
    val team: Int,
    val isStart: Boolean,
)
