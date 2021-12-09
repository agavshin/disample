package ru.vsu.db.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.annotation.Injectable;
import ru.vsu.db.entity.Item;

@Injectable
public class ItemRepository implements Repository<Item, Long> {

    @Inject
    private ConnectionManager connectionManager;

    private Extractor<Item> extractor = rs -> {
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            final Item item = new Item(
                rs.getString("name"),
                rs.getDate("created_at").toLocalDate()
            );
            item.setId(rs.getLong("id"));
            items.add(item);
        }
        return items;
    };

    @Override
    public List<Item> list() {
        return executeSelect("SELECT * FROM item", extractor);
    }

    @Override
    public Item find(Long id) {
        final List<Item> items = executeSelect(String.format("SELECT * FROM item WHERE id = %d", id), extractor);
        return items.size() > 0 ? items.get(0) : null;
    }

    private <T> List<T> executeSelect(String query, Extractor<T> extractor) {
        try (
            Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            return extractor.extract(resultSet);
        } catch (SQLException e) {
            System.out.println("Unable to get data: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
