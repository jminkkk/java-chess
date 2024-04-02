package chess.domain.board.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Finished extends Running {
    protected Finished(final Board board) {
        super(board);
    }

    @Override
    public GameStatus move(final Position source, final Position target) {
        throw new IllegalStateException("게임이 종료되어 이동이 불가능합니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Color getTurn() {
        throw new IllegalStateException("게임이 종료되어 턴을 조회할 수 없습니다.");
    }

    @Override
    public Board getBoard() {
        return board();
    }
}
