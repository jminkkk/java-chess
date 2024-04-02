package chess.controller;

import chess.domain.Game;
import chess.domain.board.ClearBoardInitializer;
import chess.domain.board.SavedBoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.scorerule.Referee;
import chess.service.ChessGameService;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

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
            Game game = Game.of(new SavedBoardInitializer(gameService.findAllPiece()), gameService.findTurn());
            outputView.printBoard(game.getBoard());
            outputView.printCurrentTurn(game.getTurn());
            processGame(game);
            return;
        }

        gameService.delete();
        play();
    }

    private void play() {
        Game game = initializeGame();
        processGame(game);
    }

    private void processGame(final Game game) {
        GameCommand gameCommand = inputView.getGameCommand();

        while (gameCommand.isContinuing()) {
            processCommand(gameCommand, game);
            if (game.isFinish()) {
                outputView.printFinish();
                gameService.delete();
                return;
            }

            gameCommand = inputView.getGameCommand();
        }

        restartGameIfRequested(gameCommand);
        saveGameIfEnd(gameCommand, game);
    }

    private void processCommand(final GameCommand gameCommand, final Game game) {
        if (gameCommand == GameCommand.MOVE) {
            playTurn(game);
        }
        if (gameCommand == GameCommand.STATUS) {
            viewScore(game.getBoard());
        }
    }

    private void restartGameIfRequested(final GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            play();
        }
    }

    private void saveGameIfEnd(final GameCommand gameCommand, final Game game) {
        if (gameCommand == GameCommand.END) {
            gameService.delete();
            gameService.save(game);
        }
    }

    private Game initializeGame() {
        Game game = new Game(new ClearBoardInitializer());
        outputView.printBoard(game.getBoard());
        return game;
    }

    private void playTurn(final Game game) {
        Position source = Position.of(inputView.getPosition());
        Position target = Position.of(inputView.getPosition());

        game.move(source, target);
        outputView.printBoard(game.getBoard());
    }

    private void viewScore(final Map<Position, Piece> board) {
        Referee referee = new Referee(board);

        double blackTeamScore = referee.calculateScore(Color.BLACK);
        outputView.printScore(blackTeamScore, Color.BLACK);
        double whiteTeamScore = referee.calculateScore(Color.WHITE);
        outputView.printScore(whiteTeamScore, Color.WHITE);
    }
}
