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
        checkStart(inputView.getGameCommand());

        if (gameService.existSavedGame() && inputView.isPlaySavedGame()) {
            playSavedGame();
            return;
        }

        playNewGame();
    }

    private void checkStart(final GameCommand gameCommand) {
        if (gameCommand != GameCommand.START) {
            throw new IllegalArgumentException("시작 명령어를 입력해주세요.");
        }
    }

    private void playSavedGame() {
        Game game = Game.of(new SavedBoardInitializer(gameService.findAllPiece()), gameService.findTurn());
        outputView.printBoard(game.getBoard());
        outputView.printCurrentTurn(game.getTurn());
        execute(game);
    }

    private void playNewGame() {
        Game game = new Game(new ClearBoardInitializer());
        outputView.printBoard(game.getBoard());
        execute(game);
    }

    private void execute(final Game game) {
        GameCommand gameCommand = inputView.getGameCommand();

        // TODO: depth 고려
        while (true) {

            if (gameCommand == GameCommand.START) {
                playNewGame();
                break;
            }

            if (gameCommand == GameCommand.MOVE) {
                executeTurn(game);

                if (game.isFinish()) {
                    outputView.printFinish();
                    gameService.delete();
                    break;
                }
            }

            if (gameCommand == GameCommand.STATUS) {
                viewScore(game.getBoard());
            }

            if (gameCommand == GameCommand.END) {
                gameService.saveGame(game);
                break;
            }

            gameCommand = inputView.getGameCommand();
        }
    }


    private void executeTurn(final Game game) {
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
