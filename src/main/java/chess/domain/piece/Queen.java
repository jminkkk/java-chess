package chess.domain.piece;

import chess.domain.obstaclerule.DirectionAttackPiece;

public class Queen extends DirectionAttackPiece {
    public Queen(final Color color) {
        super(PieceType.QUEEN, color);
    }
}
