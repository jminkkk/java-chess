package chess.domain.obstaclerule;

import chess.domain.movement.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Map;

public class NotAttackPiece extends Piece {
    protected NotAttackPiece(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, pieces.get(target)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target, new ArrayList<>()));
    }
}
