package chess.repository.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDtoTest {

    @Test
    @DisplayName("Piece와 Position으로부터 dto를 생성한다.")
    void of() {
        PieceDto pieceDto = PieceDto.of(new Pawn(Color.WHITE), Position.of(1, 2));
        assertThat(pieceDto).isEqualTo(new PieceDto("WHITE", "PAWN", 1, 2));
    }

    @Test
    @DisplayName("dto로부터 Piece와 Position를 반환한다.")
    void from() {
        PieceDto pieceDto = new PieceDto("WHITE", "PAWN", 1, 2);
        assertAll(
                () -> assertThat(pieceDto.getPieceFrom()).isEqualTo(new Pawn(Color.WHITE)),
                () -> assertThat(pieceDto.getPositionFrom()).isEqualTo(Position.of(1, 2)));
    }
}
