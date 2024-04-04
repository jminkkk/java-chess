package chess.repository.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Color;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void init() {
        turnDao = new FakeTurnDao();
    }

    @Test
    void addPiece() {
        assertThatCode(() -> turnDao.save(Color.BLACK.name()));
    }

    @Test
    @DisplayName("DB에서 하나의 Turn을 조회한다.")
    void findOne() {
        turnDao.save(Color.BLACK.name());
        assertThat(turnDao.findAny()).isEqualTo(Optional.of(Color.BLACK));
    }

    @Test
    @DisplayName("DB에서 하나의 Turn을 조회 시 테이블이 비어있는 경우 빈 옵셔널을 반환한다.")
    void notFindOne() {
        assertThat(turnDao.findAny()).isEqualTo(Optional.empty());
    }

    @Test
    void deleteAll() {
        turnDao.deleteAll();
        assertThat(turnDao.findAny()).isEmpty();
    }
}
