package domain

import model.CardDeck
import model.Participant
import model.Participants
import model.ParticipantsProfitResult

class CardGame(private val cardDeck: CardDeck, private val participants: Participants) {
    fun readyToStart() {
        participants.drawFirstCard(cardDeck)
    }

    fun drawCard(participant: Participant, status: (Participant) -> Unit, needToDraw: (String) -> Boolean) {
        while (participant.isHit(needToDraw)) {
            participant.drawCard(cardDeck)
            status(participant)
        }
    }

    fun getGameResult(): ParticipantsProfitResult {
        val dealerProfit = participants.dealer.calculateDealerProfit(participants)
        val playersProfit = participants.players.getGameProfitMoney(participants.dealer)
        return ParticipantsProfitResult(listOf(dealerProfit) + playersProfit)
    }
}
