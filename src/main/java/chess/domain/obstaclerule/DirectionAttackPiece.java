package chess.domain.obstaclerule;

import chess.domain.movement.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class DirectionAttackPiece extends Piece implements Attacker {
    protected DirectionAttackPiece(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, pieces.get(target)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target, findObstacle(source, target, pieces)));
    }

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);
        obstacles.remove(source);

        removeCapturableTargetFromObstacle(target, pieces.getOrDefault(source, Empty.getInstance()),
                pieces.getOrDefault(target, Empty.getInstance()), obstacles);
        return obstacles;
    }
}
