package model

data class Players(val players: List<Player>) : List<Player> by players {
    init {
        require(players.size in PLAYER_MIN..PLAYER_MAX) { PLAYER_COUNT_ERROR }
    }

    fun getGameProfitMoney(dealer: Participant): Map<Name, Long> {
        val result = mutableMapOf<Name, Long>()
        players.forEach { player ->
            result[player.name] = player.getProfitMoney(dealer)
        }
        return result
    }

    companion object {
        private const val PLAYER_MIN = 1
        private const val PLAYER_MAX = 8
        private const val PLAYER_COUNT_ERROR = "플레이어는 최소 1명 최대 8명까지 가능합니다"
    }
}
