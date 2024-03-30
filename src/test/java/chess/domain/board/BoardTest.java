package chess.domain.board;

import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드에서 체스 말을 이동시킬 수 있다.")
    void movePiece() {
        Board board = new Board(new BoardInitializer());
        board.tryMove(Position.of(1, 7), Position.of(1, 5));
        Map<Position, Piece> boardPieces = board.getBoard();
        assertAll(
                () -> assertThat(boardPieces.get(Position.of(1, 7)))
                        .isEqualTo(Empty.getInstance()),
                () -> assertThat(boardPieces.get(Position.of(1, 5)))
                        .isEqualTo(BLACK_PAWN.getPiece()));
    }

    @Test
    @DisplayName("보드에서 체스 말을 이동할 수 없는 경우 예외가 발생한다.")
    void movePieceThrowException() {
        Board board = new Board(new BoardInitializer());
        assertThatThrownBy(() -> board.tryMove(Position.of(1, 7), Position.of(1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }


    @Test
    @DisplayName("움직이려는 말의 진영 차례가 아닌 경우 예외가 발생한다.")
    void isTurnThrowException() {
        Board board = new Board(new BoardInitializer());
        assertThatThrownBy(() -> board.tryMove(Position.of(1, 2), Position.of(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 진영의 차례가 아닙니다.");

    }
}
