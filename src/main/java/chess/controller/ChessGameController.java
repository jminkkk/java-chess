package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.ClearBoardInitializer;
import chess.domain.board.SavedBoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.scorerule.Referee;
import chess.service.ChessGameService;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService gameService;

    public ChessGameController(final InputView inputView, final OutputView outputView,
                               final ChessGameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        outputView.printInitialMessage();
        GameCommand gameCommand = inputView.getGameCommand();
        checkStart(gameCommand);
        checkSavedGameExist();

        play();
    }

    private void checkStart(final GameCommand gameCommand) {
        if (gameCommand != GameCommand.START) {
            throw new IllegalArgumentException("시작 명령어를 입력해주세요.");
        }
    }

    private void checkSavedGameExist() {
        if (gameService.existGame()) {
            outputView.printSaveGameMessage();

            processSavedGame();
        }
    }

    private void processSavedGame() {
        if (inputView.isSavedGameStart()) {
            Board savedBoard = new Board(new SavedBoardInitializer(gameService.findAllPiece()), gameService.findTurn());
            outputView.printBoard(savedBoard);
            processGame(savedBoard);
            return;
        }

        gameService.delete();
        play();
    }

    private void play() {
        Board board = initializeBoard();
        processGame(board);
    }

    private void processGame(Board board) {
        GameCommand gameCommand = inputView.getGameCommand();

        while (gameCommand.isContinuing()) {
            processCommand(gameCommand, board);
            if (board.isFinish()) {
                outputView.printFinish();
                gameService.delete();
                return;
            }

            gameCommand = inputView.getGameCommand();
        }

        restartGameIfRequested(gameCommand);
        saveGameIfEnd(gameCommand, board);
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

    private void saveGameIfEnd(GameCommand gameCommand, final Board board) {
        if (gameCommand == GameCommand.END) {
            gameService.delete();
            gameService.save(board);
        }
    }

    private Board initializeBoard() {
        Board board = new Board(new ClearBoardInitializer());
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
