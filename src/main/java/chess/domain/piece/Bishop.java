package chess.domain.piece;

import chess.domain.piece.attacker.DirectionAttackPiece;

public class Bishop extends DirectionAttackPiece {
    public Bishop(final Color color) {
        super(PieceType.BISHOP, color);
    }
}
