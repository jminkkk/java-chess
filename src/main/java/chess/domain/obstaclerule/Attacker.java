package chess.domain.obstaclerule;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public interface Attacker {

    List<Position> findObstacle(final Position source, final Position target,
                                final Map<Position, Piece> pieces);

    // TODO: 제거
    default void removeCapturableTargetFromObstacle(final Position target, final Piece sourcePiece,
                                                    final Piece targetPiece,
                                                    final List<Position> obstacles) {
        if (obstacles.contains(target)
                && sourcePiece.isNotSameTeam(targetPiece)) {
            obstacles.remove(target);
        }
    }

    default List<Position> getNotEmptyPiecePositions(final Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}
