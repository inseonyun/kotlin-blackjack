package blackjack.domain

import domain.CardPackGenerator
import domain.CardPicker
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPickerTest {
    @Test
    fun `카드 한 장을 뽑는다`() {
        val cardDeck = CardPackGenerator().createCardDeck()
        val card = CardPicker(cardDeck).pick()
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.DIAMOND)
    }
}
