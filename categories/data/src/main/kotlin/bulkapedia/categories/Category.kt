package bulkapedia.categories

data class Category(
    val categoryId: String,
    val destination: String,
    val enabled: Boolean,
    val icon: String,
    val title: String,
    val subTitle: String
)
