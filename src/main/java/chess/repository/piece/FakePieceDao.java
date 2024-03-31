package chess.repository.piece;

import java.util.ArrayList;
import java.util.List;

public class FakePieceDao implements PieceDao {
    private List<PieceDto> pieces = new ArrayList<>();

    @Override
    public void save(final PieceDto pieceDto) {
        pieces.add(pieceDto);
    }

    @Override
    public List<PieceDto> findAll() {
        return pieces;
    }

    @Override
    public void deleteAll() {
        pieces = new ArrayList<>();
    }
}
