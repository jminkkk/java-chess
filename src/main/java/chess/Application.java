package chess;

import chess.controller.ChessGameController;
import chess.repository.piece.JdbcPieceDao;
import chess.repository.turn.JdbcTurnDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            ChessGameController chessGameController = new ChessGameController(new InputView(), new OutputView(),
                    new ChessGameService(new JdbcPieceDao(), new JdbcTurnDao()));
            chessGameController.start();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
