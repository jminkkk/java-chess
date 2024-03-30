package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public abstract class Piece {
    private static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Color.NONE);

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public static Piece getEmptyPiece() {
        return EMPTY_PIECE;
    }

    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, pieces.get(target)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target, findObstacle(source, target, pieces)));
    }

    public abstract List<Position> findObstacle(final Position source, final Position target,
                                                final Map<Position, Piece> pieces);

    public boolean isNotSameTeam(final Piece piece) {
        return !color.isSameColor(piece.color);
    }

    public boolean isSameTeamColor(final Color color) {
        return color.isSameColor(this.color);
    }

    public boolean isEmpty() {
        return this == EMPTY_PIECE;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }
}
