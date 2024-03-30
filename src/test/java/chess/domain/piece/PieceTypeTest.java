package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceTypeTest {
    @Nested
    class pawnScore {

        @Test
        @DisplayName("같은 세로 줄에 같은 색의 폰이 여러개 있는 경우, 각각 0.5점")
        void findScorePawn() {
            double score = PieceType.PAWN.findScore(List.of(
                    Position.of(1, 2),
                    Position.of(1, 3)
            ));

            assertThat(score).isEqualTo(1);
        }

        @Test
        @DisplayName("같은 세로 줄에 같은 색의 폰이 하나만 있는 경우, 1점(기본점수)")
        void findScoreSameFilePawn() {
            double score = PieceType.PAWN.findScore(List.of(
                    Position.of(1, 2),
                    Position.of(2, 2)
            ));

            assertThat(score).isEqualTo(2);
        }
    }
}
