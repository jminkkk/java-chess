//package chess.domain.obstaclerule;
//
//import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
//import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import chess.domain.piece.Color;
//import chess.domain.piece.Piece;
//import chess.domain.piece.PieceType;
//import chess.domain.position.Position;
//import java.util.List;
//import java.util.Map;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class DiagonalObstacleDetectorTest {
//
//    /*
//     * ........
//     * ........
//     * ........
//     * ........
//     * ........
//     * ...P.... <- target
//     * ..p..... <- source
//     * ........
//     * */
//
//    @Test
//    @DisplayName("출발 위치와 도착 위치가 대각선 이동이고 사이에 말이 존재하지 않을 경우, "
//            + "도착 위치의 말이 상대편 말이면 공격이 가능하므로 장애물이 아니다.")
//    void findObstacleWhenCapturableTargetByDiagonalMove() {
//        DiagonalObstacleDetector diagonalAttacker = new DiagonalObstacleDetector();
//
//        Position sourcePosition = Position.of(3, 2);
//        Position targetPosition = Position.of(4, 3);
//
//        List<Position> obstacle = diagonalAttacker.findObstacle(sourcePosition, targetPosition,
//                Map.of(sourcePosition, new Pawn(Color.BLACK), targetPosition,
//                        new Pawn(Color.WHITE)));
//
//        assertThat(obstacle).isEmpty();
//    }
//
//    /*
//     * ........
//     * ........
//     * ........
//     * ........
//     * ........ <- target(Empty)
//     * ..P.....
//     * ..p..... <- source
//     * ........
//     * */
//    @Test
//    @DisplayName("도착 위치가 상대편 말이지만 출발 위치와 도착 위치가 직선 이동일 경우, 공격이 불가능하므로 장애물이다.")
//    void findObstacleWhenUnCapturableTargetByStraightMove() {
//        DiagonalObstacleDetector diagonalAttacker = new DiagonalObstacleDetector();
//
//        Position sourcePosition = Position.of(3, 2);
//        Position targetPosition = Position.of(3, 3);
//
//        List<Position> obstacle = diagonalAttacker.findObstacle(sourcePosition, targetPosition,
//                Map.of(sourcePosition, WHITE_PAWN.getPiece(), targetPosition, BLACK_PAWN.getPiece()));
//
//        assertThat(obstacle).containsExactly(targetPosition);
//    }
//
//    /*
//     * ........
//     * ........
//     * ........
//     * ........
//     * ........
//     * ..E..... <- target(Empty)
//     * ..P..... <- source
//     * ........
//     * */
//    @Test
//    @DisplayName("도착 위치가 비어있고 출발 위치와 도착 위치가 직선 이동일 경우, 이동이 가능하므로 장애물에 추가되지 않는다.")
//    void findObstacleWhenTargetIsEmptyByStraight() {
//        DiagonalObstacleDetector diagonalAttacker = new DiagonalObstacleDetector();
//
//        Position sourcePosition = Position.of(3, 2);
//        Position targetPosition = Position.of(3, 3);
//
//        List<Position> obstacle = diagonalAttacker.findObstacle(sourcePosition, targetPosition,
//                Map.of(sourcePosition, BLACK_PAWN.getPiece(),
//                        targetPosition, Piece.getEmptyPiece()));
//
//        assertThat(obstacle).isEqualTo(List.of());
//    }
//
//    /*
//     * ........
//     * ........
//     * ........
//     * ........
//     * ........
//     * ...E.... <- target(Empty)
//     * ..P..... <- source
//     * ........
//     * */
//    @Test
//    @DisplayName("도착 위치가 비어있고 출발 위치와 도착 위치가 대각 이동일 경우, 이동이 불가능하므로 장애물에 추가된다.")
//    void findObstacleWhenTargetIsEmptyByDiagonal() {
//        DiagonalObstacleDetector diagonalAttacker = new DiagonalObstacleDetector();
//
//        Position sourcePosition = Position.of(3, 2);
//        Position targetPosition = Position.of(4, 3);
//
//        List<Position> obstacle = diagonalAttacker.findObstacle(sourcePosition, targetPosition,
//                Map.of(sourcePosition, BLACK_PAWN.getPiece(),
//                        targetPosition, Piece.getEmptyPiece()));
//
//        assertThat(obstacle).containsExactly(targetPosition);
//    }
//}
