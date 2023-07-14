package bulkapedia.heroes

data class Hero(
    val heroId: String,
    val name: String,
    val type: String,
    val icon: String,
    val difficult: String,
    val stats: Map<String, Double>,
    val counterpicks: List<String>,
    val personalGears: Map<String, String>
)
