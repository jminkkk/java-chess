package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("게임 시작 시 턴은 검정팀부터 시작한다.")
    void createTurn() {
        assertThat(new Turn().isTurn(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("해당 색상의 팀 차례인지 확인한다.")
    void isTurn() {
        Turn blackTurn = new Turn();
        assertAll(
                () -> assertThat(blackTurn.isTurn(Color.BLACK)).isTrue(),
                () -> assertThat(blackTurn.isTurn(Color.WHITE)).isFalse()
        );
    }
}
