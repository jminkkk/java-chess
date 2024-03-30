package chess.domain.piece;

import chess.domain.obstaclerule.DirectionAttackPiece;

public class Knight extends DirectionAttackPiece {
    public Knight(final Color color) {
        super(PieceType.KNIGHT, color);
    }
}
