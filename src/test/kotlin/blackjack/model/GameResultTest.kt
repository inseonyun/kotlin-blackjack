package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.GameResult
import model.Name
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러 21점 승리 플레이어 19점, 20점 패배`() {
        val dealer = Dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.ACE, Suit.DIAMOND),
        )
        val player1 = Player("jason", Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = Player("pobi", Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.HEART))
        val result = GameResult.of(dealer, listOf(player1, player2))

        assertThat(result.getDealerWinResult()).isEqualTo(2)
        assertThat(result.getDealerLoseResult()).isEqualTo(0)
        assertThat(result.playersResult["jason"]).isEqualTo(false)
        assertThat(result.playersResult["pobi"]).isEqualTo(false)
    }

    @Test
    fun `딜러 22점 패배 플레이어 19점, 20점 승리`() {
        val dealer = Dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.DEUCE, Suit.DIAMOND),
        )
        val player1 = Player("jason", Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = Player("pobi", Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        val result = GameResult.of(dealer, listOf(player1, player2))

        assertThat(result.getDealerWinResult()).isEqualTo(0)
        assertThat(result.getDealerLoseResult()).isEqualTo(2)
        assertThat(result.playersResult["jason"]).isEqualTo(true)
        assertThat(result.playersResult["pobi"]).isEqualTo(true)
    }

    @Test
    fun `딜러 20점 1승1패 플레이어 19점 패배, 21점 승리`() {
        val dealer = Dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.KING, Suit.HEART)
        )
        val player1 = Player("jason", Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = Player("pobi", Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))
        val result = GameResult.of(dealer, listOf(player1, player2))

        assertThat(result.getDealerWinResult()).isEqualTo(1)
        assertThat(result.getDealerLoseResult()).isEqualTo(1)
        assertThat(result.playersResult["jason"]).isEqualTo(false)
        assertThat(result.playersResult["pobi"]).isEqualTo(true)
    }

    companion object {
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
    }
}
