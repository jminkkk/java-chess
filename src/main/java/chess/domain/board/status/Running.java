package chess.domain.board.status;

import chess.domain.board.Board;

public abstract class Running implements GameStatus {
    private final Board board;

    protected Running(final Board board) {
        this.board = board;
    }

    protected Board board() {
        return board;
    }
}
