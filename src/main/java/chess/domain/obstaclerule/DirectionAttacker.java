package chess.domain.obstaclerule;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

// Direction 방향으로 공격을 하는 경우
public class DirectionAttacker extends Piece {

    protected DirectionAttacker(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        final Piece sourcePiece = pieces.get(source);
        final Piece targetPiece = pieces.getOrDefault(target, new Empty());
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);

        removeSourcePosition(source, obstacles);
        removeCapturableTargetFromObstacle(source, sourcePiece, targetPiece, obstacles);
        return obstacles;
    }
}
