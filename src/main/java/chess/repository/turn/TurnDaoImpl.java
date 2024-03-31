package chess.repository.turn;

import static chess.repository.DatabaseConfig.getConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnDaoImpl implements TurnDao {

    @Override
    public void save(final TurnDto turnDto) {
        final var query = "INSERT INTO turn (turn) VALUES(?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turnDto.turnColor());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] save [TABLE] turn", e);
        }
    }

    @Override
    public List<TurnDto> findAll() {
        List<TurnDto> turnDtos = new ArrayList<>();
        final var query = "SELECT * FROM turn";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var turnColor = resultSet.getString("turn");
                turnDtos.add(new TurnDto(turnColor));
            }

        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] findAll [TABLE] piece", e);
        }

        return turnDtos;
    }


    @Override
    public void deleteAll() {
        final var query = "DELETE FROM turn";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[METHOD] deleteAll [TABLE] turn", e);
        }
    }
}
