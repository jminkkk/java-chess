package chess.domain.piece;

import chess.domain.obstaclerule.DirectionAttackPiece;

public class King extends DirectionAttackPiece {
    public King(final Color color) {
        super(PieceType.KING, color);
    }
}
