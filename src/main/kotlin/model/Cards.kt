package model

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()
    val size: Int
        get() = _cards.size

    init {
        require(cards.distinct().size == cards.size) { CARD_DUPLICATE_ERROR }
    }

    fun add(card: Card) {
        require(!_cards.contains(card)) { CARD_DUPLICATE_ERROR }
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.filter { it.rank != Rank.ACE }.sumOf { it.rank.getScore() }
        sum += _cards.filter { it.rank == Rank.ACE }.sumOf { it.rank.getScore(sum) }
        return sum
    }

    companion object {
        private const val CARD_DUPLICATE_ERROR = "카드는 중복될 수 없습니다."
    }
}
