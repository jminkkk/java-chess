package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.scorerule.Referee;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printInitialMessage();
        GameCommand gameCommand = inputView.getGameCommand();
        checkStart(gameCommand);

        play();
    }

    private void checkStart(final GameCommand gameCommand) {
        if (gameCommand != GameCommand.START) {
            throw new IllegalArgumentException("시작 명령어를 입력해주세요.");
        }
    }

    private void play() {
        Board board = initializeBoard();
        GameCommand gameCommand = inputView.getGameCommand();

        while (gameCommand == GameCommand.MOVE ||
                gameCommand == GameCommand.STATUS) {

            if (gameCommand == GameCommand.STATUS) {
                viewScore(board);
            } else {
                playTurn(board);
            }
            gameCommand = inputView.getGameCommand();
        }

        if (gameCommand == GameCommand.START) {
            play();
        }
    }

    private Board initializeBoard() {
        Board board = new Board(new BoardInitializer());
        outputView.printBoard(board);
        return board;
    }

    private void playTurn(final Board board) {
        Position source = Position.of(inputView.getPosition());
        Position target = Position.of(inputView.getPosition());

        board.tryMove(source, target);
        outputView.printBoard(board);
    }

    private void viewScore(final Board board) {
        Referee referee = new Referee(board.getBoard());

        double blackTeamScore = referee.calculateScore(Color.BLACK);
        outputView.printScore(blackTeamScore, Color.BLACK);
        double whiteTeamScore = referee.calculateScore(Color.WHITE);
        outputView.printScore(whiteTeamScore, Color.WHITE);
    }
}
