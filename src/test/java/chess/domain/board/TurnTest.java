package chess.domain.board;

import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("게임 시작 시 턴은 검정팀부터 시작한다.")
    void createTurn() {
        assertThat(new Turn().isTurn(BLACK_PAWN.getPiece())).isTrue();
    }

    @Test
    @DisplayName("해당 색상의 팀 차례인지 확인한다.")
    void isTurn() {
        Turn blackTurn = new Turn();
        assertAll(
                () -> assertThat(blackTurn.isTurn(BLACK_PAWN.getPiece())).isTrue(),
                () -> assertThat(blackTurn.isTurn(WHITE_PAWN.getPiece())).isFalse()
        );
    }

    @Test
    @DisplayName("다음 차례를 반환한다.")
    void next() {
        Turn blackTurn = new Turn();
        assertThat(blackTurn.next()).isEqualTo(new Turn(Color.WHITE));
    }

    @Test
    @DisplayName("2팀이 턴을 번갈아가며 수행한다.")
    void turnTwoTeam() {
        assertAll(
                () -> assertThat(new Turn()).isEqualTo(new Turn(Color.BLACK)),
                () -> assertThat(new Turn().next()).isEqualTo(new Turn(Color.WHITE)),
                () -> assertThat(new Turn().next().next()).isEqualTo(new Turn(Color.BLACK))
        );
    }
}
