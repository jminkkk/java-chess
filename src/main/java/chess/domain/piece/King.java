package chess.domain.piece;

import chess.domain.piece.attacker.DirectionAttackPiece;

public class King extends DirectionAttackPiece {
    public King(final Color color) {
        super(PieceType.KING, color);
    }
}
