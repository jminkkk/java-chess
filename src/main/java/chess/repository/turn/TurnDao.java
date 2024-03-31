package chess.repository.turn;

import java.util.List;

public interface TurnDao {

    void save(final TurnDto turnDto);

    List<TurnDto> findAll();

    void deleteAll();
}
