package chess.repository.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PieceDaoImplTest {
    private PieceDaoImpl pieceDaoImpl;

    @BeforeEach
    void init() {
        pieceDaoImpl = new PieceDaoImpl();
    }

    @Test
    void addPiece() {
        PieceDto pieceDto = new PieceDto("BLACK", "PAWN", 1, 2);
        assertThatCode(() -> pieceDaoImpl.save(pieceDto));
    }

    @Test
    void findAll() {
        assertThat(pieceDaoImpl.findAll())
                .isEqualTo(List.of(new PieceDto("BLACK", "PAWN", 1, 2)));
    }

    @Test
    void deleteAll() {
        pieceDaoImpl.deleteAll();
        assertThat(pieceDaoImpl.findAll()).isEqualTo(List.of());
    }
}
