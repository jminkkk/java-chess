package chess.repository.turn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnDaoImplTest {

    private TurnDao turnDao;

    @BeforeEach
    void init() {
        turnDao = new FakeTurnDao();
    }

    @Test
    void addPiece() {
        TurnDto turnDto = new TurnDto("BLACK");
        assertThatCode(() -> turnDao.save(turnDto));
    }


    @Test
    void findAll() {
        TurnDto turnDto = new TurnDto("BLACK");
        turnDao.save(turnDto);
        assertThat(turnDao.findAll()).isEqualTo(List.of(turnDto));
    }

    @Test
    void deleteAll() {
        turnDao.deleteAll();
        assertThat(turnDao.findAll()).isEqualTo(List.of());
    }
}
