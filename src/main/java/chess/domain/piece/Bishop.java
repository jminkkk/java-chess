package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.piece.attacker.MovementDirectionObstacleFinder;
import chess.domain.piece.attacker.ObstacleFinder;
import chess.domain.position.Position;
import java.util.Map;

public class Bishop extends Piece {
    private final ObstacleFinder obstacleFinder;

    public Bishop(final Color color) {
        super(PieceType.BISHOP, color);
        this.obstacleFinder = new MovementDirectionObstacleFinder();
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, pieces.get(target)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target,
                        obstacleFinder.findObstacle(source, target, pieces)));
    }
}
