package chess.domain.piece;

import chess.domain.obstaclerule.DirectionAttackPiece;

public class Rook extends DirectionAttackPiece {
    public Rook(final Color color) {
        super(PieceType.ROOK, color);
    }
}
