package chess.domain.piece.attacker;

import chess.domain.movement.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class DiagonalAttackPiece extends Piece implements Attacker {
    protected DiagonalAttackPiece(final PieceType pieceType, final Color color) {
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
        addTargetToObstacle(source, target, pieces.getOrDefault(source, Empty.getInstance()),
                pieces.getOrDefault(target, Empty.getInstance()), obstacles);
        return obstacles;
    }

    private void addTargetToObstacle(final Position source, final Position target,
                                     final Piece sourcePiece, final Piece targetPiece, final List<Position> obstacles) {
        if (canNotKillTargetByStraightMove(source, target, sourcePiece, targetPiece)
                || canNotMoveToTargetByDiagonalMove(source, target, targetPiece)) {
            obstacles.add(target);
        }
    }

    // 도착 위치가 상대편 말이지만 출발 위치와 도착 위치가 직선 이동일 경우
    private boolean canNotKillTargetByStraightMove(final Position source, final Position target,
                                                   final Piece sourcePiece, final Piece targetPiece) {
        return targetPiece.isNotSameTeam(sourcePiece) &&
                !targetPiece.isEmpty() &&
                source.isRankMove(target);
    }

    // 도착 위치가 비어있고 출발 위치와 도착 위치가 대각 이동일 경우
    private boolean canNotMoveToTargetByDiagonalMove(final Position source, final Position target,
                                                     final Piece targetPiece) {
        return targetPiece.isEmpty()
                && source.isDiagonalBy(target);
    }
}
