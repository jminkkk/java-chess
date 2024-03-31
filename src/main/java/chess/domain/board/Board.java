package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private Turn turn;

    public Board(final BoardInitializer boardInitializer) {
        this.board = boardInitializer.initialize();
        this.turn = new Turn();
    }

    public Board(final BoardInitializer boardInitializer, final Turn turn) {
        this.board = boardInitializer.initialize();
        this.turn = turn;
    }

    public void tryMove(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        checkTurn(sourcePiece);

        if (sourcePiece.canMove(source, target, getBoard())) {
            move(source, target, sourcePiece);
            this.turn = turn.next();
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
        board.put(source, Empty.getInstance());
    }

    public boolean isFinish() {
        int kingCount = (int) board.entrySet().stream()
                .filter(entry -> entry.getValue().getPieceType() == PieceType.KING)
                .count();
        return kingCount < 2;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Turn getTurn() {
        return turn;
    }
}
