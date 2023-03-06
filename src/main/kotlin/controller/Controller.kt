package controller

import domain.CardGame
import domain.CardPackGenerator
import model.GameResult
import model.Name
import model.Names
import model.Participants
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val cardDeck = CardPackGenerator().createCardDeck().shuffled()
    fun run() {
        val cardGame = CardGame(cardDeck)
        val players = cardGame.initPlayers(initNames())
        val dealer = cardGame.initDealer()
        val participants = Participants(listOf(dealer) + players)
        outputView.printNoticeDistributeCards(participants)
        cardGame.drawPlayersCard(players, inputView::readYesOrNo, outputView::printPlayerStatus)
        cardGame.drawDealerCard(dealer) { outputView.printDealerGetCard() }
        outputView.printAllPlayerStatusResult(participants.participants)
        outputView.printFinalResult(GameResult.of(dealer, players))
    }

    private fun initNames(): Names {
        return Names(inputView.readName().map(::Name))
    }
}
