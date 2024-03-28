package chess.domain.board.turnstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackFirstTurnStrategyTest {

    @Test
    @DisplayName("항상 검정 색을 반환한다.")
    void get() {
        assertThat(new BlackFirstTurnStrategy().get()).isEqualTo(Color.BLACK);
    }
}
