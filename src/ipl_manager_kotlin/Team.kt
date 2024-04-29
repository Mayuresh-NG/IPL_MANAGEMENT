package ipl_manager_kotlin

class Team(
    val name: String,
    val homeGround: String,
    val players: List<Player>
) {
    fun getNames(): String {
        return name
    }
}
