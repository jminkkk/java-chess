package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.Position;
import java.util.Map;

public record Piece(PieceType pieceType, Color color) {
    private static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Color.NONE);

    public static Piece getEmptyPiece() {
        return EMPTY_PIECE;
    }

    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, existEnemyAtTarget(target, pieces)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target,
                        pieceType.getObstacle(source, target, pieces)));
    }

    private boolean existEnemyAtTarget(final Position target, final Map<Position, Piece> pieces) {
        if (pieces.containsKey(target)) {
            Piece piece = pieces.get(target);
            return !color.isSameColor(piece.color);
        }

        return false;
    }

    public boolean isNotSameTeam(final Piece piece) {
        return !color.isSameColor(piece.color);
    }

    public boolean isSameTeamColor(final Color color) {
        return color.isSameColor(this.color);
    }

    public boolean isRankMove(final Position source, final Position target) {
        return source.file() == target.file();
    }

    public boolean isEmpty() {
        return this == EMPTY_PIECE;
    }
}
