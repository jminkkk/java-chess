package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movement.Movement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Piece {
    private final PieceType pieceType;
    private final Color color;

    public Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean canMove(final Position from, final Position to,
                           final boolean firstMove, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, firstMove, existEnemy(to, pieces)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(from, to, findObstacle(from, to, pieces)));
    }

    private List<Position> findObstacle(final Position from, final Position to, final Map<Position, Piece> pieces) {
        List<Position> obstacles = pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isNotEmpty())
                .map(Entry::getKey)
                .collect(Collectors.toList());

        removeKillableDestinationObstacle(to, pieces, obstacles);
        addObstacleBlockedOnRankMove(from, to, pieces, obstacles);
        return obstacles;
    }

    private void removeKillableDestinationObstacle(final Position to, final Map<Position, Piece> pieces,
                                                   final List<Position> obstacles) {
        if (obstacles.contains(to) && pieces.get(to).isNotSameColor(color)) {
            obstacles.remove(to);
        }
    }

    private void addObstacleBlockedOnRankMove(final Position from, final Position to,
                                              final Map<Position, Piece> pieces, final List<Position> obstacles) {
        if (isPawnBlockedOnRankMove(from, to, pieces)) {
            obstacles.add(to);
        }
    }

    private boolean isPawnBlockedOnRankMove(final Position from, final Position to, final Map<Position, Piece> pieces) {
        return pieceType == PieceType.PAWN && isRankMove(from, to)
                && pieces.getOrDefault(to, new Piece(PieceType.EMPTY, Color.NONE)).isNotEmpty();
    }

    private boolean isRankMove(final Position from, final Position to) {
        return from.file() == to.file()
                && (Math.abs(from.rank() - to.rank()) == 1 || Math.abs(from.rank() - to.rank()) == 2);
    }

    private boolean existEnemy(final Position to, final Map<Position, Piece> pieces) {
        return pieces.containsKey(to)
                && pieces.get(to).isNotEmpty()
                && pieces.get(to).isNotSameColor(color);
    }

    private boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    private boolean isNotEmpty() {
        return pieceType != PieceType.EMPTY;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
