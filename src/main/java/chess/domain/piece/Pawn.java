package chess.domain.piece;

import chess.domain.obstaclerule.DiagonalAttackPiece;

public class Pawn extends DiagonalAttackPiece {

    public Pawn(final Color color) {
        super(PieceType.PAWN, color);
    }
}
