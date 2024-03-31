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
        processGame(board);
    }

    private void processGame(Board board) {
        GameCommand gameCommand = inputView.getGameCommand();

        while (isGameContinuing(gameCommand)) {
            processCommand(gameCommand, board);
            if (board.isFinish()) {
                outputView.printFinish();
                return;
            }
            gameCommand = inputView.getGameCommand();
        }

        restartGameIfRequested(gameCommand);
    }

    private boolean isGameContinuing(GameCommand gameCommand) {
        return gameCommand == GameCommand.MOVE || gameCommand == GameCommand.STATUS;
    }

    private void processCommand(GameCommand gameCommand, Board board) {
        if (gameCommand == GameCommand.MOVE) {
            playTurn(board);
        }
        if (gameCommand == GameCommand.STATUS) {
            viewScore(board);
        }
    }

    private void restartGameIfRequested(GameCommand gameCommand) {
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
