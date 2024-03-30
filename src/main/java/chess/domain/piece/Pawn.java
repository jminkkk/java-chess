package chess.domain.piece;

import chess.domain.piece.attacker.DiagonalAttackPiece;

public class Pawn extends DiagonalAttackPiece {

    public Pawn(final Color color) {
        super(PieceType.PAWN, color);
    }
}
