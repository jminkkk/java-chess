package chess.repository.turn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDtoTest {

    @Test
    @DisplayName("Turn으로부터 dto를 생성한다.")
    void of() {
        TurnDto turnDto = TurnDto.of(new Turn(Color.WHITE));
        assertThat(turnDto).isEqualTo(new TurnDto("WHITE"));
    }

    @Test
    @DisplayName("dto로부터 Turn을 반환한다.")
    void from() {
        TurnDto turnDto = new TurnDto("WHITE");
        assertThat(turnDto.from()).isEqualTo(new Turn(Color.WHITE));
    }
}
