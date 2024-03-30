package chess.domain.piece;

import chess.domain.piece.attacker.DirectionAttackPiece;

public class Queen extends DirectionAttackPiece {
    public Queen(final Color color) {
        super(PieceType.QUEEN, color);
    }
}
