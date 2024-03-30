package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private final Turn turn;

    public Board(final BoardInitializer boardInitializer) {
        this.board = boardInitializer.initialize();
        this.turn = new Turn();
    }

    public void tryMove(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        checkTurn(sourcePiece);

        if (sourcePiece.canMove(source, target, getBoard())) {
            move(source, target, sourcePiece);
            return;
        }

        throw new IllegalArgumentException("이동이 불가능한 위치입니다.");
    }

    private void checkTurn(final Piece piece) {
        if (!turn.isTurn(piece)) {
            throw new IllegalArgumentException("해당 진영의 차례가 아닙니다.");
        }
    }

    private void move(final Position source, final Position target, final Piece piece) {
        board.put(target, piece);
        board.put(source, new Empty());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
