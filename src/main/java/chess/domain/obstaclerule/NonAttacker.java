package chess.domain.obstaclerule;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class NonAttacker extends Piece {

    protected NonAttacker(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return false;
    }

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        final Piece sourcePiece = pieces.get(source);
        final Piece targetPiece = pieces.getOrDefault(target, new Empty());
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);

        removeCapturableTargetFromObstacle(target, sourcePiece, targetPiece, obstacles);
        removeSourcePosition(source, obstacles);
        return obstacles;
    }
}
