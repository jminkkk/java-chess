package chess.repository.turn;

import java.util.ArrayList;
import java.util.List;

public class FakeTurnDao implements TurnDao {
    private List<TurnDto> turnDtos = new ArrayList<>();

    @Override
    public void save(final TurnDto turnDto) {
        turnDtos.add(turnDto);
    }

    @Override
    public List<TurnDto> findAll() {
        return turnDtos;
    }

    @Override
    public void deleteAll() {
        turnDtos = new ArrayList<>();
    }
}
