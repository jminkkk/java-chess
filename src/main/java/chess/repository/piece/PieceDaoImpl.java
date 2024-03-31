package chess.repository.piece;

import static chess.repository.DatabaseConfig.getConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    @Override
    public void save(final PieceDto pieceDto) {
        final var query = "INSERT INTO piece (color, pieceType, `rank`, file) VALUES(?, ?, ?, ?)\n";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceDto.color());
            preparedStatement.setString(2, pieceDto.pieceType());
            preparedStatement.setInt(3, pieceDto.rank());
            preparedStatement.setInt(4, pieceDto.file());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] save [TABLE] piece", e);
        }
    }

    @Override
    public List<PieceDto> findAll() {
        List<PieceDto> pieceDtos = new ArrayList<>();
        final var query = "SELECT * FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mapToPieces(pieceDtos, resultSet);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] findAll [TABLE] piece", e);
        }

        return pieceDtos;
    }

    private void mapToPieces(List<PieceDto> pieceDtos, ResultSet resultSet) throws SQLException {
        var color = resultSet.getString("color");
        var pieceType = resultSet.getString("pieceType");
        var rank = resultSet.getInt("rank");
        var file = resultSet.getInt("file");
        pieceDtos.add(new PieceDto(color, pieceType, rank, file));
    }

    @Override
    public void deleteAll() {
        final var query = "DELETE FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[METHOD] deleteAll [TABLE] piece", e);
        }
    }
}
