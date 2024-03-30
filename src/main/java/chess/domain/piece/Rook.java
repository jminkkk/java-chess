package chess.domain.piece;

import chess.domain.piece.attacker.DirectionAttackPiece;

public class Rook extends DirectionAttackPiece {
    public Rook(final Color color) {
        super(PieceType.ROOK, color);
    }
}
